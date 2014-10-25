package data.scripts.world.corvus;

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

import data.scripts.world.BaseSpawnPoint;

@SuppressWarnings("unchecked")
public class PiratePlunderFleetSpawnPoint extends BaseSpawnPoint {

	private final SectorEntityToken convoyDestination;

	public PiratePlunderFleetSpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken anchor,
							SectorEntityToken convoyDestination) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.convoyDestination = convoyDestination;
	}

	private int convoyNumber = 0;
	
	@Override
	protected CampaignFleetAPI spawnFleet() {
		CampaignFleetAPI fleet = getSector().createFleet("fow", "plunderFleet");
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		
		CargoAPI cargo = fleet.getCargo();
		if (convoyNumber == 1) {
			cargo.addWeapons("salamanderpod", 5);
			cargo.addWeapons("sabotpod", 5); 
			cargo.addWeapons("annihilatorpod", 5); 
			cargo.addWeapons("heavyneedler", 5);
			cargo.addWeapons("heavyac", 10);
			cargo.addWeapons("hephag", 10);
			cargo.addWeapons("mark9", 10);
		} else {
			addRandomWeapons(cargo, 4);
			
			if ((float) Math.random() > 0.75f) cargo.addWeapons("harpoonpod", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("salamanderpod", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("annihilatorpod", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("sabotpod", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("pilum", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("mark9", 1);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("typhoon", 1);
		}
	
		addRandomShips(fleet.getCargo(), (int) (Math.random() * 4f));
		
		Script script = null;
		script = createArrivedScript();
		Global.getSector().addMessage("一支海盗劫掠者舰队 已经从其他星系满载而归");
		
		fleet.addAssignment(FleetAssignment.DELIVER_RESOURCES, convoyDestination, 1000);
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, convoyDestination, 1000, script);
		
		convoyNumber++;
		return fleet;
	}
	
	private Script createArrivedScript() {
		return new Script() {
			public void run() {
				Global.getSector().addMessage("一支海盗劫掠者舰队 把赃物运回基地后消失了");
			}
		};
	}
	
	private void addRandomWeapons(CargoAPI cargo, int count) {
	List weaponIds = getSector().getAllWeaponIds();
	for (int i = 0; i < count; i++) {
		String weaponId = (String) weaponIds.get((int) (weaponIds.size() * Math.random()));
		cargo.addWeapons(weaponId, 1);
	}
	noflashWeapons(cargo);
    }
	
	private void addRandomShips(CargoAPI cargo, int count) {
		List weaponIds = getSector().getAllWeaponIds();
		for (int i = 0; i < count; i++) {
			if ((float) Math.random() > 0.4f) {
				String wing = (String) wings[(int) (wings.length * Math.random())];
				cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, wing, null);
			} else {
				String ship = (String) ships[(int) (ships.length * Math.random())];
				cargo.addMothballedShip(FleetMemberType.SHIP, ship, null);
			}
		}
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
	private static String [] noflash = { 
									"test001",
									"test002",
									"Nuclear",
									"XL_MAC",
									};

	private static String [] ships = { 
									"wolf_Hull",
									"hound_Hull",
									"lasher_Hull",
									"vigilance_Hull",
									"dram_Hull",
									"tarsus_Hull",
									"hammerhead_Hull",
									"condor_Hull",
									"enforcer_Hull",
									"buffalo_Hull",
									"buffalo2_Hull",
									"venture_Hull",
									"dominator_Hull",
									"conquest_Hull",
									};

	private static String [] wings = { 
									"broadsword_wing",
									"gladius_wing",
									"warthog_wing",
									"thunder_wing",
									"talon_wing",
									"piranha_wing",
									"mining_drone_wing"
									};
									
}



