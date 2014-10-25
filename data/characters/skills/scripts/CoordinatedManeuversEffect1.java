package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class CoordinatedManeuversEffect1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		//stats.getFleetSizeTravelPenaltyMult().modifyMult(id, (1f - SkillData.COORDINATED_MANEUVERS_TRAVEL_PENALTY_REDUCTION * level));
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		//stats.getFleetSizeTravelPenaltyMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "减少 " + (int) (SkillData.COORDINATED_MANEUVERS_TRAVEL_PENALTY_REDUCTION * level * 100f) + "% 舰队规模对星际航行速度的惩罚";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) (SkillData.COORDINATED_MANEUVERS_TRAVEL_PENALTY_REDUCTION * 100f) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
