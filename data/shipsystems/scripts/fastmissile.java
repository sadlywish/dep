package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class fastmissile implements ShipSystemStatsScript {

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
			stats.getSightRadiusMod().modifyMult(id, 1f+1f * effectLevel);
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
			stats.getMissileRoFMult().modifyMult(id, 2.2f);
			stats.getMissileWeaponDamageMult().modifyMult(id, 0.75f);
			stats.getMissileWeaponRangeBonus().modifyMult(id, 1.2f);
			stats.getSightRadiusMod().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("导弹射速提升"+(int)(120)+"%", false);
		}
		if (index == 1) {
			return new StatusData("舰船感应器强度增加"+(int)(100*effectLevel)+"%", false);
		}
		return null;
	}
}
