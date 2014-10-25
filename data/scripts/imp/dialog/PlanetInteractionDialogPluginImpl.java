package data.scripts.imp.dialog;

import java.awt.Color;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.VisualPanelAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;

public class PlanetInteractionDialogPluginImpl implements InteractionDialogPlugin {

	private static enum OptionId {
		INIT,
		LEAVE,
	}
	
	private InteractionDialogAPI dialog;
	private TextPanelAPI textPanel;
	private OptionPanelAPI options;
	private VisualPanelAPI visual;
	
	private CampaignFleetAPI playerFleet;
	private PlanetAPI planet;
	
	private static final Color HIGHLIGHT_COLOR = Global.getSettings().getColor("buttonShortcut");
	
	public void init(InteractionDialogAPI dialog) {
		this.dialog = dialog;
		textPanel = dialog.getTextPanel();
		options = dialog.getOptionPanel();
		visual = dialog.getVisualPanel();

		playerFleet = Global.getSector().getPlayerFleet();
		planet = (PlanetAPI) dialog.getInteractionTarget();
		
		visual.setVisualFade(0.25f, 0.25f);
		
		if (planet.getCustomInteractionDialogImageVisual() != null) {
			visual.showImageVisual(planet.getCustomInteractionDialogImageVisual());
		} else {
			visual.showPlanetInfo(planet);
		}
	
		dialog.setOptionOnEscape("离开", OptionId.LEAVE);
		
		optionSelected(null, OptionId.INIT);
	}
	
	public void backFromEngagement(EngagementResultAPI result) {
		// no combat here, so this won't get called
	}
	
	public void optionSelected(String text, Object optionData) {
		if (optionData == null) return;
		
		OptionId option = (OptionId) optionData;
		
		if (text != null) {
			textPanel.addParagraph(text, Global.getSettings().getColor("buttonText"));
		}
		
		switch (option) {
		case INIT:
			addText(getString("approach"));
			createInitialOptions();
			break;
		case LEAVE:
			Global.getSector().setPaused(false);
			dialog.dismiss();
			break;
		}
	}
	
	private void createInitialOptions() {
		options.clearOptions();
		options.addOption("离开", OptionId.LEAVE, null);
	}
	
	
	private OptionId lastOptionMousedOver = null;
	public void optionMousedOver(String optionText, Object optionData) {

	}
	
	public void advance(float amount) {
		
	}
	
	private void addText(String text) {
		textPanel.addParagraph(text);
	}
	
	private void appendText(String text) {
		textPanel.appendToLastParagraph(" " + text);
	}
	
	private String getString(String id) {
		String str = Global.getSettings().getString("planetInteractionDialog", id);

		String fleetOrShip = "舰队";
		if (playerFleet.getFleetData().getMembersListCopy().size() == 1) {
			fleetOrShip = "舰船";
			if (playerFleet.getFleetData().getMembersListCopy().get(0).isFighterWing()) {
				fleetOrShip = "战机";
			}
		}
		str = str.replaceAll("\\$fleetOrShip", fleetOrShip);
		str = str.replaceAll("\\$planetName", planet.getFullName());
		
		return str;
	}
	

	public Object getContext() {
		return null;
	}
}



