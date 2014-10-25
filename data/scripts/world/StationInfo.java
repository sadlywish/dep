package data.scripts.world;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class StationInfo  {
	public int ID;
	public int level = 1;
	public int exp = 0;
	public int baseexp;
	public int nextlevel;
	public int day;
	public StationInfo(int id,int baseexp) 
	{
		this.day = 1;
		this.ID = ID;
		this.baseexp = baseexp;
		this.nextlevel = baseexp;
	}
	public int skipday(){
		if (day >=3 || Math.random()<=0.8)
		{
			int rr =day;
			day = 1;
			return rr;
		}
		day++;
		return 0;
	}
	public int getid (){
		return ID;
	}
	public int getlv (){
		return level;
	}
	public boolean addexp(int a){
		exp += a;
		return LevelUpCheck();
	}
	public boolean LevelUpCheck()
	{
		if (level == 10)
		{
			return false;
		}
		if (exp>nextlevel)
		{
			exp -= nextlevel;
			level++;
			nextlevel = baseexp*(2^(level-1));
			return true;
		}
		return false;
	}
}
