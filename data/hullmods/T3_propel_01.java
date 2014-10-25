package data.hullmods;

import java.util.HashMap;
import java.util.Map;
import data.hullmods.limitship;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import java.util.List;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class T3_propel_01 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1400f);
		HULL_BONUS.put(HullSize.DESTROYER, 3400f);
		HULL_BONUS.put(HullSize.CRUISER, 5500f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 8000f);
	}
	private static Map MaxSpeed_BONUS = new HashMap();
	static {
		MaxSpeed_BONUS.put(HullSize.FRIGATE, 120f);
		MaxSpeed_BONUS.put(HullSize.DESTROYER, 80f);
		MaxSpeed_BONUS.put(HullSize.CRUISER, 60f);
		MaxSpeed_BONUS.put(HullSize.CAPITAL_SHIP, 40f);
	}
	private static Map Acceleration_BONUS = new HashMap();
	static {
		Acceleration_BONUS.put(HullSize.FRIGATE, 100f);
		Acceleration_BONUS.put(HullSize.DESTROYER, 60f);
		Acceleration_BONUS.put(HullSize.CRUISER, 40f);
		Acceleration_BONUS.put(HullSize.CAPITAL_SHIP, 30f);
	}
	private static Map Deceleration_BONUS = new HashMap();
	static {
		Deceleration_BONUS.put(HullSize.FRIGATE, 80f);
		Deceleration_BONUS.put(HullSize.DESTROYER, 60f);
		Deceleration_BONUS.put(HullSize.CRUISER, 30f);
		Deceleration_BONUS.put(HullSize.CAPITAL_SHIP, 25f);
	}
	private static Map MaxTurnRate_BONUS = new HashMap();
	static {
		MaxTurnRate_BONUS.put(HullSize.FRIGATE, 90f);
		MaxTurnRate_BONUS.put(HullSize.DESTROYER, 80f);
		MaxTurnRate_BONUS.put(HullSize.CRUISER, 50f);
		MaxTurnRate_BONUS.put(HullSize.CAPITAL_SHIP, 20f);
	}
	private static Map TurnAcceleration_BONUS = new HashMap();
	static {
		TurnAcceleration_BONUS.put(HullSize.FRIGATE, 100f);
		TurnAcceleration_BONUS.put(HullSize.DESTROYER, 90f);
		TurnAcceleration_BONUS.put(HullSize.CRUISER, 60f);
		TurnAcceleration_BONUS.put(HullSize.CAPITAL_SHIP, 30f);
	}
	private static final float FluxC_BONUS = 8f;
	private static final float FluxD_BONUS = 4f;
	private static final float EngineHealth_BONUS = 50f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getMaxSpeed().modifyFlat(id,(Float) MaxSpeed_BONUS.get(hullSize));
		stats.getAcceleration().modifyFlat(id,(Float) Acceleration_BONUS.get(hullSize));
		stats.getDeceleration().modifyFlat(id,(Float) Deceleration_BONUS.get(hullSize));
		stats.getMaxTurnRate().modifyFlat(id,(Float) MaxTurnRate_BONUS.get(hullSize));
		stats.getTurnAcceleration().modifyFlat(id,(Float) TurnAcceleration_BONUS.get(hullSize));
		stats.getFluxCapacity().modifyPercent(id, FluxC_BONUS);
		stats.getFluxDissipation().modifyPercent(id, FluxD_BONUS);
		stats.getEngineHealthBonus().modifyPercent(id, EngineHealth_BONUS);

	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
		MutableShipStatsAPI stats = ship.getMutableStats();
		HullSize hullSize = ship.getHullSize();

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) FluxC_BONUS;
		if (index == 1) return "" + (int) FluxD_BONUS;
		if (index == 2) return "" + (int) EngineHealth_BONUS;
		if (index == 3) return "" + ((Float) HULL_BONUS.get(hullSize)).intValue();
		if (index == 4) return "" + ((Float) MaxSpeed_BONUS.get(hullSize)).intValue();
		return null;
	}
	public boolean isApplicableToShip(ShipAPI ship) {
		String ID = ship.getHullSpec().getHullId();
		String[] limit = limitship.T3;
		for (int a = 0;a<limit.length ;a++ )
		{
			if(limit[a].equals(ID))
			{
				String[] lim = limitship.pro;
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
