package data.scripts.world;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.fs.starfarer.api.Global;


@SuppressWarnings("unchecked")
public class FairyBUSS  {
	public boolean instation = false;
	Fairyintt one = new Fairyintt();
	public Map FB = new HashMap();//帝国空间站
	{
		FB.put("SU",new Bussinstation(0, 4, 240, 120, 0.5f, false, 4));
		FB.put("AA",new Bussinstation(1, 3, 240, 520, 0.5f, true, 4));
		FB.put("AB",new Bussinstation(2, 10, 800, 2200, 0.5f, true, 4));
		FB.put("AC",new Bussinstation(3, 10, 600, 1200, 0.6f, true, 4));
		FB.put("AD",new Bussinstation(4, 4, 120, 160, 0.5f, true, 4));
		FB.put("AE",new Bussinstation(5, 4, 120, 160, 0.5f, true, 4));
	}
	public Map HB = new HashMap();//霸主
	{
		HB.put("SU",new Bussinstation(0, 10, 1200 , 2400, 0.8f, true, 3));
		HB.put("AA",new Bussinstation(1, 4, 120, 280, 0.5f, true, 4));
		HB.put("AB",new Bussinstation(2, 4, 120, 180, 0.8f, true, 4));
		HB.put("AC",new Bussinstation(3, 4, 160, 380, 0.5f, true, 4));
		HB.put("AD",new Bussinstation(4, 3, 120, 150, 0.5f, true, 4));
		HB.put("AE",new Bussinstation(5, 4, 120, 160, 0.5f, true, 4));
	}
	public Map IB = new HashMap();//自由贸易
	{
		IB.put("SU",new Bussinstation(0, 3, 120, 480 , 0.5f,true, 8));
		IB.put("AA",new Bussinstation(1, 3, 180, 360, 0.5f, true, 4));
		IB.put("AB",new Bussinstation(2, 3, 120, 240, 0.5f, true, 4));
		IB.put("AC",new Bussinstation(3, 3, 240, 360, 0.5f, true, 4));
		IB.put("AD",new Bussinstation(4, 10, 600, 1400, 0.5f, true, 4));
		IB.put("AE",new Bussinstation(5, 10, 720, 2100, 0.5f, true, 4));
	}
	public Map TB = new HashMap();//速子
	{
		TB.put("SU",new Bussinstation(0, 3, 120, 360, 0.8f,true, 3));
		TB.put("AA",new Bussinstation(1, 4, 60, 120, 0.5f, false, 4));
		TB.put("AB",new Bussinstation(2, 10, 500, 1800, 0.5f, true, 4));
		TB.put("AC",new Bussinstation(3, 3, 180, 320, 0.5f, true, 4));
		TB.put("AD",new Bussinstation(4, 10, 600, 2000, 0.5f, true, 4));
		TB.put("AE",new Bussinstation(5, 4, 120, 140, 0.5f, true, 4));
	}
	public Map PB = new HashMap();//海盗
	{
		PB.put("SU",new Bussinstation(0, 4, 120, 240, 0.3f, true, 4));
		PB.put("AA",new Bussinstation(1, 10, 400, 1200, 0.5f, true, 4));
		PB.put("AB",new Bussinstation(2, 3, 120, 220, 0.5f, true, 4));
		PB.put("AC",new Bussinstation(3, 4, 120, 360, 0.5f, true, 4));
		PB.put("AD",new Bussinstation(4, 3, 120, 160, 0.5f, true, 4));
		PB.put("AE",new Bussinstation(5, 10, 320, 960, 0.5f, true, 4));
	}
	public Map station = new HashMap();//空间站等级信息
	{
		station.put("FF",new StationInfo(0, 25000));
		station.put("HH",new StationInfo(1, 80000));
		station.put("II",new StationInfo(2, 20000));
		station.put("TT",new StationInfo(3, 30000));
		station.put("PP",new StationInfo(4, 30000));
	}
	//获取空间站列表
	public String[] getstationlist()
	{
		return ss;
	}
	//获取通用货物列表
	public String[] getnormallist()
	{
		return bu;
	}
	//获取商品价阶表
	public String[][] getvaluelist()
	{
		return bu_b;
	}
	//获取指定空间站价目表
	public int[] getLL (int ID){
		int i[] = new int[bu.length];
		Map BB= HM(ID);
		Bussinstation bins;
		for(int a = 0;a < bu.length;a++)
		{
			bins = (Bussinstation)BB.get(bu[a]);
			i[a] =bins.getvalue();
		}
		return i;
	}
	//操作货物状态
	public void addStatus (int ID,String name,int a){
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		bins.addStatus(a);
	}
	//修改货物状态
	public void setStatus (int ID,String name,int a){
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		bins.setStatus(a);
	}
	//获取货物状态
	public int getStatus (int ID,String name){
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		return bins.getStatus();
	}
	//获取货物基础储量
	public int getReserve (int ID,String name){
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		int a = bins.getReserve();
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv = sa.getlv();
		int i = a*lv;
		return i;
	}
	//获取生产间隔
	public int skipday(int ID){
		StationInfo sa = (StationInfo)station.get(st[ID]);
		return sa.skipday();
	}
	//平衡对应货舱
	public int blanceCargo (int ID,String name,int num){
		if(!isneed(ID, name))
		{
			return 0;
		}
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		float mart_mult = (float)num / (float)(getReserve(ID, name) *2);
		float cargo_mult = (float)bins.Cargo / (float)(bins.maxCargo*lv);
		float mult = (mart_mult + cargo_mult)/2;
		float mid_mult = mult - mart_mult;
		int change =(int)((float)(getReserve(ID, name) *2) *mid_mult);
		int rr = Cargochange(bins,-change,lv);
		int re = change +rr;
		return re;
	}
	public String blancemult (int ID,String name,int num){
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		float mart_mult = (float)num / (float)(getReserve(ID, name) *2);
		float cargo_mult = (float)bins.Cargo / (float)(bins.maxCargo*lv);
		float mult = (mart_mult + cargo_mult)/2;
		float mid_mult = mult - mart_mult;
		int change =(int)((float)num *mid_mult);
		return (num+"/"+(getReserve(ID, name) *2)+"-"+bins.Cargo+"/"+(bins.maxCargo*lv)+"-"+change);
	}
	public int Cargochange(Bussinstation bins, int num ,int lv)
	{
			int re =0;
			bins.Cargo = num +bins.Cargo ;
			if(bins.Cargo>(bins.maxCargo*lv))
			{
				re = bins.Cargo - (bins.maxCargo*lv);
				bins.Cargo = (bins.maxCargo*lv);
			}
			if(bins.Cargo < 0)
			{
				re = (-num)+bins.Cargo ;
				bins.Cargo  = 0;
			}
			return re;
	}
	public boolean isfull(int ID,String name)
	{
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		if(bins.Cargo == (bins.maxCargo*lv))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean isnull(int ID,String name)
	{
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		if(bins.Cargo == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public float getCargomult(int ID,String name)
	{	
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		return ((float)bins.Cargo/(float)(bins.maxCargo*lv) );
	}
	//校验货物状态
	public int checkStatus(int ID, String name, int num)
	{
		float base = (float)getReserve(ID, name);
		float Status = (float)getStatus(ID, name);
		float bis = num/base;
		if(!isneed(ID, name))
		{
			Status = NeedlessStatus(bis);
		}
		else
		{
			Status = inStatus(bis);
		}
		setStatus(ID, name,(int) Status);
		return  (int) Status;
	}
	//状态离开值域修正
	public boolean outStatus(int Status, float num)
	{
		if(num >= Statusout[Status][0] && num < Statusout[Status][1])
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	//根据值域重取状态
	public int inStatus(float num)
	{
		for(int aa = 0;aa<6;aa++)
		{
			if(num >= Statusin[aa][0] && num < Statusin[aa][1])
			{
				return aa;
			}
		}
		return 2;
	}
	//根据值域重取状态(非必需品)
	public int NeedlessStatus(float num)
	{
		for(int aa = 0;aa<3;aa++)
		{
			if(num >= Statusin[aa][0] && num < Statusin[aa][1])
			{
				return aa;
			}
		}
		return 4;
	}
	//获取生产量
	public int getProduce(int ID, String name)
	{
		Map BB= HM(ID);
		Bussinstation bins = (Bussinstation)BB.get(name);
		int Reserve = getReserve(ID, name);
		int Status = bins.getStatus();
		int Produce = (int)((float)Reserve * (0.09f+0.08f*Math.random()));
		return Produce;
	}
	//获取消耗量
	public int getConsume(int ID, String name)
	{
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins = (Bussinstation)BB.get(name);
		int Reserve = getReserve(ID, name);
		int Status = bins.getStatus();
		int Consume = (int)((float)Reserve * (float)(0.09 + 0.06*Math.random())* effect[Status]);
		return Consume;
	}
	public String mult(int ID, String name)
	{	
		StationInfo sa = (StationInfo)station.get(st[ID]);
		int lv =sa.getlv();
		Map BB= HM(ID);
		Bussinstation bins = (Bussinstation)BB.get(name);
		return (ss[ID]+":"+bins.Cargo+"/"+bins.maxCargo*lv+"-"+getCargomult(ID, name)+"-"+bins.getStatus()+"-"+bins.getvalue());
	}
	//增加空间站经验值（基于商品数）
	public boolean addexp(int ID, String name,int num)
	{
		StationInfo sa = (StationInfo)station.get(st[ID]);
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		int exp = bins.getexp();
		return sa.addexp(exp*num);
	}
	//增加空间站经验值（直接）
	public boolean addexp(int ID, int exp)
	{
		StationInfo sa = (StationInfo)station.get(st[ID]);
		return sa.addexp(exp);
	}
	public int getlv(int ID)
	{
		StationInfo sa = (StationInfo)station.get(st[ID]);
		return sa.getlv();
	}
	//获取商品必须性
	public boolean isneed(int ID,String name)
	{
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		return bins.isneed();
	}
	//获取商品全名
	public String getfullname(int ID,String name)
	{
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		return bins.getfullname();
	}
	public int getvalue(int ID,String name)
	{
		Map BB= HM(ID);
		Bussinstation bins;
		bins = (Bussinstation)BB.get(name);
		return bins.getvalue();
	}
	//进站
	public void in(int ID)
	{
		instation = true;
		one.seti(ID);
	}
	//离站
	public void out()
	{
		instation = false;
	}
	//进站检查
	public boolean isin(int ID)
	{
		if(instation)
		{
			if (ID == one.geti())
			{
				return true;
			}
		}
		return false;
	}
	public Map HM(int a)
	{
		if (a == 0)
		{
			return FB;
		}
		if (a == 1)
		{
			return HB;
		}
		if (a == 2)
		{
			return IB;
		}
		if (a == 3)
		{
			return TB;
		}
		if (a == 4)
		{
			return PB;
		}
		return null;
	}
	public static String [] bu = { 
									"SU",
									"AA",
									"AB",
									"AC",
									"AD",
									"AE",
									};
	public static String [] ss = { 
									"菲雅利帝国驻Corvus星系临时办事处",
									"轨道空间站",
									"联合贸易中心",
									"企业总部",
									"秘密基地",
									};
	public static String [][] bu_b = { 
									{"su_b_0",
									"su_b_1",
									"su_b_2",
									"su_b_3",
									"su_b_4",
									"su_b_5",
									"su_b_6",
									"su_s_0",
									"su_s_1",
									"su_s_2",
									"su_s_3",
									"su_s_4",
									"su_s_5",},

									{"aa_b_0",
									"aa_b_1",
									"aa_b_2",
									"aa_b_3",
									"aa_b_4",
									"aa_b_5",
									"aa_b_6",
									"aa_s_0",
									"aa_s_1",
									"aa_s_2",
									"aa_s_3",
									"aa_s_4",
									"aa_s_5",},

									{"ab_b_0",
									"ab_b_1",
									"ab_b_2",
									"ab_b_3",
									"ab_b_4",
									"ab_b_5",
									"ab_b_6",
									"ab_s_0",
									"ab_s_1",
									"ab_s_2",
									"ab_s_3",
									"ab_s_4",
									"ab_s_5",},

									{"ac_b_0",
									"ac_b_1",
									"ac_b_2",
									"ac_b_3",
									"ac_b_4",
									"ac_b_5",
									"ac_b_6",
									"ac_s_0",
									"ac_s_1",
									"ac_s_2",
									"ac_s_3",
									"ac_s_4",
									"ac_s_5",},

									{"ad_b_0",
									"ad_b_1",
									"ad_b_2",
									"ad_b_3",
									"ad_b_4",
									"ad_b_5",
									"ad_b_6",
									"ad_s_0",
									"ad_s_1",
									"ad_s_2",
									"ad_s_3",
									"ad_s_4",
									"ad_s_5",},

									{"ae_b_0",
									"ae_b_1",
									"ae_b_2",
									"ae_b_3",
									"ae_b_4",
									"ae_b_5",
									"ae_b_6",
									"ae_s_0",
									"ae_s_1",
									"ae_s_2",
									"ae_s_3",
									"ae_s_4",
									"ae_s_5",},

									};
	public  float [][] Statusin = { 
									{2.4f,99999f},
									{1.5f,2f},
									{1f,1.5f},
									{0.75f,1f},
									{0.45f,0.75f},
									{-1f,0.45f},
									};
	public  float [][] Statusout = { 
									{1.2f,9999f},
									{1.2f,1.5f},
									{0.8f,1.2f},
									{0.5f,0.8f},
									{-100f,0.55f},
									};
	public  float [] effect = { 
										2.5f,
										1.8f,
										1.3f,
										1f,
										0.5f,
										0.2f,
									};
	public  String [] st = { 
									"FF",
									"HH",
									"II",
									"TT",
									"PP",
									};

}
