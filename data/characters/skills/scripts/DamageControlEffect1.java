package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class DamageControlEffect1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getArmorDamageTakenMult().modifyMult(id, (1f - SkillData.DAMAGE_CONTROL_DAMAGE_REDUCTION_PERCENTAGE * level * 0.01f));
		stats.getHullDamageTakenMult().modifyMult(id, (1f - SkillData.DAMAGE_CONTROL_DAMAGE_REDUCTION_PERCENTAGE * level * 0.01f));
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getArmorDamageTakenMult().unmodify(id);
		stats.getHullDamageTakenMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "-" + (int)(SkillData.DAMAGE_CONTROL_DAMAGE_REDUCTION_PERCENTAGE * level) + "% 结构和装甲受到的伤害";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.DAMAGE_CONTROL_DAMAGE_REDUCTION_PERCENTAGE) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
