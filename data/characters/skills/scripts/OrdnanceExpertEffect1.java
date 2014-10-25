package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class OrdnanceExpertEffect1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getBallisticWeaponDamageMult().modifyPercent(id, SkillData.ORDNANCE_EXPERT_DAMAGE_BONUS_PERCENTAGE * level);
		stats.getEnergyWeaponDamageMult().modifyPercent(id, SkillData.ORDNANCE_EXPERT_DAMAGE_BONUS_PERCENTAGE * level);
		stats.getMissileWeaponDamageMult().modifyPercent(id, SkillData.ORDNANCE_EXPERT_DAMAGE_BONUS_PERCENTAGE * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getBallisticWeaponDamageMult().unmodify(id);
		stats.getEnergyWeaponDamageMult().unmodify(id);
		stats.getMissileWeaponDamageMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.ORDNANCE_EXPERT_DAMAGE_BONUS_PERCENTAGE * level) + "% 武器伤害";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.ORDNANCE_EXPERT_DAMAGE_BONUS_PERCENTAGE) + "%";
	}
	
	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
