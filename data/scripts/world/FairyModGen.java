package data.scripts.world;

import java.awt.Color;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetMemberType;

import data.scripts.world.shiplevel.FactionManager;
import data.scripts.world.shiplevel.Fairy_ShipBuilder;
import data.scripts.world.shiplevel.Fairy_ShipLevelUpPoint;


@SuppressWarnings("unchecked")
public class FairyModGen implements SectorGeneratorPlugin {

	public void generate(SectorAPI sector) {
	FairyBUSS buss = new FairyBUSS();
	String bu[] = buss.getnormallist();
	String ss[] = buss.getstationlist();
	String bu_b[][] = buss.getvaluelist();
	StarSystemAPI system = sector.getStarSystem("Corvus");
	SectorEntityToken star = system.getStar();
	SectorEntityToken corvusV = system.addPlanet(star, "Corvus Unknow", "barren", 60, 275, 9000, 550);
	SectorEntityToken CorvusI = system.getEntityByName("Corvus IV");
	FactionManager factionManager = new FactionManager(sector);
	int aa = (int)(360* Math.random());
	int bb = (int)(10000+2500* Math.random());
	int cc = (int)(45+10* Math.random());
	SectorEntityToken stationuni = system.addOrbitalStation(star, 235, 3000, 100, "空间舰船制造总局", "independent");
//	SectorEntityToken stationB = system.addOrbitalStation(star, aa, bb, cc, "黑市", "Blackmarket");
	SectorEntityToken station = system.addOrbitalStation(CorvusI, 45, 300, 50, "联合贸易中心", "independent");
	for (int id = 1;id< ss.length;id++ )
	{
		SectorEntityToken station1 = system.getEntityByName(ss[id]);
		if(station1 == null)continue;
		CargoAPI Cargo = station1.getCargo();
		for (int a = 0; a< bu.length; a++)
		{
			if (buss.isneed(id, bu[a]))
			{
				Cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu[a], buss.getReserve(id, bu[a])*1.1f);
			}
		}
	}
	SectorEntityToken token = system.createToken(15000, 0);
	system.addSpawnPoint(new FairyConvoySpawnPoint(sector, system, 3, 1, token,buss));
//	FairyshipConvoySpawnPoint point = new FairyshipConvoySpawnPoint(sector, system, 20, 1, token);
//	system.addSpawnPoint(new FairyMissionSpawnPoint1(sector, system, 0, 100, token));	
//	system.addSpawnPoint(new Fairy_BlackmarketSpawnPoint(sector, system, 0, 100, token));	
//	system.addSpawnPoint(new supershipPoint(sector, system, 65, 1, token));	
	//system.addSpawnPoint(new CheckPoint(sector, system, 0, 1, token));	
	system.addSpawnPoint(new Fairy_ShipLevelUpPoint(sector, system, 0, 100, token, factionManager));
	Fairy_ShipBuilder builder = new Fairy_ShipBuilder(sector, system, 5, 10, token, factionManager);
	system.addSpawnPoint(builder);
	builder.spawnFleet();
//	system.addSpawnPoint(new fuelPoint(sector, system, 1, 1, token));	
//	system.addSpawnPoint(point);
//	point.spawnFleet(); 
//	SectorEntityToken stationH = system.getEntityByName("轨道空间站");
//	CargoAPI Hcargo = stationH.getCargo();
	FairyPirateSpawnPoint p1 = new FairyPirateSpawnPoint(sector, system, 2, 30, token);
	FairyHighPirateSpawnPoint p2 = new FairyHighPirateSpawnPoint(sector, system, 3, 30, token);
	FairyPirateSpawnPoint p3 = new FairyPirateSpawnPoint(sector, system, 2, 30, token);
	system.addSpawnPoint(p1);
	system.addSpawnPoint(p2);
	system.addSpawnPoint(p3);
	stationuni.getCargo().addItems(CargoAPI.CargoItemType.RESOURCES,"L1",1);
	SectorEntityToken stationI = system.getEntityByName("废弃空间站");
	CargoAPI Icargo = stationI.getCargo();
	system.addSpawnPoint(new bussPoint(sector, system, 0, 10, token, buss));	
	system.addSpawnPoint(new ProducePoint(sector, system, 1, 10, token, buss));	

		FactionAPI Fairy = sector.getFaction("Fairy");
		
		Fairy.setRelationship("hegemony", 0);
		Fairy.setRelationship("tritachyon", 0);
		Fairy.setRelationship("pirates", -1);
		Fairy.setRelationship("independent",0);

		FactionAPI pirates = sector.getFaction("pirates-m");
		
		pirates.setRelationship("hegemony", 0);
		pirates.setRelationship("tritachyon", 0);
		pirates.setRelationship("pirates", 0);
		pirates.setRelationship("independent",0);
		pirates.setRelationship("player", -1);
		pirates.setRelationship("Fairy", 0);

		FactionAPI mission = sector.getFaction("mission");
		
		mission.setRelationship("hegemony", 0);
		mission.setRelationship("tritachyon", 0);
		mission.setRelationship("pirates", 0);
		mission.setRelationship("independent",0);
		mission.setRelationship("player", 0);
		mission.setRelationship("Fairy", 0);
		mission.setRelationship("pirates-m", -1f);//贸易系统当前入站
		
		FactionAPI fow = sector.getFaction("fow");
		
		fow.setRelationship("hegemony", -1);
		fow.setRelationship("tritachyon", 1);
		fow.setRelationship("independent",-1);
		
		FactionAPI tritachyon = sector.getFaction("tritachyon");
		
		tritachyon.setRelationship("hegemony", -1);
		tritachyon.setRelationship("fow", 1);
		tritachyon.setRelationship("independent",-1);		
		
		FactionAPI hegemony = sector.getFaction("hegemony");
		
		hegemony.setRelationship("fow", -1);
		hegemony.setRelationship("tritachyon", -1);
		hegemony.setRelationship("independent", 1);
		
		FactionAPI independent = sector.getFaction("independent");
		
		independent.setRelationship("hegemony", 1);
		independent.setRelationship("tritachyon", -1);
		independent.setRelationship("fow", -1);
		
	}
}
