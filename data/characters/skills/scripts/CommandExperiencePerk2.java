package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class CommandExperiencePerk2 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMinCrewMod().modifyMult(id, SkillData.COMMAND_EXPERIENCE_MIN_CREW_MULT);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMinCrewMod().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "减少 " + (int)(SkillData.COMMAND_EXPERIENCE_MIN_CREW_MULT * 100f) + "% 战舰作战需要的必要船员";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}
}
