package data.scripts.world.shiplevel;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;

public class ShipList {
	private static int[] newShipnum= {
		4,
		2,
		3,
		2,
		3,
		2,
		2,
		2,
		1,
		1		
	};
	private static int[] newWingnum= {
		8,
		6,
		2
	};
	private static int[] fuelnum= {
		100,
		150,
		400,
		550,
		1100,
		1300,
		2000,
		2400,
		3500,
		5000
	};
	private static String[] hum1= {
		"lasher_Hull",
		"hound_Hull"
	};
	private static String[] hum2= {
		"lasher_Hull",
		"enforcer_Hull"
	};
	private static String[] hum3= {
		"enforcer_Hull",
		"mule_Hull"
	};
	private static String[] hum4= {
		"enforcer_Hull",
		"yd_heavyhammer_Hull"
	};
	private static String[] hum5= {
		"venture_Hull",
		"dominator_Hull"
	};
	private static String[] hum6= {
		"venture_Hull",
		"dominator_Hull",
		"yd_Angel_Hull"
	};
	private static String[] hum7= {
		"onslaught_Hull"
	};
	private static String[] hum8= {
		"onslaught_Hull",
		"yd_Eternal_Hull"
	};
	private static String[] hum9= {
		"yd_Eternal_Hull"
	};
	private static String[] hum10= {
		"hum_worldDestroyer_Hull"
	};
	
	
	private static String[] inf1= {
		"vigilance_Hull",
		"brawler_Hull"
	};	
	private static String[] inf2= {
		"vigilance_Hull",
		"yd_cracks_Hull",
		"monitor_Hull"
	};	
	private static String[] inf3= {
		"hammerhead_Hull"
	};	
	private static String[] inf4= {
		"hammerhead_Hull",
		"sunder_Hull"
	};	
	private static String[] inf5= {
		"falcon_Hull"
	};	
	private static String[] inf6= {
		"falcon_Hull",
		"eagle_Hull"
	};
	private static String[] inf7= {
		"yd_Nightingale_Hull",
		"conquest_Hull"
	};
	private static String[] inf8= {
		"conquest_Hull",
		"HGN_Lord_Hull"
	};
	private static String[] inf9= {
		"yd_Eternal_Hull"
	};	
	private static String[] inf10= {
		"yd_worldDestroyer_Hull"
	};	
	
	
	
	private static String[] tri1= {
		"omen_Hull",
		"wolf_Hull",
		"afflictor_Hull"
	};
	private static String[] tri2= {
		"wolf_Hull",
		"afflictor_Hull",
		"tempest_Hull",
		"shade_Hull"
	};
	private static String[] tri3= {
		"wolf_Hull",
		"tempest_Hull",
		"hyperion_Hull"
	};
	private static String[] tri4= {
		"tempest_Hull",
		"hyperion_Hull",
		"medusa_Hull"
	};
	private static String[] tri5= {
		"medusa_Hull",
		"apogee_Hull",
		"doom_Hull"
	};
	private static String[] tri6= {
		"apogee_Hull",
		"doom_Hull",
		"aurora_Hull",
		"yd_apogee2_Hull"
	};
	private static String[] tri7= {
		"astral_Hull",
		"odyssey_Hull"
	};
	private static String[] tri8= {
		"paragon_Hull"
	};
	private static String[] tri9= {
		"paragon_Hull"
	};
	private static String[] tri10= {
		"yd_Restricted_Hull"
	};
	
	private static String[] fre1= {
		"lasher_Hull",
		"hound_Hull",
		"warhound_Hull"
	};
	private static String[] fre2= {
		"lasher_Hull",
		"hound_Hull",
		"yd_Drillhead_Hull",
	};
	private static String[] fre3= {
		"enforcer_Hull",
		"buffalo2_Hull"
	};
	private static String[] fre4= {
		"enforcer_Hull",
		"hammerhead_Hull"
	};
	private static String[] fre5= {
		"venture_Hull",
		"yd_Angel_Hull",
		"heron_Hull"
	};
	private static String[] fre6= {
		"venture_Hull",
		"yd_Angel_Hull",
		"yd_Nightingale_Hull"
	};
	private static String[] fre7= {
		"conquest_Hull"
	};
	private static String[] fre8= {
		"conquest_Hull",
		"yd_frontier_Hull"
	};
	private static String[] fre9= {
		"Fairy_cromass_Hull"
	};
	private static String[] fre10= {
		"yd_worldDestroyer_Hull"
	};
	
	private static String[] fay1= {
		"Fairy_Aries_Hull",
		"Fairy_Aries_Hull",
		"Fairy_FYS1000_Hull"
	};
	private static String[] fay2= {
		"Fairy_Aries_Hull",
		"Fairy_Fang_Hull"
	};
	private static String[] fay3= {
		"Fairy_Private_Hull"
	};
	private static String[] fay4= {
		"Fairy_Private_Hull",
		"Fairy_jumbo_Hull",
		"Fairy_Advance_Hull"
	};
	private static String[] fay5= {
		"Fairy_Mammoth_Hull",
		"Fairy_Broadblade_Hull",
	};
	private static String[] fay6= {
		"Fairy_Griffin_Hull",
		"Fairy_matrix_Hull",
		"Fairy_Twilight_Hull"
	};
	private static String[] fay7={
		"Fairy_Twilight_Hull",
		"Fairy_itinerant_Hull",
		"Fairy_FYS10000_Hull"
	};
	private static String[] fay8= {
		"Fairy_Dawn_Hull",
		"Fairy_Dawn_B_Hull"
	};
	private static String[] fay9= {
		"Fairy_DawnII_Hull",
		"Fairy_Shine_Hull"
	};
	private static String[] fay10= {
		"Fairy_Enchantress_Hull",
		"Fairy_Empress_Hull"
	};
	
	private static String[] uni= {
		"shuttle_Hull",
		"hermes_Hull",
		"dram_Hull",
		"buffalo_Hull",
		"condor_Hull",
		"gemini_Hull",
		"tarsus_Hull",
		"valkyrie_Hull",
		"phaeton_Hull",
		"atlas_Hull",
		"prometheus_Hull",
		"ox_Hull",
		"crig_Hull"
	};
	
	private static String[] basicWing= {
		"talon_wing",
		"warthog_wing",
		"mining_drone_wing",
		"piranha_wing",
		"Rover_Rambler_A_1_wing",
		"Rover_Rambler_A_2_wing"
	};
	private static String[] advancedWing=  {
		"broadsword_wing",
		"broadsword_wing",
		"longbow_wing",
		"Rover_Rambler_A_3_wing",
		"Rover_Rambler_A_4_wing",
		"Rover_Rambler_A_5_wing"
	};
	private static String[] triWing= {
		"wasp_wing",
		"dagger_wing"
	};
	private static String[] triAdvancedWing= {
		"xyphos_wing",
		"trident_wing"
	};
	private static String[] triACEWing= {
		"TRI_Silent_wing",
		"TRI_Supernova_wing"
	};
	private static String[] fayWing= {
		"Fairy_larvae_wing",
		"Fairy_Twinbee_wing"
	};
	private static String[] fayAdvancedWing= {
		"Fairy_Vespa_wing",
		"Fairy_RavenS_wing"
	};
	private static String[] fayACEWing= {
		"Fairy_Stars_wing",
		"Fairy_Dragonhawk_wing"
	};
	private static void addEXFuel(CargoAPI cargoAPI) {
//		if (cargoAPI.getFuel()<10000) {
//			cargoAPI.addFuel(100);
//		}
		if (cargoAPI.getSupplies()<10000) {
			cargoAPI.addSupplies(200);
		}

	}
	private static void addFuel(CargoAPI cargoAPI, int shipLevel) {
//		if (cargoAPI.getFuel()<20000) {
//			cargoAPI.addFuel(fuelnum[shipLevel-1]);
//		}
		if (cargoAPI.getSupplies()<10000) {
			cargoAPI.addSupplies(200*shipLevel);
		}
	}
	private static void clearships(CargoAPI cargo) {
		FleetDataAPI ship = cargo.getMothballedShips();
		List ships = ship.getMembersListCopy();
		for(int a = 0;a<ships.size();a++)
		{
			FleetMemberAPI oneship = (FleetMemberAPI)ships.get(a);
			ship.removeFleetMember(oneship);
		}

	}
	private static void addCrew(CargoAPI cargoAPI , int shipLevel){
		float level = shipLevel;
		if (level<1) {
			level = 0.5f;
		}
		cargoAPI.addCrew(CrewXPLevel.GREEN, (int) (50*level));
		cargoAPI.addCrew(CrewXPLevel.REGULAR, (int) (25*level));
		if (cargoAPI.getCrew(CrewXPLevel.GREEN)>1000) {
			cargoAPI.removeCrew(CrewXPLevel.GREEN, cargoAPI.getCrew(CrewXPLevel.GREEN)-5000);
		}
		if (cargoAPI.getCrew(CrewXPLevel.REGULAR)>500) {
			cargoAPI.removeCrew(CrewXPLevel.REGULAR, cargoAPI.getCrew(CrewXPLevel.REGULAR)-1000);
		}
	}
	public static void addTriWing(CargoAPI cargoAPI, int shipLevel){
		for (int i = 0; i < newWingnum[0]; i++) {
			cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, triWing[(int)(Math.random()*triWing.length)], null);
		}
		if (shipLevel>2) {
			for (int i = 0; i < newWingnum[1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, triAdvancedWing[(int)(Math.random()*triAdvancedWing.length)], null);
			}			
		}
		if (shipLevel>6) {
			for (int i = 0; i < newWingnum[2]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, triACEWing[(int)(Math.random()*triACEWing.length)], null);
			}	
		}
	}
	public static void addWing(CargoAPI cargoAPI, int shipLevel){
		for (int i = 0; i < newWingnum[0]; i++) {
			cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, basicWing[(int)(Math.random()*basicWing.length)], null);
		}
		if (shipLevel>4) {
			for (int i = 0; i < newWingnum[1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, advancedWing[(int)(Math.random()*advancedWing.length)], null);
			}			
		}
	}
	public static void addFayWing(CargoAPI cargoAPI, int shipLevel){
		for (int i = 0; i < newWingnum[0]; i++) {
			cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, fayWing[(int)(Math.random()*fayWing.length)], null);
		}
		if (shipLevel>2) {
			for (int i = 0; i < newWingnum[1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, fayAdvancedWing[(int)(Math.random()*fayAdvancedWing.length)], null);
			}			
		}
		if (shipLevel>6) {
			for (int i = 0; i < newWingnum[2]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.FIGHTER_WING, fayACEWing[(int)(Math.random()*fayACEWing.length)], null);
			}	
		}
	}	
	
	public static void addship(CargoAPI cargoAPI ,int shipLevel ,int factionID){
		if (shipLevel<0) {
			if (factionID!=5) {
				clearships(cargoAPI);
				addEXFuel(cargoAPI);
			}
			return;
		}
		shipLevel++;
		if (factionID!=5) {
			clearships(cargoAPI);
		}
		addFuel(cargoAPI, shipLevel);
		addCrew(cargoAPI, shipLevel);
		if (factionID==0) {
			addShipfay(cargoAPI, shipLevel);
			return;
		} else if (factionID==1) {
			addShiphum(cargoAPI, shipLevel);
		} else if (factionID==2) {
			addShipinf(cargoAPI, shipLevel);
		} else if (factionID==3) {
			addShipfre(cargoAPI, shipLevel);
		} else if (factionID==4) {
			addShiptri(cargoAPI, shipLevel);
		}
		
	}
	public static void addshippri(CargoAPI cargoAPI ,CargoAPI rawCargoAPI , int shipLevel){
		clearships(cargoAPI);
		if (shipLevel<1) {
			shipLevel = 1;
		}
		double random = Math.random();
		if (random<0.2) {
			addShiphum(cargoAPI, shipLevel);
		}else if (random<0.4) {
			addShipfre(cargoAPI, shipLevel);
		}else if (random<0.6) {
			addShipinf(cargoAPI, shipLevel);
		}else if (random<0.8) {
			addShiptri(cargoAPI, shipLevel);
		}else {
			addShipfay(cargoAPI, shipLevel);
		}
		List fleet= cargoAPI.getMothballedShips().getMembersListCopy();
		for (int i = 0; fleet.size()>1; i++) {
			fleet.remove((int)(Math.random()*fleet.size()));
		}
		FleetMemberAPI ship =(FleetMemberAPI)fleet.get(0);
		System.out.println(ship.getId());
		rawCargoAPI.getMothballedShips().addFleetMember(ship);
	}
	public static void addShipuni(CargoAPI cargoAPI ,int shipLevel){
		shipLevel++;
		clearships(cargoAPI);
		addFuel(cargoAPI, shipLevel);
		addWing(cargoAPI, shipLevel);
		
		for (int i = 0; i <4 ; i++) {
			cargoAPI.addMothballedShip(FleetMemberType.SHIP, uni[(int)(Math.random()*uni.length)], null);
		}
	}
	public static void addShiphum(CargoAPI cargoAPI ,int shipLevel){
		if (shipLevel>=1){
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum1[(int)(Math.random()*hum1.length)], null);
			}
		}
		if (shipLevel>=2) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum2[(int)(Math.random()*hum2.length)], null);
			}
		}
		if (shipLevel>=3) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum3[(int)(Math.random()*hum3.length)], null);
			}
		}
		if (shipLevel>=4) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum4[(int)(Math.random()*hum4.length)], null);
			}
		}
		if (shipLevel>=5) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum5[(int)(Math.random()*hum5.length)], null);
			}
		}
		if (shipLevel>=6) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum6[(int)(Math.random()*hum6.length)], null);
			}
		}
		if (shipLevel>=7) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum7[(int)(Math.random()*hum7.length)], null);
			}
		}
		if (shipLevel>=8) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum8[(int)(Math.random()*hum8.length)], null);
			}
		}
		if (shipLevel>=9) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum9[(int)(Math.random()*hum9.length)], null);
			}
		}
		if (shipLevel>=10) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, hum10[(int)(Math.random()*hum10.length)], null);
			}
		}
	}
	
	public static void addShipinf(CargoAPI cargoAPI ,int shipLevel){

		if (shipLevel>=1){
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf1[(int)(Math.random()*inf1.length)], null);
			}
		}
		if (shipLevel>=2) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf2[(int)(Math.random()*inf2.length)], null);
			}
		}
		if (shipLevel>=3) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf3[(int)(Math.random()*inf3.length)], null);
			}
		}
		if (shipLevel>=4) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf4[(int)(Math.random()*inf4.length)], null);
			}
		}
		if (shipLevel>=5) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf5[(int)(Math.random()*inf5.length)], null);
			}
		}
		if (shipLevel>=6) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf6[(int)(Math.random()*inf6.length)], null);
			}
		}
		if (shipLevel>=7) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf7[(int)(Math.random()*inf7.length)], null);
			}
		}
		if (shipLevel>=8) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf8[(int)(Math.random()*inf8.length)], null);
			}
		}
		if (shipLevel>=9) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf9[(int)(Math.random()*inf9.length)], null);
			}
		}
		if (shipLevel>=10) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, inf10[(int)(Math.random()*inf10.length)], null);
			}
		}
	}
	
	public static void addShiptri(CargoAPI cargoAPI ,int shipLevel){
		addTriWing(cargoAPI, shipLevel);
		if (shipLevel>=1){
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri1[(int)(Math.random()*tri1.length)], null);
			}
		}
		if (shipLevel>=2) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri2[(int)(Math.random()*tri2.length)], null);
			}
		}
		if (shipLevel>=3) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri3[(int)(Math.random()*tri3.length)], null);
			}
		}
		if (shipLevel>=4) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri4[(int)(Math.random()*tri4.length)], null);
			}
		}
		if (shipLevel>=5) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri5[(int)(Math.random()*tri5.length)], null);
			}
		}
		if (shipLevel>=6) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri6[(int)(Math.random()*tri6.length)], null);
			}
		}
		if (shipLevel>=7) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri7[(int)(Math.random()*tri7.length)], null);
			}
		}
		if (shipLevel>=8) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri8[(int)(Math.random()*tri8.length)], null);
			}
		}
		if (shipLevel>=9) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri9[(int)(Math.random()*tri9.length)], null);
			}
		}
		if (shipLevel>=10) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, tri10[(int)(Math.random()*tri10.length)], null);
			}
		}
	}
	public static void addShipfre(CargoAPI cargoAPI ,int shipLevel){
		if (shipLevel>=1){
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre1[(int)(Math.random()*fre1.length)], null);
			}
		}
		if (shipLevel>=2) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre2[(int)(Math.random()*fre2.length)], null);
			}
		}
		if (shipLevel>=3) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre3[(int)(Math.random()*fre3.length)], null);
			}
		}
		if (shipLevel>=4) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre4[(int)(Math.random()*fre4.length)], null);
			}
		}
		if (shipLevel>=5) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre5[(int)(Math.random()*fre5.length)], null);
			}
		}
		if (shipLevel>=6) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre6[(int)(Math.random()*fre6.length)], null);
			}
		}
		if (shipLevel>=7) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre7[(int)(Math.random()*fre7.length)], null);
			}
		}
		if (shipLevel>=8) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre8[(int)(Math.random()*fre8.length)], null);
			}
		}
		if (shipLevel>=9) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre9[(int)(Math.random()*fre9.length)], null);
			}
		}
		if (shipLevel>=10) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fre10[(int)(Math.random()*fre10.length)], null);
			}
		}
	}
	public static void addShipfay(CargoAPI cargoAPI ,int shipLevel){
		addFayWing(cargoAPI, shipLevel);
		if (shipLevel>=1){
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay1[(int)(Math.random()*fay1.length)], null);
			}
		}
		if (shipLevel>=2) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay2[(int)(Math.random()*fay2.length)], null);
			}
		}
		if (shipLevel>=3) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay3[(int)(Math.random()*fay3.length)], null);
			}
		}
		if (shipLevel>=4) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay4[(int)(Math.random()*fay4.length)], null);
			}
		}
		if (shipLevel>=5) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay5[(int)(Math.random()*fay5.length)], null);
			}
		}
		if (shipLevel>=6) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay6[(int)(Math.random()*fay6.length)], null);
			}
		}
		if (shipLevel>=7) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay7[(int)(Math.random()*fay7.length)], null);
			}
		}
		if (shipLevel>=8) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay8[(int)(Math.random()*fay8.length)], null);
			}
		}
		if (shipLevel>=9) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay9[(int)(Math.random()*fay9.length)], null);
			}
		}
		if (shipLevel>=10) {
			for (int i = 0; i < newShipnum[shipLevel-1]; i++) {
				cargoAPI.addMothballedShip(FleetMemberType.SHIP, fay10[(int)(Math.random()*fay10.length)], null);
			}
		}
	}
}
