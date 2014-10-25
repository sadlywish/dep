package data.scripts.world;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.StarSystemAPI;

import java.lang.Thread;
import java.util.List;


public class Fairy_BlackmarketSpawnPoint extends BaseSpawnPoint 
{
	public Fairy_BlackmarketSpawnPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}	
	Fairyintt i = new Fairyintt();
	CampaignClockAPI clock = getSector().getClock();
	private long lastSpawnTime = Long.MIN_VALUE;

	public CampaignFleetAPI spawnFleet() 
	{
	if(i.geti() == 1){
		CheckPoint cp = new CheckPoint(getSector());
		cp.run();
		i.seti(1);
	}
	StarSystemAPI system = getSector().getStarSystem("Corvus");
	SectorEntityToken station = system.getEntityByName("黑市");
	SectorEntityToken stationP = system.getEntityByName("秘密基地");
	CargoAPI cargo = station.getCargo();
	CargoAPI cargoP = stationP.getCargo();
    SectorEntityToken playerfleet = system.getEntityByName("Fleet");
    CargoAPI playercargo = playerfleet.getCargo();
	noflashWeapons(cargoP);
	if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "cout", 1)){
		clearships(cargoP);
		cargo.clear();
		Global.getSectorAPI().addMessage("来吧我的朋友，来看看有没有你喜欢的武器。");
		lastSpawnTime = clock.getTimestamp();
		addRandomWeapons(cargo, 80);
	}
	if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "ship", 1)){
		cargo.clear();
		clearships(cargoP);
		Global.getSectorAPI().addMessage("无论你在舰船机库里看到了什么，这都是天意。");
		lastSpawnTime = clock.getTimestamp();
		if( Math.random() >= 0.4)
		{
		addRandomShips(cargo, 1);
		}
		else
		{
		addRandomWings(cargo, 3);
		}
	}

	if(clock.getElapsedDaysSince(lastSpawnTime) >= 1)
	{
		cargo.clear();
		clearships(cargoP);
		cargo.addItems(CargoAPI.CargoItemType.RESOURCES,"cout",1);
		cargo.addItems(CargoAPI.CargoItemType.RESOURCES,"ship",1);
	}

  return null;

	}
private void addRandomWeapons(CargoAPI cargo, int count) {
	List weaponIds = getSector().getAllWeaponIds();
	for (int i = 0; i < count; i++) {
		String weaponId = (String) weaponIds.get((int) (weaponIds.size() * Math.random()));
		cargo.addWeapons(weaponId, 1);
	}
	noflashWeapons(cargo);
    }

private void clearships(CargoAPI cargo) {
	FleetDataAPI ship = cargo.getMothballedShips();
	List ships = ship.getMembersListCopy();
	for(int a = 0;a<ships.size();a++)
	{
		FleetMemberAPI oneship = (FleetMemberAPI)ships.get(a);
		ship.removeFleetMember(oneship);
	}
}
private void noflashWeapons(CargoAPI cargo) {
	int count = 0;
	for(int i=0;i<noflash.length;i++)
	{
		while (cargo.removeItems(CargoAPI.CargoItemType.WEAPONS, noflash[i], 1)){
			count++;
		}
	}
	if(count > 0)
		{
			addRandomWeapons(cargo,count);
			count=0;
		}
}
private void addRandomWings(CargoAPI cargo, int count) {
		for (int i = 0; i < count; i++) {
			String wing = (String) wings[(int) (wings.length * Math.random())];
			cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, wing, null);
		}
	}

private void addRandomShips(CargoAPI cargo, int count) {
		for (int i = 0; i < count; i++) {
			String ship = (String) ships[(int) (ships.length * Math.random())];
			cargo.addMothballedShip(FleetMemberType.SHIP, ship, null);
		}
	}

private static String [] ships = { 

									"Fairy_cromass_hull",
									"yd_Angel_hull",
									"yd_Nightingale_hull",
									"yd_frontier_hull",
									"yd_worldDestroyer_hull",
									"yd_apogee2_hull",
									"yd_cracks_hull",
									"yd_Drillhead_hull",
									"yd_heavyhammer_hull",
									"yd_Eternal_hull",
									"wolf_Hull",
									"hound_Hull",
									"hyperion_Hull",
									"shuttle_Hull",
									"lasher_Hull",
									"omen_Hull",
									"tempest_Hull",
									"brawler_Hull",
									"vigilance_Hull",
									"dram_Hull",
									"hammerhead_Hull",
									"sunder_Hull",
									"condor_Hull",
									"enforcer_Hull",
									"buffalo_Hull",
									"gemini_Hull",
									"tarsus_Hull",
									"valkyrie_Hull",
									"buffalo2_Hull",
									"medusa_Hull",
									"falcon_Hull",
									"eagle_Hull",
									"venture_Hull",
									"apogee_Hull",
									"dominator_Hull",
									"aurora_Hull",
									"onslaught_Hull",
									"astral_Hull",
									"atlas_Hull",
									"conquest_Hull",
									"paragon_Hull",
									"odyssey_Hull",
};
	private static String [] noflash = { 
									"test001",
									"Nuclear",
									"XL_MAC",
									};
	private static String [] wings = { 
									"broadsword_wing",
									"xyphos_wing",
									"gladius_wing",
									"warthog_wing",
									"thunder_wing",
									"talon_wing",
									"wasp_wing",
									"dagger_wing",
									"piranha_wing",
									"trident_wing",
									"longbow_wing",
									"mining_drone_wing",
									};

}