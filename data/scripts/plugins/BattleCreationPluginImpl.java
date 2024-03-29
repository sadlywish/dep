package data.scripts.plugins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BattleCreationPlugin;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.combat.BattleCreationContext;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.combat.EscapeRevealPlugin;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;

public class BattleCreationPluginImpl implements BattleCreationPlugin {

	private float width, height;
	
	private float xPad = 2000;
	private float yPad = 2000;
	
	private List<String> objs = null;

	private float prevXDir = 0;
	private float prevYDir = 0;
	private boolean escape;
	
	private BattleCreationContext context;
	private MissionDefinitionAPI loader;
	
	public void initBattle(final BattleCreationContext context, MissionDefinitionAPI loader) {

		this.context = context;
		this.loader = loader;
		CampaignFleetAPI playerFleet = context.getPlayerFleet();
		CampaignFleetAPI otherFleet = context.getOtherFleet();
		FleetGoal playerGoal = context.getPlayerGoal();
		FleetGoal enemyGoal = context.getOtherGoal();
		loader.addPlugin(new SmokenSwpanPlugin());
		
		escape = playerGoal == FleetGoal.ESCAPE || enemyGoal == FleetGoal.ESCAPE;
		
		int maxFP = (int) Global.getSettings().getFloat("maxNoObjectiveBattleSize");
		int fpOne = 0;
		int fpTwo = 0;
		for (FleetMemberAPI member : playerFleet.getFleetData().getMembersListCopy()) {
			if (member.canBeDeployedForCombat() || playerGoal == FleetGoal.ESCAPE) {
				fpOne += member.getFleetPointCost();
			}
		}
		for (FleetMemberAPI member : otherFleet.getFleetData().getMembersListCopy()) {
			if (member.canBeDeployedForCombat() || playerGoal == FleetGoal.ESCAPE) {
				fpTwo += member.getFleetPointCost();
			}
		}
		
		int smaller = Math.min(fpOne, fpTwo);
		
		boolean withObjectives = smaller > maxFP;
		int numObjectives = 0;
		if (withObjectives) {
			if (fpOne + fpTwo > maxFP + 70) {
				numObjectives = 3 + (int)(Math.random() * 2.0);
			} else {
				numObjectives = 2 + (int)(Math.random() * 2.0);
			}
		}
		
		// shouldn't be possible, but..
		if (numObjectives > 4) {
			numObjectives = 4;
		}
		
		int baseCommandPoints = (int) Global.getSettings().getFloat("startingCommandPoints");

		loader.initFleet(FleetSide.PLAYER, "ISS", playerGoal, false,
				(int) playerFleet.getCommanderStats().getCommandPoints().getModifiedValue() - baseCommandPoints);
		loader.initFleet(FleetSide.ENEMY, "", enemyGoal, true, 
				(int) otherFleet.getCommanderStats().getCommandPoints().getModifiedValue() - baseCommandPoints);

		List<FleetMemberAPI> playerShips = playerFleet.getFleetData().getCombatReadyMembersListCopy();
		if (playerGoal == FleetGoal.ESCAPE) {
			playerShips = playerFleet.getFleetData().getMembersListCopy();
		}
		for (FleetMemberAPI member : playerShips) {
			loader.addFleetMember(FleetSide.PLAYER, member);
		}
		
		
		List<FleetMemberAPI> enemyShips = otherFleet.getFleetData().getCombatReadyMembersListCopy();
		if (enemyGoal == FleetGoal.ESCAPE) {
			enemyShips = otherFleet.getFleetData().getMembersListCopy();
		}
		for (FleetMemberAPI member : enemyShips) {
			loader.addFleetMember(FleetSide.ENEMY, member);
		}
		
		width = 18000f;
		height = 18000f;
		
		if (escape) {
			width = 18000f;
			height = 24000f;
		} else if (withObjectives) {
			width = 24000f;
			if (numObjectives == 2) {
				height = 14000f;
			} else {
				height = 18000f;
			}
		}
		
		createMap();
		
		context.setInitialDeploymentBurnDuration(1.5f);
		context.setNormalDeploymentBurnDuration(6f);
		context.setEscapeDeploymentBurnDuration(1.5f);
		
		xPad = 2000f;
		yPad = 3000f;
		
		if (escape) {
			addEscapeObjectives(loader, 4);
			context.setInitialEscapeRange(7000f);
			context.setFlankDeploymentDistance(9000f);
			
			loader.addPlugin(new EscapeRevealPlugin(context));
		} else {
			if (withObjectives) {
				addObjectives(loader, numObjectives);
				context.setStandoffRange(height - 4500f);
			} else {
				context.setStandoffRange(6000f);
			}
		}

	}
	
	private void createMap() {
		loader.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		for (int i = 0; i < 15; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			loader.addNebula(x, y, radius);
		}
		
		float numAsteroidsWithinRange = countNearbyAsteroids(context.getPlayerFleet());
		
		int numAsteroids = Math.min(400, (int)((numAsteroidsWithinRange + 1f) * 20f));
		
		loader.addAsteroidField(0, 0, (float) Math.random() * 360f, width,
								20f, 70f, numAsteroids);
		
		//setRandomBackground(loader);
		loader.setBackgroundSpriteName(context.getPlayerFleet().getContainingLocation().getBackgroundTextureFilename());

		if (context.getPlayerFleet().getContainingLocation() == Global.getSector().getHyperspace()) {
			loader.setHyperspaceMode(true);
		} else {
			loader.setHyperspaceMode(false);
		}
		
		//addMultiplePlanets();
		addClosestPlanet();
	}
	
	private void addClosestPlanet() {
		float bgWidth = 2048f;
		float bgHeight = 2048f;
		
		PlanetAPI planet = getClosestPlanet(context.getPlayerFleet());
		if (planet == null) return;
		
		float dist = Vector2f.sub(context.getPlayerFleet().getLocation(), planet.getLocation(), new Vector2f()).length() - planet.getRadius();
		if (dist < 0) dist = 0;
		float baseRadius = planet.getRadius();
		float scaleFactor = 1.5f;
		float maxRadius = 500f;
				
//		if (planet.isStar()) {
//			scaleFactor = 0.01f;
//			maxRadius = 20f;
//		}
		
		float maxDist = SINGLE_PLANET_MAX_DIST - planet.getRadius();
		if (maxDist < 1) maxDist = 1;
		
		float f = (maxDist - dist) / maxDist * 0.65f + 0.35f;
		float radius = baseRadius * f * scaleFactor;
		if (radius > maxRadius) radius = maxRadius;
		
		loader.setPlanetBgSize(bgWidth * f, bgHeight * f);
		loader.addPlanet(0f, 0f, radius, planet, 0f, true);
	}
	
	private void addMultiplePlanets() {
		float bgWidth = 2048f;
		float bgHeight = 2048f;
		loader.setPlanetBgSize(bgWidth, bgHeight);
		
		
		List<NearbyPlanetData> planets = getNearbyPlanets(context.getPlayerFleet());
		if (!planets.isEmpty()) {
			float maxDist = PLANET_MAX_DIST;
			for (NearbyPlanetData data : planets) {
				float dist = Vector2f.sub(context.getPlayerFleet().getLocation(), data.planet.getLocation(), new Vector2f()).length();
				float baseRadius = data.planet.getRadius();
				float scaleFactor = 1.5f;
				float maxRadius = 500f;
				
				if (data.planet.isStar()) {
					// skip stars in combat, bright and annoying
					continue;
//					scaleFactor = 0.1f;
//					maxRadius = 50f;
				}
				
				float f = (maxDist - dist) / maxDist * 0.65f + 0.35f;
				float radius = baseRadius * f * scaleFactor;
				if (radius > maxRadius) radius = maxRadius;
				
				loader.addPlanet(data.offset.x * bgWidth / PLANET_AREA_WIDTH * scaleFactor,
								 data.offset.y * bgHeight / PLANET_AREA_HEIGHT * scaleFactor,
								 radius, data.planet.getTypeId(), 0f, true);
			}
			
		}
	}
	
	
	private void setRandomBackground(MissionDefinitionAPI loader) {
		// these have to be loaded using the graphics section in settings.json
		String [] bgs = new String [] {
				"graphics/backgrounds/background1.jpg",
				"graphics/backgrounds/background2.jpg",
				"graphics/backgrounds/background3.jpg",
				"graphics/backgrounds/background4.jpg"
		};
		String pick = bgs[(int)(Math.random() * bgs.length)];
		loader.setBackgroundSpriteName(pick);
	}

	private static String COMM = "comm_relay";
	private static String SENSOR = "sensor_array";
	private static String NAV = "nav_buoy";
	
	private void addObjectives(MissionDefinitionAPI loader, int num) {
		objs = new ArrayList<String>(Arrays.asList(new String [] {
				SENSOR,
				SENSOR,
				NAV,
				NAV,
				COMM,
				COMM,
		}));
		
		if (num == 2) {
			objs = new ArrayList<String>(Arrays.asList(new String [] {
					SENSOR,
					SENSOR,
					NAV,
					NAV,
					COMM,
			}));
			addObjectiveAt(0.25f, 0.5f, 0f, 0f);
			addObjectiveAt(0.75f, 0.5f, 0f, 0f);
		} else if (num == 3) {
			float r = (float) Math.random();
			if (r < 0.33f) {
				addObjectiveAt(0.25f, 0.7f, 1f, 1f);
				addObjectiveAt(0.25f, 0.3f, 1f, 1f);
				addObjectiveAt(0.75f, 0.5f, 1f, 1f);
			} else if (r < 0.67f) {
				addObjectiveAt(0.25f, 0.7f, 1f, 1f);
				addObjectiveAt(0.25f, 0.3f, 1f, 1f);
				addObjectiveAt(0.75f, 0.7f, 1f, 1f);
			} else {
				addObjectiveAt(0.25f, 0.5f, 1f, 1f);
				addObjectiveAt(0.5f, 0.5f, 1f, 1f);
				addObjectiveAt(0.75f, 0.5f, 1f, 1f);
			}
		} else if (num == 4) {
			float r = (float) Math.random();
			if (r < 0.33f) {
				addObjectiveAt(0.25f, 0.25f, 2f, 1f);
				addObjectiveAt(0.25f, 0.75f, 2f, 1f);
				addObjectiveAt(0.75f, 0.25f, 2f, 1f);
				addObjectiveAt(0.75f, 0.75f, 2f, 1f);
			} else if (r < 0.67f) {
				addObjectiveAt(0.25f, 0.5f, 1f, 1f);
				addObjectiveAt(0.5f, 0.75f, 1f, 1f);
				addObjectiveAt(0.75f, 0.5f, 1f, 1f);
				addObjectiveAt(0.5f, 0.25f, 1f, 1f);
			} else {
				addObjectiveAt(0.2f, 0.5f, 1f, 2f);
				addObjectiveAt(0.4f, 0.5f, 0f, 3f);
				addObjectiveAt(0.6f, 0.5f, 0f, 3f);
				addObjectiveAt(0.8f, 0.5f, 1f, 2f);
			}
		}
	}
	
	
	private void addEscapeObjectives(MissionDefinitionAPI loader, int num) {
		objs = new ArrayList<String>(Arrays.asList(new String [] {
				SENSOR,
				SENSOR,
				NAV,
				NAV,
				COMM,
		}));
		
		if (num == 2) {
			float r = (float) Math.random();
			if (r < 0.33f) {
				addObjectiveAt(0.25f, 0.25f, 1f, 1f);
				addObjectiveAt(0.75f, 0.75f, 1f, 1f);
			} else if (r < 0.67f) {
				addObjectiveAt(0.75f, 0.25f, 1f, 1f);
				addObjectiveAt(0.25f, 0.75f, 1f, 1f);
			} else {
				addObjectiveAt(0.5f, 0.25f, 4f, 2f);
				addObjectiveAt(0.5f, 0.75f, 4f, 2f);
			}
		} else if (num == 3) {
			float r = (float) Math.random();
			if (r < 0.33f) {
				addObjectiveAt(0.25f, 0.75f, 1f, 1f);
				addObjectiveAt(0.25f, 0.25f, 1f, 1f);
				addObjectiveAt(0.75f, 0.5f, 1f, 6f);
			} else if (r < 0.67f) {
				addObjectiveAt(0.25f, 0.5f, 1f, 6f);
				addObjectiveAt(0.75f, 0.75f, 1f, 1f);
				addObjectiveAt(0.75f, 0.25f, 1f, 1f);
			} else {
				addObjectiveAt(0.5f, 0.25f, 4f, 1f);
				addObjectiveAt(0.5f, 0.5f, 4f, 1f);
				addObjectiveAt(0.5f, 0.75f, 4f, 1f);
			}
		} else if (num == 4) {
			float r = (float) Math.random();
			if (r < 0.33f) {
				addObjectiveAt(0.25f, 0.25f, 1f, 1f);
				addObjectiveAt(0.25f, 0.75f, 1f, 1f);
				addObjectiveAt(0.75f, 0.25f, 1f, 1f);
				addObjectiveAt(0.75f, 0.75f, 1f, 1f);
			} else if (r < 0.67f) {
				addObjectiveAt(0.35f, 0.25f, 2f, 0f);
				addObjectiveAt(0.65f, 0.35f, 2f, 0f);
				addObjectiveAt(0.5f, 0.6f, 4f, 1f);
				addObjectiveAt(0.5f, 0.8f, 4f, 1f);
			} else {
				addObjectiveAt(0.65f, 0.25f, 2f, 0f);
				addObjectiveAt(0.35f, 0.35f, 2f, 0f);
				addObjectiveAt(0.5f, 0.6f, 4f, 1f);
				addObjectiveAt(0.5f, 0.8f, 4f, 1f);
			}
		}
	}	

	private void addObjectiveAt(float xMult, float yMult, float xOff, float yOff) {
		String type = pickAny();
		if (objs != null && objs.size() > 0) {
			int index = (int) (Math.random() * objs.size());
			type = objs.remove(index); 
		}
		
		float minX = -width/2 + xPad;
		float minY = -height/2 + yPad;
		
		float x = (width - xPad * 2f) * xMult + minX;
		float y = (height - yPad * 2f) * yMult + minY;
		
		x = ((int) x / 1000) * 1000f;
		y = ((int) y / 1000) * 1000f;
		
		float offsetX = Math.round((Math.random() - 0.5f) * xOff * 2f) * 1000f;
		float offsetY = Math.round((Math.random() - 0.5f) * yOff * 2f) * 1000f;
		
		float xDir = (float) Math.signum(offsetX);
		float yDir = (float) Math.signum(offsetY);
		
		if (xDir == prevXDir && xOff > 0) {
			xDir = -xDir;
			offsetX = Math.abs(offsetX) * -prevXDir;
		}
		
		if (yDir == prevYDir && yOff > 0) {
			yDir = -yDir;
			offsetY = Math.abs(offsetY) * -prevYDir;
		}
		
		prevXDir = xDir;
		prevYDir = yDir;
		
		x += offsetX;
		y += offsetY;
		
		loader.addObjective(x, y, type);
		
		if ((float) Math.random() > 0.6f) {
			float nebulaSize = (float) Math.random() * 1500f + 500f;
			loader.addNebula(x, y, nebulaSize);
		}
	}
	
	private String pickAny() {
		float r = (float) Math.random();
		if (r < 0.33f) return "nav_buoy";
		else if (r < 0.67f) return "sensor_array";
		else return "comm_relay"; 
	}

	private float countNearbyAsteroids(CampaignFleetAPI playerFleet) {
		float numAsteroidsWithinRange = 0;
		LocationAPI loc = playerFleet.getContainingLocation();
		if (loc instanceof StarSystemAPI) {
			StarSystemAPI system = (StarSystemAPI) loc;
			List<SectorEntityToken> asteroids = system.getAsteroids();
			for (SectorEntityToken asteroid : asteroids) {
				float range = Vector2f.sub(playerFleet.getLocation(), asteroid.getLocation(), new Vector2f()).length();
				if (range < 300) numAsteroidsWithinRange ++;
			}
		}
		return numAsteroidsWithinRange;
	}
	
	private static class NearbyPlanetData {
		private Vector2f offset;
		private PlanetAPI planet;
		public NearbyPlanetData(Vector2f offset, PlanetAPI planet) {
			this.offset = offset;
			this.planet = planet;
		}
	}
	
	private static float PLANET_AREA_WIDTH = 2000;
	private static float PLANET_AREA_HEIGHT = 2000;
	private static float PLANET_MAX_DIST = (float) Math.sqrt(PLANET_AREA_WIDTH/2f * PLANET_AREA_WIDTH/2f + PLANET_AREA_HEIGHT/2f * PLANET_AREA_WIDTH/2f);
	
	private static float SINGLE_PLANET_MAX_DIST = 1000f;
	
	private List<NearbyPlanetData> getNearbyPlanets(CampaignFleetAPI playerFleet) {
		LocationAPI loc = playerFleet.getContainingLocation();
		List<NearbyPlanetData> result = new ArrayList<NearbyPlanetData>();
		if (loc instanceof StarSystemAPI) {
			StarSystemAPI system = (StarSystemAPI) loc;
			List<PlanetAPI> planets = system.getPlanets();
			for (PlanetAPI planet : planets) {
				float diffX = planet.getLocation().x - playerFleet.getLocation().x;
				float diffY = planet.getLocation().y - playerFleet.getLocation().y;
				
				if (Math.abs(diffX) < PLANET_AREA_WIDTH/2f && Math.abs(diffY) < PLANET_AREA_HEIGHT/2f) {
					result.add(new NearbyPlanetData(new Vector2f(diffX, diffY), planet));
				}
			}
		}
		return result;
	}
	
	private PlanetAPI getClosestPlanet(CampaignFleetAPI playerFleet) {
		LocationAPI loc = playerFleet.getContainingLocation();
		PlanetAPI closest = null;
		float minDist = Float.MAX_VALUE;
		if (loc instanceof StarSystemAPI) {
			StarSystemAPI system = (StarSystemAPI) loc;
			List<PlanetAPI> planets = system.getPlanets();
			for (PlanetAPI planet : planets) {
				if (planet.isStar()) continue;
				float dist = Vector2f.sub(context.getPlayerFleet().getLocation(), planet.getLocation(), new Vector2f()).length();
				if (dist < minDist && dist < SINGLE_PLANET_MAX_DIST) {
					closest = planet;
					minDist = dist;
				}
			}
		}
		return closest;
	}
}




