package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class DamageControlPerk2_2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getCombatWeaponRepairTimeMult().modifyMult(id, 1f - SkillData.DAMAGE_CONTROL_PERK_SYSTEM_REPAIR_BONUS * 0.01f);
		stats.getCombatEngineRepairTimeMult().modifyMult(id, 1f - SkillData.DAMAGE_CONTROL_PERK_SYSTEM_REPAIR_BONUS * 0.01f);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getCombatWeaponRepairTimeMult().unmodify(id);
		stats.getCombatEngineRepairTimeMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "加快 " + (int)(SkillData.DAMAGE_CONTROL_PERK_SYSTEM_REPAIR_BONUS) + "% 在战斗中修复受损武器及引擎的速度";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
