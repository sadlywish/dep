package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class AdvancedTacticsPerk1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getMarineEffectivnessMult().modifyPercent(id, SkillData.ADVANCED_TACTICS_PERK_MARINE_EFFECTIVENESS_BONUS_PERCENTAGE);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getMarineEffectivnessMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "" + (int) (SkillData.ADVANCED_TACTICS_PERK_MARINE_EFFECTIVENESS_BONUS_PERCENTAGE) + "% 陆战队及船员在登陆行动中的战斗力";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
