package data.scripts.world;

import org.lwjgl.util.vector.Vector2f;

public class SmokenPoint {
	private int cellX;
	private int cellY;
	private float locationX;
	private float locationY;
	private float mult;

	public SmokenPoint(int cellX, int cellY, float locationX, float locationY,float mult) {
		this.cellX = cellX;
		this.cellY = cellY;
		this.locationX = locationX;
		this.locationY = locationY;
		this.mult = mult;
	}

	public int getCellX() {
		return cellX;
	}

	public int getCellY() {
		return cellY;
	}

	public float getLocationX() {
		return locationX;
	}

	public float getLocationY() {
		return locationY;
	}
	
	
	public float getMult() {
		return mult;
	}

	public Vector2f getLocationVector2f(){
		return new Vector2f(locationX,locationY);
	}

}
