package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class FluxModulationPerk1 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getVentRateMult().modifyPercent(id, SkillData.FLUX_MODULATION_PERK_VENT_RATE_PERCENTAGE);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getVentRateMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int) SkillData.FLUX_MODULATION_PERK_VENT_RATE_PERCENTAGE + "% 强行散热时的辐能散耗速度";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
