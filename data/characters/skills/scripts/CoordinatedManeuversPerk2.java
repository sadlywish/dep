package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class CoordinatedManeuversPerk2 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		//stats.getCombatDeploymentCost().modifyPercent(id, -SkillData.COORDINATED_MANEUVERS_PERK_DEPLOY_COST_REDUCTION_PERCENTAGE);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		//stats.getCombatDeploymentCost().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "-" + (int) (SkillData.COORDINATED_MANEUVERS_PERK_DEPLOY_COST_REDUCTION_PERCENTAGE) + " 战斗中部署舰队消耗的统帅点数";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
