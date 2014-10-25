package data.scripts.world.corvus;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import data.scripts.world.BaseSpawnPoint;

public class PirateSpawnPoint extends BaseSpawnPoint {

	public PirateSpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}

	@Override
	protected CampaignFleetAPI spawnFleet() {
		//if ((float) Math.random() < 0.5f) return null;
		
		String type = null;
		float r = (float) Math.random();
		if (r > .9f) {
			type = "scout";
		} else if (r > 0.65f) {
			type = "raiders1";
		} else if (r > 0.45f) {
			type = "raiders2";
		} else if (r > 0.3f) {
			type = "raiders3";
		} else if (r > 0.15f) {
			type = "attackFleet";
		} else if (r > 0.05f) {
			type = "carrierGroup";
		} else {
			type = "armada";
		}
		
		
		CampaignFleetAPI fleet = getSector().createFleet("fow", type);
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		
		fleet.setPreferredResupplyLocation(getAnchor());
		
		if (type.equals("scout") || type.equals("raiders1") || type.equals("raiders2") ||
						type.equals("raiders3") || type.equals("attackFleet")) {
			fleet.addAssignment(FleetAssignment.RAID_SYSTEM, null, 10);
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
		} else {
			if ((float) Math.random() > 0.5f) {
				fleet.addAssignment(FleetAssignment.RAID_SYSTEM, null, 30);
				fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
			} else {
				fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, getAnchor(), 20);
				fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
			}
		}
		
		return fleet;
	}

}
