package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class FluxModulationPerk2_2 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getPhaseCloakUpkeepCostBonus().modifyMult(id, (1f - SkillData.FLUX_MODULATION_PERK_PHASE_CLOAK_UPKEEP_REDUCTION_PERCENTAGE * 0.01f));
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getEmpDamageTakenMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "-" + (int)(SkillData.FLUX_MODULATION_PERK_PHASE_CLOAK_UPKEEP_REDUCTION_PERCENTAGE) + "% 相位潜航状态下辐能产生速度";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
