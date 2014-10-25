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

public class T2_ATTACK_02 extends BaseHullMod {

	private static final float Damage_BONUS = 150f;
	private static final float AMMO_BONUS = 80f;
	private static final float Range_BONUS = 60f;
	private static final float Recoil_Bonus = 0.8f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getEnergyAmmoBonus().modifyPercent(id, AMMO_BONUS);
		stats.getEnergyWeaponDamageMult().modifyPercent(id, Damage_BONUS);
		stats.getEnergyWeaponRangeBonus().modifyPercent(id, Range_BONUS);
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
		if (index == 3) return "" + (int) (Recoil_Bonus * 100);
		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		if(ship.getHullSize() == HullSize.FIGHTER)
		{
			return true;
		}
		String ID = ship.getHullSpec().getHullId();
		String[] limit = limitship.T2;
		for (int a = 0;a<limit.length ;a++ )
		{
			if(limit[a].equals(ID))
			{
					String[] lim = limitship.att_t2;
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
