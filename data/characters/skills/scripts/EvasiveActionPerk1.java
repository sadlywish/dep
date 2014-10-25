package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class EvasiveActionPerk1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getAcceleration().modifyPercent(id, SkillData.EVASIVE_ACTION_PERK_MANEUVER_BONUS);
		stats.getDeceleration().modifyPercent(id, SkillData.EVASIVE_ACTION_PERK_MANEUVER_BONUS);
		stats.getTurnAcceleration().modifyPercent(id, SkillData.EVASIVE_ACTION_PERK_MANEUVER_BONUS * 2f);
		stats.getMaxTurnRate().modifyPercent(id, SkillData.EVASIVE_ACTION_PERK_MANEUVER_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getAcceleration().unmodify(id);
		stats.getDeceleration().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(SkillData.EVASIVE_ACTION_PERK_MANEUVER_BONUS) + "% 舰船机动性";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}
}
