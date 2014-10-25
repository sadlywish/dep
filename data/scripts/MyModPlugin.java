package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.CampaignPlugin.PickPriority;
import com.fs.starfarer.api.combat.ShipAIPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import data.scripts.plugins.ai.ship.LateralShipAiPlugin;


public class MyModPlugin extends BaseModPlugin {
	
	public static String REGISTERED_PLUGIN_KEY = "MyModPlugin_MyCampaignPlugin_registered"; 
	@Override
	public void onEnabled(boolean wasEnabled) {		
		// Failsafe to ensure that the plugin doesn't get registered twice in the same savegame.
		// Not necessary from 0.6.1a onward.
		if (!Global.getSector().getPersistentData().containsKey(REGISTERED_PLUGIN_KEY)) {
			Global.getSector().getPersistentData().put(REGISTERED_PLUGIN_KEY, true);
			Global.getSector().registerPlugin(new FairyModPlugin());
		}
	}
//	@Override
//	public PluginPick<ShipAIPlugin> pickShipAI(FleetMemberAPI member,
//			ShipAPI ship) {
//		if (ship.getHullSpec().getHullId().equals("HGN_Lord")) {
//			return new PluginPick<ShipAIPlugin>(new LateralShipAiPlugin(ship), PickPriority.MOD_GENERAL);
//		}
//		return null;
//	}
	
}
