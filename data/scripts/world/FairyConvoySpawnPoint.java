package data.scripts.world;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.StarSystemAPI;

import data.scripts.world.shiplevel.ShipList;


@SuppressWarnings("unchecked")
public class FairyConvoySpawnPoint extends BaseSpawnPoint {
	public final FairyBUSS buss;
	public final String bu[];
	public final String ss[];
	public final String bu_b[][];

	public FairyConvoySpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken anchor,
							FairyBUSS buss) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.buss = buss;
		bu = buss.getnormallist();
		ss = buss.getstationlist();
		bu_b = buss.getvaluelist();
	}
	

	@Override
	protected CampaignFleetAPI spawnFleet() {
		Script script = null;
		CampaignFleetAPI fleet = null;
		
		StarSystemAPI system = getSector().getStarSystem("Corvus");
		SectorEntityToken station = system.getEntityByName("菲雅利帝国驻Corvus星系临时办事处");
		if (station == null) {
		fleet = getSector().createFleet("Fairy", "FristConvoy");
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		CargoAPI cargo = fleet.getCargo();
		script = createArrivedScript();
		Global.getSectorAPI().addMessage("警告：检测到未知超空间扰动。");
		Global.getSectorAPI().addMessage("全星系广播：一支舰队进入了Corvus星系，无法识别该舰队身份信息，请各位舰长不要靠近。");
		SectorEntityToken convoyDestination = system.getEntityByName("Corvus Unknow");
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION, convoyDestination, 1000, script);
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, convoyDestination, 1000);

		} else {
		fleet = getSector().createFleet("Fairy", "supplyConvoy");
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);

		script = createArrivedScript();
		Global.getSectorAPI().addMessage("一支帝国舰队的后勤队已经到达本星系，正在向其设立的空间站进发。");
		fleet.addAssignment(FleetAssignment.DELIVER_RESOURCES, station, 1000, script);
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
		}
		
		return fleet;
	}
	
	private Script createArrivedScript() {
		return new Script() {
			public void run() {
				StarSystemAPI system = getSector().getStarSystem("Corvus");
				SectorEntityToken station = system.getEntityByName("菲雅利帝国驻Corvus星系临时办事处");
				if (station == null) {
					SectorEntityToken CorvusI = system.getEntityByName("Corvus Unknow");
					SectorEntityToken CorvusIIIA = system.getEntityByName("Corvus IIIA");
					station = system.addOrbitalStation(CorvusI, 45, 300, 50, "菲雅利帝国驻Corvus星系临时办事处", "Fairy");
					Global.getSectorAPI().addMessage("全星系广播：未知舰队在Corvus星系外围一颗未命名行星上展开了空间站。");
					Global.getSectorAPI().addMessage("未知舰队：我们是来自遥远银河的菲雅利帝国游弋舰队");
					Global.getSectorAPI().addMessage("菲雅利帝国：我们由于偶然来到了这里，并且决定暂时停驻在这个星系。");
					Global.getSectorAPI().addMessage("菲雅利帝国：我们欢迎任何人和我们进行贸易，但我们绝不放过任何试图攻击我们的人");
					Global.getSectorAPI().addMessage("各个自由佣兵团对新势力的加入持观望态度。");
					Global.getSectorAPI().addMessage("霸主和速子科技对不速之客的突然造访感到不屑");
					SectorEntityToken token = system.createToken(0, 15000);
					missionSpawnPoint1 MS = new missionSpawnPoint1(getSector(), system, 0, 50, token);//任务进行监视点
					system.addSpawnPoint(MS);
					MS.spawnFleet();
					system.addSpawnPoint(new FairyMissionSpawnPoint(getSector(), system, 0, 50, token));//任务发起监视点
					SectorEntityToken Pirate1 = system.createToken(-15000,15000);
					SectorEntityToken Pirate2 = system.createToken(-15000,7500);
					SectorEntityToken Pirate3 = system.createToken(-15000,0);
					SectorEntityToken Pirate4 = system.createToken(-15000,-7500);
					SectorEntityToken Pirate5 = system.createToken(-15000,-15000);
					SectorEntityToken station1 = system.getEntityByName("菲雅利帝国驻Corvus星系临时办事处");
					FairyDefenseSpawnPoint FD = new  FairyDefenseSpawnPoint (getSector(), system, 2, 3, station1);
					system.addSpawnPoint(FD);
					FD.spawnFleet();
					FairyFreeSpawnPoint raidSpawn = new FairyFreeSpawnPoint(getSector(), system, 2, 12, station1);
					system.addSpawnPoint(raidSpawn);
					for (int i = 0; i < 5; i++) raidSpawn.spawnFleet();
					CargoAPI cargo = station.getCargo();
					for (int a = 0; a< bu.length; a++)
					{
						if (buss.isneed(0, bu[a]))
						{
							cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu[a], buss.getReserve(0, bu[a])*1.1f);
						}
					}
					cargo.addFuel(500);
					cargo.addMarines(175);
					cargo.addSupplies(200);
					addCargo(cargo, 40);
					ShipList.addship(station1.getCargo(), 0, 0);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Dawn_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Dawn_B_Hull", null);	
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_DawnII_Hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Aries_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Fang_Hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Aries_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Aries_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Private_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Private_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_jumbo_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Twilight_Hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Griffin_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_FYS1000_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_FYS10000_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Broadblade_hull", null);
//					cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Enchantress_hull", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "trident_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Stars_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Vespa_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Vespa_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Vespa_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Vespa_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "trident_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "xyphos_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "xyphos_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "xyphos_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "dagger_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "dagger_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "dagger_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "dagger_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Twinbee_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_Twinbee_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_larvae_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_larvae_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_larvae_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_RavenS_wing", null);
//					cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "Fairy_RavenS_wing", null);
					
				} else {
					Global.getSectorAPI().addMessage("帝国补给舰队已经驶离空间站，返回菲雅利帝国本土进行休整。");
					CargoAPI cargo = station.getCargo();
					cargo.addFuel(300);
					addCargo(cargo, 10);
//					addRandomShips(cargo, 4);
//					addRandomWings(cargo, 6);
					float supplies = cargo.getSupplies();
					if (supplies > 14000) {
					supplies = supplies - 14000;
					cargo.removeSupplies(supplies);}
					supplies = cargo.getFuel();
					if (supplies > 1000) {
					supplies = supplies - 1000;
					cargo.removeFuel(supplies);}
					int quantity = cargo.getCrew(CrewXPLevel.REGULAR);
					if (quantity > 1000) {
					quantity = quantity - 1000;
					cargo.removeCrew(CrewXPLevel.REGULAR, quantity);}
					quantity = cargo.getCrew(CrewXPLevel.VETERAN);
					if (quantity > 500) {
					quantity = quantity - 500;
					cargo.removeCrew(CrewXPLevel.VETERAN, quantity);}
					quantity = cargo.getCrew(CrewXPLevel.GREEN);
					if (quantity > 2500) {
					quantity = quantity - 2500;
					cargo.removeCrew(CrewXPLevel.GREEN, quantity);}
					quantity = cargo.getCrew(CrewXPLevel.ELITE);
					if (quantity > 500) {
					quantity = quantity - 500;
					cargo.removeCrew(CrewXPLevel.ELITE, quantity);}
					quantity = cargo.getMarines();
					if (quantity > 2000) {
					quantity = quantity - 2000;
					cargo.removeMarines(quantity);}
					List weaponIds = getSector().getAllWeaponIds();
					for (int i = 0; i < weaponIds.size(); i++) {
					String weaponId = (String) weaponIds.get((int) i);
					quantity = cargo.getNumWeapons(weaponId);
					if (quantity > 120) {
					quantity = quantity - 120;
					cargo.removeWeapons(weaponId, quantity);}
					}
				}
			}
		};
	}
	
	private void addCargo(CargoAPI cargo, int count) {
	addEmpireWeapons(cargo,count);
	cargo.addSupplies(800);
	cargo.addCrew(CrewXPLevel.REGULAR, 200);
	cargo.addCrew(CrewXPLevel.GREEN, 750);
    cargo.addCrew(CrewXPLevel.ELITE, 25);
    cargo.addCrew(CrewXPLevel.VETERAN, 150);
	}
	
	private void addRandomShips(CargoAPI cargo, int count) {
		for (int i = 0; i < count; i++) {
			String ship = (String) ships[(int) (ships.length * Math.random())];
			cargo.addMothballedShip(FleetMemberType.SHIP, ship, null);
		}
	}
private void addRandomWings(CargoAPI cargo, int count) {
		for (int i = 0; i < count; i++) {
			String wing = (String) wings[(int) (wings.length * Math.random())];
			cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, wing, null);
		}
	}
private void addEmpireWeapons(CargoAPI cargo, int count) {
	for (int i = 0; i < count; i++) {
		String weaponId = (String) Weapons[(int) (Weapons.length * Math.random())];
		cargo.addWeapons(weaponId, 2);
	}
    }

private void addRandomWeapons(CargoAPI cargo, int count) {
	List weaponIds = getSector().getAllWeaponIds();
	for (int i = 0; i < count; i++) {
		String weaponId = (String) weaponIds.get((int) (weaponIds.size() * Math.random()));
		cargo.addWeapons(weaponId, 2);
	}
	noflashWeapons(cargo);
    }
private void noflashWeapons(CargoAPI cargo) {
	int count = 0;
	for(int i=0;i<noflash.length;i++)
	{
		while (cargo.removeItems(CargoAPI.CargoItemType.WEAPONS, noflash[i], 2)){
			count++;
		}
	}
	if(count > 0)
		{
			addRandomWeapons(cargo,count);
			count=0;
		}
}


	private static String [] ships = { 
									"Fairy_Shine_Hull",
									"Fairy_Shine_Hull",
									"Fairy_Dawn_Hull",
									"Fairy_Dawn_Hull",
									"Fairy_Dawn_Hull",
									"Fairy_Dawn_B_Hull",
									"Fairy_Dawn_B_Hull",
									"Fairy_Dawn_B_Hull",	
									"Fairy_Fang_Hull",
									"Fairy_Mammoth_Hull",
									"HGN_Lord_Hull",
									"HGN_Lord_Hull",
									"Fairy_Advance_Hull",
									"Fairy_Griffin_hull",
									"Fairy_Griffin_hull",
									"Fairy_Griffin_hull",
									"Fairy_Griffin_hull",
									"Fairy_Twilight_Hull",
									"Fairy_Twilight_Hull",
									"Fairy_Twilight_Hull",
									"Fairy_Twilight_Hull",
									"Fairy_DawnII_Hull",
									"Fairy_DawnII_Hull",
									"Fairy_DawnII_Hull",
									"Fairy_DawnII_Hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Aries_hull",
									"Fairy_Private_hull",
									"Fairy_Private_hull",
									"Fairy_Private_hull",
									"Fairy_Private_hull",
									"Fairy_Private_hull",
									"Fairy_Private_hull",
									"Fairy_FYS1000_hull",
									"Fairy_FYS1000_hull",
									"Fairy_FYS10000_hull",
									"Fairy_FYS10000_hull",
									"Fairy_jumbo_hull",
									"Fairy_jumbo_hull",
									"Fairy_jumbo_hull",
									"Fairy_jumbo_hull",
									"Fairy_Broadblade_hull",
									"Fairy_Broadblade_hull",
									"Fairy_Broadblade_hull",
									"Fairy_Broadblade_hull",
									"Fairy_itinerant_Hull",
									"Fairy_matrix_Hull",
									"Fairy_matrix_Hull",
									};

	private static String [] wings = { 
									"Fairy_Stars_wing",
									"Fairy_RavenS_wing",
									"Fairy_RavenS_wing",
									"Fairy_larvae_wing",
									"Fairy_larvae_wing",
									"Fairy_larvae_wing",
									"Fairy_Vespa_wing",
									"Fairy_Vespa_wing",
									"Fairy_Twinbee_wing",
									"Fairy_Twinbee_wing",
									"Fairy_Twinbee_wing",
									"Fairy_Twinbee_wing",
									"piranha_wing",
									"piranha_wing",
									};
	private static String [] noflash = { 
									"test001",
									"test002",
									"Nuclear",
									"XL_MAC",
									};
	private static String [] Weapons = { 
									"4800MM",
									"MAC",
									"3500MM",
									"1400HE",
									"1400MM",
									"1400AP",
									"610AP",
									"MAC_S",
									"425HE",
									"381HE",
									"305MM",
									"254MM",
									"203MM",
									"152MM",
									"175MM",
									"127MM",
									"75MM",
									"57MM",
									"75AP",
									"57AP",
									"20MM",
									"12MM",
									"Firefly_VLS",
									"Firefly_VLSS",
									"Universal_VLS",
									"Universal_VLSS",
									"Heavy_VLS",
									"Heavy_VLSS",
									"Firefly",
									"Firefly_S",
									"Universal",
									"Universal_S",
									"Heavy",
									"Heavy_S",
									"baihe",
									"baofeng",
									"bianta",
									"Firefly_Pod",
									"Firefly_PodS",
									"harpoon_VLS",
									"harpoon_VLSS",
									"baiheX2S",
									"biantaX2S",
									"baofengX2S",
									"baiheX2",
									"biantaX2",
									"baofengX2",
									"Photon",
									"PhotonS",
									"Lance",
									"pulse",
									"Ion",
									"IonS",
									"Fluctuation",
									"heavyIon",
									"Ionpulse",
									"IonpulseS",
									"FluctuationX2",
									"heavyIonX2",
									"Plasma_torpedoes",
									"Plasma_torpedoesS",
									"Particle",
									"ParticleS",
									};
									
}



