package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class FluxDynamicsPerk1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getMaxCapacitorsBonus().modifyPercent(id, SkillData.FLUX_DYNAMICS_PERK_MAX_CAPACITORS_PERCENTAGE);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getMaxCapacitorsBonus().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "" + (int) (SkillData.FLUX_DYNAMICS_PERK_MAX_CAPACITORS_PERCENTAGE) + "% 额外辐能寄存器改造限额";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
