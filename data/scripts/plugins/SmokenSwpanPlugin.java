package data.scripts.plugins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.lwjgl.util.vector.Vector2f;

import com.fs.starfarer.api.combat.BoundsAPI.SegmentAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.input.InputEventAPI;
import data.scripts.world.SmokenPoint;

public class SmokenSwpanPlugin implements EveryFrameCombatPlugin {
	private CombatEngineAPI engine;
	private boolean active;
	@Override
	public void init(CombatEngineAPI engine) {
		this.engine = engine;
		this.active = true;
	}

	@Override
	public void advance(float amount, List events) {

		if (engine.isPaused()) {
			return;
		}
//		if (active) {
//			active =false;
//			return;
//		}
//		engine.getPlayerShip().setFacing(180);
		List ships = engine.getShips();
//		ShipAPI player = engine.getPlayerShip();
//		engine.addFloatingDamageText(player.getLocation(), ships.size(), new Color(255, 255, 255), player, null);
		for (int a =0; a<ships.size();a++) {
			ShipAPI ship = (ShipAPI) ships.get(a);
			int adv = 0;
			if (ship.getHullSpec().getHullId().equals("onslaught")) {
				adv=1;
			}
//			engine.addFloatingDamageText(ship.getLocation(), a+1, new Color(255, 255, 255), ship, null);
			if (ship.isHulk()||ship.isFighter()||ship.isDrone()) {
				continue;
			}
			float max = ship.getArmorGrid().getMaxArmorInCell();
			float size = ship.getArmorGrid().getCellSize();
			float face = ship.getFacing();
			Vector2f shiplocation = ship.getLocation();
			float[][] armors =ship.getArmorGrid().getGrid();
//			int act = 0;
			List actList = new ArrayList();
			for (int i = 3+adv; i < armors.length-3; i++) {
				for (int j = 3; j < armors[i].length-2; j++) {
//					Vector2f loaction = getLoaction(i, j, size, armors, face, shiplocation);
//					engine.addFloatingDamageText(loaction, 1, new Color(255, 255, 255), ship, null);
					float mult = ship.getArmorGrid().getArmorValue(i, j)/max;
					if (mult<0.4) {
//						engine.addFloatingText(shiplocation, face+"", 10, new Color(255, 255, 255), ship, 0, 0);

						if (mult<0) {
							mult=0;
						}
						Vector2f loaction = getLoaction(i, j, size, armors, face, shiplocation);
						actList.add(new SmokenPoint(i, j, loaction.getX(), loaction.getY(), mult));
//						this.smoken(i,j,armors,loaction,mult);
//							act++;
//						}
					}
				}
			}
			int[] remove = new int[actList.size()];
			for (int i = 0; i < remove.length; i++) {
				remove[i]=-1;
			}
			int num =1;
			if (actList.size()<1) {
				continue;
			}
//			engine.addFloatingDamageText(shiplocation, actList.size(), new Color(255, 255, 255), ship, null);
			for (int i = 0; i < actList.size(); i++) {
				SmokenPoint point1 = (SmokenPoint)actList.get(i);
				Vector2f smoken = point1.getLocationVector2f();
				float maxmult = point1.getMult();
				int add = 1;
				boolean breaken =false;
				for (int k = 0; k < remove.length; k++) {
					if (i==remove[k]) {
						breaken =true;
						break;
					}
				}
				if (breaken) {
//					System.out.println(""+i+" pass");
					continue;
				}
//				System.out.println(i+"ok("+point1.getCellX()+","+point1.getCellY()+")");
				for (int j = i+1; j < actList.size(); j++) {
//					System.out.println(j+"");
					SmokenPoint point2 = (SmokenPoint)actList.get(j);
					breaken =false;
					for (int k = 0; k < remove.length; k++) {
						if (j==remove[k]) {
							breaken =true;
							break;
						}
					}
					if (breaken) {
						continue;
					}
					if (!breaken&&isNear(point1.getCellX(), point1.getCellY(), point2.getCellX(), point2.getCellY())) {
						remove[num]=j;
						num++;
						add++;
						smoken = new Vector2f(smoken.getX()+point2.getLocationX(),smoken.getY()+point2.getLocationY());
						if (point2.getMult()>maxmult) {
							maxmult = point2.getMult();
						}
					}
				}
				smoken = new Vector2f(smoken.getX()/add,smoken.getY()/add);
//				engine.addFloatingDamageText(smoken, num, new Color(255, 255, 255), ship, null);
//				for (int j = 0; j < remove.length; j++) {
//					System.out.print(remove[j]+",");
//				}
//				System.out.println();
				smoken(smoken, maxmult);
			}
//			act=0;
		}
		active=true;
	}
	private boolean smoken(Vector2f locetion,float mult) {
		if (Math.random()>0.5) {
			return false;
		}
//		
//
//		float cellX = ((float)j - (float)armors[i].length / 2 + 0.5f) * size;
//		float cellY = ((float)armors.length / 2 - 0.5f - (float)i) * size;
//		float cellrange = (float)Math.sqrt((double)((cellX*cellX)+(cellY*cellY)));
////		float smokenface = (float)Math.atan2(cellY, cellX);
//		
////		float standardface = (smokenface-face)*(float)Math.PI/180;
////		Vector2f facebase=new Vector2f((float)Math.cos((double)standardface),(float)Math.sin((double)standardface));
////		Vector2f standardbaesLocation = new Vector2f(cellrange*facebase.getX()+shiplocetion.getX(),cellrange*facebase.getY()+shiplocetion.getY());
//		float cosface = (float)Math.cos((double)(face)*Math.PI/180);
//		float sinface = (float)Math.sin((double)(face)*Math.PI/180);
//		Vector2f standardbaesLocation = new Vector2f(cellX*cosface-cellY*sinface+shiplocetion.getX(),cellX*sinface+cellY*cosface+shiplocetion.getY());
		float velX = (float)Math.random() * 10f - 5f;
		float velY = (float)Math.sqrt(25f - velX * velX);
		if((float)Math.random() >= 0.5f){
			velY = -velY;
		}
//		engine.addFloatingText(standardbaesLocation, ""+smokenface, 10, new Color(255, 255, 255), null, 0, 0);

		engine.addSmokeParticle(locetion, new Vector2f(velX,velY), 40f*(0.8f + 0.4f * (float)Math.random()), 1f, 8f, new Color(25,25,25,(int)((0.5-mult)*200f)));
		if (mult<0.2f&&Math.random()>0.5) {
			engine.addHitParticle(locetion, new Vector2f(velX,velY), 15f*(0.8f + 0.4f * (float)Math.random()), 1f, 2f, new Color(255,90,0,200));
		}
		return true;
	}
	private Vector2f getLoaction(int i,int j, float size, float[][] armors, float face, Vector2f shiplocetion) {
		float cellX = ((float)j - (float)armors[i].length / 2 + 0.5f) * size;
		float cellY = ((float)armors.length / 2 - 0.5f - (float)i) * size;
		float cosface = (float)Math.cos((double)(face)*Math.PI/180);
		float sinface = (float)Math.sin((double)(face)*Math.PI/180);
		return new Vector2f(cellX*cosface-cellY*sinface+shiplocetion.getX(),cellX*sinface+cellY*cosface+shiplocetion.getY());
	}
	private boolean isNUMNear(int num1, int num2){
		if (num1<=num2 && num1+3>=num2) {
			return true;
		}
		return false;
	}
	private boolean isNear(int x1, int y1, int x2, int y2) {
		if (isNUMNear(x1, x2)&&isNUMNear(y1, y2)) {
			return true;
		}
		return false;
	}	
	
}
