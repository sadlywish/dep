package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class FluxModulationEffect1_2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getShieldDamageTakenMult().modifyMult(id, (1f - SkillData.FLUX_MODULATION_SHIELD_DAMAGE_PERCENTAGE * level * 0.01f));
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getFluxDissipation().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "-" + (int)(SkillData.FLUX_MODULATION_SHIELD_DAMAGE_PERCENTAGE * level) + "% 护盾受到伤害时的幅能输出量"; 
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.FLUX_MODULATION_SHIELD_DAMAGE_PERCENTAGE) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
