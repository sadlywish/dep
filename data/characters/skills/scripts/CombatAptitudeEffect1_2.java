package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class CombatAptitudeEffect1_2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getCRPerDeploymentPercent().modifyPercent(id, -1f * SkillData.COMBAT_APTITUDE_CR_USE_REDUCTION * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getCRPerDeploymentPercent().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "减少 " + (int)(SkillData.COMBAT_APTITUDE_CR_USE_REDUCTION * level) + "% 部署战备消耗";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.COMBAT_APTITUDE_CR_USE_REDUCTION) + "%";
	}
	
	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
