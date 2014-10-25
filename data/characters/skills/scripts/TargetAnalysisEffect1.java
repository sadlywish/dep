package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class TargetAnalysisEffect1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getHitStrengthBonus().modifyPercent(id, SkillData.TARGET_ANALYSIS_HIT_STRENGTH_BONUS * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getHitStrengthBonus().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.TARGET_ANALYSIS_HIT_STRENGTH_BONUS * level) + "% 对目标装甲侵彻力"; 
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.TARGET_ANALYSIS_HIT_STRENGTH_BONUS) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
