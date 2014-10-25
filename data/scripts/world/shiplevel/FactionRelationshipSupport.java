package data.scripts.world.shiplevel;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;

public class FactionRelationshipSupport {
	
	public static void checkFactionEnemy(FactionManager factionManager,SectorAPI sector){
		FactionAPI player =  sector.getFaction("player");
		for (int i = 0; i < factionManager.campNameList.length; i++) {
			for (int j = 0; factionManager.camp_faction_list[i][j]!=-1; j++) {
					if (player.getRelationship(factionManager.factionIDList[factionManager.camp_faction_list[i][j]])<factionManager.realtionship[factionManager.campID][i]) {
						if (factionManager.checkEnemy(i)) {
							if (factionManager.checkEndure(sector)) {
								sector.addMessage("你所在的势力对你的行径忍无可忍，将你名字计入了星系黑名单。现在你只能与海盗为伍了。");
							}else {
								sector.addMessage("你攻击了与你非敌对的势力，你所在势力对你的容忍度剩余:"+factionManager.endure);
							}
						}
						break;
					}
			}
		}
		factionManager.intiRealtionship(sector);
	}
	private static void actionEnemy(FactionManager factionManager, SectorAPI sector, int campID, FactionAPI player) {
		for (int i = 0; factionManager.camp_faction_list[campID][i]!=-1; i++) {
			player.setRelationship(factionManager.factionIDList[factionManager.camp_faction_list[campID][i]],-1);
		}
	}
	public static void checkFactionStation(FactionManager factionManager, SectorAPI sector){
		FactionAPI player =  sector.getFaction("player");
		StarSystemAPI system = sector.getStarSystem("Corvus");
		for (int i = 0; i < factionManager.campNameList.length; i++) {
			for (int j = 0; factionManager.camp_faction_list[i][j]!=-1&&factionManager.camp_faction_list[i][j]!=5; j++) {
					if (player.getRelationship(factionManager.factionIDList[factionManager.camp_faction_list[i][j]])<0) {
						SectorEntityToken station = system.getEntityByName(factionManager.stationNameList[factionManager.camp_faction_list[i][j]]);
						if (station==null) {
							continue;
						}
						station.getCargo().clear();
					}
			}
		}
	}
}
