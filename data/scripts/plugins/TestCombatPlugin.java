package data.scripts.plugins;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;

public class TestCombatPlugin implements EveryFrameCombatPlugin {

	private static boolean TEST_MODE = false;
	private CombatEngineAPI engine;
	public List HULK;
	public static int num;
	public void init(CombatEngineAPI engine) {
		this.engine = engine;
		HULK = new ArrayList();
		num=0;
	}
	public static int get()
	{
		int a =num;
		num=0;
		return a;
	}
	public void advance(float amount, List events) {
		if (!TEST_MODE)return;
		if (engine.isPaused()) return;
		if (!engine.isInCampaign()) return;
		ShipAPI player = engine.getPlayerShip();
		Vector2f Location = player.getLocation();
		Vector2f loc = new Vector2f(Location.getX(),Location.getY());
		//engine.addFloatingText(loc, ""+HULK.size(), 100, new Color(255,255,255,255), player, 1f, 1f);
		List allShips = engine.getAllShips();
		int i = 1;
		for (int a =0; a<allShips.size();a++) {
			ShipAPI ship = (ShipAPI) allShips.get(a);
			//Location = ship.getLocation();
			if (ship.isHulk() && !ship.isDrone() && !ship.isFighter())
			{
				//String name =new String(ship.getFleetMemberId());
				if (check((ShipAPI) allShips.get(a)))
				{	
					HULK.add(((ShipAPI) allShips.get(a)).getFleetMemberId());
					ShipAPI shiphulk  = (ShipAPI)allShips.get(a);
					//Hulkdone.done(shiphulk,player, engine);
					//engine.addFloatingText(loc, "打捞成功", 30, new Color(255,255,255,255), player, 1f, 1f);
					
//					num++;
				}
			}

		}
		return;
	}
	public boolean check(ShipAPI ship)
	{
		if(ship == null)
		{
			return false;
		}
		String name = ship.getFleetMemberId();
		for(int a = 0; a<HULK.size();a++)
		{
			String ch =  (String) HULK.get(a);
			if(ch.equals(name))
			{
				return false;
			}
		}
		return true;
	}


}
