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

public class T3_propel_02 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1800f);
		HULL_BONUS.put(HullSize.DESTROYER, 4000f);
		HULL_BONUS.put(HullSize.CRUISER, 6400f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 8800f);
	}
	private static Map MaxSpeed_BONUS = new HashMap();
	static {
		MaxSpeed_BONUS.put(HullSize.FRIGATE, 240f);
		MaxSpeed_BONUS.put(HullSize.DESTROYER, 180f);
		MaxSpeed_BONUS.put(HullSize.CRUISER, 120f);
		MaxSpeed_BONUS.put(HullSize.CAPITAL_SHIP, 90f);
	}
	private static Map Acceleration_BONUS = new HashMap();
	static {
		Acceleration_BONUS.put(HullSize.FRIGATE, 200f);
		Acceleration_BONUS.put(HullSize.DESTROYER, 120f);
		Acceleration_BONUS.put(HullSize.CRUISER, 80f);
		Acceleration_BONUS.put(HullSize.CAPITAL_SHIP, 60f);
	}
	private static Map Deceleration_BONUS = new HashMap();
	static {
		Deceleration_BONUS.put(HullSize.FRIGATE, 160f);
		Deceleration_BONUS.put(HullSize.DESTROYER, 100f);
		Deceleration_BONUS.put(HullSize.CRUISER, 60f);
		Deceleration_BONUS.put(HullSize.CAPITAL_SHIP, 50f);
	}
	private static Map MaxTurnRate_BONUS = new HashMap();
	static {
		MaxTurnRate_BONUS.put(HullSize.FRIGATE, 80f);
		MaxTurnRate_BONUS.put(HullSize.DESTROYER, 60f);
		MaxTurnRate_BONUS.put(HullSize.CRUISER, 40f);
		MaxTurnRate_BONUS.put(HullSize.CAPITAL_SHIP, 20f);
	}
	private static Map TurnAcceleration_BONUS = new HashMap();
	static {
		TurnAcceleration_BONUS.put(HullSize.FRIGATE, 80f);
		TurnAcceleration_BONUS.put(HullSize.DESTROYER, 72f);
		TurnAcceleration_BONUS.put(HullSize.CRUISER, 48f);
		TurnAcceleration_BONUS.put(HullSize.CAPITAL_SHIP, 24f);
	}
	private static final float EMP = 24f;
	private static final float FluxD_PENALTY = 5f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getMaxSpeed().modifyFlat(id,(Float) MaxSpeed_BONUS.get(hullSize));
		stats.getAcceleration().modifyFlat(id,(Float) Acceleration_BONUS.get(hullSize));
		stats.getDeceleration().modifyFlat(id,(Float) Deceleration_BONUS.get(hullSize));
		stats.getMaxTurnRate().modifyFlat(id,(Float) MaxTurnRate_BONUS.get(hullSize));
		stats.getTurnAcceleration().modifyFlat(id,(Float) TurnAcceleration_BONUS.get(hullSize));
		stats.getFluxDamageTakenMult().modifyPercent(id, -EMP);
		stats.getFluxDissipation().modifyPercent(id, FluxD_PENALTY);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) EMP;
		if (index == 1) return "" + (int) FluxD_PENALTY;
		if (index == 2) return "" + ((Float) HULL_BONUS.get(hullSize)).intValue();
		if (index == 3) return "" + ((Float) MaxSpeed_BONUS.get(hullSize)).intValue();
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
