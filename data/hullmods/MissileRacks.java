package data.hullmods;

import java.util.List;

import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class MissileRacks extends BaseHullMod {

	public static final float AMMO_BONUS = 200f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getMissileAmmoBonus().modifyPercent(id, AMMO_BONUS);

	}
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
	}	
	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) AMMO_BONUS;
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
