package data.hullmods;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import data.hullmods.limitship;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;

public class T3_Defense_02 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 950f);
		HULL_BONUS.put(HullSize.DESTROYER, 2900f);
		HULL_BONUS.put(HullSize.CRUISER, 4800f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 6900f);
	}
	private static Map Armor_BONUS = new HashMap();
	static {
		Armor_BONUS.put(HullSize.FRIGATE, 2500f);
		Armor_BONUS.put(HullSize.DESTROYER, 4800f);
		Armor_BONUS.put(HullSize.CRUISER, 7200f);
		Armor_BONUS.put(HullSize.CAPITAL_SHIP, 12000f);
	}
	private static final float EA_BONUS = 200f;
	private static final float WA_BONUS = 150f;
	private static final float ARMORDamage_BONUS = 50f;
	private static final float MAX_PENALTY = 12f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getArmorBonus().modifyFlat(id,(Float) Armor_BONUS.get(hullSize));
		stats.getEngineHealthBonus().modifyPercent(id,EA_BONUS);
		stats.getWeaponHealthBonus().modifyPercent(id,WA_BONUS);
		stats.getArmorDamageTakenMult().modifyPercent(id, -ARMORDamage_BONUS);
		stats.getMaxSpeed().modifyPercent(id, -MAX_PENALTY);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) ARMORDamage_BONUS;
		if (index == 1) return "" + (int) EA_BONUS;
		if (index == 2) return "" + (int) MAX_PENALTY;
		if (index == 3) return "" + ((Float) HULL_BONUS.get(hullSize)).intValue();
		if (index == 4) return "" + ((Float) Armor_BONUS.get(hullSize)).intValue();
		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		String ID = ship.getHullSpec().getHullId();
		String[] limit = limitship.T3;
		for (int a = 0;a<limit.length ;a++ )
		{
			if(limit[a].equals(ID))
			{
				String[] lim = limitship.def;
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
