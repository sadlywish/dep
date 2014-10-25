package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class LeadershipAptitudeEffect1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getLogistics().modifyFlat(id, SkillData.LEADERSHIP_APTITUDE_LOGISTICS_BONUS * level, "Leadership aptitude");
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getLogistics().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.LEADERSHIP_APTITUDE_LOGISTICS_BONUS * level) + " 统帅点数上限";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) SkillData.LEADERSHIP_APTITUDE_LOGISTICS_BONUS;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
