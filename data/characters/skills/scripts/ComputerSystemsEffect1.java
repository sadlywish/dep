package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class ComputerSystemsEffect1 implements CharacterStatsSkillEffect {
	
	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getShipOrdnancePointBonus().modifyPercent(id, SkillData.COMPUTER_SYSTEMS_SHIP_OP_BONUS_PERCENTAGE * level);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getShipOrdnancePointBonus().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.COMPUTER_SYSTEMS_SHIP_OP_BONUS_PERCENTAGE * level) + "% 装配点数";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(SkillData.COMPUTER_SYSTEMS_SHIP_OP_BONUS_PERCENTAGE) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
