package data.characters.skills.scripts;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;


/**
 * Small:	1 0 1 0 1 0 1 0 1 0
 * Medium:  0 1 0 0 0 1 0 0 0 1
 * Large:   0 0 0 1 0 0 0 1 0 1
 * Capital: 0 0 0 0 1 0 0 0 0 1
 * 
 * @author Alex Mosolov
 *
 * Copyright 2013 Fractal Softworks, LLC
 */
public class NavigationEffect1 implements ShipSkillEffect {
	
	public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {
		int bonus = 0;
		if (hullSize == HullSize.FIGHTER || hullSize == HullSize.FRIGATE) {
			bonus = getBonusForSmall(level);
		} else if (hullSize == HullSize.DESTROYER) {
			bonus = getBonusForMedium(level);
		} else if (hullSize == HullSize.CRUISER) {
			bonus = getBonusForLarge(level);
		} else if (hullSize == HullSize.CAPITAL_SHIP) {
			bonus = getBonusForCapital(level);
		}
		stats.getMaxBurnLevel().modifyFlat(id, bonus);
	}
	
	public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {
		stats.getMaxBurnLevel().unmodify(id);
	}	
	
	public String getEffectDescription(float level) {
		return "+" + getBonusForSmall(level) + "/" + getBonusForSmall(level) + "/" +
				getBonusForMedium(level) + "/" +
				getBonusForLarge(level) + "/" + getBonusForCapital(level) + " 最大航速等级， 取决于船体体积";
	}
	
	public String getEffectPerLevelDescription() {
		return "varies";
	}

	public ScopeDescription getScopeDescription() {
		return ScopeDescription.ALL_SHIPS;
	}

	
	private int getBonusForSmall(float level) {
		if (level >= 10) return 5;
		return (int) (level - 1) / 2 + 1;
	}
	
	private int getBonusForMedium(float level) {
		int bonus = 0;
		if (level >= 10) bonus = 3;
		else if (level >= 6) bonus = 2;
		else if (level >= 2) bonus = 1;
		return bonus;
	}
	
	private int getBonusForLarge(float level) {
		int bonus = 0;
		if (level >= 10) bonus = 3;
		else if (level >= 8) bonus = 2;
		else if (level >= 4) bonus = 1;
		return bonus;
	}
	
	private int getBonusForCapital(float level) {
		int bonus = 0;
		if (level >= 10) bonus = 2;
		else if (level >= 5) bonus = 1;
		return bonus;
	}
}


//public class NavigationEffect1 implements CharacterStatsSkillEffect {
//	
//	public void apply(MutableCharacterStatsAPI stats, String id, float level) {
//		stats.getTravelSpeedBonus().modifyPercent(id, SkillData.NAVIGATION_SPEED_BONUS_PERCENTAGE * level);
//	}
//
//	public void unapply(MutableCharacterStatsAPI stats, String id) {
//		stats.getTravelSpeedBonus().unmodify(id);
//	}
//	
//	public String getEffectDescription(float level) {
//		return "+" + (int)(SkillData.NAVIGATION_SPEED_BONUS_PERCENTAGE * level) + "% travel speed";
//	}
//	
//	public String getEffectPerLevelDescription() {
//		return "" + (int)(SkillData.NAVIGATION_SPEED_BONUS_PERCENTAGE) + "%";
//	}
//
//	public ScopeDescription getScopeDescription() {
//		return ScopeDescription.FLEET;
//	}
//
//}
