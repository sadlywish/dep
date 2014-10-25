package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class AppliedPhysicsEffect1 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getFluxCapacity().modifyPercent(id, SkillData.APPLIED_PHYSICS_FLUX_CAPACITY_BONUS * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getFluxCapacity().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.APPLIED_PHYSICS_FLUX_CAPACITY_BONUS * level) + "% 辐能存储峰值";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) (SkillData.APPLIED_PHYSICS_FLUX_CAPACITY_BONUS) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
