package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class DamageControlPerk1_2 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getOverloadTimeMod().modifyMult(id, (1f - SkillData.DAMAGE_CONTROL_PERK_OVERLOAD_REDUCTION_PERCENTAGE * 0.01f));
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getOverloadTimeMod().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "减少 " + (int)(SkillData.DAMAGE_CONTROL_PERK_OVERLOAD_REDUCTION_PERCENTAGE) + "% 超载持续时间";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
