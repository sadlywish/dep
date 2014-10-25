package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class TargetAnalysisPerk2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getDamageToTargetShieldsMult().modifyPercent(id, SkillData.TARGET_ANALYSIS_PERK_SHIELD_DAMAGE_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getDamageToTargetShieldsMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.TARGET_ANALYSIS_PERK_SHIELD_DAMAGE_BONUS) + "% 对护盾造成的伤害"; 
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
