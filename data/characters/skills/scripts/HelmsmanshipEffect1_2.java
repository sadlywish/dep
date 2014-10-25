package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class HelmsmanshipEffect1_2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getAcceleration().modifyPercent(id, level * SkillData.HELMSMANSHIP_ACCELERATION_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getAcceleration().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.HELMSMANSHIP_ACCELERATION_BONUS * level) + "% 加速能力";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.HELMSMANSHIP_ACCELERATION_BONUS) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
