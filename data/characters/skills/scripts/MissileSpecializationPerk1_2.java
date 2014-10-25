package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class MissileSpecializationPerk1_2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMissileAmmoBonus().modifyFlat(id, SkillData.MISSILE_SPEC_PERK_AMMO_BONUS);
	}

	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMissileAmmoBonus().unmodify(id);
	}

	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.MISSILE_SPEC_PERK_AMMO_BONUS) + " 导弹备弹量";
	}

	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}

