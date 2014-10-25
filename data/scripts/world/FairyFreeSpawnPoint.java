package data.scripts.world;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.Global;

public class FairyFreeSpawnPoint extends BaseSpawnPoint {

	public FairyFreeSpawnPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}

	
	@Override
	public CampaignFleetAPI spawnFleet() {
		CampaignFleetAPI fleet = getSector().createFleet("Fairy", "FreeConvoy");
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		if((float) Math.random()>0.6f){
			fleet.addAssignment(FleetAssignment.RAID_SYSTEM, null, 30);}
		else{
			fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, getAnchor(), 10);}
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
		
		return fleet;
	}
	
}






