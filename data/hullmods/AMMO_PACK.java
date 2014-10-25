package data.hullmods;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class AMMO_PACK extends BaseHullMod {

	public static final float AMMO_BONUS = 200f;
	public static final float TURN_PENALTY = 10f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getBallisticAmmoBonus().modifyPercent(id, AMMO_BONUS);
		stats.getWeaponTurnRateBonus().modifyPercent(id, -TURN_PENALTY);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) AMMO_BONUS;
		if (index == 1) return "" + (int) TURN_PENALTY;
		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		String[] ammos = limitship.ammos;
		List mod = ship.getVariant().getHullMods();
		for (int i = 0; i < ammos.length; i++) {
			for (int j = 0; j < mod.size(); j++) {
				String hullmod = (String)mod.get(j);
				if (hullmod.equals(ammos[i])) {
					return false;
				}
			}
		}
		return true;
	}


}
