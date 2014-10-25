package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class EvasiveActionEffect1 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getEngineDamageTakenMult().modifyMult(id, 1f - SkillData.EVASIVE_ACTION_ENGINE_DAMAGE_REDUCTION_PERCENTAGE * 0.01f * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getEngineDamageTakenMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "-" + (int)(SkillData.EVASIVE_ACTION_ENGINE_DAMAGE_REDUCTION_PERCENTAGE * level) + "% 引擎受到的伤害"; 
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.EVASIVE_ACTION_ENGINE_DAMAGE_REDUCTION_PERCENTAGE) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
