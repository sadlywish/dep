package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class EvasiveActionPerk2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getEffectiveArmorBonus().modifyPercent(id, SkillData.EVASIVE_ACTION_PERK_ARMOR_STRENGTH_BONUS_PERCENTAGE);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getEffectiveArmorBonus().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		//return "+" + (int)(SkillData.EVASIVE_ACTION_PERK_ARMOR_STRENGTH_BONUS_PERCENTAGE) + "% armor strength for damage reduction only";
		return "双倍装甲防御强度（伤害减免程度）";
	}

	public String getEffectPerLevelDescription() {
		return null;
	}
	
	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
