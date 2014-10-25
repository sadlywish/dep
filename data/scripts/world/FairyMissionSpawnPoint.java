package data.scripts.world;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.campaign.fleet.CampaignFleet;
import com.fs.starfarer.campaign.fleet.FleetData;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.fleet.FleetMemberType;
import java.lang.Thread;

public class FairyMissionSpawnPoint extends BaseSpawnPoint 
{
	public FairyMissionSpawnPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}	
	@Override
	public CampaignFleetAPI spawnFleet() 
	{
	StarSystemAPI system = getSector().getStarSystem("Corvus");
	SectorEntityToken stationM = system.getEntityByName("妖精重工临时停靠点");
	CargoAPI Mcargo = stationM.getCargo();
	float pp = 0;
    SectorEntityToken playerfleet = system.getEntityByName("Fleet");
    CargoAPI playercargo = playerfleet.getCargo();
	FactionAPI mission = getSector().getFaction("mission");
	float cost = mission.getRelationship("tritachyon");
	float coo = mission.getRelationship("pirates");
	CampaignFleetAPI player = getSector().getPlayerFleet();
	//int point = player.getFleetPoints();
	FleetDataAPI fleet = player.getFleetData();
	int i = 0;
	//Global.getSectorAPI().addMessage(point +"");


	 

		if(mission.getRelationship("hegemony") < 1 && mission.getRelationship("hegemony") > -1 )
		{
			for (i = 0;i < (int)missionA.length && (mission.getRelationship("hegemony") < 1 && mission.getRelationship("hegemony") > -1);i++ )
			{
				missionM(playercargo,i,mission);
			}
		}
		for (i = 0;i<(int)RI.length;i++)
		{
			RR(playercargo,Mcargo,i);
		}
		for (i = 0;i<(int)RI2.length;i++)
		{
			RR2(playercargo,Mcargo,i);
		}
		for (i = 0;i<(int)RW.length;i++)
		{
			RR3(playercargo,Mcargo,i);
		}
		addEmpireWeapons(playercargo,Mcargo);



  return null;

	}
	private void missionM(CargoAPI playercargo,int cout,FactionAPI mission)
	{
		if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, missionA[cout], 1))
		{
			playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,missionB[cout],1);
			mission.setRelationship("hegemony", num[cout]);
		}
	}
	private void RR(CargoAPI playercargo,CargoAPI Mcargo,int cout)
	{
			if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, RI[cout], 1))
		{
			float Quantity = playercargo.getQuantity(CargoAPI.CargoItemType.RESOURCES,"hao_zi");
			if (Quantity >= (float)hao_zi[cout])
			{
				playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "hao_zi", (float)hao_zi[cout]);
				Mcargo.addMothballedShip(FleetMemberType.SHIP, (String) ships[cout], null);
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“我已经把需要的耗子取走了，");
				Global.getSectorAPI().addMessage("现在你可指挥你需要的舰船了。”");
			}
			else
			{
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“你没有足够的耗子，");
				Global.getSectorAPI().addMessage("等你弄够了再来吧。”");
			}
			Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES, RI[cout],1);
		}
	}
	public void RR2(CargoAPI playercargo,CargoAPI Mcargo,int cout)
	{
		if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, RI2[cout], 1))
		{
			float Quantity = playercargo.getQuantity(CargoAPI.CargoItemType.RESOURCES,"hao_zi");
			if (Quantity >= (float)hao_zi2[cout])
			{
				playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "hao_zi", (float)hao_zi2[cout]);
				Mcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, (String) ships2[cout], null);
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“我已经把需要的耗子取走了，");
				Global.getSectorAPI().addMessage("现在你可指挥你需要的舰船了。”");
			}
			else
			{
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“你没有足够的耗子，");
				Global.getSectorAPI().addMessage("等你弄够了再来吧。”");
			}
			Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES, RI2[cout],1);
		}
	}
	public void RR3(CargoAPI playercargo,CargoAPI Mcargo,int cout)
	{
		if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, RW[cout], 1))
		{
			float Quantity = playercargo.getQuantity(CargoAPI.CargoItemType.RESOURCES,"hao_zi");
			if (Quantity >= (float)hao_zi3[cout])
			{
				playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "hao_zi", (float)hao_zi3[cout]);
				Mcargo.addWeapons((String) WEAPON[cout], 1);
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“我已经把需要的耗子取走了，");
				Global.getSectorAPI().addMessage("现在你可购买你需要的武器了。”");
			}
			else
			{
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“你没有足够的耗子，");
				Global.getSectorAPI().addMessage("等你弄够了再来吧。”");
			}
			Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES, RW[cout],1);
		}
	}
	private void addEmpireWeapons(CargoAPI playercargo,CargoAPI Mcargo) {
		if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "RWR", 1))
		{
			float Quantity = playercargo.getQuantity(CargoAPI.CargoItemType.RESOURCES,"hao_zi");
			if (Quantity >= 300)
			{
				playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "hao_zi", 350);
				String weaponId = (String) Weapons[(int) (Weapons.length * Math.random())];
				Mcargo.addWeapons(weaponId, 1);
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“我已经把需要的耗子取走了，");
				Global.getSectorAPI().addMessage("现在来空间站里看看你都拿到了些什么吧。”");
			}
			else
			{
				Global.getSectorAPI().addMessage("【哈克奥罗·传颂】");
				Global.getSectorAPI().addMessage("“你没有足够的耗子，");
				Global.getSectorAPI().addMessage("等你弄够了再来吧。”");
			}
			Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES, "RWR",1);
		}
	
	}

	private static String [] RI = { 
									"R0",
									"R1",
									"R2",
									};
	private static String [] ships = { 
									"Fairy_Ripper_hull",
									"Fairy_Raven_hull",
									"Fairy_SPS-500_hull",
									};
	private static float [] hao_zi = { 
									300,
									500,
									2400,
									};
	private static String [] RI2 = { 
									"R11",
									};

	private static String [] ships2 = { 
									"Fairy_Dragonhawk_wing",
									};
	private static float [] hao_zi2 = { 
									700,
									};
	private static String [] RW = { 
								"RW0",
								"RW1",
								"RW2",
								};
	private static String [] WEAPON = { 
								"test001",
								"XL_MAC",
								"Nuclear",
								};
	private static float [] hao_zi3 = { 
									1200,
									1000,
									1500,
									};
	
	private static String [] missionA = {
									"ms11",
									"m11",
									"m12",
									"m13",
									"m14",
									};
	private static String [] missionB = {
									"bms11",
									"bm11",
									"bm12",
									"bm13",
									"bm14",
									};
	private static float [] num = { 
									1,
									11,
									12,
									13,
									14,
									};
	private static String [] Weapons = { 
									"test001",
									"XL_MAC",
									"Nuclear",
									"4800MM",
									"MAC",
									"3500MM",
									"1400HE",
									"1400MM",
									"1400AP",
									"610AP",
									"MAC_S",
									"425HE",
									"381HE",
									"305MM",
									"254MM",
									"203MM",
									"152MM",
									"175MM",
									"127MM",
									"75MM",
									"57MM",
									"75AP",
									"57AP",
									"20MM",
									"12MM",
									"Firefly_VLS",
									"Firefly_VLSS",
									"Universal_VLS",
									"Universal_VLSS",
									"Heavy_VLS",
									"Heavy_VLSS",
									"Firefly",
									"Firefly_S",
									"Universal",
									"Universal_S",
									"Heavy",
									"Heavy_S",
									"baihe",
									"baofeng",
									"bianta",
									"Firefly_Pod",
									"Firefly_PodS",
									"harpoon_VLS",
									"harpoon_VLSS",
									"baiheX2S",
									"biantaX2S",
									"baofengX2S",
									"baiheX2",
									"biantaX2",
									"baofengX2",
									"Photon",
									"PhotonS",
									"Lance",
									"pulse",
									"Ion",
									"IonS",
									"Fluctuation",
									"heavyIon",
									"Ionpulse",
									"IonpulseS",
									"FluctuationX2",
									"heavyIonX2",
									"Plasma_torpedoes",
									"Plasma_torpedoesS",
									"Particle",
									"ParticleS",
									};

}