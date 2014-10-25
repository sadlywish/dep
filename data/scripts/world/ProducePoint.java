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

public class ProducePoint extends BaseSpawnPoint 
{
	public final FairyBUSS buss;
	public final String bu[];
	public final String ss[];
	public final String bu_b[][];

	public ProducePoint(SectorAPI sector, LocationAPI location, 
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
		//产生货物
		for (int a = 0;a<ss.length ; a++)
		{
			if(system.getEntityByName(ss[a]) == null)
			{
				continue;
			}
			int day = buss.skipday(a);
			for (int b = 0; b< day; b++ )
			{
				Produce(system.getEntityByName(ss[a]), a);
			}
		}
		//刷新货物状态
		for (int a = 0;a<ss.length ; a++)
		{
			if(system.getEntityByName(ss[a]) == null){
				continue;
			}
			blance(system.getEntityByName(ss[a]), a);
			updata(system.getEntityByName(ss[a]) , playerfleet , a);
			Consume(system.getEntityByName(ss[a]), a);
			blance(system.getEntityByName(ss[a]), a);
		}


		return null;

	}
	public void Produce(SectorEntityToken station,int ID)
	{
		CargoAPI Cargo = station.getCargo();
		int ii[] = buss.getLL (ID);
		if(buss.isin(ID))
		{		
			for (int a = 0; a< ii.length; a++)
			{
				if (buss.isneed(ID, bu[a]))
				{
					int num = (int)Cargo.getQuantity(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]]);
					Cargo.addItems(CargoAPI.CargoItemType.RESOURCES,bu_b[a][ii[a]], (float)buss.getProduce(ID, bu[a]) );
					//Global.getSectorAPI().addMessage(buss.getProduce(ID, bu[a])+"份货物已经从生产线流入了"+ss[ID]+"市场");

				}
			}

			return;
		}
		else
		{

 			for (int a = 0; a< ii.length; a++)
			{
				if (buss.isneed(ID, bu[a]))
				{	
					int num = (int)Cargo.getQuantity(CargoAPI.CargoItemType.RESOURCES, bu[a]);
					Cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu[a], ((float)buss.getProduce(ID, bu[a])));
					//Global.getSectorAPI().addMessage(buss.getProduce(ID, bu[a])+"份货物已经从生产线流入了市场");

				}
			}
			return;
		}
	}

	public void blance(SectorEntityToken station, int ID)//市场仓库双方向价格平易
	{
		CargoAPI Cargo = station.getCargo();
		int ii[] = buss.getLL (ID);
		if(buss.isin(ID))
		{
			for (int a=0;a<ii.length;a++)
			{
				int num = (int)Cargo.getQuantity(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]]);
				int change = buss.blanceCargo(ID,bu[a],num);	
				//Global.getSectorAPI().addMessage(buss.blancemult(ID,bu[a],num));			
				if (change<0)
				{
					Cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], -change); 

				}
				else
				{
					Cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], change);
				}
				//Global.getSectorAPI().addMessage(num+"/"+change);
			}
		}
		else
		{
			for (int a=0;a<ii.length;a++)
			{
				int num = (int)Cargo.getQuantity(CargoAPI.CargoItemType.RESOURCES, bu[a]);
				int change = buss.blanceCargo(ID,bu[a],num);
				//Global.getSectorAPI().addMessage(buss.blancemult(ID,bu[a],num));
				if (change<0)
				{
					int tool = 0;
					Cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu[a], -change);

				}
				else
				{
					Cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu[a], change);
				}	
				//Global.getSectorAPI().addMessage(num+"/"+change);
			}
		}


	}
	public void updata(SectorEntityToken station, SectorEntityToken playerfleet, int ID)
	{
		CargoAPI Cargo = station.getCargo();
		CargoAPI PlayerCargo = playerfleet.getCargo();
		int ii[] = buss.getLL (ID);
		if(buss.isin(ID))
		{
			//flash(station, ID);
			for (int a=0;a<ii.length;a++)
			{
				int stata = buss.getStatus(ID, bu[a]);
				int num = (int)Cargo.getQuantity(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]]);
				int Status = buss.checkStatus(ID, bu[a], num);
				//Global.getSectorAPI().addMessage(""+Status);
				if (stata != Status)
				{
					int tool = 0;
					int ii2[] = buss.getLL(ID);
					while (Cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
						{
							tool++;
						}
					Cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)buss.getvalue(ID,bu[a])], tool);
					tool =0;
					while (PlayerCargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
						{
							tool++;
						}
					PlayerCargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)buss.getvalue(ID,bu[a])], tool);

				}
				if (stata != Status && (Status == 0 || Status ==5))
				{
//					Global.getSectorAPI().addMessage(""+ss[ID]+"市场上的"+buss.getfullname(ID, bu[a])+""+stat[Status]);
				}
			}
		}
		else
		{
			for (int a=0;a<ii.length;a++)
			{
				int stata = buss.getStatus(ID, bu[a]);
				int num = (int)Cargo.getQuantity(CargoAPI.CargoItemType.RESOURCES, bu[a]);
				int Status = buss.checkStatus(ID, bu[a], num);
				//Global.getSectorAPI().addMessage(""+Status);
				//Global.getSectorAPI().addMessage(""+Status);
			/*
				if (stata != Status)
				{
					int tool = 0;
					int ii2[] = buss.getLL(ID);
					while (Cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu[a], 1)) 
						{
							tool++;
						}
					Cargo.addItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)buss.getvalue(ID,bu[a])], tool);
				}
				*/
				if (stata != Status && (Status == 0 || Status ==5))
				{
//					Global.getSectorAPI().addMessage(""+ss[ID]+"市场上的"+buss.getfullname(ID, bu[a])+""+stat[Status]);
				}
			}
		}


	}
	public void Consume(SectorEntityToken station, int ID)
	{
		CargoAPI Cargo = station.getCargo();
		int ii[] = buss.getLL (ID);
		int tool = 0;
		if(buss.isin(ID))
		{
			//flash(station, ID);
			for (int a=0;a<ii.length;a++)
			{
				int spend =buss.getConsume(ID ,bu[a]);
				while (Cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu_b[a][(int)ii[a]], 1)) 
				{
					tool++;
					if (tool >= spend)
					{
						break;
					}
				}
				//Global.getSectorAPI().addMessage(buss.mult(ID,bu[a]));
				//Global.getSectorAPI().addMessage(""+tool);
				if(buss.addexp(ID,bu[a],tool))
				{
					Global.getSectorAPI().addMessage(ss[a]+"已发展为"+buss.getlv(ID)+"级空间站");
				}
				tool = 0;
			}
		}
		else
		{
			for (int a=0;a<ii.length;a++)
			{
				int spend =buss.getConsume(ID ,bu[a]);
				while (Cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, bu[a], 1)) 
				{
					tool++;
					if (tool >= spend)
					{
						break;
					}
				}
				//Global.getSectorAPI().addMessage(buss.mult(ID,bu[a]));
				//Global.getSectorAPI().addMessage(""+tool);
				if(buss.addexp(ID,bu[a],tool))
				{
					Global.getSectorAPI().addMessage(ss[ID]+"已发展为"+buss.getlv(ID)+"级空间站");
				}
				tool = 0;

			}
		}
	}
	public static String [] stat = {
									"由于库存过度积压，已进入饱和状态",
									"的储量已超过市场消耗，进入积压状态",
									"的储量已回归正常水平",
									"的储量已回归正常水平",
									"的储量储量已无法满足空间站的需求，部分商人开始借机哄抬物价",
									"的储量已低于警戒值，空间站已采取紧急措施应对可能发生的资源短缺",
	};

	
}