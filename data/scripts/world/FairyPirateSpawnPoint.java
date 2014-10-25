package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import data.scripts.world.Fairyintt;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;

public class FairyPirateSpawnPoint extends BaseSpawnPoint {

	public FairyPirateSpawnPoint(SectorAPI sector, LocationAPI location, 
							float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	
	}
	Fairyintt i = new Fairyintt();
	Fairyintt i1 = new Fairyintt();
	Fairyintt i2 = new Fairyintt();
	Fairyintt i3 = new Fairyintt();
	@Override
		protected
		 CampaignFleetAPI spawnFleet() {
		CampaignFleetAPI fleet = null;
		CampaignFleetAPI fleet1 = null;		
		CampaignFleetAPI fleet2 = null;
		CampaignFleetAPI fleet3 = null;
		CampaignFleetAPI fleet4 = null;
		StarSystemAPI system = getSector().getStarSystem("Corvus");
		SectorEntityToken station = system.getEntityByName("菲雅利帝国驻Corvus星系临时办事处");
		SectorEntityToken corvusV = system.getEntityByName("Corvus Unknow");
		SectorEntityToken tonk = rendomtonk(system);
		FactionAPI mission = getSector().getFaction("mission");
		float coo = mission.getRelationship("tritachyon");

		if((float) Math.random()>0.02f && i1.geti() == 0)
			{
			if((float) Math.random()>0.6f)
				{
				if(i3.geti() == 0)
					{
					Global.getSectorAPI().addMessage("海盗们在本星系和相邻星系之间开辟了一条超空间通道,开始向这里输送舰队");
					i3.seti(1);
					}
				if(i3.geti() == 2)
					{
					Global.getSectorAPI().addMessage("海盗们开始重新稳定超空间通道，但这需要一些时间");
					i3.seti(1);
					}
					if(coo < 1)
						{
							fleet = getSector().createFleet("pirates", "raiders5");
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 20);
						}
					if(coo < 2 && coo > 0)
						{
							fleet = getSector().createFleet("pirates", "plunderFleet");
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 50);
						}
					if(coo < 3 && coo > 1)
						{
							fleet = getSector().createFleet("pirates", "armada");
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 80);
						}
					if(coo < 4 && coo > 2)
						{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_3");
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 150);
						}
					if(coo < 5 && coo > 3)
						{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_4");
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 250);
						}
					if(coo < 6 && coo > 4)
					{
						fleet = getSector().createFleet("pirates", "Reinforcement_fleet_5");
						CargoAPI cargo = fleet.getCargo();
						cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 350);
					}
					if(coo > 5)
						{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_6");
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 550);
						}
					getLocation().spawnFleet(tonk, 0, 0, fleet);

					if((float) Math.random() > 0.7f)
						{
							fleet.addAssignment(FleetAssignment.ATTACK_LOCATION, corvusV, 12);
						}
					else
						{
							fleet.addAssignment(FleetAssignment.RAID_SYSTEM,null, 12);
						}
							fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 30);
				}
			else
				{
				return null;
				}
			}
			else
				{	
					if(coo < 3)
						return null;
					if(i1.geti() == 0)
						{
					Global.getSectorAPI().addMessage("海盗们为了保持在星系的地位，强行扩张了超空间通道，开始向本星系输送大批的舰队。");
					fleet2 = getSector().createFleet("Fairy", "defenseForce1");
					system.spawnFleet(station, 0f, 0f, fleet2);
					fleet2.addAssignment(FleetAssignment.DEFEND_LOCATION, corvusV, 30);
					fleet2.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
					fleet1 = getSector().createFleet("Fairy", "defenseForce");
					system.spawnFleet(station, 0f, 0f, fleet1);
					fleet1.addAssignment(FleetAssignment.DEFEND_LOCATION, corvusV, 30);
					fleet1.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
					i1.seti(1);	
						}
					if(i2.geti() == 1)
					{
//						Global.getSectorAPI().addMessage("为了应对海盗的攻击，帝国舰队增调了一艘妖夜级战斗母舰加入了战局");
//						Global.getSectorAPI().addMessage("拥有足够能力控制该舰的指挥官可以付出相应的代价以获得该舰的临时指挥权。");
//						Global.getSectorAPI().addMessage("妖夜级母舰将成为你舰队中最为可靠的中间力量");
//						CargoAPI cargo = station.getCargo();
//						cargo.addMothballedShip(FleetMemberType.SHIP, "Fairy_Enchantress_hull", null);
					}
					fleet = getSector().createFleet("pirates", "Reinforcement_fleet_5");
					getLocation().spawnFleet(getAnchor(), 0f, 0f, fleet);
					fleet.addAssignment(FleetAssignment.ATTACK_LOCATION, corvusV, 12);
					fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 30);
					CargoAPI cargo = fleet.getCargo();
					cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 500);
					if(i2.geti() == 8)
					{
						Global.getSectorAPI().addMessage("海盗们建立的临时超空间通道崩塌了，未来一段时间内海盗只能通过这个超空间通道输送很少的舰队");
						i1.seti(0);
						i2.seti(0);
						i3.seti(2);
						i.seti(30);
					}
					else
					{
						i2.seti(i2.geti()+1);
					}
				}		
		return fleet;
	}
	public SectorEntityToken rendomtonk(StarSystemAPI system)
	{
		int [] x={1,1};
		for(int c=0;c<2;c++)
		{
		if (Math.random()>0.5f)
		{
			x[c]=1;
		}else
		{
			x[c]=-1;
		}
		}
		return system.createToken((float)(10000*Math.random()*x[0]), (float)(10000*Math.random()*x[1]));
	}
}
