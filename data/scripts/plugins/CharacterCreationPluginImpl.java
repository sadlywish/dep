package data.scripts.plugins;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.characters.CharacterCreationPlugin;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.campaign.CargoAPI;

public class CharacterCreationPluginImpl implements CharacterCreationPlugin {
	public static int faction;
	public static int campLevel;
	public static class ResponseImpl implements Response {
		private String text;
		public ResponseImpl(String text) {
			this.text = text;
		}
		public String getText() {
			return text;
		}
	}
	
	// Not using an enum for this because Janino doesn't support it.
	// It does, apparently, support using enums defined elsewhere - just can't compile new ones.
//	private ResponseImpl SUPPLY_OFFICER = new ResponseImpl("Served as a junior supply officer in an independent system's navy");
	private ResponseImpl SUPPLY_OFFICER = new ResponseImpl("在一个独立星系的海军中担任初级后勤官");
//	private ResponseImpl GUNNER = new ResponseImpl("Hired out as a gunner on a mercenary ship");
	private ResponseImpl GUNNER = new ResponseImpl("在一艘雇佣兵舰船上担任炮手");
//	private ResponseImpl ENGINEER = new ResponseImpl("Found employment as an assistant engineer on an exploration vessel");
	private ResponseImpl ENGINEER = new ResponseImpl("在一个助理工程师的勘探船上谋了份差事");
//	private ResponseImpl COPILOT = new ResponseImpl("Spent time as a co-pilot on a patrol ship in an independent system");
	private ResponseImpl COPILOT = new ResponseImpl("在一个独立星系中做了一阵子某条巡逻艇的副驾驶");
//	private ResponseImpl SOMETHING_ELSE_1 = new ResponseImpl("Did something else");
	private ResponseImpl SOMETHING_ELSE_1 = new ResponseImpl("什么也没做");
//	private ResponseImpl ADJUTANT = new ResponseImpl("Served as an adjutant in the Hegemony Navy");
	private ResponseImpl ADJUTANT = new ResponseImpl("在霸主海军中担任副官");
//	private ResponseImpl QUARTERMASTER = new ResponseImpl("Performed the duties of a quartermaster on an independent warship");
	private ResponseImpl QUARTERMASTER = new ResponseImpl("在一条独立战舰上担任军需官");
//	private ResponseImpl HELM = new ResponseImpl("Helmed a patrol ship operating in a backwater system");
	private ResponseImpl HELM = new ResponseImpl("在死水星系指挥一艘巡逻艇");
//	private ResponseImpl COMBAT_ENGINEER = new ResponseImpl("Took over the duties of chief combat engineer during a lengthy campaign");
	private ResponseImpl COMBAT_ENGINEER = new ResponseImpl("在一场漫长的战役中担任首席战斗工程师");
//	private ResponseImpl SOMETHING_ELSE_2 = new ResponseImpl("Did something else");
	private ResponseImpl SOMETHING_ELSE_2 = new ResponseImpl("什么也没做");
	
	private ResponseImpl faction_fairy = new ResponseImpl("菲雅利帝国(帝国阵营)");
	private ResponseImpl faction_hegemony = new ResponseImpl("霸主(轴心阵营)");
	private ResponseImpl faction_independent = new ResponseImpl("自由贸易联盟(轴心阵营)");
	private ResponseImpl faction_pirates = new ResponseImpl("自由之翼(挑战者阵营)");
	private ResponseImpl faction_tritachyon = new ResponseImpl("速子科技(挑战者阵营)");
	
	private ResponseImpl level1 = new ResponseImpl("1级");
	private ResponseImpl level3 = new ResponseImpl("3级");
	private ResponseImpl level5 = new ResponseImpl("5级");
	private ResponseImpl level7 = new ResponseImpl("7级");
	private ResponseImpl level10 = new ResponseImpl("10级");

	
	private ResponseImpl next = new ResponseImpl("确认");
	
	private int stage = 0;
	private String [] prompts = new String [] {
		"在之前的职业生涯中，你...",
		"最近，你...",
		"请选择你的初始舰队等级\n"
		+"（测试用，请尽量从1级开始测试，玩家人物等级不受影响）",
		
		"请选择您所在的势力",
		
		"菲雅利帝国： \n"
		+"一个来自遥远星系的帝国，来的乌鸦星系的只是她的一支探索舰队，但是随\n"
		+"着帝国势力逐步深入乌鸦星系的争端，越来越多的帝国舰队开始赶往这里。\n"
		+"帝国势力的舰船有着高超的单舰实力,但是其舰船油耗巨大。同时由于再次\n"
		+"驻扎只有一个帝国巡游舰队，所以不会为其旗下的雇佣兵发放太多的薪水。",
		
		"霸主： \n"
		+"'大崩落'之后统一了整个乌鸦星系的势力，作为乌鸦星系实力最为身深厚的\n"
		+"势力，其占据着乌鸦星系大多数的资源。霸主随时监视着乌鸦星系所有可能\n"
		+"对其造成威胁的势力，准备动用一切手段将其扼杀在摇篮之中。\n"
		+"霸主舰船装甲坚实火力强大，价格便宜并且油耗可以接受，但是其火力无法\n"
		+"照顾到身后，这是霸主舰船最大的弱点。\n"
		+"金钱对于霸主来说不是问题，他会不遗余力的笼络任何可以为他工作的人。",
		
		"自由贸易联盟： \n"
		+"作为新近兴起的势力，自由贸易联盟凭借其高超的贸易手腕迅速占领了乌鸦\n"
		+"星系几乎所有的市场。很多人都在议论他们是否与霸主在某些程度上有些见\n"
		+"不得人的交易，因为他们之间的联盟关系倒是坚固得有点让人难以置信，尤\n"
		+"其是当他们结盟的时间并不长的时候。\n"
		+"自由贸易联盟的舰船都拥有不错的机动性和高泛用度的火力配置，这使得他\n"
		+"们可以在敌人的援军赶到之前就解决掉目标然后消失的无影无踪。\n"
		+"自由贸易联盟虽然是由一群商人发起的，但是他们知道如果一件事单靠钱就\n"
		+"能搞定的话，就不值得再去耍别的手段了。",
		
		"速子科技： \n"
		+"速子科技曾经只是一个平凡的能源公司，直到他们开始插手武器开发和舰船\n"
		+"设计。他们所设计的能量武器和护盾系统是目前为止最接近'大崩落'之前描\n"
		+"述的，而速子科技的舰船也依托于这两个系统，使得其在攻防两方面都有着\n"
		+"良好的表现。但是由于护盾系统需要再舰身装甲上加装大量的发生器，使得\n"
		+"速子设计的舰船往往缺乏足够的装甲防御，一旦护盾系统超载，等待他们的\n"
		+"就只有死亡。速子舰船非常昂贵，远超其他势力的同级舰船。同时速子拥有\n"
		+"非常完备的小型空间飞行器，即使在巨舰面前也能从容不迫。\n"
		+"速子科技由于受到了霸主的压制，无法拿出足够的资金提供给他们的雇佣兵",
		
		"自由之翼： \n"
		+"自由之翼原本是霸主旗下的一支武装力量，但是由于某些契机，这支部队脱\n"
		+"离了霸主的控制，转而反抗霸主的统治。从武器装备上自由之翼与霸主的建\n"
		+"制非常相近，但是其根据乌鸦星系现有的舰船改进出了大量的特装舰船，但\n"
		+"是其舰船的稳定并不强，在某些情况下经常发生一些特殊情况。\n"
		+"自由之翼在独立后接受了速子科技的台下自主才得以在乌鸦星系立足，据传\n"
		+"还有一支不明势力也资助了他们。\n"
		+"自由之翼虽然手头并不宽松，但是他们为了获得更多战斗力不惜不惜花费更"
		+"大的代价",
		

	};
	
	public String getPrompt() {
		if (stage < prompts.length) return prompts[stage];
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getResponses() {
		List result = new ArrayList();
		if (stage == 0) {
			result.add(SUPPLY_OFFICER);
			result.add(GUNNER);
			result.add(ENGINEER);
			result.add(COPILOT);
			result.add(SOMETHING_ELSE_1);
		} else if (stage == 1) {
			result.add(ADJUTANT);
			result.add(QUARTERMASTER);
			result.add(HELM);
			result.add(COMBAT_ENGINEER);
			result.add(SOMETHING_ELSE_2);
		} else if (stage == 2) {
			result.add(level1);
			result.add(level3);
			result.add(level5);
			result.add(level7);
			result.add(level10);
		} else if (stage == 3) {
			result.add(faction_fairy);
			result.add(faction_hegemony);
			result.add(faction_independent);
			result.add(faction_pirates);
			result.add(faction_tritachyon);

		} else {
			result.add(next);
		}
		return result;
	}

	
	public void submit(Response response, CharacterCreationData data) {
		if (stage == 0) { // just in case
			data.addStartingShipChoice("shuttle_Attack");
			data.setStartingLocationName("Corvus");
			data.getStartingCoordinates().set(-2500, -3500);
					}		
		stage++;
		if (stage>3) {
			stage=prompts.length;
		}
		MutableCharacterStatsAPI stats = data.getPerson().getStats();
		if (response == SUPPLY_OFFICER) {
			stats.increaseAptitude("leadership");
			stats.increaseSkill("fleet_logistics");
			stats.increaseSkill("command_experience");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == GUNNER) {
			stats.increaseAptitude("combat");
			stats.increaseSkill("ordnance_expert");
			stats.increaseSkill("target_analysis");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == ENGINEER) {
			stats.increaseAptitude("technology");
			stats.increaseSkill("field_repairs");
			stats.increaseSkill("mechanical_engineering");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == COPILOT) {
			stats.increaseAptitude("combat");
			stats.increaseSkill("helmsmanship");
			stats.increaseSkill("evasive_action");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == SOMETHING_ELSE_1) {
			stats.addAptitudePoints(1);
			stats.addSkillPoints(2);
			data.getStartingCargo().getCredits().add(1000f);
		} 
		
		else if (response == ADJUTANT) {
			faction = 0;
			stats.increaseAptitude("leadership");
			stats.increaseSkill("fleet_logistics");
			stats.increaseSkill("advanced_tactics");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == QUARTERMASTER) {
			stats.increaseAptitude("technology");
			stats.increaseSkill("navigation");
			stats.addSkillPoints(1);
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == HELM) {
			stats.increaseAptitude("combat");
			stats.increaseSkill("helmsmanship");
			stats.increaseSkill("evasive_action");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == COMBAT_ENGINEER) {
			stats.increaseAptitude("combat");
			stats.increaseSkill("damage_control");
			stats.increaseSkill("flux_modulation");
			data.getStartingCargo().getCredits().add(3000f);
		} else if (response == SOMETHING_ELSE_2) {
			stats.addAptitudePoints(1);
			stats.addSkillPoints(2);
			data.getStartingCargo().getCredits().add(1000f);
		} else if (response == faction_fairy) {
			faction = 0;
			data.getStartingCargo().getCredits().add(1000f);
			data.addStartingShipChoice("hound_Assault");
			stage=4;
		}else if (response == faction_hegemony) {
			faction = 1;
			data.getStartingCargo().getCredits().add(1000f);
			data.addStartingShipChoice("lasher_Standard");
			stage=5;
		} else if (response == faction_independent) {
			faction = 2;
			data.addStartingShipChoice("vigilance_Standard");
			stage=6;
		} else if (response == faction_tritachyon) {
			faction = 4;
			data.addStartingShipChoice("wolf_CS");
			stage=7;
		} else if (response == faction_pirates) {
			faction = 3;
			data.getStartingCargo().getCredits().add(1000f);
			data.addStartingShipChoice("lasher_Standard");
			stage=8;
		} else if (response == level1) {
			campLevel = 0;
		} else if (response == level3) {
			campLevel = 2;
			data.getStartingCargo().getCredits().add(30000f);
		} else if (response == level5) {
			campLevel = 4;
			data.getStartingCargo().getCredits().add(50000f);
		} else if (response == level7) {
			campLevel = 6;
			data.getStartingCargo().getCredits().add(100000f);
		} else if (response == level10) {
			campLevel = 9;
			data.getStartingCargo().getCredits().add(5000000f);
		}

	}

	public void startingShipPicked(String variantId, CharacterCreationData data) {
		MutableCharacterStatsAPI stats = data.getPerson().getStats();
		stats.addAptitudePoints(1);
		stats.addSkillPoints(2);
//		data.getStartingCargo().addItems(CargoAPI.CargoItemType.RESOURCES, "SU", 5f);
//		data.getStartingCargo().getCredits().add(2000000f);

		if (variantId.equals("vigilance_Standard")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(30);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 20);
			data.getStartingCargo().addMarines(5);
		} else
		if (variantId.equals("lasher_Standard")) {
			data.getStartingCargo().addFuel(25);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 40);
			data.getStartingCargo().addMarines(10);
		} else
		if (variantId.equals("wolf_CS")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 22);
			data.getStartingCargo().addMarines(7);
		} else
		if (variantId.equals("shuttle_Attack")) {
			data.getStartingCargo().addFuel(10);
			data.getStartingCargo().addSupplies(10);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 10);
			data.getStartingCargo().addMarines(3);
		} else				
		if (variantId.equals("hound_Assault")) {
			data.getStartingCargo().addFuel(30);
			data.getStartingCargo().addSupplies(40);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 25);
			data.getStartingCargo().addMarines(15);
		} else {
			data.getStartingCargo().getCredits().add(1000f);
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 20);
			data.getStartingCargo().addMarines(10);
		}
	}
}







