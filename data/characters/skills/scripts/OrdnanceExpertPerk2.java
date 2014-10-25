package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class OrdnanceExpertPerk2 implements CharacterStatsSkillEffect {

	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
		stats.getSmallWeaponOPCost().modifyFlat(id, -SkillData.ORDNANCE_EXPERT_PERK_OP_REDUCTION_SMALL);
		stats.getMediumWeaponOPCost().modifyFlat(id, -SkillData.ORDNANCE_EXPERT_PERK_OP_REDUCTION_MEDIUM);
		stats.getLargeWeaponOPCost().modifyFlat(id, -SkillData.ORDNANCE_EXPERT_PERK_OP_REDUCTION_LARGE);
	}

	public void unapply(MutableCharacterStatsAPI stats, String id) {
		stats.getSmallWeaponOPCost().unmodify(id);
		stats.getMediumWeaponOPCost().unmodify(id);
		stats.getLargeWeaponOPCost().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		String s = "减少 ";
		s += (int)SkillData.ORDNANCE_EXPERT_PERK_OP_REDUCTION_SMALL + "/" + 
			 (int)SkillData.ORDNANCE_EXPERT_PERK_OP_REDUCTION_MEDIUM + "/" +
			 (int)SkillData.ORDNANCE_EXPERT_PERK_OP_REDUCTION_LARGE;
		s += " （小型/中型/大型）炮塔装配点数消耗";
		return s;
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

}
