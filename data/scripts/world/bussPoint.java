package data.scripts.world;
import java.util.List;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;
import data.scripts.world.Fairyintt;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class bussPoint extends BaseSpawnPoint 
{
	public final FairyBUSS buss;
	public final String bu[];
	public final String ss[];
	public final String bu_b[][];
	public bussPoint(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor,
									FairyBUSS buss) {
		super(sector, location, daysInterval, maxFleets, anchor);
		this.buss = buss;
		bu = buss.getnormallist();
		ss = buss.getstationlist();
		bu_b = buss.getvaluelist();
	}
	boolean InArea = false;
	Fairyintt one = new Fairyintt();
	Fairyintt area = new Fairyintt();

	@Override
	public CampaignFleetAPI spawnFleet() 
	{
		FactionAPI mission = getSector().getFaction("mission");
		StarSystemAPI system = getSector().getStarSystem("Corvus");
		SectorEntityToken playerfleet = system.getEntityByName("Fleet");

		//进站检查
		if(!InArea){
			for (int a = 0;a<ss.length ; a++)
			{
				if(system.getEntityByName(ss[a]) == null){
					continue;
				}
				inArea(playerfleet, system.getEntityByName(ss[a]), a);
			}
		}
		//货物交换
		if(InArea){ 
			//Change(playerfleet, system.getEntityByName(ss[area.geti()]), area.geti());		
			OutArea(playerfleet, system.getEntityByName(ss[area.geti()]), area.geti());
		}
		//离站检查


		return null;

	}
	public void inArea(SectorEntityToken fleet, SectorEntityToken station, int id)//进出站判断
	{
		Vector2f fleetLocation = fleet.getLocation();
		Vector2f stationLocation = station.getLocation();
		float Range = (float)Math.sqrt((fleetLocation.getX() - stationLocation.getX()) * (fleetLocation.getX() - stationLocation.getX()) + (fleetLocation.getY() - stationLocation.getY()) * (fleetLocation.getY() - stationLocation.getY()));
		if (Range <= 300f)
		{
			InArea = true;
			area.seti(id);
			INinti(fleet, station, id);
			buss.in(id);
		}
	}
	public void OutArea(SectorEntityToken fleet, SectorEntityToken station, int id)//进出站判断
	{
		Vector2f fleetLocation = fleet.getLocation();
		Vector2f stationLocation = station.getLocation();
		float Range = (float)Math.sqrt((fleetLocation.getX() - stationLocation.getX()) * (fleetLocation.getX() - stationLocation.getX()) + (fleetLocation.getY() - stationLocation.getY()) * (fleetLocation.getY() - stationLocation.getY()));
		if(Range > 300f ){
			InArea = false;
			OUTinti(fleet, station, id);
			buss.out();
		}
	}

	public void INinti(SectorEntityToken fleet, SectorEntityToken station, int id)//进站初始化
	{
		//CampaignFleetAPI aaa = getSector().getPlayerFleet();
		//FleetDataAPI fll =  aaa.getFleetData();
		//List feel =  fll.getMembersListCopy();
		CargoAPI cargoS = station.getCargo();
		CargoAPI cargoP = fleet.getCargo();
		int ii[] = buss.getLL (id);
		float tool = 0;
		for(int a=0;a<ii.length;a++)//初始化玩家
		{
			while (cargoP.removeItems(CargoAPI.CargoItemType.RESOURCES, bu[a], 1)) 
			{
				tool++;
			}
			cargoP.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], tool);
			//Global.getSectorAPI().addMessage(""+tool);

			tool=0;
		}
		for(int a=0;a<ii.length;a++)//初始化空间站
		{
			while (cargoS.removeItems(CargoAPI.CargoItemType.RESOURCES, bu[a], 1)) 
			{
				tool++;
			}
			cargoS.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], tool);
			tool=0;
		}			
		Global.getSectorAPI().addMessage("您的舰船已成功与"+ss[id]+"的商务系统链接，");
		Global.getSectorAPI().addMessage("您货舱中的货物已完成标价。");
		/*FleetMemberAPI ccc;
		for (int b = 0;b<feel.size() ;b++ )
		{
			if (feel.get(b)==null)
			{
				continue;
			}
			ccc = (FleetMemberAPI) feel.get(b);
			Global.getSectorAPI().addMessage(ccc.getId());

		}
		*/
		
	}


	public void OUTinti(SectorEntityToken fleet, SectorEntityToken station, int id)//离站初始化
	{
		CargoAPI cargoS = station.getCargo();
		CargoAPI cargoP = fleet.getCargo();
		int ii[] = buss.getLL (id);
		float tool = 0;
		for(int a=0;a<ii.length;a++)//初始化玩家
		{
			while (cargoP.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
			{
				tool++;
			}
			cargoP.addItems(CargoAPI.CargoItemType.RESOURCES, bu[a], tool);
			//Global.getSectorAPI().addMessage(""+tool);
			tool=0;
		}
		for(int a=0;a<ii.length;a++)//初始化空间站
		{
			while (cargoS.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
			{
				tool++;
			}
			cargoS.addItems(CargoAPI.CargoItemType.RESOURCES, bu[a], tool);
			//Global.getSectorAPI().addMessage(""+tool);
			tool=0;
		}
		Global.getSectorAPI().addMessage("您的舰船已断开与"+ss[id]+"的商务系统之间的链接，");

	}

/*
	public void Change(SectorEntityToken fleet, SectorEntityToken station, int id)//站内货物兑换
	{
		CargoAPI cargoS = station.getCargo();
		CargoAPI cargoP = fleet.getCargo();
		int ii[] = buss.getLL (id);
		float tool = 0;
		for(int a=0;a<ii.length;a++)//结算玩家
		{
			while (cargoP.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
			{
				tool++;
			}
			cargoP.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], tool);
			if(tool>0){
				//Global.getSectorAPI().addMessage(""+tool);
			}
			tool=0;
		}
		for(int a=0;a<ii.length;a++)//结算空间站
		{
			while (cargoS.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
			{
				tool++;
			}
			cargoS.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], tool);
			if(tool>0){
				//Global.getSectorAPI().addMessage(""+tool);
			}
			tool=0;
		}
	}
	*/
	}