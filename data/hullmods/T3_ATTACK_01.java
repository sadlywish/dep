package data.hullmods;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import data.hullmods.limitship;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;

public class T3_ATTACK_01 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1200f);
		HULL_BONUS.put(HullSize.DESTROYER, 3000f);
		HULL_BONUS.put(HullSize.CRUISER, 5000f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 7500f);
	}
	private static Map FLUX_BONUS = new HashMap();
	static {
		FLUX_BONUS.put(HullSize.FRIGATE, 600f);
		FLUX_BONUS.put(HullSize.DESTROYER, 900f);
		FLUX_BONUS.put(HullSize.CRUISER, 1400f);
		FLUX_BONUS.put(HullSize.CAPITAL_SHIP, 2000f);
	}
	private static final float Damage_BONUS = 200f;
	private static final float AMMO_BONUS = 900f;
	private static final float Range_Bonus = 200f;
	private static final float speed_Bonus = 250f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getMissileAmmoBonus().modifyPercent(id, AMMO_BONUS);
		stats.getMissileWeaponRangeBonus().modifyPercent(id, Range_Bonus);
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getFluxDissipation().modifyFlat(id,(Float) FLUX_BONUS.get(hullSize));
		stats.getMissileWeaponDamageMult().modifyPercent(id, Damage_BONUS);
		stats.getMissileWeaponDamageMult().modifyPercent(id, Damage_BONUS);
		stats.getMissileRoFMult().modifyPercent(id, speed_Bonus);
		stats.getMissileMaxSpeedBonus().modifyPercent(id, speed_Bonus);
		stats.getMissileAccelerationBonus().modifyPercent(id, speed_Bonus);
		stats.getMissileMaxTurnRateBonus().modifyPercent(id, speed_Bonus);
		stats.getMissileTurnAccelerationBonus().modifyPercent(id, speed_Bonus);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
		HullSize hullSize = ship.getHullSize();
		MutableShipStatsAPI stats = ship.getMutableStats();

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) Damage_BONUS;
		if (index == 1) return "" + (int) AMMO_BONUS;
		if (index == 2) return "" + (int) Range_Bonus;
		if (index == 3) return "" + (int) speed_Bonus;
		if (index == 4) return "" + ((Float) HULL_BONUS.get(hullSize)).intValue();
		if (index == 5) return "" + ((Float) FLUX_BONUS.get(hullSize)).intValue();

		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		String ID = ship.getHullSpec().getHullId();
		String[] limit = limitship.T3;
		for (int a = 0;a<limit.length ;a++ )
		{
			if(limit[a].equals(ID))
			{
				String[] lim = limitship.att;
				List mod = ship.getVariant().getHullMods();
				for(int b = 0;b<mod.size();b++)
				{
					String thismod = (String)mod.get(b);
					for (int c = 0;c<lim.length ;c++ )
					{
						if(thismod.equals(lim[c]))
						{
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;	}


}
