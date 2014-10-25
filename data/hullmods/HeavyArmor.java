package data.hullmods;

import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class HeavyArmor extends BaseHullMod {

	public static final float MANEUVER_PENALTY = 20f;
	public static final float Armor_BONUS = 150f;
	
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getArmorBonus().modifyPercent(id, Armor_BONUS);
		
		stats.getAcceleration().modifyPercent(id, -MANEUVER_PENALTY);
		stats.getDeceleration().modifyPercent(id, -MANEUVER_PENALTY);
		stats.getTurnAcceleration().modifyPercent(id, -MANEUVER_PENALTY);
		stats.getMaxTurnRate().modifyPercent(id, -MANEUVER_PENALTY);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int)Armor_BONUS;
		if (index == 1) return "" + (int) MANEUVER_PENALTY;
		return null;
		//if (index == 0) return "" + ((Float) mag.get(hullSize)).intValue();
		//return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		return true;
	}


}
