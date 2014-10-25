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
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

public class FairyHighPirateSpawnPoint extends BaseSpawnPoint {

	public FairyHighPirateSpawnPoint(SectorAPI sector, LocationAPI location, 
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
		SectorEntityToken stationP = system.getEntityByName("秘密基地");
		SectorEntityToken corvusV = system.getEntityByName("Corvus Unknow");
		FactionAPI mission = getSector().getFaction("mission");
		float coo = mission.getRelationship("tritachyon");
		float coo1 = 3;
		float cost;
		if (coo > 4f)
		{
			cost = 0.02f;
		}
		else
		{
			cost = 0.01f;
		}
		if ( Math.random()>0.5)
		{
			stationP = rendomtonk(system);
		}
		if((float) Math.random()> cost && i1.geti() == 0)
			{
				if(i3.geti() == 0)
					{
					Global.getSectorAPI().addMessage("海盗们相邻星系聚集了巨量舰队,在进行整编之后开始向帝国进攻");
					i3.seti(1);
					}
				if(i3.geti() == 2)
					{
					Global.getSectorAPI().addMessage("海盗们开始重新稳定超空间通道，但这需要一些时间");
					i3.seti(1);
					}
					if(coo < 1)
						{
							if (coo1 < 1)
							{
								fleet = getSector().createFleet("pirates", "raiders5");
							}
							else
							{
								fleet = getSector().createFleet("pirates", "Reinforcement_fleet_1");
								if (Math.random()>0.5) {
									fleet = getSector().createFleet("pirates", "Reinforcement_fleet_1.5");
								}
							}
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 50);
						}
					if(coo < 2 && coo > 0)
						{
							if (coo1 < 1)
							{
								fleet = getSector().createFleet("pirates", "armada");
							}
							else
							{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_2");
							}
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 100);
						}
					if(coo < 3 && coo > 1)
						{
							if (coo1 < 1)
							{
								fleet = getSector().createFleet("pirates", "plunderFleet");
							}
							else
							{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_3");
							}
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 150);
						}
					if(coo < 4 && coo > 2)
						{
							if (coo1 < 1)
							{
								fleet = getSector().createFleet("pirates", "Reinforcement_fleet_3");
							}
							else
							{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_4");
							}
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 250);
						}
					if(coo < 5 && coo > 3)
						{
							if (coo1 < 1)
							{
								fleet = getSector().createFleet("pirates", "Reinforcement_fleet_4");
							}
							else
							{
								fleet = getSector().createFleet("pirates", "Reinforcement_fleet_5");
							}
							CargoAPI cargo = fleet.getCargo();
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 350);
						}
					if(coo<6 &&coo > 4)
						{
							if (coo1 < 1)
							{
								fleet = getSector().createFleet("pirates", "Reinforcement_fleet_4");
							}
							else
							{
								fleet = getSector().createFleet("pirates", "Reinforcement_fleet_6");
							}
							CargoAPI cargo = fleet.getCargo();
							fullSkill(fleet);
							cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 500);
						}
					if(coo > 5)
					{
						if (coo1 < 1)
						{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_4");
						}
						else
						{
							fleet = getSector().createFleet("pirates", "Reinforcement_fleet_7");
						}
						CargoAPI cargo = fleet.getCargo();
						fullSkill(fleet);
						cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 750);
					}
					i.seti(i.geti()+1);
					getLocation().spawnFleet(rendomtonk(system), 0, 0, fleet);
					if (coo<1) {
						getLocation().spawnFleet(rendomtonk(system), 0, 0, fleet);
					}
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
					if(coo < 4)
						return null;
					if(i1.geti() == 0)
						{
						Global.getSectorAPI().addMessage("海盗们为了保持在星系的地位，强行扩张了超空间通道，开始将数量恐怖的的混成舰队送入星系。");
						fleet2 = getSector().createFleet("Fairy", "defenseForce1");
						system.spawnFleet(station, 0f, 0f, fleet2);
						fleet2.addAssignment(FleetAssignment.DEFEND_LOCATION, corvusV, 30);
						fleet2.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
						fleet3 = getSector().createFleet("Fairy", "defenseForce");
						system.spawnFleet(station, 0f, 0f, fleet3);
						fleet3.addAssignment(FleetAssignment.DEFEND_LOCATION, corvusV, 30);
						fleet3.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
						fleet4 = getSector().createFleet("Fairy", "defenseForce");
						system.spawnFleet(station, 0f, 0f, fleet4);
						fleet4.addAssignment(FleetAssignment.DEFEND_LOCATION, corvusV, 30);
						fleet4.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 1000);
						i1.seti(1);	
						}
					if(i2.geti() == 1 && Math.random()<0.05)
					{
						Global.getSectorAPI().addMessage("为了应对海盗的攻击，前线指挥部决定启用“永夜级”战舰");
						Global.getSectorAPI().addMessage("“永夜级”作为妖夜级的修改型拥有更高的攻击性");
						Global.getSectorAPI().addMessage("本舰将锚定在妖精重工临时停靠点");
						CargoAPI cargoF = system.getEntityByName("妖精重工临时停靠点").getCargo();
						cargoF.addMothballedShip(FleetMemberType.SHIP, "Fairy_Empress_hull", null);
					}
					fleet = getSector().createFleet("pirates", "Reinforcement_fleet_6");
					fleet1 = getSector().createFleet("pirates", "Reinforcement_fleet_6");
					CargoAPI cargo = fleet.getCargo();
					cargo.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 500);
					CargoAPI cargo1 = fleet.getCargo();
					cargo1.addItems(CargoAPI.CargoItemType.WEAPONS, "hao_zi", 500);
					system.spawnFleet(getAnchor(), -500f, 0f, fleet1);
					getLocation().spawnFleet(getAnchor(), 0f, 0f, fleet);
					fleet1.addAssignment(FleetAssignment.ATTACK_LOCATION, corvusV, 12);
					fleet1.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 30);
					fleet.addAssignment(FleetAssignment.ATTACK_LOCATION, corvusV, 12);
					fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, getAnchor(), 30);
					if(i2.geti() == 8){
						Global.getSectorAPI().addMessage("临时超空间通道在崩塌之前被海盗们重新打开，他们将继续混成舰队的投入。");
						i1.seti(0);
						i2.seti(0);
						i3.seti(2);
						i.seti(40);
					}
					else
					{
						i2.seti(i2.geti()+1);
					}
				}		
		return fleet;
	}
	public void fullSkill (CampaignFleetAPI fleet){
		MutableCharacterStatsAPI skill = fleet.getCommanderStats();
		for (int i = 0; i < skillList.length; i++) {
			skill.setSkillLevel(skillList[i], 10f);
		}
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
	private String[] skillList = {
			"missile_specialization",
			"ordnance_expert",
			"damage_control",
			"target_analysis",
			"evasive_action",
			"helmsmanship",
			"flux_modulation",
			"advanced_tactics",
			"command_experience",
			"fleet_logistics",
			"gunnery_implants",
			"applied_physics",
			"flux_dynamics",
			"computer_systems",
			"construction",
			"mechanical_engineering",
			"field_repairs",
			"navigation",
	};

}
