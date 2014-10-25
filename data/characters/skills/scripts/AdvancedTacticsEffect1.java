package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class AdvancedTacticsEffect1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getCommandPoints().modifyFlat(id, SkillData.ADVANCED_TACTICS_COMMAND_POINTS_BONUS * level);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getCommandPoints().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.ADVANCED_TACTICS_COMMAND_POINTS_BONUS * level) + " 指挥点数";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) SkillData.ADVANCED_TACTICS_COMMAND_POINTS_BONUS;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
