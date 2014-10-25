package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class MechanicalEngineeringEffect1 implements CharacterStatsSkillEffect {
	
	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getShipOrdnancePointBonus().modifyPercent(id, SkillData.MECHANICAL_ENGINEERING_SHIP_OP_BONUS_PERCENTAGE * level);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getShipOrdnancePointBonus().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.MECHANICAL_ENGINEERING_SHIP_OP_BONUS_PERCENTAGE * level) + "% 装配点数";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.MECHANICAL_ENGINEERING_SHIP_OP_BONUS_PERCENTAGE) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
