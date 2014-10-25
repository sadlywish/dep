package data.hullmods;

import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class ATTACK_01 extends BaseHullMod {

	private static final float AMMO_BONUS = 0;
	private static final int TURN_PENALTY = 0;
	private static Map mag = new HashMap();
	static {
		mag.put(HullSize.FRIGATE, 500f);
		mag.put(HullSize.DESTROYER, 1000f);
		mag.put(HullSize.CRUISER, 1500f);
		mag.put(HullSize.CAPITAL_SHIP, 2500f);
	}
	
	
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

	@Override
	public boolean isApplicableToShip(ShipAPI arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
