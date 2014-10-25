package data.hullmods;

import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class RepairUnit extends BaseHullMod {

	public static final float REPAIR_BONUS = 95f;
	public static final float Shield_PENALTY = 300f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getCombatWeaponRepairTimeMult().modifyPercent(id, -REPAIR_BONUS);
		stats.getWeaponHealthBonus().modifyPercent(id, Shield_PENALTY);
		stats.getEngineHealthBonus().modifyPercent(id, Shield_PENALTY);

	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) REPAIR_BONUS;
		if (index == 1) return "" + (int) Shield_PENALTY;
		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		return true;
	}


}
