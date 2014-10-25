package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class FieldRepairsPerk2 implements ShipSkillEffect {
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getBaseRepairCost().modifyPercent(id, -SkillData.FIELD_REPAIRS_REPAIRS_COST_REDUCTION);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getBaseRepairCost().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "-" + (int)(SkillData.FIELD_REPAIRS_REPAIRS_COST_REDUCTION) + "% 维修物资消耗";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}
}