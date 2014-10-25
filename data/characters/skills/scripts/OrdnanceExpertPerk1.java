package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class OrdnanceExpertPerk1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getProjectileSpeedMult().modifyPercent(id, SkillData.ORDNANCE_EXPERT_PERK_PROJECTILE_SPEED_PERCENTAGE);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getProjectileSpeedMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.ORDNANCE_EXPERT_PERK_PROJECTILE_SPEED_PERCENTAGE) + "% 实弹以及能量武器弹药飞行速度";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
