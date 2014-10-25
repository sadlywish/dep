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

public class T3_ATTACK_03 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE,2000f);
		HULL_BONUS.put(HullSize.DESTROYER, 5200f);
		HULL_BONUS.put(HullSize.CRUISER, 8400f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 14000f);
	}
	private static Map FLUX_BONUS = new HashMap();
	static {
		FLUX_BONUS.put(HullSize.FRIGATE, 900f);
		FLUX_BONUS.put(HullSize.DESTROYER, 1500f);
		FLUX_BONUS.put(HullSize.CRUISER, 2100f);
		FLUX_BONUS.put(HullSize.CAPITAL_SHIP, 3000f);
	}

	private static final float Damage_BONUS = 150f;
	private static final float AMMO_BONUS = 250f;
	private static final float Range_BONUS = 75f;
	private static final float TurnRate_Bonus = 75f;
	private static final float ROF_Bonus = 150f;
	private static final float speed_Bonus = 150f;
	private static final float Recoil_Bonus = 0.3f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getBallisticAmmoBonus().modifyPercent(id, AMMO_BONUS);
		stats.getBallisticWeaponDamageMult().modifyPercent(id, Damage_BONUS);
		stats.getBallisticWeaponRangeBonus().modifyPercent(id, Range_BONUS);
		stats.getWeaponTurnRateBonus().modifyPercent(id, TurnRate_Bonus);
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getFluxDissipation().modifyFlat(id,(Float) FLUX_BONUS.get(hullSize));
		stats.getBallisticRoFMult().modifyPercent(id,ROF_Bonus);
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
		if (index == 3) return "" + (int) TurnRate_Bonus;
		if (index == 4) return "" + (int) ROF_Bonus;
		if (index == 5) return "" + (int) speed_Bonus;
		if (index == 6) return "" + (int) (Recoil_Bonus * 100);
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
