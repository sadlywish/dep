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

public class T3_Defense_03 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1200f);
		HULL_BONUS.put(HullSize.DESTROYER, 3500f);
		HULL_BONUS.put(HullSize.CRUISER, 5800f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 8000f);
	}
	private static Map Armor_BONUS = new HashMap();
	static {
		Armor_BONUS.put(HullSize.FRIGATE, 780f);
		Armor_BONUS.put(HullSize.DESTROYER, 1250f);
		Armor_BONUS.put(HullSize.CRUISER, 2450f);
		Armor_BONUS.put(HullSize.CAPITAL_SHIP, 4800f);
	}

	private static final float ARMORDamage_BONUS = 30f;
	private static final float MAX_BONUS = 50f;
	private static final float TURM_BONUS = 75f;


	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getArmorBonus().modifyFlat(id,(Float) Armor_BONUS.get(hullSize));
		stats.getArmorDamageTakenMult().modifyPercent(id,-ARMORDamage_BONUS);
		stats.getMaxSpeed().modifyPercent(id, MAX_BONUS);
		stats.getWeaponTurnRateBonus().modifyPercent(id, TURM_BONUS);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) ARMORDamage_BONUS;
		if (index == 1) return "" + (int) TURM_BONUS;
		if (index == 2) return "" + (int) MAX_BONUS;
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
