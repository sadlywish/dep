package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class MissileSpecializationEffect1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMissileMaxSpeedBonus().modifyPercent(id, SkillData.MISSILE_SPEC_SPEED_BONUS * level);
		stats.getMissileAccelerationBonus().modifyPercent(id, SkillData.MISSILE_SPEC_ACCEL_BONUS * level * 2f);
		stats.getMissileMaxTurnRateBonus().modifyPercent(id, SkillData.MISSILE_TURN_RATE_BONUS * level);
		stats.getMissileTurnAccelerationBonus().modifyPercent(id, SkillData.MISSILE_TURN_ACCEL_BONUS * level);
		
//		stats.getMissileMaxTurnRateBonus().modifyFlat(id, 5f * level);
//		stats.getMissileTurnAccelerationBonus().modifyFlat(id, 10f * level);
		
		stats.getMissileWeaponRangeBonus().modifyMult(id, 1f - SkillData.MISSILE_SPEC_RANGE_PENALTY * level * 0.01f);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMissileMaxSpeedBonus().unmodify(id);
		stats.getMissileAccelerationBonus().unmodify(id);
		stats.getMissileMaxTurnRateBonus().unmodify(id);
		stats.getMissileTurnAccelerationBonus().unmodify(id);
		
		stats.getMissileWeaponRangeBonus().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.MISSILE_SPEC_SPEED_BONUS * level) + "% 导弹飞行速度和追踪能力";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.MISSILE_SPEC_SPEED_BONUS) + "%";
	}
	
	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
