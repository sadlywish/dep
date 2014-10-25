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
import com.fs.starfarer.api.combat.ShieldAPI;
import com.fs.starfarer.api.combat.ShieldAPI.ShieldType;

public class T3_Engineering_03 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 1200f);
		HULL_BONUS.put(HullSize.DESTROYER, 2800f);
		HULL_BONUS.put(HullSize.CRUISER, 3600f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 6200f);
	}
	private static Map FLUX_BONUS = new HashMap();
	static {
		FLUX_BONUS.put(HullSize.FRIGATE, 4000f);
		FLUX_BONUS.put(HullSize.DESTROYER, 7500f);
		FLUX_BONUS.put(HullSize.CRUISER, 15000f);
		FLUX_BONUS.put(HullSize.CAPITAL_SHIP, 20000f);
	}
	private static final float ARC_BONUS = 360f;
	private static final float SPEED_BONUS = 70f;
	private static final float Armor_Bonus = 45f;
	private static final float SHIELD_BONUS_TURN = 500f;
	private static final float SHIELD_BONUS_keep = 0.4f;
	private static final float SHIELD_BONUS_taken = 0.4f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getShieldArcBonus().modifyFlat(id, (Float)ARC_BONUS);
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getFluxCapacity().modifyFlat(id,(Float) FLUX_BONUS.get(hullSize));
		stats.getMaxSpeed().modifyPercent(id, (Float)SPEED_BONUS);
		stats.getArmorBonus().modifyPercent(id, (Float)Armor_Bonus);
		stats.getShieldUpkeepMult().modifyFlat(id, -(Float)SHIELD_BONUS_keep);
		stats.getShieldAbsorptionMult().modifyFlat(id, -(Float)SHIELD_BONUS_taken);
		stats.getShieldUnfoldRateMult().modifyPercent(id, (Float)SHIELD_BONUS_TURN);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
			ShieldAPI shield = ship.getShield();
			shield.setType(ShieldType.OMNI);
	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) ARC_BONUS;
		if (index == 1) return "" + (int) SHIELD_BONUS_TURN;
		if (index == 2) return "" + (int) SPEED_BONUS;
		if (index == 3) return "" + (int) Armor_Bonus;
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
				String[] lim = limitship.eng;
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
