package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class DamageControlPerk2_1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMaxCombatHullRepairFraction().modifyFlat(id, SkillData.DAMAGE_CONTROL_PERK_MAX_REPAIR_FRACTION);
		stats.getHullCombatRepairRatePercentPerSecond().modifyFlat(id, SkillData.DAMAGE_CONTROL_PERK_REPAIR_RATE_PERCENT);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMaxCombatHullRepairFraction().unmodify(id);
		stats.getHullCombatRepairRatePercentPerSecond().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "每秒修复 " + (SkillData.DAMAGE_CONTROL_PERK_REPAIR_RATE_PERCENT) + "% 结构强度, 最多修复 " + 
						(int)(SkillData.DAMAGE_CONTROL_PERK_MAX_REPAIR_FRACTION * 100f) + "% 舰船结构强度最大值";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
