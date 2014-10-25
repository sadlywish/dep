package data.scripts;

import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.AsteroidAPI;
import com.fs.starfarer.api.campaign.BattleCreationPlugin;
import com.fs.starfarer.api.campaign.OrbitalStationAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.BaseCampaignPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import data.scripts.imp.dialog.FleetInteractionDialogPluginImpl;
import data.scripts.imp.dialog.JumpPointInteractionDialogPluginImpl;
import data.scripts.imp.dialog.OrbitalStationInteractionDialogPluginImpl;
import data.scripts.imp.dialog.PlanetInteractionDialogPluginImpl;
import data.scripts.plugins.BattleCreationPluginImpl;

/**
 * @author user
 * 
 */
public class FairyModPlugin extends BaseCampaignPlugin {
	// "modPlugin":"data.scripts.ChinesizationPlugin",

	public String getId() {
		return "Fairy"; // make sure to change this for your mod
	}

	// not marking this plugin as transient, because it saves data of type:
	// AsteriodInteractionDialogPlugin.AsteroidData in the savefile.
	// Because of that, a savegame that ran with this plugin would not load
	// properly
	// after this mod is disabled.
	public boolean isTransient() {
		return false;
	}

	@SuppressWarnings("unchecked")
	public PluginPick pickInteractionDialogPlugin(
			SectorEntityToken interactionTarget) {
		// if the player is attempting to interact with an asteroid,
		// return our custom dialog plugin.
		// if (interactionTarget instanceof AsteroidAPI) {
		// return new PluginPick(new AsteriodInteractionDialogPlugin(),
		// PickPriority.MOD_GENERAL);
		// }
		if (interactionTarget instanceof OrbitalStationAPI) {
			return new PluginPick(
					new OrbitalStationInteractionDialogPluginImpl(),
					PickPriority.MOD_GENERAL);
		} else if (interactionTarget instanceof PlanetAPI) {
			return new PluginPick(new PlanetInteractionDialogPluginImpl(),
					PickPriority.MOD_GENERAL);
		} else if (interactionTarget instanceof JumpPointAPI) {
			return new PluginPick(new JumpPointInteractionDialogPluginImpl(),
					PickPriority.MOD_GENERAL);
		} else if (interactionTarget instanceof CampaignFleetAPI) {
			return new PluginPick(new FleetInteractionDialogPluginImpl(),
					PickPriority.MOD_GENERAL);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public PluginPick<BattleCreationPlugin> pickBattleCreationPlugin(
			SectorEntityToken opponent) {
		if ((opponent instanceof CampaignFleetAPI)) {
			return new PluginPick(new BattleCreationPluginImpl(),
					PickPriority.MOD_GENERAL);
		}
		return null;
	}

}
