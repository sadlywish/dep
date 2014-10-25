package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class MissileSpecializationPerk2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMissileWeaponDamageMult().modifyPercent(id, SkillData.MISSILE_SPEC_PERK_DAMAGE_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMissileWeaponDamageMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.MISSILE_SPEC_PERK_DAMAGE_BONUS) + "% 导弹伤害";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
