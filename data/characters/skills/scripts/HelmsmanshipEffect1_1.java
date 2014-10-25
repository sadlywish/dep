package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class HelmsmanshipEffect1_1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMaxSpeed().modifyPercent(id, level * SkillData.HELMSMANSHIP_TOP_SPEED_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMaxSpeed().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.HELMSMANSHIP_TOP_SPEED_BONUS * level) + "% 最大战斗航速";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.HELMSMANSHIP_TOP_SPEED_BONUS) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
