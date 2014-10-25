package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.StarSystemAPI;
public class FairyshipConvoySpawnPoint extends BaseSpawnPoint 
{
	public FairyshipConvoySpawnPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}	
	@Override
	public CampaignFleetAPI spawnFleet() 
	{
	StarSystemAPI system = getSector().getStarSystem("Corvus");
	SectorEntityToken station = system.getEntityByName("轨道空间站");
	SectorEntityToken station2 = system.getEntityByName("企业总部");
	CargoAPI cargo2 = station2.getCargo();
	CargoAPI cargo = station.getCargo();
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_Angel_hull", null);
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_Nightingale_hull", null);
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_Drillhead_hull", null);
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_frontier_hull", null);
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_cracks_hull", null);
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_heavyhammer_hull", null);
	cargo2.addMothballedShip(FleetMemberType.SHIP, "yd_apogee2_hull", null);
  return null;

	}	
}