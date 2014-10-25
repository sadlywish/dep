package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class GunneryImplantsEffect1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getAutofireAimAccuracy().modifyFlat(id, SkillData.GUNNERY_IMPLANTS_AIM_BONUS * level);
		//stats.getCargoMod().modifyFlat(id, 100 * level);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getAutofireAimAccuracy().unmodify(id);
		//stats.getCargoMod().unmodify();
	}
	
	public String getEffectDescription(float level) {
		return "+" + (int)(100f * level * SkillData.GUNNERY_IMPLANTS_AIM_BONUS) + "% 自动射击武器瞄准精度";
	}
	
	public String getEffectPerLevelDescription() {
		return "" + (int)(100f * SkillData.GUNNERY_IMPLANTS_AIM_BONUS) + "%";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
