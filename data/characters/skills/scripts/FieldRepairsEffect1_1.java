package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class FieldRepairsEffect1_1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getEmergencyRepairs().modifyFlat(id, SkillData.FIELD_REPAIRS_EMERGENCY_REPAIRS_PER_LEVEL * level);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getEmergencyRepairs().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.FIELD_REPAIRS_EMERGENCY_REPAIRS_PER_LEVEL * level) + " 每天分配于紧急维修的物资";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) SkillData.FIELD_REPAIRS_EMERGENCY_REPAIRS_PER_LEVEL + "";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}
