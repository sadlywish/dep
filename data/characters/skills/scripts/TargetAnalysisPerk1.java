package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class TargetAnalysisPerk1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getDamageToTargetEnginesMult().modifyPercent(id, SkillData.TARGET_ANALYSIS_PERK_SYSTEM_DAMAGE_BONUS);
		stats.getDamageToTargetWeaponsMult().modifyPercent(id, SkillData.TARGET_ANALYSIS_PERK_SYSTEM_DAMAGE_BONUS);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getDamageToTargetEnginesMult().unmodify(id);
		stats.getDamageToTargetWeaponsMult().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		//return "+" + (int)(SkillData.TARGET_ANALYSIS_PERK_SYSTEM_DAMAGE_BONUS) + "% damage to weapons and engines"; 
		return "对敌人的炮塔和引擎造成双倍伤害"; 
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
