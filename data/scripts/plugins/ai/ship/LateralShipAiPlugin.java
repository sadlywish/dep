package data.scripts.plugins.ai.ship;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipAIPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipCommand;
import com.fs.starfarer.combat.ai.BasicShipAI;
import com.fs.starfarer.coreui.floatsuper;
import com.sun.org.apache.regexp.internal.recompile;

public class LateralShipAiPlugin implements ShipAIPlugin {

	private ShipAPI ship;

	public LateralShipAiPlugin(ShipAPI ship) {
		this.ship = ship;
	}

	@Override
	public void advance(float amount) {
		ShipAPI target = ship.getShipTarget();
		if (target == null) {
			return;
		}
		float arcA = ship.getAngularVelocity()%360f;
		float shipFacing = ship.getFacing();
		Vector2f shipLocation = ship.getLocation();
		Vector2f targetLocation = target.getLocation();
		Vector2f arcBase = new Vector2f(shipLocation.getX()
				- targetLocation.getX(), shipLocation.getY()
				- targetLocation.getY());
		float arc = (float) Math
				.atan((double) (arcBase.getY() / arcBase.getX()));
		float lateralArc1 = (arc+90f-shipFacing)%360f;
		float lateralArc2 = (arc-90f-shipFacing)%360f;
		boolean baseTurn = swichArc(lateralArc1, lateralArc2);
			if (Math.abs(arcA)>Math.abs(lateralArc1)) {
				
			}
			
	}

	private boolean swichArc(float arc1, float arc2) {
		float difference1 = Math.abs(arc1);
		float difference2 = Math.abs(arc2);
		if (difference1>difference2) {
			return true;
		}
		return false;
	}
	private boolean turnBoolean(boolean base) {
		if (base) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param turn true 顺时针旋转 false 逆时针旋转
	 */
	private void commandTurn(boolean turn, ShipAPI ship) {
		if (turn) {
			ship.giveCommand(ShipCommand.TURN_RIGHT, ship.getMouseTarget(), 1);
		} else {
			ship.giveCommand(ShipCommand.TURN_LEFT, ship.getMouseTarget(), 1);
		}
	}
	@Override
	public void setDoNotFireDelay(float amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forceCircumstanceEvaluation() {
		List<ShipAPI> ships = Global.getCombatEngine().getShips();
		for (Iterator iterator = ships.iterator(); iterator.hasNext();) {
			ShipAPI shipAPI = (ShipAPI) iterator.next();
			System.out.println(shipAPI.getOwner());
		}
	}

	@Override
	public boolean needsRefit() {
		// TODO Auto-generated method stub
		return false;
	}

}
