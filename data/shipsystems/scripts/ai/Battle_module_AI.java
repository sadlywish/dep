package data.shipsystems.scripts.ai;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAIScript;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.combat.ShipwideAIFlags;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.util.IntervalUtil;


/**
 * Sample ship system AI. 
 * 
 * THIS CODE IS NOT ACTUALLY USED. To enable it, uncomment the relevant lines in fastmissileracks.system.
 * 
 * @author Alex Mosolov
 *
 * Copyright 2012 Fractal Softworks, LLC
 */
public class Battle_module_AI implements ShipSystemAIScript {

	private ShipAPI ship;
	private CombatEngineAPI engine;
	private ShipwideAIFlags flags;
	private ShipSystemAPI system;
	
	private IntervalUtil tracker = new IntervalUtil(0.5f, 1f);
	
	public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine) {
		this.ship = ship;
		this.flags = flags;
		this.engine = engine;
		this.system = system;
	}
	
	private float bestFractionEver = 0f;
	private float sinceLast = 0f;
	
	@SuppressWarnings("unchecked")
	public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target) {
		tracker.advance(amount);
		
		sinceLast += amount;
		
		if (tracker.intervalElapsed()) {
			if (system.getCooldownRemaining() > 0) return;
			if (system.isOutOfAmmo()) return;
			if (system.isActive()) return;
		}
		List shipsList = engine.getShips();
//		System.out.println(shipsList.size());
		Vector2f shipLocation = ship.getLocation();
		int flightNUM = 0;
		for (int i = 0; i < shipsList.size(); i++) {
			ShipAPI tship = (ShipAPI)shipsList.get(i);
			if (tship==ship) {
				continue;
			}
			if (tship.isFighter()) {
				if (getRange(tship.getLocation(), shipLocation)<2000) {
					flightNUM++;
				}
				if (flightNUM>20) {
					ship.useSystem();
					return;
				}
			}
			if (tship.isCapital()) {
				if (getRange(shipLocation, tship.getLocation())<4200) {
					ship.useSystem();
					return;
				}
			}
		}
		return;
		
	}
	
	private float getRange(Vector2f a, Vector2f b) {
		float range = 0;
		range = (float)Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX())+(a.getY()-b.getY())*(a.getY()-b.getY()));
		return range;
	}

}
