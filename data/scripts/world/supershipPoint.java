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
public class supershipPoint extends BaseSpawnPoint 
{
	public supershipPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}	
	@Override
	public CampaignFleetAPI spawnFleet() 
	{
	StarSystemAPI system = getSector().getStarSystem("Corvus");
	SectorEntityToken station = system.getEntityByName("轨道空间站");
	SectorEntityToken stationI = system.getEntityByName("企业总部");
	CargoAPI cargo = station.getCargo();
	CargoAPI cargoI =stationI.getCargo();
	cargo.addMothballedShip(FleetMemberType.SHIP, "yd_Eternal_hull", null);
    cargo.addMothballedShip(FleetMemberType.SHIP, "hum_worldDestroyer_Hull", null);
    cargoI.addMothballedShip(FleetMemberType.SHIP, "yd_Restricted_Hull", null);
    cargoI.addMothballedShip(FleetMemberType.FIGHTER_WING, "TRI_Silent_wing", null);
    cargoI.addMothballedShip(FleetMemberType.FIGHTER_WING, "TRI_Supernova_wing", null);

  return null;

	}	
}