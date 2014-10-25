package data.scripts.plugins.logistics;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class LogisticsPlugins implements EveryFrameScript{

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean runWhilePaused() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void advance(float amount) {
		boolean noNeedClean = true;
		CampaignFleetAPI fleet = Global.getSector().getPlayerFleet();
		List shipList = fleet.getFleetData().getMembersListCopy();
		for (int i = 0; i < shipList.size(); i++) {
			FleetMemberAPI ship = (FleetMemberAPI)shipList.get(i);
			List hullmod = ship.getVariant().getHullMods();
			for (int j = 0; j < hullmod.size(); j++) {
				if (hullmod.get(j).equals("FleetLogistics")) {
					noNeedClean = false;
					break;
				}
			}
			if (!noNeedClean) {
				break;
			}
		}
		if (noNeedClean) {
			fleet.getCommanderStats().getLogistics().unmodifyFlat("舰队物流管理模块");
		}
		
	}




}
