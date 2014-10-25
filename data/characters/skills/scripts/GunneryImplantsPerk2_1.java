package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class GunneryImplantsPerk2_1 implements ShipSkillEffect {

	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		stats.getMaxRecoilMult().modifyMult(id, 1f - (0.01f * SkillData.GUNNERY_IMPLANTS_PERK_RECOIL_BONUS));
		stats.getRecoilPerShotMult().modifyMult(id, 1f - (0.01f * SkillData.GUNNERY_IMPLANTS_PERK_RECOIL_BONUS));
		stats.getRecoilDecayMult().modifyMult(id, 1f + (0.01f * SkillData.GUNNERY_IMPLANTS_PERK_RECOIL_BONUS));
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMaxRecoilMult().unmodify(id);
		stats.getRecoilPerShotMult().unmodify(id);
		stats.getRecoilDecayMult().unmodify(id);
	}
	
	public String getEffectDescription(float level) {
		return "-" + (int)(SkillData.GUNNERY_IMPLANTS_PERK_RECOIL_BONUS) + "% 武器后座力";
	}
	
	public String getEffectPerLevelDescription() {
		return null;
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.PILOTED_SHIP;
	}

}
