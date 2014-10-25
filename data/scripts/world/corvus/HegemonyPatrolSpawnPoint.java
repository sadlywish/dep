package data.scripts.world.corvus;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import data.scripts.world.BaseSpawnPoint;

@SuppressWarnings("unchecked")
public class HegemonyPatrolSpawnPoint extends BaseSpawnPoint {

	public HegemonyPatrolSpawnPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}

	
	@Override
	public CampaignFleetAPI spawnFleet() {
		//if ((float) Math.random() < 0.75f) return null;
		String type = "patrol";
		
		float angle = (float) ((float) Math.random() * Math.PI * 2f);
		float x = (float) (Math.cos(angle) * 12000f);
		float y = (float) (Math.sin(angle) * 12000f);
		SectorEntityToken spawnPoint = getLocation().createToken(x, y);
		
		
		CampaignFleetAPI fleet = getSector().createFleet("hegemony", type);
		getLocation().spawnFleet(spawnPoint, 0, 0, fleet);
		fleet.setPreferredResupplyLocation(getAnchor());
		
		fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, null, 60);
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		
		return fleet;
	}
	
}






