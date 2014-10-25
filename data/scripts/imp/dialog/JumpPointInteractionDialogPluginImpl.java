package data.scripts.imp.dialog;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.VisualPanelAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI.JumpDestination;
import com.fs.starfarer.api.combat.EngagementResultAPI;

public class JumpPointInteractionDialogPluginImpl implements InteractionDialogPlugin {

	private static enum OptionId {
		INIT,
		JUMP_1,
		JUMP_2,
		JUMP_3,
		JUMP_4,
		JUMP_5,
		JUMP_6,
		JUMP_7,
		JUMP_8,
		JUMP_9,
		LEAVE,
	}
	
	private InteractionDialogAPI dialog;
	private TextPanelAPI textPanel;
	private OptionPanelAPI options;
	private VisualPanelAPI visual;
	
	private CampaignFleetAPI playerFleet;
	private JumpPointAPI jumpPoint;
	
	private List<OptionId> jumpOptions = Arrays.asList(
							new OptionId [] {
									OptionId.JUMP_1,
									OptionId.JUMP_2,
									OptionId.JUMP_3,
									OptionId.JUMP_4,
									OptionId.JUMP_5,
									OptionId.JUMP_6,
									OptionId.JUMP_7,
									OptionId.JUMP_8,
									OptionId.JUMP_9
							});
	
	
	private static final Color HIGHLIGHT_COLOR = Global.getSettings().getColor("buttonShortcut");
	
	public void init(InteractionDialogAPI dialog) {
		this.dialog = dialog;
		textPanel = dialog.getTextPanel();
		options = dialog.getOptionPanel();
		visual = dialog.getVisualPanel();

		playerFleet = Global.getSector().getPlayerFleet();
		jumpPoint = (JumpPointAPI) (dialog.getInteractionTarget());
		
		visual.setVisualFade(0.25f, 0.25f);
		if (jumpPoint.getCustomInteractionDialogImageVisual() != null) {
			visual.showImageVisual(jumpPoint.getCustomInteractionDialogImageVisual());
		} else {
			if (playerFleet.getContainingLocation().isHyperspace()) {
				visual.showImagePortion("illustrations", "jump_point_hyper", 400, 400, 0, 0, 400, 400);
			} else {
				visual.showImagePortion("illustrations", "jump_point_normal", 400, 400, 0, 0, 400, 400);
			}
		}
	
		dialog.setOptionOnEscape("Leave", OptionId.LEAVE);
		
		optionSelected(null, OptionId.INIT);
	}
	
	private EngagementResultAPI lastResult = null;
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
			if (jumpPoint.isStarAnchor()) {
				addText(getString("starAnchor"));
			}
			createInitialOptions();
			break;
		case LEAVE:
			Global.getSector().setPaused(false);
			dialog.dismiss();
			break;
		}
		
		if (jumpOptions.contains(option)) {
			JumpDestination dest = destinationMap.get(option);
			if (dest != null) {
				
				
//				SectorEntityToken token = dest.getDestination();
//				//System.out.println("JUMP SELECTED");
//				LocationAPI destLoc = token.getContainingLocation();
//				LocationAPI curr = playerFleet.getContainingLocation();
//				
//				Global.getSector().setCurrentLocation(destLoc);
//				curr.removeEntity(playerFleet);
//				destLoc.addEntity(playerFleet);
//				
//				Global.getSector().setPaused(false);
//				playerFleet.setLocation(token.getLocation().x, token.getLocation().y);
//				playerFleet.setMoveDestination(token.getLocation().x, token.getLocation().y);
				
				dialog.dismiss();
				
				Global.getSector().setPaused(false);
				Global.getSector().doHyperspaceTransition(playerFleet, jumpPoint, dest);
				
				return;
			}
		}
	}
	
	private Map<OptionId, JumpDestination> destinationMap = new HashMap<OptionId, JumpDestination>();
	private void createInitialOptions() {
		options.clearOptions();

		boolean dev = Global.getSettings().isDevMode();
		float navigation = Global.getSector().getPlayerFleet().getCommanderStats().getSkillLevel("navigation");
		boolean isStarAnchor = jumpPoint.isStarAnchor();
		boolean okToUseIfAnchor = isStarAnchor && navigation >= 7;
		
		if (isStarAnchor && !okToUseIfAnchor && dev) {
			addText("(Can always be used in dev mode)");
		}
		okToUseIfAnchor |= dev;
		
		if (jumpPoint.getDestinations().isEmpty()) {
			addText(getString("noExits"));
		} else if (playerFleet.getCargo().getFuel() <= 0) {
			addText(getString("noFuel"));
		} else if (isStarAnchor && !okToUseIfAnchor) {
			addText(getString("starAnchorUnusable"));
		} else {
			int index = 0;
			for (JumpDestination dest : jumpPoint.getDestinations()) {
				if (index >= jumpOptions.size()) break;
				OptionId option = jumpOptions.get(index);
				index++;
				
				options.addOption("跃迁至" + dest.getLabelInInteractionDialog(), option, null);
				destinationMap.put(option, dest);
			}
		}
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
		String str = Global.getSettings().getString("jumpPointInteractionDialog", id);

		String fleetOrShip = "舰队";
		if (playerFleet.getFleetData().getMembersListCopy().size() == 1) {
			fleetOrShip = "舰船";
			if (playerFleet.getFleetData().getMembersListCopy().get(0).isFighterWing()) {
				fleetOrShip = "战机";
			}
		}
		str = str.replaceAll("\\$fleetOrShip", fleetOrShip);
		
		return str;
	}
	

	public Object getContext() {
		return null;
	}
}



