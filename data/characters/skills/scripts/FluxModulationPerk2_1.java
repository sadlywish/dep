package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class FluxModulationPerk2_1 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getHardFluxDissipationFraction().modifyFlat(id, SkillData.FLUX_MODULATION_PERK_HARD_FLUX_DISSIPATION_FRACTION);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getHardFluxDissipationFraction().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "" + (int)(SkillData.FLUX_MODULATION_PERK_HARD_FLUX_DISSIPATION_FRACTION * 100f) + "% 当护盾激活时硬辐能散耗速度";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
