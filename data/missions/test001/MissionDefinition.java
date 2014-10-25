package data.missions.test001;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

import data.scripts.plugins.SmokenSwpanPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {
		api.addPlugin(new SmokenSwpanPlugin());
		
		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "FAY", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "妖精重工测试舰队");
		api.setFleetTagline(FleetSide.ENEMY, "靶子");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("击败所有敌军");
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "Fairy_Empress_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Enchantress_Windy_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "yd_Restricted_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Empress_E_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_SPS-500_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_SPS-1500_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_Shine_hull", FleetMemberType.SHIP,"FAY 嫩牛五方号", true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_Twilight_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_Fang_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "HGN_Lord_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_DawnII_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dawn_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_worldDestroyer_bettle", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "hum_worldDestroyer_Hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dawn_hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_matrix_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_itinerant_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_cromass_Elite",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_Eternal_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_Angel_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_Nightingale_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_frontier_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_apogee2_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_cracks_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_Drillhead_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_heavyhammer_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "yd_Eternal_Hull",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Fairy_Enchantress_E",FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_5_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_4_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_3_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_2_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_1_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_5_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_4_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_3_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_2_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_1_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_5_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_4_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_3_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_2_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_1_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_5_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_4_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_3_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_2_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_1_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_5_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_4_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_3_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_2_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Rover_Rambler_A_1_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);

		api.addToFleet(FleetSide.PLAYER, "Fairy_RavenS_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_RavenS_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_RavenS_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_RavenS_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_RavenS_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_RavenS_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_larvae_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Vespa_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Vespa_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Vespa_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Vespa_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Vespa_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Vespa_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Stars_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dragonhawk_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dragonhawk_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dragonhawk_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dragonhawk_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "Fairy_Dragonhawk_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "TRI_Silent_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Supernova_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Silent_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Supernova_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Silent_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Supernova_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Silent_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "TRI_Supernova_wing", FleetMemberType.FIGHTER_WING, true, CrewXPLevel.VETERAN);

		// Mark both ships as essential - losing either one results in mission failure. Could also be set on an enemy ship, in which case destroying it would result in a win.		
		// Set up the enemy fleet.
		api.addToFleet(FleetSide.ENEMY, "yd_worldDestroyer_bettle", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.ENEMY, "Fairy_cromass_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.ENEMY, "Fairy_cromass_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.ENEMY, "Fairy_cromass_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.ENEMY, "Fairy_cromass_hull", FleetMemberType.SHIP, true, CrewXPLevel.VETERAN);		
		api.addToFleet(FleetSide.ENEMY, "onslaught_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "onslaught_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "onslaught_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "onslaught_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);

		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 50000f;
		float height = 50000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// All the addXXX methods take a pair of coordinates followed by data for
		// whatever object is being added.
		
		// Add two big nebula clouds
		api.addNebula(minX + width * 0.75f, minY + height * 0.5f, 2000);
		api.addNebula(minX + width * 0.25f, minY + height * 0.5f, 1000);
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		for (int i = 0; i < 5; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		api.addObjective(minX + width * 0.75f, minY + height * 0.5f, 
						 "sensor_array");
		api.addObjective(minX + width * 0.25f, minY + height * 0.5f, 
						 "nav_buoy");
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		api.addAsteroidField(minY, minY, 45, 2000f,
								20f, 70f, 100);
		
		// Add some planets.  These are defined in data/config/planets.json.
		api.addPlanet(minX + width * 0.2f, minY + height * 0.8f, 320f, "star_yellow", 300f);
		api.addPlanet(minX + width * 0.8f, minY + height * 0.8f, 256f, "desert", 250f);
		api.addPlanet(minX + width * 0.55f, minY + height * 0.25f, 200f, "cryovolcanic", 200f);
	}

}
