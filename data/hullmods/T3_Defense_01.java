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

public class T3_Defense_01 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1100f);
		HULL_BONUS.put(HullSize.DESTROYER, 3200f);
		HULL_BONUS.put(HullSize.CRUISER, 5400f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 7800f);
	}
	private static Map Armor_BONUS = new HashMap();
	static {
		Armor_BONUS.put(HullSize.FRIGATE, 1480f);
		Armor_BONUS.put(HullSize.DESTROYER, 2450f);
		Armor_BONUS.put(HullSize.CRUISER, 3650f);
		Armor_BONUS.put(HullSize.CAPITAL_SHIP, 7200f);
	}
	private static final float ArmorDamage_BONUS = 30f;
	private static final float HullDamage_BONUS = 30f;
	private static final float FLUX_PENALTY = 10f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getArmorBonus().modifyFlat(id,(Float) Armor_BONUS.get(hullSize));
		stats.getArmorDamageTakenMult().modifyPercent(id, -ArmorDamage_BONUS);
		stats.getHullDamageTakenMult().modifyPercent(id, -HullDamage_BONUS);
		stats.getFluxDissipation().modifyPercent(id, -FLUX_PENALTY);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
		HullSize hullSize = ship.getHullSize();
		MutableShipStatsAPI stats = ship.getMutableStats();

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) ArmorDamage_BONUS;
		if (index == 1) return "" + (int) HullDamage_BONUS;
		if (index == 2) return "" + (int) FLUX_PENALTY;
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
