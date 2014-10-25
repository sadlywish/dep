package data.scripts.world.corvus;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import data.scripts.world.BaseSpawnPoint;

@SuppressWarnings("unchecked")
public class HegemonySDFSpawnPoint extends BaseSpawnPoint {

	private final SectorEntityToken station;


	public HegemonySDFSpawnPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor, SectorEntityToken station) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.station = station;
	}

	
	@Override
	public CampaignFleetAPI spawnFleet() {
		//if ((float) Math.random() < 0.75f) return null;
		String type = "systemDefense";
		
		CampaignFleetAPI fleet = getSector().createFleet("hegemony", type);
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		
		fleet.setPreferredResupplyLocation(station);
		
		fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, station, 100000);
		
		return fleet;
	}
	
}






