package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class HelmsmanshipPerk2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getZeroFluxMinimumFluxLevel().modifyFlat(id, SkillData.HELMSMANSHIP_MIN_FLUX_LEVEL);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getZeroFluxMinimumFluxLevel().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "0-辐能引擎效率提升可以在低于 " + (int)(SkillData.HELMSMANSHIP_MIN_FLUX_LEVEL * 100f) + "% 的辐能下激活";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
