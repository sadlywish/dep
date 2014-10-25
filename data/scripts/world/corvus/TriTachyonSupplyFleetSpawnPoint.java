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
public class TriTachyonSupplyFleetSpawnPoint extends BaseSpawnPoint {

	private final SectorEntityToken convoyDestination;

	public TriTachyonSupplyFleetSpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken anchor,
							SectorEntityToken convoyDestination) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.convoyDestination = convoyDestination;
	}

	private int convoyNumber = 0;
	
	@Override
	protected CampaignFleetAPI spawnFleet() {
		CampaignFleetAPI fleet = getSector().createFleet("tritachyon", "supplyfleet");
		getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
		
		CargoAPI cargo = fleet.getCargo();
//		float supplyQuantity = daysInterval * (convoyDestination.getCargo().getMarines() + convoyDestination.getCargo().getTotalCrew()) /100;
//		cargo.addSupplies(supplyQuantity);
//		Global.getSectorAPI().addMessage("The Tri-Tachyon base requests delivery of " +  supplyQuantity + " units of supplies");
		if (convoyNumber == 1) {
			cargo.addWeapons("atropos", 5); //?
			cargo.addWeapons("typhoon", 5); //?
			cargo.addWeapons("ioncannon", 6); //?
			cargo.addWeapons("heavyneedler", 3);//?
			cargo.addWeapons("sabot", 5); //?
			cargo.addWeapons("dualflak", 6); //?
			cargo.addWeapons("guardian", 3); //?
			cargo.addWeapons("plasma", 3); //?
			//assault
			cargo.addWeapons("chaingun", 3); //?
		} else if(convoyNumber == 2){
			cargo.addWeapons("sabotpod", 5); //??
			cargo.addWeapons("pilum", 7); //??
			
			cargo.addWeapons("autopulse", 3); //??
			cargo.addWeapons("plasma", 3); //??
			cargo.addWeapons("hil", 3); //??
			
			cargo.addWeapons("hurricane", 3); //??

			cargo.addWeapons("mjolnir", 3); //??
		} else {
			addRandomWeapons(cargo, 10);
			
			if ((float) Math.random() > 0.75f) cargo.addWeapons("taclaser", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("lrpdlaser", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("pdlaser", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("pdburst", 5);
			if ((float) Math.random() > 0.75f) cargo.addWeapons("irpulse", 5);
		}
	
		addRandomShips(fleet.getCargo(), (int) (Math.random() * 2));
		
		Script dockScript = null;
		dockScript = createDockedScript();
		Script leaveScript = createLeaveScript();
		Global.getSector().addMessage("一支速子科技补给舰队已经进入本星系");
		
		fleet.addAssignment(FleetAssignment.DELIVER_RESOURCES, convoyDestination, 1000, dockScript);
		fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000, leaveScript);
		
		convoyNumber++;
		return fleet;
	}
	
	private Script createDockedScript() {
		return new Script() {
			public void run() {
				Global.getSector().addMessage("一支速子科技补给舰队已经停泊在空间站");
			}
		};
	}
	
	private Script createLeaveScript() {
		return new Script() {
			public void run() {
				Global.getSector().addMessage("一支速子科技补给舰队 已进入超空间");
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
									"tempest_Hull",
									"buffalo_Hull",
									"medusa_Hull",
									"aurora_Hull",
									"odyssey_Hull",
									"paragon_Hull",
									};

	private static String [] wings = { 
									"xyphos_wing",
									"thunder_wing",
									"wasp_wing",
									};
	private static String [] noflash = { 
									"test001",
									"test002",
									"Nuclear",
									"XL_MAC",
									};

									
}



