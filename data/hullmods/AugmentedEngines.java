package data.hullmods;

import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class AugmentedEngines extends BaseHullMod {

	public static final float REPAIR_PENALTY = 100f;
	
	
	private static Map mag = new HashMap();
	static {
		mag.put(HullSize.FRIGATE, 40f);
		mag.put(HullSize.DESTROYER, 30f);
		mag.put(HullSize.CRUISER, 25f);
		mag.put(HullSize.CAPITAL_SHIP, 20f);
	}
	
	private static final int BURN_LEVEL_BONUS = 1;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().modifyFlat(id, (Float) mag.get(hullSize));
		stats.getCombatEngineRepairTimeMult().modifyPercent(id, REPAIR_PENALTY);
	
		stats.getMaxBurnLevel().modifyFlat(id, BURN_LEVEL_BONUS);
	}
	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + ((Float) mag.get(HullSize.FRIGATE)).intValue();
		if (index == 1) return "" + ((Float) mag.get(HullSize.DESTROYER)).intValue();
		if (index == 2) return "" + ((Float) mag.get(HullSize.CRUISER)).intValue();
		if (index == 3) return "" + ((Float) mag.get(HullSize.CAPITAL_SHIP)).intValue();
		if (index == 4) return "两倍";
		if (index == 5) return "" + BURN_LEVEL_BONUS;
		return null;
	}


}
