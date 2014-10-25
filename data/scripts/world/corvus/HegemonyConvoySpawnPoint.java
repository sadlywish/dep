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
public class HegemonyConvoySpawnPoint extends BaseSpawnPoint {

	private final SectorEntityToken convoyDestination;

	public HegemonyConvoySpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken anchor,
							SectorEntityToken convoyDestination) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.convoyDestination = convoyDestination;
	}

	private int convoyNumber = 0;
	
	@Override
	protected CampaignFleetAPI spawnFleet() {
		String type = null;
		float r = (float) Math.random();
		if (r > .9f) {
			type = "fuelConvoy";
		} else if (r > 0.6f) {
			type = "personnelConvoy";
		} else {
			type = "supplyConvoy";
		}
		
		CampaignFleetAPI fleet = getSector().createFleet("hegemony", type);
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		
		if (type.equals("supplyConvoy")) {
			convoyNumber++;
			CargoAPI cargo = fleet.getCargo();
//			float supplyQuantity = daysInterval * (convoyDestination.getCargo().getMarines() + convoyDestination.getCargo().getTotalCrew()) /100;
//			cargo.addSupplies(supplyQuantity);
//			Global.getSectorAPI().addMessage("The High Hegemon has authorized the delivery of " + supplyQuantity + " units of supplies");
			if (convoyNumber == 1) {
				cargo.addWeapons("annihilatorpod", 10);
				cargo.addWeapons("harpoon", 5); 
				cargo.addWeapons("heavymg", 5); 
				cargo.addWeapons("arbalest", 35); 
				cargo.addWeapons("heavyac", 10); 
//				cargo.addWeapons("dualflak", 5);
//				cargo.addWeapons("heavyblaster", 5);
//				cargo.addWeapons("lightdualac", 5);
//				cargo.addWeapons("lightag", 5);
//				cargo.addWeapons("lightneedler", 5);
//				cargo.addWeapons("heavymauler", 5);
//				cargo.addWeapons("gravitonbeam", 5);
				//cargo.addMothballedShip(type, variantOrWingId, optionalName)
			} else if (convoyNumber == 2) {
				cargo.addWeapons("hellbore", 5);
				cargo.addWeapons("cyclone", 2); 
				cargo.addWeapons("pilum", 5);
//				cargo.addWeapons("taclaser", 5);
//				cargo.addWeapons("pulselaser", 5);
//				cargo.addWeapons("heavyburst", 5);
//				cargo.addWeapons("hil", 3);
//				cargo.addWeapons("hveldriver", 5);
//				cargo.addWeapons("reaper", 5);
				//cargo.addMothballedShip(type, variantOrWingId, optionalName)
			} else {
				addRandomWeapons(cargo, (int)(Math.random() * 4f + 2f));
				
				if ((float) Math.random() > 0.75f) cargo.addWeapons("lightag", 10);
				if ((float) Math.random() > 0.75f) cargo.addWeapons("lightdualac", 5);
				if ((float) Math.random() > 0.75f) cargo.addWeapons("lightac", 5);
				if ((float) Math.random() > 0.75f) cargo.addWeapons("lightmg", 5);
				if ((float) Math.random() > 0.75f) cargo.addWeapons("vulcan", 5);
				if ((float) Math.random() > 0.75f) cargo.addWeapons("bomb", 5);
				if ((float) Math.random() > 0.75f) cargo.addWeapons("lightdualmg", 5);
				
			}
		}
		
		addRandomShips(fleet.getCargo(), (int) (Math.random() * 4f));
		
		Script script = null;
		if (type.equals("supplyConvoy")) {
			script = createArrivedScript();
			Global.getSector().addMessage("一支霸主补给舰队已 已进入星系 并正准备进入霸主轨道空间站");
		}
		
		fleet.addAssignment(FleetAssignment.DELIVER_RESOURCES, convoyDestination, 1000, script);
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
		
		return fleet;
	}
	
	private Script createArrivedScript() {
		return new Script() {
			public void run() {
				Global.getSector().addMessage("一支霸主补给舰队 为轨道空间站 带来了新设备");
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

	private static String [] ships = { 
									"wolf_Hull",
									"hound_Hull",
									"shuttle_Hull",
									"lasher_Hull",
									"brawler_Hull",
									"vigilance_Hull",
									"dram_Hull",
									"tarsus_Hull",
									"hammerhead_Hull",
									"sunder_Hull",
									"condor_Hull",
									"enforcer_Hull",
									"buffalo_Hull",
									"gemini_Hull",
									"buffalo2_Hull",
									"falcon_Hull",
									"eagle_Hull",
									"venture_Hull",
									"dominator_Hull",
									"onslaught_Hull",
									"atlas_Hull",
									"conquest_Hull",
									};

	private static String [] wings = { 
									"broadsword_wing",
									"gladius_wing",
									"talon_wing",
									"dagger_wing",
									"piranha_wing",
									"mining_drone_wing"
									};
	private static String [] noflash = { 
									"test001",
									"test002",
									"Nuclear",
									"XL_MAC",
									};
}



