package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class CombatAptitudeEffect1_1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMaxCombatReadiness().modifyFlat(id, SkillData.COMBAT_APTITUDE_MAX_CR_BONUS * level * 0.01f, "Combat aptitude of captain");
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMaxCombatReadiness().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.COMBAT_APTITUDE_MAX_CR_BONUS * level) + "% 最大战备等级";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.COMBAT_APTITUDE_MAX_CR_BONUS) + "%";
	}
	
	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
