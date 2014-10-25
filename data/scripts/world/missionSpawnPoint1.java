package data.scripts.world;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.MutableValue;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;



public class missionSpawnPoint1 extends BaseSpawnPoint {

	public missionSpawnPoint1(SectorAPI sector, LocationAPI location, 
									float daysInterval, int maxFleets, SectorEntityToken anchor) {
		super(sector, location, daysInterval, maxFleets, anchor);
	}

		StarSystemAPI system = getSector().getStarSystem("Corvus");
		SectorEntityToken CorvusI = system.getEntityByName("Corvus Unknow");
		SectorEntityToken stationM = system.addOrbitalStation(CorvusI, 225, 300, 50, "妖精重工临时停靠点", "Fairy");
		CargoAPI Mcargo = stationM.getCargo();
		SectorEntityToken star = system.getStar();
		SectorEntityToken stationH = null;
		SectorEntityToken playerfleet = null;
		CargoAPI playercargo = null;
		CargoAPI Hcargo = null;
		SectorEntityToken stationI = system.getEntityByName("企业总部");
		CargoAPI Icargo = stationI.getCargo();
		SectorEntityToken stationP = system.getEntityByName("秘密基地");
		CargoAPI Pcargo = stationP.getCargo();
		SectorEntityToken stationF = system.getEntityByName("菲雅利帝国驻Corvus星系临时办事处");
		CargoAPI Fcargo = stationF.getCargo();
		MutableValue money = null;
		SectorEntityToken token = system.createToken(15000, 15000);
		CampaignFleetAPI fleet = null;
		CampaignFleetAPI fleet1 = null;		
		CampaignFleetAPI fleet2 = null;
		CampaignFleetAPI fleet3 = null;
		CampaignFleetAPI fleet4 = null;
		Fairyintt num = new Fairyintt();
		Fairyintt limitday = new Fairyintt();
		Fairyintt day = new Fairyintt();
		Fairyintt i = new Fairyintt();
		Fairyintt i1 = new Fairyintt();
		Fairyintt i2 = new Fairyintt();
		Fairyintt SW = new Fairyintt();
		Fairyintt FP = new Fairyintt();
		Fairyintt FP1 = new Fairyintt();
		Fairyintt FP2 = new Fairyintt();
		Fairyintt FP3 = new Fairyintt();
		Fairyintt FP4 = new Fairyintt();
		Fairyintt ID = new Fairyintt();
		FactionAPI mission = getSector().getFaction("mission");
		CampaignClockAPI clock = null;
		private long lastSpawnTime = Long.MIN_VALUE;
		boolean ME = true;

	public CampaignFleetAPI spawnFleet() {
		if(num.geti()==0){
				stationH = system.getEntityByName("轨道空间站");
				Hcargo = stationH.getCargo();
				Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"ms11",1);
				Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"R0",1);
				Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"R1",1);
				num.seti(1);
				clock = getSector().getClock();
		}
		playerfleet = system.getEntityByName("Fleet");
		playercargo = playerfleet.getCargo();
		money = playercargo.getCredits();


		//任务发起部分
		if (mission.getRelationship("hegemony") == 1 && ID.geti() == 0)//剧情线1-初次见面
		{
			mission.setRelationship("hegemony", -1);
			Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】：");
			Global.getSectorAPI().addMessage("“有些事情我们可以谈谈，指挥官。");
			Global.getSectorAPI().addMessage("如你所见，我们刚刚来到这里，");
			Global.getSectorAPI().addMessage("而我们现在需要一个可以信任的代理人来作为我们和本地势力之间的桥梁。");
			Global.getSectorAPI().addMessage("你或许是其中之一，");
			Global.getSectorAPI().addMessage("不过你需要向我们证明你是值得信任的。");
			Global.getSectorAPI().addMessage("我已经把一份货物放到了你的货舱中，");
			Global.getSectorAPI().addMessage("你需要把它运送到霸主的轨道空间站。”");
			playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,"mi01",1);
			fleet = getSector().createFleet("pirates-m", "raiders5");
			fleet1 = getSector().createFleet("pirates-m", "raiders5");
			fleet2 = getSector().createFleet("pirates-m", "raiders5");
			system.spawnFleet(stationH, 0f, 0f, fleet1);
			system.spawnFleet(stationH, 0f, 0f, fleet2);
			system.spawnFleet(stationH, 0f, 0f, fleet);
			fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, stationH, 20);
			fleet1.addAssignment(FleetAssignment.DEFEND_LOCATION, stationH, 20);
			fleet2.addAssignment(FleetAssignment.DEFEND_LOCATION, stationH, 20);
			start(1,9);

			

		}//剧情线1启动结束

		//任务监视&结算部分
		if(ID.geti() == 1)//剧情线1-初次见面
		{
			ME = Hcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi01", 1);//检测任务结束
			if(ME)
			{
				 boolean end = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bms11", 1);
				 Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				 Global.getSectorAPI().addMessage("“很好，果然我的眼光是没错的");
				 Global.getSectorAPI().addMessage("拿去吧，这是你应得的。”");
				 Global.getSectorAPI().addMessage("【你获得了2000元任务报酬】");
				 money.add(2000);
				 Global.getSectorAPI().addMessage("【你获得了5只香喷喷的小耗子】");
				 playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,"hao_zi",5);
				 Global.getSectorAPI().addMessage("“你可以用这个来我这里换取一些特别的东西。”");
				 Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"R2",1);
//				 Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"R11",1);
				 Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"RW0",1);
				 Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"RW1",1);
				 Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"RW2",1);
				 Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"RWR",1);
				 fleet.clearAssignments();
				 fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				 fleet1.clearAssignments();
				 fleet1.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				 fleet2.clearAssignments();
				 fleet2.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				 fleet = null;
				 fleet1 = null;
				 fleet2 = null;
				 reflash1();
			}
			if(clock.getElapsedDaysSince(lastSpawnTime) >= limitday.geti() || Mcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bms11", 1))//任务失败判定
			{
				boolean BADend = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bms11", 1);
				boolean bad = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi01", 1);
				Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				Global.getSectorAPI().addMessage("“你的动作太慢了，我想我还是去找别人来吧。");
				Global.getSectorAPI().addMessage("相信会有其他人愿意接下这份工作的。”");
				Global.getSectorAPI().addMessage("【任务失败】");
				fleet.clearAssignments();
				fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				fleet1.clearAssignments();
				fleet1.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				fleet2.clearAssignments();
				fleet2.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				fleet = null;
				fleet1 = null;
				fleet2 = null;
                Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"ms11",1);
				ID.seti(0);
				mission.setRelationship("hegemony", 0);
			}
		}
		//剧情线1判定结束
////////////////////////////////////////////////////////////////////////////////////////////////
		if (mission.getRelationship("hegemony") == 11 && ID.geti() == 0)//自由任务1-小麻烦
		{
			mission.setRelationship("hegemony", -1);
			Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】：");
			Global.getSectorAPI().addMessage("“指挥官，我留了个活给你。");
			Global.getSectorAPI().addMessage("最近总有一支海盗舰队袭击我们的补给舰队，");
			Global.getSectorAPI().addMessage("我已经把他们在地图上标记出来了，击退他们。");
			Global.getSectorAPI().addMessage("等你完成的时候我会再联系你的。”");
			fleet = getSector().createFleet("pirates-m", (String) xmf[(int) (xmf.length * Math.random())]);
			system.spawnFleet(stationM, 500f, 0f, fleet);
			fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, stationM, 20);
			lastSpawnTime = clock.getTimestamp();
			start(11,20);
			FP.seti((int) fleet.getFleetData().getFleetPointsUsed());
			

		}//自由1启动结束

		if(ID.geti() == 11)
		{
			ME = fleet.isAlive();//检测任务结
			if(!ME || ((int) fleet.getFleetData().getFleetPointsUsed()) <(FP.geti()*0.25))
			{
				 boolean end = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm11", 1);
				 Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				 Global.getSectorAPI().addMessage("“做的很好，");
				 Global.getSectorAPI().addMessage("起码这群海盗短时间内是无法骚扰我们了。”");
				 Global.getSectorAPI().addMessage("【你获得了500元任务报酬】");
				 money.add(500);
				 Global.getSectorAPI().addMessage("【你获得了8只香喷喷的小耗子】");
				 playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,"hao_zi",8);
				 SW.seti(SW.geti()+1);
				 fleet = null;
				 reflash1();
			}
			if(clock.getElapsedDaysSince(lastSpawnTime) >= limitday.geti() || Mcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm11", 1))//任务失败判定
			{
				boolean BADend = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm11", 1);
				Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				Global.getSectorAPI().addMessage("“你的动作太慢了，我想我还是去找别人来吧。");
				Global.getSectorAPI().addMessage("相信会有其他人愿意接下这份工作的。”");
				Global.getSectorAPI().addMessage("【任务失败】");
				fleet.clearAssignments();
				fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				fleet = null; 
				reflash1();
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////
		if (mission.getRelationship("hegemony") == 12 && ID.geti() == 0)//自由任务2-借刀杀人
		{			
			Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】：");
			Global.getSectorAPI().addMessage("“指挥官，有个任务有些棘手");
			Global.getSectorAPI().addMessage("我们收到了一份霸主发来的消息，");
			Global.getSectorAPI().addMessage("上面说有一支叛逃的霸主巡逻队准备投靠宇宙海盗。");
			Global.getSectorAPI().addMessage("这群叛军手里握有霸主整个防卫的部署图，毫无疑问这将动摇霸主在这个星系的地位。");
			Global.getSectorAPI().addMessage("但是霸主的那些死脑筋们不愿意公开征集内部出了叛徒这种事。");
			Global.getSectorAPI().addMessage("所以去击败他们吧，这对你我都有好处。”");
			fleet = getSector().createFleet("pirates-m", "patrol");
			rendomtonk();
			system.spawnFleet(token, 0, 0, fleet);
			fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, token, 25);
			start(12,25);
			FP.seti((int) fleet.getFleetData().getFleetPointsUsed());
			

		}//自由2启动结束
		if(ID.geti() == 12)
		{
			ME = fleet.isAlive();//检测任务结束
			if(!ME || ((int) fleet.getFleetData().getFleetPointsUsed() < (FP.geti()*0.25)))
			{
				 boolean end = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm12", 1);
				 Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				 Global.getSectorAPI().addMessage("“完成的不错，");
				 Global.getSectorAPI().addMessage("这样霸主的那群老头子就又欠了我们一个人情。”");
				 Global.getSectorAPI().addMessage("【你获得了1500元任务报酬】");
				 money.add(1500);
				 Global.getSectorAPI().addMessage("【你获得了20只香喷喷的小耗子】");
				 playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,"hao_zi",20);
				 SW.seti(SW.geti()+2);
				 fleet = null;
				 reflash1();
			}
			if(clock.getElapsedDaysSince(lastSpawnTime) >= limitday.geti() || Mcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm12", 1))//任务失败判定
			{
				boolean BADend = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm11", 1);
				Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				Global.getSectorAPI().addMessage("“你的动作太慢了，我想我还是去找别人来吧。");
				Global.getSectorAPI().addMessage("相信会有其他人愿意接下这份工作的。”");
				Global.getSectorAPI().addMessage("【任务失败】");
				fleet.clearAssignments();
				fleet.addAssignment(FleetAssignment.GO_TO_LOCATION_AND_DESPAWN, star, 30);
				fleet = null; 
				reflash1();
				
			}
		}


////////////////////////////////////////////////////////////////////////////////////////////////

		if (mission.getRelationship("hegemony") == 13 && ID.geti() == 0)//自由任务3-运动有益健康
		{
			mission.setRelationship("hegemony", -1);
			Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】：");
			Global.getSectorAPI().addMessage("“来说说工作的事情吧。");
			Global.getSectorAPI().addMessage("我们为了让舰船更适应这里的环境，从速子科技那里订购了一批舰船零件。");
			Global.getSectorAPI().addMessage("不过速子科技的那个蛋疼的家伙死活不肯把这些东西运过来。");
			Global.getSectorAPI().addMessage("而碰巧我们的所有货船都已经有工作了，所以就交给你了。");
			Global.getSectorAPI().addMessage("而且偶尔像这样运动运动对你的健康是好处的。");
			Global.getSectorAPI().addMessage("好了，把这些零件从速子总部运到帝国办事处，不要搞错地方了。”");
			Icargo.addItems(CargoAPI.CargoItemType.RESOURCES,"mi02",1);
			start(13,24);

			

		}//剧情线1启动结束
		if(ID.geti() == 13)//自由任务3-运动有益健康
		{
			ME = Fcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi02", 1);//检测任务结束
			if(ME)
			{
				 boolean end = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm13", 1);
				 Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				 Global.getSectorAPI().addMessage("“嗯，你看上去气色不错。");
				 Global.getSectorAPI().addMessage("这是你的报酬，我们要开始着手改造战舰了”");
				 Global.getSectorAPI().addMessage("【你获得了6000元任务报酬】");
				 money.add(6000);
				 Global.getSectorAPI().addMessage("【你获得了4只香喷喷的小耗子】");
				 playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,"hao_zi",4);
				 reflash1();
			}
			if(clock.getElapsedDaysSince(lastSpawnTime) >= limitday.geti() || Mcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm13", 1))//任务失败判定
			{
				boolean BADend = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm13", 1);
				boolean bad = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi02", 1);
				Icargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi02", 1);
				Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				Global.getSectorAPI().addMessage("“你的动作太慢了，我想我还是去找别人来吧。");
				Global.getSectorAPI().addMessage("相信会有其他人愿意接下这份工作的。”");
				Global.getSectorAPI().addMessage("【任务失败】");
				reflash1();

			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////



		if (mission.getRelationship("hegemony") == 14 && ID.geti() == 0)//自由任务4-你的就是我的
		{
			mission.setRelationship("hegemony", -1);
			Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】：");
			Global.getSectorAPI().addMessage("“来做点有意思的事情吧。");
			Global.getSectorAPI().addMessage("我们得到情报，海盗在几天前洗劫了一支霸主商队。");
			Global.getSectorAPI().addMessage("并且把上面还活着的船员都作为人质绑架到了他们的基地里。");
			Global.getSectorAPI().addMessage("去把他们都救出来，这对于我们和霸主直接改善关系是有益的。");
			Global.getSectorAPI().addMessage("好了，动手吧，把被绑架的人送回他们的空间站吧。”");
			Pcargo.addItems(CargoAPI.CargoItemType.RESOURCES,"mi03",1);
			i.seti(0);
			start(14,15);

			

		}//
		if(ID.geti() == 14)//自由任务-你的就是我的
		{
			if(playercargo.getQuantity(CargoAPI.CargoItemType.RESOURCES,"mi03")>0 && i.geti()==0)
			{
				Global.getSectorAPI().addMessage("【被救起的霸主员工】");
				Global.getSectorAPI().addMessage("谢谢你救了我，现在请把我送到霸主轨道空间站去");
				Global.getSectorAPI().addMessage("在那里我会稍微觉得安全些。");
				i.seti(1);
			}
			if(Hcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi03", 1))
			{
				 boolean end = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm14", 1);
				 Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				 Global.getSectorAPI().addMessage("“干的不错，我们和霸主交涉的时候又多了一个筹码。");
				 Global.getSectorAPI().addMessage("这是你的报酬，和你合作很愉快”");
				 Global.getSectorAPI().addMessage("【你获得了5000元任务报酬】");
				 money.add(5000);
				 Global.getSectorAPI().addMessage("【你获得了10只香喷喷的小耗子】");
				 playercargo.addItems(CargoAPI.CargoItemType.RESOURCES,"hao_zi",10);
				 i.seti(0);
				 reflash1();
			}
			if(clock.getElapsedDaysSince(lastSpawnTime) >= limitday.geti() || Mcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm14", 1))//任务失败判定
			{
				boolean BADend = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "bm14", 1);
				boolean bad = playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi03", 1);
				Pcargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "mi03", 1);
				Global.getSectorAPI().addMessage("【阿尼亚·萝蕾拉】");
				Global.getSectorAPI().addMessage("“你的动作太慢了，我想我还是去找别人来吧。");
				Global.getSectorAPI().addMessage("相信会有其他人愿意接下这份工作的。”");
				Global.getSectorAPI().addMessage("【任务失败】");
				i.seti(0);
				reflash1();

			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////

		return null;
	}
	public void start(int MID,int day)
	{
			lastSpawnTime = clock.getTimestamp();
			limitday.seti(day);
			ID.seti(MID);
			mission.setRelationship("hegemony", -1);
	}
	public void reflash1()
	{
			String miss = (String) M1[(int) (M1.length * Math.random())];
            Mcargo.addItems(CargoAPI.CargoItemType.RESOURCES,miss,1);
			ID.seti(0);
			mission.setRelationship("hegemony", 0);
	}
	private static String [] M1 = { 
									"m11",
									"m12",
									"m13",
									"m14",
								  };
	private static String [] xmf = { 
									"raiders2",
									"raiders3",
									"raiders4",
								  };
					
	public void rendomtonk()
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
		token = system.createToken((float)(8000*Math.random()*x[0]), (float)(8000*Math.random()*x[1]));
	}
}






