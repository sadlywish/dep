package data.scripts.world.corvus;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetMemberType;

@SuppressWarnings("unchecked")
public class Corvus implements SectorGeneratorPlugin {

	public void generate(SectorAPI sector) {
		ClassLoader cl = Global.getSettings().getScriptClassLoader();
		
		StarSystemAPI system = sector.getStarSystem("Corvus");

		SectorEntityToken hegemonyStation = system.addOrbitalStation(system.getEntityByName("Corvus II"), 45, 300, 50, "轨道空间站", "hegemony");
		SectorEntityToken tritachyonStation = system.addOrbitalStation(system.getEntityByName("Corvus V"), 45, 300, 50, "企业总部", "tritachyon");
		SectorEntityToken pirateStation = system.addOrbitalStation(system.getEntityByName("Corvus IIIA"), 45, 300, 50, "秘密基地", "fow");
		initOrbitalStationCargo(sector, hegemonyStation);
		initTriTachyonHQCargo(sector, tritachyonStation);
		initPirateBaseCargo(sector, pirateStation);

		
		SectorEntityToken neutralStation = system.addOrbitalStation(system.getEntityByName("Corvus I"), 45, 300, 50, "废弃空间站", "neutral");
		neutralStation.getCargo().setFreeTransfer(true);
		
		PirateSpawnPoint pirateSpawn = new PirateSpawnPoint(sector, system, 1, 15, system.getEntityByName("Corvus IIIA"));
		system.addSpawnPoint(pirateSpawn);
		for (int i = 0; i < 10; i++)
			pirateSpawn.spawnFleet();

		HegemonyPatrolSpawnPoint patrolSpawn = new HegemonyPatrolSpawnPoint(sector, system, 3, 15, hegemonyStation);
		system.addSpawnPoint(patrolSpawn);
		for (int i = 0; i < 5; i++)
			patrolSpawn.spawnFleet();

		SectorEntityToken token = system.createToken(0, 15000);
		HegemonySDFSpawnPoint sdfSpawn = new HegemonySDFSpawnPoint(sector, system, 15, 2, token, hegemonyStation);
		system.addSpawnPoint(sdfSpawn);
		spawnSDF(sector, system, hegemonyStation);

		TriTachyonSpawnPoint triSpawn = new TriTachyonSpawnPoint(sector, system, 3, 15, system.getEntityByName("Corvus V"), hegemonyStation);
		system.addSpawnPoint(triSpawn);
		for (int i = 0; i < 6; i++)
			triSpawn.spawnFleet();

		IndependentSpawnPoint independentSpawn = new IndependentSpawnPoint(sector, system, 3, 15, hegemonyStation);
		system.addSpawnPoint(independentSpawn);
		for (int i = 0; i < 8; i++)
			independentSpawn.spawnFleet();

		token = system.createToken(-15000, 15000);
		system.addSpawnPoint(new HegemonyConvoySpawnPoint(sector, system, 13, 1, token, hegemonyStation));
		system.addSpawnPoint(new PiratePlunderFleetSpawnPoint(sector, system, 17, 1, token, pirateStation));
		system.addSpawnPoint(new TriTachyonSupplyFleetSpawnPoint(sector, system, 15, 1, token, tritachyonStation));

	}

	private void spawnSDF(SectorAPI sector, StarSystemAPI system, SectorEntityToken location) {
		CampaignFleetAPI fleet = sector.createFleet("hegemony", "systemDefense");
		system.spawnFleet(location, -500, 200, fleet);

		fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, location, 1000000);
		fleet.setPreferredResupplyLocation(location);
		//		
		// fleet = sector.createFleet("tritachyon", "securityDetachment");
		// system.spawnFleet(location, -200, 200, fleet);
		// fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, location,
		// 1000000);

	}

	private void initOrbitalStationCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();

		List weaponIds = sector.getAllWeaponIds();
		// for (int i = 0; i < 10; i++) {
		// String weaponId = (String) weaponIds.get((int) (weaponIds.size() *
		// Math.random()));
		// int quantity = (int)(Math.random() * 7 + 3);
		// cargo.addWeapons(weaponId, quantity);
		// }

		// focused on weapons that are hard to get from looting
		// and present an upgrade path for the initial ships
		// cargo.addWeapons("heavymg", 5);
		
		//strike
		cargo.addWeapons("bomb", 25);
		cargo.addWeapons("reaper", 12);
		
		//Support
		cargo.addWeapons("lightac", 25);
		cargo.addWeapons("lightmg", 40);
		cargo.addWeapons("annihilator", 10);
		cargo.addWeapons("taclaser", 10);

		cargo.addWeapons("harpoon_single", 12); //medium

		//assault
		cargo.addWeapons("lightmortar", 40);
		cargo.addWeapons("miningblaster", 1); //medium
		
		//PD
		cargo.addWeapons("swarmer", 5);
		cargo.addWeapons("mininglaser", 25);
		cargo.addWeapons("pdlaser", 25);
		
		cargo.addWeapons("flak", 5); //medium
		cargo.addWeapons("shredder", 5); //medium
		cargo.addWeapons("annihilatorpod", 1); //medium
		cargo.addWeapons("pilum", 2); //medium
		cargo.addWeapons("mark9", 2); //large
		
		
//		cargo.addCrew(CrewXPLevel.ELITE, 25);
		// cargo.addCrew(CrewXPLevel.VETERAN, 200);
		cargo.addCrew(CrewXPLevel.REGULAR, 30);
		cargo.addCrew(CrewXPLevel.GREEN, 500);
		cargo.addMarines(100);
		cargo.addSupplies(630);
		cargo.addFuel(1000);

	
		cargo.addMothballedShip(FleetMemberType.SHIP, "vigilance_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "hound_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "lasher_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "brawler_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "dram_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "enforcer_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "hammerhead_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "sunder_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "valkyrie_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "falcon_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "eagle_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "atlas_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "onslaught_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "mule_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "mule_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		
		

	}

	private void initTriTachyonHQCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();

		List weaponIds = sector.getAllWeaponIds();
		cargo.addCrew(CrewXPLevel.ELITE, 10);
		cargo.addCrew(CrewXPLevel.VETERAN, 10);
		cargo.addCrew(CrewXPLevel.REGULAR, 50);
		// cargo.addCrew(CrewXPLevel.GREEN, 1500);
		cargo.addMarines(75);
		cargo.addSupplies(145);
		cargo.addFuel(300);
		
		//strike
		cargo.addWeapons("amblaster", 5);
		cargo.addWeapons("atropos_single", 5);
		
		//support
		cargo.addWeapons("taclaser", 15);
		cargo.addWeapons("railgun", 5);
		cargo.addWeapons("harpoon", 5);
		
		cargo.addWeapons("pulselaser", 6);
		cargo.addWeapons("gravitonbeam", 10);
		cargo.addWeapons("heavyburst", 6);
		cargo.addWeapons("heavyblaster", 3);
		cargo.addWeapons("phasebeam", 3);
		cargo.addWeapons("harpoonpod", 5);
		cargo.addWeapons("sabotpod", 5);
		
		//PD
		cargo.addWeapons("vulcan", 6);
		cargo.addWeapons("lrpdlaser", 6);
		cargo.addWeapons("pdburst", 6);
		cargo.addWeapons("swarmer", 6);
		
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "hyperion_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "hyperion_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "medusa_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "medusa_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "apogee_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "omen_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "omen_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "doom_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "doom_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "afflictor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "afflictor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "shade_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "shade_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "odyssey_Hull", null);
	}

	private void initPirateBaseCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();

		List weaponIds = sector.getAllWeaponIds();
		// cargo.addCrew(CrewXPLevel.ELITE, 25);
		cargo.addCrew(CrewXPLevel.VETERAN, 20);
		cargo.addCrew(CrewXPLevel.REGULAR, 50);
		cargo.addCrew(CrewXPLevel.GREEN, 100);
		cargo.addMarines(50);
		cargo.addSupplies(100);
		cargo.addFuel(50);
		
		//strike
		cargo.addWeapons("bomb", 15);
		cargo.addWeapons("typhoon", 5);
		
		//PD
		cargo.addWeapons("clusterbomb", 10);
		cargo.addWeapons("flak", 10);
		cargo.addWeapons("irpulse", 10);
		cargo.addWeapons("swarmer", 10);

		//support
		cargo.addWeapons("fragbomb", 10);
		cargo.addWeapons("heatseeker", 5);
		cargo.addWeapons("harpoon", 5);
		
		cargo.addWeapons("sabot", 5);
		cargo.addWeapons("annihilator", 5);
		cargo.addWeapons("lightdualmg", 10);
		cargo.addWeapons("lightdualac", 10);
		cargo.addWeapons("lightneedler", 10);
		cargo.addWeapons("heavymg", 10);
		cargo.addWeapons("heavymauler", 5);
		cargo.addWeapons("salamanderpod", 5);
		
		cargo.addWeapons("hveldriver", 5);

		//assault
		cargo.addWeapons("lightag", 10);
		cargo.addWeapons("chaingun", 5);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "wolf_Hull", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "gladius_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "gemini_Hull", null);


		cargo.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "conquest_Hull", null);
	}
}
