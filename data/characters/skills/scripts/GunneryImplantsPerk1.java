package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class GunneryImplantsPerk1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getBallisticWeaponRangeBonus().modifyPercent(id, SkillData.GUNNERY_IMPLANTS_PERK_RANGE_BONUS_PERCENTAGE);
		stats.getEnergyWeaponRangeBonus().modifyPercent(id, SkillData.GUNNERY_IMPLANTS_PERK_RANGE_BONUS_PERCENTAGE);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getBallisticWeaponRangeBonus().unmodify(id);
		stats.getEnergyWeaponRangeBonus().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.GUNNERY_IMPLANTS_PERK_RANGE_BONUS_PERCENTAGE) + "% 实弹武器和能量武器射程";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
