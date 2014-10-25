package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class FluxDynamicsPerk2 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getMaxVentsBonus().modifyPercent(id, SkillData.FLUX_DYNAMICS_PERK_MAX_VENTS_PERCENTAGE);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getMaxVentsBonus().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "" + (int) (SkillData.FLUX_DYNAMICS_PERK_MAX_VENTS_PERCENTAGE) + "% 额外辐能散耗通道改造限额";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
