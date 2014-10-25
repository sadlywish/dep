package data.scripts.world.corvus;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import data.scripts.world.BaseSpawnPoint;

public class IndependentSpawnPoint extends BaseSpawnPoint {

	private final SectorEntityToken station;

	public IndependentSpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken station) {
		super(sector, location, daysInterval, maxFleets, null);
		this.station = station;
	}

	@Override
	protected CampaignFleetAPI spawnFleet() {
		//if ((float) Math.random() < 0.5f) return null;
		
		String type = null;
		float r = (float) Math.random();
		if (r > .9f) {
			type = "miner";
		} else if (r > 0.75f) {
			type = "loneTrader";
		} else if (r > 0.65f) {
			type = "trader1";
		} else if (r > 0.55f) {
			type = "trader2";
		} else if (r > 0.45f) {
			type = "trader3";
		} else if (r > 0.3f) {
			type = "loneMerc";
		} else if (r > 0.15f) {
			type = "merc1";
		} else {
			type = "mercForce";
		}
		if ((float) Math.random()>0.8f)
		{
			CampaignFleetAPI fleet1 = getSector().createFleet("pirates", "DOLLForce");
			fleet1.addAssignment(FleetAssignment.PATROL_SYSTEM, null, 30);

		}
		float angle = (float) ((float) Math.random() * Math.PI * 2f);
		float x = (float) (Math.cos(angle) * 12000f);
		float y = (float) (Math.sin(angle) * 12000f);
		SectorEntityToken spawnPoint = getLocation().createToken(x, y);
		
		CampaignFleetAPI fleet = getSector().createFleet("independent", type);
		getLocation().spawnFleet(spawnPoint, 0, 0, fleet);
		
		fleet.setPreferredResupplyLocation(station);
		
		if (type.equals("miner")) {
			fleet.addAssignment(FleetAssignment.RESUPPLY, station, 1000);
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		} else if (type.equals("loneTrader")) {
			fleet.addAssignment(FleetAssignment.DELIVER_SUPPLIES, station, 1000);
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		} else if (type.equals("trader1")) {
			fleet.addAssignment(FleetAssignment.DELIVER_SUPPLIES, station, 1000);
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		} else if (type.equals("trader2")) {
			fleet.addAssignment(FleetAssignment.DELIVER_SUPPLIES, station, 1000);
			fleet.addAssignment(FleetAssignment.DELIVER_FUEL, station, 1000);
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		} else if (type.equals("trader3")) {
			fleet.addAssignment(FleetAssignment.DELIVER_SUPPLIES, station, 1000);
			fleet.addAssignment(FleetAssignment.DELIVER_FUEL, station, 1000);
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		} else if (type.equals("loneMerc")) {
			fleet.addAssignment(FleetAssignment.RESUPPLY, station, 1000);
			if ((float) Math.random() > 0.5f) {
				fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, null, 30);
			} else {
				fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, station, 15);
			}
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		} else {
			fleet.addAssignment(FleetAssignment.RESUPPLY, station, 1000);
			if ((float) Math.random() > 0.5f) {
				fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, null, 30);
			} else {
				fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, station, 15);
			}
			fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, spawnPoint, 1000);
		}
		
		
		return fleet;
	}

}







