package data.scripts.plugins;

import com.fs.starfarer.api.plugins.LevelupPlugin;

public class LevelupPluginImpl implements LevelupPlugin {

	public int getAptitudePointsAtLevel(int level) {
		if (level % 2 == 0) return 1;
		return 0;
	}

	public int getSkillPointsAtLevel(int level) {
		return 2;
	}

	
	public long getXPForLevel(int level) {

		if (level <= 1) return 0;
		
		float p1 = 10;
		float p2 = 35;
		
		float f1 = 1f;
		float f2 = Math.min(1, Math.max(0, level - p1) / 5f);
		float f3 = Math.max(0, level - p2);
		
		float p1level = Math.max(0, level - p1 + 1);
		float p2level = Math.max(0, level - p2 + 1);
		float mult1 = (1f + (float) level) * 0.5f * (float) level * 1f;
		float mult2 = (1f + (float) p1level) * 0.5f * (float) p1level * 0.25f;
		float mult3 = (1f + (float) p2level) * 0.5f * (float) p2level * 2f;
		
		float base = 1500;
		
		float r = f1 * mult1 * base +
			      f2 * mult2 * base +
			      f3 * mult3 * base;
		
		return (long) r;
	}
//	
//	public static void main(String[] args) {
//		LevelupPluginImpl impl = new LevelupPluginImpl();
//		for (int i = 0; i <= 100; i++) {
//			System.out.println(String.format("% 4d: % 20d", i, impl.getXPForLevel(i)));
//		}
//	}

}
