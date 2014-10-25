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

public class T2_ATTACK_03 extends BaseHullMod {

	private static final float Damage_BONUS = 60f;
	private static final float AMMO_BONUS = 100f;
	private static final float Range_BONUS = 30f;
	private static final float TurnRate_Bonus = 30f;
	private static final float speed_Bonus = 50f;
	private static final float Recoil_Bonus = 0.5f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getBallisticAmmoBonus().modifyPercent(id, AMMO_BONUS);
		stats.getBallisticWeaponDamageMult().modifyPercent(id, Damage_BONUS);
		stats.getBallisticWeaponRangeBonus().modifyPercent(id, Range_BONUS);
		stats.getWeaponTurnRateBonus().modifyPercent(id, TurnRate_Bonus);
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
		if (index == 4) return "" + (int) speed_Bonus;
		if (index == 5) return "" + (int) (Recoil_Bonus * 100);
		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		String ID = ship.getHullSpec().getHullId();
		String[] limit = limitship.T2;
		if(ship.getHullSize() == HullSize.FIGHTER)
		{
			return true;
		}
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
		return false;
	}


}
