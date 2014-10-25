package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class MechanicalEngineeringPerk1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getHullBonus().modifyPercent(id, SkillData.MECHANICAL_ENGINEERING_PERK_HULL_BONUS_PERCENTAGE);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getHullBonus().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.MECHANICAL_ENGINEERING_PERK_HULL_BONUS_PERCENTAGE) + "% 结构强度";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}
}
