package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class FieldRepairsPerk1 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getShipRepairChance().modifyFlat(id, SkillData.FIELD_REPAIRS_DISABLED_REPAIR_PERCENTAGE);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getShipRepairChance().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int) (SkillData.FIELD_REPAIRS_DISABLED_REPAIR_PERCENTAGE) + "% 几率战后抢救失去作战能力的友军舰船";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int) SkillData.FIELD_REPAIRS_DISABLED_REPAIR_PERCENTAGE + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.FLEET;
	}

}