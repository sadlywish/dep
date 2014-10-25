package data.scripts.imp.CombatReadiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.combat.CombatReadinessPlugin;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI.ShipEngineAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.combat.LowCRShipDamageSequence;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class CRPluginImpl implements CombatReadinessPlugin {

	public static final float MAINENANCE_MULT_AT_FULL_CR = 0.1f;
	
	public static final float NO_SYSTEM_THRESHOLD = 0.0f;
	public static final float DEGRADE_START = 0.5f;
	public static final float IMPROVE_START = 0.6f;
	public static final float SHIELD_MALFUNCTION_START = 0.1f;
	public static final float CRITICAL_MALFUNCTION_START = 0.2f;
	public static final float MALFUNCTION_START = 0.4f;
	public static final float MISSILE_AMMO_REDUCTION_START = 0.3f;
    public static final float Bullte_AMMO_REDUCTION_START = 0.2f;	
    public static final float Engry_Damage_REDUCTION_START = 0.35f;
    public static final float Engry_Range_REDUCTION_START = 0.3f;	
    public static final float MAX_MOVEMENT_CHANGE = 10f; // percent
	public static final float MAX_DAMAGE_TAKEN_CHANGE = 10f; // percent
	//public static final float MAX_ROF_CHANGE = 25f; // percent
	public static final float MAX_DAMAGE_CHANGE = 10f; // percent
	public static final float MAX_REFIT_CHANGE = 40f; // percent
	
	public static final float MAX_SHIELD_MALFUNCTION_CHANCE = 5f; // percent
	public static final float MAX_CRITICAL_MALFUNCTION_CHANCE = 25f; // percent
	public static final float MAX_ENGINE_MALFUNCTION_CHANCE = 7.5f; // percent
	public static final float MAX_WEAPON_MALFUNCTION_CHANCE = 10f; // percent
	
	
	public void applyMaxCRLogisticsPenalty(FleetMemberAPI member, float logisticsRating) {
		if (logisticsRating >= 1) {
			member.getStats().getMaxCombatReadiness().unmodify("logistics rating cr penalty");
		} else {
			float penalty = (1f - logisticsRating) * 0.5f;
			member.getStats().getMaxCombatReadiness().modifyFlat("logistics rating cr penalty", -penalty, "Logistical inefficiency");
		}
	}
	
	/**
	 * Used by logistics rating tooltip.
	 * 
	 * @param logisticsRating
	 * @return 0-1, with 1 meaning -100% to max CR.
	 */
	public float getMaxCRPenaltyFor(float logisticsRating) {
		float penalty = Math.max(0, 1f - logisticsRating) * 0.5f;
		return penalty;
	}
	
	public void applyMaxCRCrewModifiers(FleetMemberAPI member) {
		float maxCRBasedOnLevel = (40f + member.getAverageCrewLevel() * 10f) / 100f;
		member.getStats().getMaxCombatReadiness().modifyFlat("crew skill bonus", maxCRBasedOnLevel, "Crew experience");
		
		float cf = member.getCrewFraction();
		if (cf < 1) {
			float penalty = 0.5f * (1f - cf);
			//float penalty = (1f - cf);
			member.getStats().getMaxCombatReadiness().modifyFlat("crew understrength", -penalty, "Crew understrength");
		} else {
			member.getStats().getMaxCombatReadiness().unmodify("crew understrength");
		}
	}
	
	
	
	public List<CRStatusItemData> getCRStatusDataForShip(ShipAPI ship) {
		float startingCR = ship.getCRAtDeployment();
		float cr = ship.getCurrentCR();
		
		List<CRStatusItemData> list = new ArrayList<CRStatusItemData>();
		
		String icon = null;
		
		if (cr > IMPROVE_START) {
			icon = Global.getSettings().getSpriteName("ui", "icon_tactical_cr_bonus");
		} else if (cr < DEGRADE_START) {
			icon = Global.getSettings().getSpriteName("ui", "icon_tactical_cr_penalty");
		} else {
			icon = Global.getSettings().getSpriteName("ui", "icon_tactical_cr_neutral");
			//return list;
		}
		
		String title = "战备程度 " + Math.round(cr * 100f) + "%";
		
		String malfStr = getMalfunctionString(cr);
		
		if (cr <= NO_SYSTEM_THRESHOLD && ship.getSystem() != null) {
			CRStatusItemData itemData = new CRStatusItemData(statusKeys[7], icon, title,
					 ship.getSystem().getDisplayName() + "无法激活", true);
			list.add(itemData);
		}
		
		if (cr < MALFUNCTION_START) {
			CRStatusItemData itemData = new CRStatusItemData(statusKeys[0], icon, title,
					 "故障风险:" + malfStr, true);
			list.add(itemData);
		}
		
		if (cr < DEGRADE_START) {
			CRStatusItemData itemData = new CRStatusItemData(statusKeys[3], icon, title,
					"整备不足", true);
			list.add(itemData);
		} else if (cr > IMPROVE_START) {
			CRStatusItemData itemData = new CRStatusItemData(statusKeys[4], icon, title,
					"良好的作战状态", false);
			list.add(itemData);
		} else {
			CRStatusItemData itemData = new CRStatusItemData(statusKeys[8], icon, title,
					"标准作战状态", false);
			list.add(itemData);
		}
		
		if (ship.losesCRDuringCombat() && cr > 0) {
			//float noLossTime = ship.getHullSpec().getNoCRLossTime();
			float noLossTime = ship.getMutableStats().getPeakCRDuration().computeEffective(ship.getHullSpec().getNoCRLossTime());
			if (noLossTime > ship.getTimeDeployedForCRReduction()) {
				CRStatusItemData itemData = new CRStatusItemData(statusKeys[5], icon, "战斗续航倒计时",
						"剩余时间: " + (int) (noLossTime - ship.getTimeDeployedForCRReduction()) + " 秒", true);
				list.add(itemData);
			} else {
				CRStatusItemData itemData = new CRStatusItemData(statusKeys[6], icon, "【警告】超出续航上限",
						"全机能下降", true);
				list.add(itemData);
			}
		}
		
		return list;
	}
	
	
	private float getWeaponMalfuctionPercent(float cr) {
		return MAX_WEAPON_MALFUNCTION_CHANCE * (MALFUNCTION_START - cr) / MALFUNCTION_START;
	}
	private float getEngineMalfuctionPercent(float cr) {
		return MAX_ENGINE_MALFUNCTION_CHANCE * (MALFUNCTION_START - cr) / MALFUNCTION_START;
	}
	private float getCriticalMalfuctionPercent(float cr) {
		return MAX_CRITICAL_MALFUNCTION_CHANCE * (CRITICAL_MALFUNCTION_START - cr) / CRITICAL_MALFUNCTION_START;
	}
	private float getShieldMalfuctionPercent(float cr) {
		return MAX_SHIELD_MALFUNCTION_CHANCE * (SHIELD_MALFUNCTION_START - cr) / SHIELD_MALFUNCTION_START;
	}
	
	
	private float getMovementChangePercent(float cr) {
		float movementChange = 0f;
		if (cr < DEGRADE_START) {
			float f = (DEGRADE_START - cr) / DEGRADE_START;
			movementChange = -1f * f * MAX_MOVEMENT_CHANGE;
		} else if (cr > IMPROVE_START) {
			float f = (cr - IMPROVE_START) / (1f - IMPROVE_START);
			movementChange = 1f * f * MAX_MOVEMENT_CHANGE;
		}
		return movementChange;
	}
	
	private float getDamageTakenChangePercent(float cr) {
		float damageTakenChange = 0f;
		if (cr < DEGRADE_START) {
			float f = (DEGRADE_START - cr) / DEGRADE_START;
			damageTakenChange = 1f * f * MAX_DAMAGE_TAKEN_CHANGE;
		} else if (cr > IMPROVE_START) {
			float f = (cr - IMPROVE_START) / (1f - IMPROVE_START);
			damageTakenChange = -1f * f * MAX_DAMAGE_TAKEN_CHANGE;
		}
		return damageTakenChange;
	}
	
	private float getRefitTimeChangePercent(float cr) {
		float refitTimeChange = 0f;
		if (cr < DEGRADE_START) {
			float f = (DEGRADE_START - cr) / DEGRADE_START;
			refitTimeChange = 1f * f * MAX_REFIT_CHANGE;
		} else if (cr > IMPROVE_START) {
			float f = (cr - IMPROVE_START) / (1f - IMPROVE_START);
			refitTimeChange = -1f * f * MAX_REFIT_CHANGE;
		}
		return refitTimeChange;
	}
	
	private float getDamageChangePercent(float cr) {
		float damageChange = 0f;
		if (cr < DEGRADE_START) {
			float f = (DEGRADE_START - cr) / DEGRADE_START;
			damageChange = -1f * f * MAX_DAMAGE_CHANGE;
		} else if (cr > IMPROVE_START) {
			float f = (cr - IMPROVE_START) / (1f - IMPROVE_START);
			damageChange = 1f * f * MAX_DAMAGE_CHANGE;
		}
		return damageChange;
	}
	
	/**
	 * From negative whatever to best accuracy of 1.
	 * @param cr
	 * @return
	 */
	private float getAimAccuracy(float cr) {
		return cr * 1.5f - 0.5f;
	}
	
	public void applyCRToStats(float cr, MutableShipStatsAPI stats, HullSize hullSize) {
		String id = "cr_effect";

		boolean fighter = hullSize == HullSize.FIGHTER;
		

		if (!fighter) {
			if (cr < MALFUNCTION_START) {
				stats.getWeaponMalfunctionChance().modifyFlat(id, 0.01f * getWeaponMalfuctionPercent(cr));
				stats.getEngineMalfunctionChance().modifyFlat(id, 0.01f * getEngineMalfuctionPercent(cr));
			} else {
				stats.getWeaponMalfunctionChance().unmodify(id);
				stats.getEngineMalfunctionChance().unmodify(id);
			}
		}
		
		if (!fighter) {
			if (cr < CRITICAL_MALFUNCTION_START) {
				stats.getCriticalMalfunctionChance().modifyFlat(id, 0.01f * getCriticalMalfuctionPercent(cr));
			} else {
				stats.getCriticalMalfunctionChance().unmodify(id);
			}
		}
		
		if (!fighter) {
			if (cr < SHIELD_MALFUNCTION_START) {
				stats.getShieldMalfunctionChance().modifyFlat(id, 0.01f * getShieldMalfuctionPercent(cr));
				stats.getShieldMalfunctionFluxLevel().modifyFlat(id, 0.75f);
			} else {
				stats.getShieldMalfunctionChance().unmodify(id);
				stats.getShieldMalfunctionFluxLevel().unmodify(id);
			}
		}
	
		if (!fighter) {
			if (stats.getEntity() instanceof ShipAPI) {
				ShipAPI ship = (ShipAPI)stats.getEntity();
				if (cr <= NO_SYSTEM_THRESHOLD) {
					ship.setShipSystemDisabled(true);
				} else if (stats.getEntity() instanceof ShipAPI) {
					ship.setShipSystemDisabled(false);	
				}
			}
		}
		
		float movementChange = getMovementChangePercent(cr);
		float damageTakenChange = getDamageTakenChangePercent(cr);
		float damageChange = getDamageChangePercent(cr);
		float refitTimeChange = getRefitTimeChangePercent(cr);
		
		if (refitTimeChange != 0) {
			stats.getFighterRefitTimeMult().modifyPercent(id, refitTimeChange);
		} else {
			stats.getFighterRefitTimeMult().unmodify(id);
		}
		
		if (movementChange != 0) {
			stats.getMaxSpeed().modifyPercent(id, movementChange);
			stats.getAcceleration().modifyPercent(id, movementChange);
			stats.getDeceleration().modifyPercent(id, movementChange);
			stats.getTurnAcceleration().modifyPercent(id, movementChange);
			stats.getMaxTurnRate().modifyPercent(id, movementChange);
		} else {
			stats.getMaxSpeed().unmodify(id);
			stats.getAcceleration().unmodify(id);
			stats.getDeceleration().unmodify(id);
			stats.getTurnAcceleration().unmodify(id);
			stats.getMaxTurnRate().unmodify(id);
		}
		
		if (damageTakenChange != 0) {
			stats.getArmorDamageTakenMult().modifyPercent(id, damageTakenChange);
			stats.getHullDamageTakenMult().modifyPercent(id, damageTakenChange);
			stats.getShieldDamageTakenMult().modifyPercent(id, damageTakenChange);
			//stats.getPhaseCloakUpkeepCostBonus().modifyPercent(id, damageTakenChange);
		} else {
			stats.getArmorDamageTakenMult().unmodify(id);
			stats.getHullDamageTakenMult().unmodify(id);
			stats.getShieldDamageTakenMult().unmodify(id);
			//stats.getPhaseCloakUpkeepCostBonus().unmodify(id);
		}
		
		if (damageChange != 0) {
			stats.getBallisticWeaponDamageMult().modifyPercent(id, damageChange);
			stats.getEnergyWeaponDamageMult().modifyPercent(id, damageChange);
			stats.getMissileWeaponDamageMult().modifyPercent(id, damageChange);
		} else {
			stats.getBallisticWeaponDamageMult().unmodify(id);
			stats.getEnergyWeaponDamageMult().unmodify(id);
			stats.getMissileWeaponDamageMult().unmodify(id);
		}
		
		stats.getEnergyWeaponFluxCostMod().modifyPercent(id, ((1-cr)/(1-Engry_Damage_REDUCTION_START)-1f)*0.7f+1);
		stats.getEnergyWeaponRangeBonus().modifyMult(id, cr/Engry_Range_REDUCTION_START);	
		float aimAccuracy = getAimAccuracy(cr);
		stats.getAutofireAimAccuracy().modifyFlat(id, aimAccuracy);
	}
	
	public void applyCRToShip(float cr, ShipAPI ship) {
		if(!ship.isFighter()){
			for (WeaponAPI weapon : ship.getAllWeapons()) {
				if (weapon.getType() == WeaponType.MISSILE) {
					float ammo = ship.getMutableStats().getMissileAmmoBonus().computeEffective((float)weapon.getMaxAmmo()) * (cr/MISSILE_AMMO_REDUCTION_START);	
					if (ammo < 0) ammo = 0;
					weapon.setAmmo(Math.round(ammo));
				}
				if (weapon.getType() == WeaponType.BALLISTIC) {
					float ammo = ship.getMutableStats().getBallisticAmmoBonus().computeEffective((float) weapon.getMaxAmmo()) * (cr/Bullte_AMMO_REDUCTION_START);
					if (ammo < 0) ammo = 0;
					weapon.setAmmo(Math.round(ammo));
				}
			}
		}
		ship.setCRAtDeployment(cr);
		
		if (cr < CRITICAL_MALFUNCTION_START && !ship.controlsLocked() && !ship.isFighter()) {
			float severity = (CRITICAL_MALFUNCTION_START - cr) / (CRITICAL_MALFUNCTION_START);
			Global.getCombatEngine().addPlugin(new LowCRShipDamageSequence(ship, severity));
		}
	}
	
	public float getMissileLoadedFraction(float cr) {
		float test = Global.getSettings().getFloat("noDeployCRPercent") * 0.01f;
		float f = (cr - test) / (MISSILE_AMMO_REDUCTION_START - test);
		if (f > 1) f = 1;
		if (f < 0) f = 0;
		return f;
	}
	
	
	public float getMalfunctionThreshold() {
		return MALFUNCTION_START;
	}
	public float getCriticalMalfunctionThreshold() {
		return CRITICAL_MALFUNCTION_START;
	}
	
	/**
	 * @param cr from 0 to 1
	 * @param shipOrWing "ship" or "fighter wing".
	 * @return
	 */
	public CREffectDescriptionForTooltip getCREffectDescription(float cr, String shipOrWing, FleetMemberAPI member) {
		CREffectDescriptionForTooltip result = new CREffectDescriptionForTooltip();
		
		List<CREffectDetail> details = getCREffectDetails(cr, member);
		boolean hasPositive = false;
		boolean hasNegative = false;
		for (CREffectDetail detail : details) {
			if (detail.getType() == CREffectDetailType.BONUS) hasPositive = true;
			if (detail.getType() == CREffectDetailType.PENALTY) hasNegative = true;
		}
		
		float noDeploy = Global.getSettings().getFloat("noDeployCRPercent") * 0.01f;
		String crStr = (int)(cr * 100f) + "%";
		String str;
		if (cr < noDeploy) {
			str = String.format(" %s 没有完成战备，无法在战斗中部署.", shipOrWing);
		} else if (cr < CRITICAL_MALFUNCTION_START) {
			str = String.format("%s 性能大幅下降, 如果部署在战斗中可能会导致武器和引擎不可修复性当机.", shipOrWing);
		} else if (cr < MALFUNCTION_START) {
			str = String.format(" %s 性能大幅下降，同时伴有武器和引擎随时当机的风险.", shipOrWing);
		} else if (cr < DEGRADE_START && hasNegative) {
			str = String.format(" %s 还没有准备好，强制出击将会使得它无法全力作战.", shipOrWing);
		} else if (cr < IMPROVE_START || !hasPositive) {
			str = String.format(" %s 保持着标准的战备程度.", shipOrWing);
		} else {
			str = String.format(" %s 状态良好，一些系统可以短时超载来提升性能.", shipOrWing);
		}
		
		//result.getHighlights().add(crStr);
		

		if (member.isFighterWing()) {
			boolean canReplaceFighters = false;
			FleetDataAPI data = member.getFleetData();
			if (data != null) {
				for (FleetMemberAPI curr : data.getMembersListCopy()) {
					if (curr.isMothballed()) continue;
					if (curr.getNumFlightDecks() > 0) {
						canReplaceFighters = true;
						break;
					}
				}
			}
			if (canReplaceFighters) {
				details.add(new CREffectDetail("", "", CREffectDetailType.NEUTRAL));
				float costPer = member.getStats().getCRPerDeploymentPercent().computeEffective(member.getVariant().getHullSpec().getCRToDeploy()) / 100f;
				String numStr = "" + (int) Math.ceil((float)((int) (cr * 100f)) / (costPer * 100f));
				
				str += "已准备了" + numStr + " 台备用机，以替换战斗中的损失";
				result.getHighlights().add(numStr);
				
			} else {
				details.add(new CREffectDetail("Replacement chassis", "None", CREffectDetailType.PENALTY));
				str += " " + "由于战舰缺乏相关设施 (例如飞行甲板 )战机的损失将无法得到补给.";
			}
		}
		
		
		result.setString(str);
		
		return result;
	}
	
	private String getMalfunctionString(float cr) {
		String malfStr = "无";
		if (cr < CRITICAL_MALFUNCTION_START) {
			malfStr = "严重";
		} else if (cr < 0.3f) {
			malfStr = "频繁";
		} else if (cr < MALFUNCTION_START) {
			malfStr = "稀有";
		}
		return malfStr;
	}
	
	public List<CREffectDetail> getCREffectDetails(float cr, FleetMemberAPI member) {
		List<CREffectDetail> result = new ArrayList<CREffectDetail>();
		
		int engine = (int) getEngineMalfuctionPercent(cr);
		int weapon = (int) getWeaponMalfuctionPercent(cr);
		
		int speed = (int) getMovementChangePercent(cr);
		int damage = (int) getDamageTakenChangePercent(cr);
		int damageDealt = (int) getDamageChangePercent(cr);
		int refit = (int) getRefitTimeChangePercent(cr);
		
		float acc = getAimAccuracy(cr);
		
		String malfStr = getMalfunctionString(cr);
		
		String accString;
		//System.out.println(acc);
		CREffectDetailType accType;
		if (acc < 0) {
			accString = "非常低";
			accType = CREffectDetailType.PENALTY;
		} else if (acc < 0.25f) {
			accString = "低下";
			accType = CREffectDetailType.PENALTY;
		} else if (acc < 0.67) {
			accString = "正常";
			accType = CREffectDetailType.NEUTRAL;
		} else {
			accString = "良好";
			accType = CREffectDetailType.BONUS;
		}
		
		String speedStr = speed + "%";
		if (speed >= 0) {
			speedStr = "+" + speedStr;
		}
		String damageStr = damage + "%";
		if (damage >= 0) {
			damageStr = "+" + damageStr;
		}
		String rofStr = damageDealt + "%";
		if (damageDealt >= 0) {
			rofStr = "+" + rofStr;
		}
		
		String refitStr = refit + "%";
		if (refit >= 0) {
			refitStr = "+" + refitStr;
		}
		
		result.add(new CREffectDetail("机动性", speedStr, getTypeFor(speed, false)));
		result.add(new CREffectDetail("受创程度", damageStr, getTypeFor(damage, true)));
		result.add(new CREffectDetail("伤害加成", rofStr, getTypeFor(damageDealt, false)));
		
		if (member.getNumFlightDecks() > 0) {
			result.add(new CREffectDetail("战机整备时间", refitStr, getTypeFor(refit, true)));
		}
		
		result.add(new CREffectDetail("自动射击精准度", accString, accType));
		

		
		CREffectDetailType malfType = CREffectDetailType.NEUTRAL;
		if (getWeaponMalfuctionPercent(cr) > 0) {
			malfType = CREffectDetailType.PENALTY;
		}

		result.add(new CREffectDetail("故障风险", malfStr, malfType));
		
		Collection<String> slots = member.getVariant().getFittedWeaponSlots();
		boolean hasMissiles = false;
		boolean hasBallistic = false;
		boolean hasEnergy = false;
		for (String slotId : slots) {
			WeaponSpecAPI w = member.getVariant().getWeaponSpec(slotId);
			if (w.getType() == WeaponType.MISSILE) {
				hasMissiles = true;
				break;
			}
		}
		for (String slotId : slots) {
			WeaponSpecAPI w = member.getVariant().getWeaponSpec(slotId);
			if (w.getType() == WeaponType.BALLISTIC) {
				hasBallistic = true;
				break;
			}
		}
		for (String slotId : slots) {
			WeaponSpecAPI w = member.getVariant().getWeaponSpec(slotId);
			if (w.getType() == WeaponType.ENERGY) {
				hasEnergy = true;
				break;
			}
		}
		if (hasMissiles) {
			float missileFraction = cr/MISSILE_AMMO_REDUCTION_START;
			if (missileFraction < 0) missileFraction = 0;
			String missileStr = (int)(missileFraction * 100f) + "%";
			
			result.add(new CREffectDetail("导弹备弹数", missileStr, effectDetaitype(missileFraction)));
		}
		if (hasBallistic) {
			float missileFraction = cr/Bullte_AMMO_REDUCTION_START;
			String missileStr = (int)(missileFraction * 100f) + "%";
			
			result.add(new CREffectDetail("火炮备弹数", missileStr, effectDetaitype(missileFraction)));
		}
		if (hasEnergy) {
			float missileFraction = ((1-cr)/(1-Engry_Damage_REDUCTION_START)-1f)*0.7f+1;
			float engryRange = cr/Engry_Range_REDUCTION_START;			
			String missileStr = (int)(missileFraction * 100f) + "%";
			String rangeStr = (int)(engryRange * 100f) + "%";			
			result.add(new CREffectDetail("能量热损耗率", missileStr, effectDetaitype(missileFraction)));			
			result.add(new CREffectDetail("能量包覆体稳定性", rangeStr, effectDetaitype(missileFraction)));
		}
		
		return result;
	}
	
	private CREffectDetailType effectDetaitype( float effectMult ) {
		if (effectMult>1f) {
			return CREffectDetailType.BONUS;
		}
		if (effectMult<1f) {
			return CREffectDetailType.PENALTY;
		}
		return CREffectDetailType.NEUTRAL;
	}
	
	private CREffectDetailType getTypeFor(int val, boolean invert) {
		if (invert) {
			if (val < 0) return CREffectDetailType.BONUS;
			else if (val > 0) return CREffectDetailType.PENALTY;
			return CREffectDetailType.NEUTRAL;
		} else {
			if (val > 0) return CREffectDetailType.BONUS;
			else if (val < 0) return CREffectDetailType.PENALTY;
			return CREffectDetailType.NEUTRAL;
		}
	}
	
	protected static Object [] statusKeys  = new Object [] {
			new Object(),
			new Object(),
			new Object(),
			new Object(),
			new Object(),
			new Object(),
			new Object(),
			new Object(),
			new Object(),
			new Object(),
		};

	
	
	public boolean isOkToPermanentlyDisable(ShipAPI ship, Object module) {
		return isOkToPermanentlyDisableStatic(ship, module);
	}
	
	public static boolean isOkToPermanentlyDisableStatic(ShipAPI ship, Object module) {
		if (module instanceof ShipEngineAPI) {
			float fractionIfDisabled = ((ShipEngineAPI) module).getContribution() + ship.getEngineFractionPermanentlyDisabled();
			if (fractionIfDisabled > 0.66f) {
				return false;
			} else {
				return true;
			}
		}
		
		if (module instanceof WeaponAPI) {
			WeaponType type = ((WeaponAPI)module).getType();
			if (type == WeaponType.DECORATIVE || type == WeaponType.LAUNCH_BAY || type == WeaponType.SYSTEM) {
				return false;
			}
			
			if (ship.getCurrentCR() <= 0) {
				return true;
			}
			
			List<Object> usableWeapons = new ArrayList<Object>();
			for (WeaponAPI weapon : ship.getAllWeapons()) {
				if (weapon.isPermanentlyDisabled()) continue;
				if (weapon.getAmmo() > 0 && (weapon.getMaxAmmo() > 20 || weapon.getSpec().getAmmoPerSecond() > 0)) {
					usableWeapons.add(weapon);
				}
			}
			usableWeapons.remove(module);
			
			return usableWeapons.size() >= 1;
		}
		return false;
	}
}


