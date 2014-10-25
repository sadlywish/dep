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

public class T3_ATTACK_02 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1000f);
		HULL_BONUS.put(HullSize.DESTROYER, 2600f);
		HULL_BONUS.put(HullSize.CRUISER, 4200f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 7000f);
	}
	private static Map FLUX_BONUS = new HashMap();
	static {
		FLUX_BONUS.put(HullSize.FRIGATE, 800f);
		FLUX_BONUS.put(HullSize.DESTROYER, 1300f);
		FLUX_BONUS.put(HullSize.CRUISER, 1900f);
		FLUX_BONUS.put(HullSize.CAPITAL_SHIP, 2500f);
	}
	private static final float Damage_BONUS = 100f;
	private static final float AMMO_BONUS = 200f;
	private static final float Range_BONUS = 150f;
	private static final float FLUXC_BONUS = 15f;
	private static final float speed_Bonus = 250f;
	private static final float ROF_Bonus = 200f;
	private static final float Recoil_Bonus = 0.5f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getEnergyAmmoBonus().modifyPercent(id, AMMO_BONUS);
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getFluxDissipation().modifyFlat(id,(Float) FLUX_BONUS.get(hullSize));
		stats.getEnergyWeaponDamageMult().modifyPercent(id, Damage_BONUS);
		stats.getEnergyWeaponRangeBonus().modifyPercent(id, Range_BONUS);
		stats.getFluxCapacity().modifyPercent(id,(Float) FLUXC_BONUS);
		stats.getEnergyRoFMult().modifyPercent(id, ROF_Bonus);
		stats.getProjectileSpeedMult().modifyPercent(id, speed_Bonus);
		stats.getMaxRecoilMult().modifyMult(id, Recoil_Bonus);

	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
		HullSize hullSize = ship.getHullSize();
		MutableShipStatsAPI stats = ship.getMutableStats();

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) Damage_BONUS;
		if (index == 1) return "" + (int) AMMO_BONUS;
		if (index == 2) return "" + (int) Range_BONUS;
		if (index == 3) return "" + (int) ROF_Bonus;
		if (index == 4) return "" + (int) speed_Bonus;
		if (index == 5) return "" + (int) (Recoil_Bonus * 100);
		if (index == 6) return "" + (int) FLUXC_BONUS;
		if (index == 7) return "" + ((Float) HULL_BONUS.get(hullSize)).intValue();
		if (index == 8) return "" + ((Float) FLUX_BONUS.get(hullSize)).intValue();
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
