package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class GunneryImplantsPerk2_2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getBallisticRoFMult().modifyPercent(id, SkillData.GUNNERY_IMPLANTS_PERK_ROF_BONUS);
		stats.getEnergyRoFMult().modifyPercent(id, SkillData.GUNNERY_IMPLANTS_PERK_ROF_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getBallisticRoFMult().unmodify(id);
		stats.getEnergyRoFMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.GUNNERY_IMPLANTS_PERK_ROF_BONUS) + "% 实弹武器和能量武器射速";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
