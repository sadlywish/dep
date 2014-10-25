package data.scripts.world.shiplevel;

import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;

import data.scripts.world.BaseSpawnPoint;
import data.scripts.world.Fairyintt;

public class Fairy_ShipLevelUpPoint extends BaseSpawnPoint {
	private FactionManager factionManager;
	private boolean showLevel = false;
	public Fairy_ShipLevelUpPoint(SectorAPI sector, LocationAPI location,
			float daysInterval, int maxFleets, SectorEntityToken anchor, FactionManager factionManager) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.factionManager = factionManager;
	}

	String thisupitem = "L1";

	@Override
	public CampaignFleetAPI spawnFleet() {
		StarSystemAPI system = getSector().getStarSystem("Corvus");
		SectorEntityToken stationH = system.getEntityByName("空间舰船制造总局");
		CargoAPI Hcargo = stationH.getCargo();
//		int shiplevel = (int)getSector().getFaction("mission").getRelationship("independent");
//		int shiplevel = factionManager.getFleetLevel();
		SectorEntityToken playerfleet = system.getEntityByName("Fleet");
		CargoAPI playercargo = playerfleet.getCargo();
		Vector2f fleetLocation = playerfleet.getLocation();
		Vector2f stationLocation = stationH.getLocation();
		float Range = (float)Math.sqrt((fleetLocation.getX() - stationLocation.getX()) * (fleetLocation.getX() - stationLocation.getX()) + (fleetLocation.getY() - stationLocation.getY()) * (fleetLocation.getY() - stationLocation.getY()));
		if (Range <= 300f && !showLevel)
		{
			getSector().addMessage("您当前所属的阵营为："+factionManager.campNameList[factionManager.campID]+"阵营");
			getSector().addMessage("您当前的舰队等级为："+(factionManager.getFleetLevel()+1)+"");
			showLevel=true;
		}
		if (Range >300f && showLevel) {
			showLevel=false;
		}
		
		if (playercargo.removeItems(CargoItemType.RESOURCES, thisupitem, 1)) {
//			getSector().getFaction("mission").setRelationship("independent",shiplevel);
			factionManager.levelup();
			getSector().addMessage("舰队升级成功,当前舰队等级："+(factionManager.getFleetLevel()+1));
			if (factionManager.getFleetLevel()<9) {
				thisupitem = "L"+(factionManager.getFleetLevel()+1);
				Hcargo.addItems(CargoItemType.RESOURCES, thisupitem, 1);
			}
		}
			getSector().getFaction("mission").setRelationship("tritachyon",factionManager.getEnemyLevel());

		float tool = 0;
		while (playercargo.removeItems(CargoAPI.CargoItemType.WEAPONS,
				"hao_zi", 1)) {
			tool++;
		}
		playercargo.addItems(CargoAPI.CargoItemType.RESOURCES, "hao_zi", tool);
		FactionRelationshipSupport.checkFactionEnemy(factionManager, getSector());
		FactionRelationshipSupport.checkFactionStation(factionManager, getSector());
		
		FactionAPI pirates1 = getSector().getFaction("pirates-m");
		
		pirates1.setRelationship("hegemony", 0);
		pirates1.setRelationship("tritachyon", 0);
		pirates1.setRelationship("pirates", 0);
		pirates1.setRelationship("independent",0);
		pirates1.setRelationship("player", -1);
		pirates1.setRelationship("Fairy", 0);
		
		return null;
	}

}
