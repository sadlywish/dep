package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class CommandExperienceEffect1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getCrewXPGainMult().modifyPercent(id, SkillData.COMMAND_EXPERIENCE_CREW_XP_BONUS * level);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getCrewXPGainMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.COMMAND_EXPERIENCE_CREW_XP_BONUS * level) + "% 船员的获得经验值";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) SkillData.COMMAND_EXPERIENCE_CREW_XP_BONUS + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
