package data.scripts.world.shiplevel;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;

import data.scripts.plugins.CharacterCreationPluginImpl;


public class FactionManager {
	public int factionID = 0;
	public int campID = 0;
	public int campLevel = 0;
	public int endure = 5;
	public FactionManager(SectorAPI sector) {
		factionID =  CharacterCreationPluginImpl.faction;
		campLevel = CharacterCreationPluginImpl.campLevel;
		intiFaction(sector);
	}
	public boolean checkEndure(SectorAPI sector){
		endure--;
		if (endure<1) {
			campID=3;
			factionID=5;
			endure=5;
			intiFaction(sector);
			return true;
		}
		return false;
	}
	public boolean checkEnemy(int otherCamp){
		if (realtionship[campID][otherCamp]>-0.5f) {
			return true;
		}
		return false;
	}
	public void levelup(){
		campLevel++;
	}
	public int getFleetLevel(){
		return campLevel;
	}
	public int[] getLevelList(){
		int[] levelList = new int[this.factionNameList.length];
		for (int i = 0; i < levelList.length; i++) {
			levelList[i]=-1;
		}
		for (int i = 0; camp_faction_list[campID][i]!=-1; i++) {
			int subLevel = 0;
			if (campLevel!=0) {
				subLevel=campLevel-1;
			}
			levelList[camp_faction_list[campID][i]]=subLevel;
		}
		levelList[factionID]=campLevel;
		return levelList;
	}
	
	private void intiFaction(SectorAPI sector){
		setcamp();	
		intiRealtionship(sector);
	}
	public void intiRealtionship(SectorAPI sector) {
		FactionAPI player = sector.getFaction("player");
		List friendList = new ArrayList();
		List enemyList = new ArrayList();
		for (int i = 0; i < factionIDList.length; i++) {
			player.setRelationship(factionIDList[i], 0f);
		}
		for (int i = 0; camp_faction_list[campID][i]!=-1; i++) {
			friendList.add(camp_faction_list[campID][i]);
		}
		for (int i = 0; i < campNameList.length; i++) {
			if (realtionship[campID][i]<0) {
				for (int j = 0; camp_faction_list[i][j]!=-1 ; j++) {
					enemyList.add(camp_faction_list[i][j]);
				}
			}
		}
		for (int i = 0; i < friendList.size(); i++) {
			player.setRelationship(factionIDList[(int)(Integer)friendList.get(i)], 1f);
		}
		for (int i = 0; i < enemyList.size(); i++) {
			player.setRelationship(factionIDList[(int)(Integer)enemyList.get(i)], -1f);
		}
	}
	private void setcamp(){
		for (int i = 0; i < campNameList.length; i++) {
			for (int j = 0; camp_faction_list[i][j]!=-1; j++) {
				if (camp_faction_list[i][j]==this.factionID) {
					this.campID = i;
					return;
				}
			}
		}
	}
	public float getSalary(){
		
		float vaule = ((campLevel+1)^2)/2;
		if (vaule<1) {
			vaule=1;
		}
		return salary[factionID]*vaule;
	} 
	public float getEnemyLevel(){
		return enemyLevel[campLevel];
	}
	public static String[] factionNameList={
			"菲雅利帝国",
			"霸主",
			"自由贸易联盟",
			"自由之翼",
			"速子科技",
			"海盗",
	};
	public static String[] factionIDList={
		"Fairy",
		"hegemony",
		"independent",
		"fow",
		"tritachyon",
		"pirates",
	};
	public static String[] campNameList={
			"帝国",
			"轴心",
			"挑战者",
			"海盗"
	};
	public static int[][] camp_faction_list={
			{0,-1},
			{1,2,-1},
			{3,4,-1},
			{5,-1}
	};
	public static String[] stationNameList={
		"菲雅利帝国驻Corvus星系临时办事处",
		"轨道空间站",
		"联合贸易中心",
		"秘密基地",
		"企业总部",
		"废弃空间站",
	};
	public static int[] needCheck={
		0,
	};
	public static float[][] realtionship={
		{1,0,0,-1},
		{0,1,-1,-1},
		{0,-1,1,-1},
		{-1,-1,-1,1}
	};
	private static float[] salary = {
		1800,
		3500,
		2500,
		3500,
		2000,
		10,
	};
	private float[] enemyLevel={
		0,
		0,
		1,
		1,
		2,
		2,
		3,
		4,
		5,
		6
	};
}
