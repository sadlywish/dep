package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class Battle_module_Stats1 implements ShipSystemStatsScript {

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().unmodify(id); // to slow down ship to its regular top speed while powering drive down
		} else {
			stats.getMaxSpeed().modifyMult(id, 1.5f);
			stats.getMissileWeaponDamageMult().modifyMult(id, 1f+1.5f * effectLevel);
			stats.getBallisticWeaponDamageMult().modifyMult(id, 1f+1.5f * effectLevel);
			stats.getEnergyWeaponDamageMult().modifyMult(id, 1f+1.5f * effectLevel);
			stats.getBeamWeaponDamageMult().modifyMult(id, 1f+1.5f * effectLevel);
			stats.getEnergyWeaponRangeBonus().modifyMult(id, 1f+2f * effectLevel);
			stats.getBallisticWeaponRangeBonus().modifyMult(id, 1f+2f * effectLevel);
			stats.getMissileWeaponRangeBonus().modifyMult(id, 1f+2f * effectLevel);
			stats.getBeamWeaponRangeBonus().modifyMult(id, 1f+2f * effectLevel);
			stats.getSightRadiusMod().modifyMult(id, 1f+1f * effectLevel);
			stats.getWeaponTurnRateBonus().modifyMult(id, 0.5f * effectLevel);
			stats.getShieldDamageTakenMult().modifyMult(id, 0.5f * effectLevel);
			stats.getFluxDissipation().modifyMult(id, 1f+0.5f * effectLevel);
		}
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
			stats.getMaxSpeed().unmodify(id);
			stats.getMissileWeaponDamageMult().unmodify(id);
			stats.getBallisticWeaponDamageMult().unmodify(id);
			stats.getEnergyWeaponDamageMult().unmodify(id);
			stats.getBeamWeaponDamageMult().unmodify(id);
			stats.getEnergyWeaponRangeBonus().unmodify(id);
			stats.getBallisticWeaponRangeBonus().unmodify(id);
			stats.getMissileWeaponRangeBonus().unmodify(id);
			stats.getBeamWeaponRangeBonus().unmodify(id);
			stats.getSightRadiusMod().unmodify(id);
			stats.getWeaponTurnRateBonus().unmodify(id);
			stats.getShieldDamageTakenMult().unmodify(id);
			stats.getFluxDissipation().unmodify(id);
			stats.getArmorDamageTakenMult().modifyMult(id, 0.7f);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("辐能散耗速度提升"+(int)(50*effectLevel)+"%,舰船感应器强度增加"+(int)(100*effectLevel)+"%", false);
		}
		if (index == 1) {
			return new StatusData("武器射程提升"+(int)(200*effectLevel)+"%,伤害提升"+(int)(150*effectLevel)+"%", false);
		}
		if (index == 2) {
			return new StatusData("炮台转速减少"+(int)(50*effectLevel)+"%,航行速度提升50%", false);
		}
		if (index == 3) {
			return new StatusData("减少护盾受到的"+(int)(50*effectLevel)+"%伤害", false);
		}
		return null;
	}
}
