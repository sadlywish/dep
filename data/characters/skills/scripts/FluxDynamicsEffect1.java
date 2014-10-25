package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class FluxDynamicsEffect1 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getFluxDissipation().modifyPercent(id, SkillData.FLUX_DYNAMICS_FLUX_DISSIPATION_BONUS * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getFluxDissipation().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.FLUX_DYNAMICS_FLUX_DISSIPATION_BONUS * level) + "% 辐能散耗速度";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) (SkillData.FLUX_DYNAMICS_FLUX_DISSIPATION_BONUS) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
