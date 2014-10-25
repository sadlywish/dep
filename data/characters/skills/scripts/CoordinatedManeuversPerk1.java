package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class CoordinatedManeuversPerk1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getLogistics().modifyFlat(id, SkillData.COORDINATED_MANEUVERS_PERK_LOGISTICS_BONUS, "Coordinated maneuvers");
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getLogistics().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.COORDINATED_MANEUVERS_PERK_LOGISTICS_BONUS) + " 最大统帅点数";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
