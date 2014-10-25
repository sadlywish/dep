package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class ConstructionEffect1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getArmorBonus().modifyPercent(id, SkillData.CONSTRUCTION_ARMOR_BONUS_PERCENTAGE * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getArmorBonus().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.CONSTRUCTION_ARMOR_BONUS_PERCENTAGE * level) + "% 装甲";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) (SkillData.CONSTRUCTION_ARMOR_BONUS_PERCENTAGE) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}
}
