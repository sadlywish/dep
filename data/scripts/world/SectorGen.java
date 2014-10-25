package data.scripts.world;

import java.awt.Color;


import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.CoreCampaignPluginImpl;

import data.scripts.plugins.logistics.LogisticsPlugins;
import data.scripts.world.corvus.Corvus;

@SuppressWarnings("unchecked")
public class SectorGen implements SectorGeneratorPlugin {

	public void generate(SectorAPI sector) {
		
		StarSystemAPI system = sector.createStarSystem("Corvus");
		system.getLocation().set(1000, 1000);
		system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");
		
		SectorEntityToken star = system.initStar("star_yellow", Color.white, 500f);
		
		SectorEntityToken corvusI = system.addPlanet(star, "Corvus I", "desert", 55, 150, 3000, 100);
		SectorEntityToken corvusII = system.addPlanet(star, "Corvus II", "jungle", 235, 200, 5500, 490);

		sector.setRespawnLocation(system);
		sector.getRespawnCoordinates().set(-2500, -3500);

		system.addAsteroidBelt(star, 500, 5500, 1000, 150, 300);
		
		SectorEntityToken corvusIII = system.addPlanet(star, "Corvus III", "gas_giant", 150, 300, 7500, 520);
		SectorEntityToken corvusIIIA = system.addPlanet(corvusIII, "Corvus IIIA", "cryovolcanic", 235, 120, 800, 20);
		system.addAsteroidBelt(corvusIII, 50, 1000, 200, 10, 45);
		SectorEntityToken corvusIIIB = system.addPlanet(corvusIII, "Corvus IIIB", "barren", 235, 100, 1300, 60);
		
		SectorEntityToken corvusIV = system.addPlanet(star, "Corvus IV", "barren", 0, 100, 10500, 500);
		SectorEntityToken corvusV = system.addPlanet(star, "Corvus V", "frozen", 300, 175, 12000, 480);
		
		initFactionRelationships(sector);
		sector.registerPlugin(new CoreCampaignPluginImpl());
		sector.addScript(new LogisticsPlugins());
		new Corvus().generate(sector);

		new FairyModGen().generate(sector);

	}
	
	private void initFactionRelationships(SectorAPI sector) {
		FactionAPI hegemony = sector.getFaction("hegemony");
		FactionAPI tritachyon = sector.getFaction("tritachyon");
		FactionAPI pirates = sector.getFaction("pirates");
		FactionAPI independent = sector.getFaction("independent");
		FactionAPI player = sector.getFaction("player");
		
//		player.setRelationship(hegemony.getId(), 0);
//		player.setRelationship(tritachyon.getId(), 0);
		player.setRelationship(pirates.getId(), -1);
//		player.setRelationship(independent.getId(), 0);
		

//		hegemony.setRelationship(tritachyon.getId(), -1);
		hegemony.setRelationship(pirates.getId(), -1);
		
		tritachyon.setRelationship(pirates.getId(), -1);
//		tritachyon.setRelationship(independent.getId(), -1);
		
		pirates.setRelationship(independent.getId(), -1);
		
//		independent.setRelationship(hegemony.getId(), 0);
//		independent.setRelationship(tritachyon.getId(), 0);
//		independent.setRelationship(pirates.getId(), 0);
//		independent.setRelationship(independent.getId(), 0);
//		independent.setRelationship(player.getId(), 0);
		
	}
}