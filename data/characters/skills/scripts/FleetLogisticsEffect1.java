package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class FleetLogisticsEffect1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getLogistics().modifyFlat(id, SkillData.FLEET_LOGISTICS_LOGISTICS_STAT_BONUS * level, "Fleet logistics skill");
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getLogistics().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.FLEET_LOGISTICS_LOGISTICS_STAT_BONUS * level) + " 最大统帅点数";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) SkillData.FLEET_LOGISTICS_LOGISTICS_STAT_BONUS;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
