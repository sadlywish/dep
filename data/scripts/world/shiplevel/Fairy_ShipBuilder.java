package data.scripts.world.shiplevel;

import java.util.List;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import data.scripts.world.shiplevel.ShipList;

import data.scripts.world.BaseSpawnPoint;

public class Fairy_ShipBuilder extends BaseSpawnPoint {
	private FactionManager factionManager;

	public Fairy_ShipBuilder(SectorAPI sector, LocationAPI location,
			float daysInterval, int maxFleets, SectorEntityToken anchor,
			FactionManager factionManager) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.factionManager = factionManager;
	}

	@Override
	public CampaignFleetAPI spawnFleet() {
		StarSystemAPI system = getSector().getStarSystem("Corvus");
		// int shipLevel =
		// (int)getSector().getFaction("mission").getRelationship(
		// "independent");
		// SectorEntityToken stationhum = system.getEntityByName("轨道空间站");
		// SectorEntityToken stationinf = system.getEntityByName("联合贸易中心");
		// SectorEntityToken stationfre = system.getEntityByName("秘密基地");
		// SectorEntityToken stationtri = system.getEntityByName("企业总部");
		// SectorEntityToken stationfay = system
		// .getEntityByName("菲雅利帝国驻Corvus星系临时办事处");
		SectorEntityToken stationuni = system.getEntityByName("空间舰船制造总局");
		SectorEntityToken station = system.getEntityByName("废弃空间站");
		CampaignFleetAPI player = getSector().getPlayerFleet();
		if (player!=null) {
			player.getCargo().getCredits().add(factionManager.getSalary());
		}
		int[] levelList = factionManager.getLevelList();
		int[] checkList = factionManager.needCheck;
		String[] stationList = factionManager.stationNameList;
		boolean jump = false;
		if (factionManager.campID==3) {
			ShipList.addshippri(stationuni.getCargo(), station.getCargo(), factionManager.campLevel);
		}
		ShipList.addShipuni(stationuni.getCargo(),
				factionManager.getFleetLevel());
		addRandomWeapons(stationuni.getCargo(), 35);
		for (int i = 0; i < levelList.length; i++) {
			for (int j = 0; j < checkList.length; j++) {
				if (i == checkList[j]) {
					if (system.getEntityByName(stationList[i]) == null) {
						jump = true;
					}
				}
			}
			if (jump) {
				jump = false;
				continue;
			}
			ShipList.addship(system.getEntityByName(stationList[i]).getCargo(),
					levelList[i], i);
		}
		// if (stationfay!=null && levelList[0]!=-1) {
		// ShipList.addShipfay(stationfay.getCargo(), shipLevel);
		// }
		// ShipList.addShipfre(stationfre.getCargo(), shipLevel);
		// ShipList.addShipinf(stationinf.getCargo(), shipLevel);
		//
		// ShipList.addShiptri(stationtri.getCargo(), shipLevel);

		return null;
	}

	private void addRandomWeapons(CargoAPI cargo, int count) {
		List weaponIds = getSector().getAllWeaponIds();
		for (int i = 0; i < count; i++) {
			String weaponId = (String) weaponIds
					.get((int) (weaponIds.size() * Math.random()));
			cargo.addWeapons(weaponId, 1);
		}
		noflashWeapons(cargo);
	}

	private void noflashWeapons(CargoAPI cargo) {
		int count = 0;
		for (int i = 0; i < noflash.length; i++) {
			while (cargo.removeItems(CargoAPI.CargoItemType.WEAPONS,
					noflash[i], 1)) {
				count++;
			}
		}
		if (count > 0) {
			addRandomWeapons(cargo, count);
			count = 0;
		}
	}

	private static String[] noflash = { "test001", "Nuclear", "XL_MAC", };
}
