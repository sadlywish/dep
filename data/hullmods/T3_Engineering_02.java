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

public class T3_Engineering_02 extends BaseHullMod {

	private static Map HULL_BONUS = new HashMap();
	static {
		HULL_BONUS.put(HullSize.FRIGATE, 800f);
		HULL_BONUS.put(HullSize.DESTROYER, 2000f);
		HULL_BONUS.put(HullSize.CRUISER, 2700f);
		HULL_BONUS.put(HullSize.CAPITAL_SHIP, 5400f);
	}
	private static Map FLUX_BONUS = new HashMap();
	static {
		FLUX_BONUS.put(HullSize.FRIGATE, 4700f);
		FLUX_BONUS.put(HullSize.DESTROYER, 8500f);
		FLUX_BONUS.put(HullSize.CRUISER, 18000f);
		FLUX_BONUS.put(HullSize.CAPITAL_SHIP, 25000f);
	}
	private static final float ARC_BONUS = 300f;
	private static final float FLUXD_BONUS = 30f;
	private static final float SHIELD_BONUS_TURN = 150f;
	private static final float SHIELD_BONUS_keep = 0.5f;
	private static final float SHIELD_BONUS_taken = 0.9f;

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getShieldArcBonus().modifyFlat(id, (Float)ARC_BONUS);
		stats.getHullBonus().modifyFlat(id,(Float) HULL_BONUS.get(hullSize));
		stats.getFluxCapacity().modifyFlat(id,(Float) FLUX_BONUS.get(hullSize));
		stats.getShieldDamageTakenMult().modifyPercent(id, -(Float)FLUXD_BONUS);
		stats.getShieldUpkeepMult().modifyFlat(id, -(Float)SHIELD_BONUS_keep);
		stats.getShieldAbsorptionMult().modifyFlat(id, -(Float)SHIELD_BONUS_taken);
		stats.getShieldUnfoldRateMult().modifyPercent(id, (Float)SHIELD_BONUS_TURN);
	}
	
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
			ShieldAPI shield = ship.getShield();
			shield.setType(ShieldType.FRONT);
	}

	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) ARC_BONUS;
		if (index == 1) return "" + (int) SHIELD_BONUS_TURN;
		if (index == 2) return "" + ((Float) HULL_BONUS.get(hullSize)).intValue();
		if (index == 3) return "" + ((Float) FLUX_BONUS.get(hullSize)).intValue();
		if (index == 4) return "" + (int) FLUXD_BONUS;
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
