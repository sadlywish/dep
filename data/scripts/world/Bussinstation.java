package data.scripts.world;

@SuppressWarnings("unchecked")
class  Bussinstation
{
	public String name_full;//商品全名
	public String name;//检索用缩写
	public int value;//价阶
	public int Status;//状态
	public int Reserve;//中间储量
	public int exp;//升级点数增加量
	public boolean need;//是否必需品
	public int Cargo;//库存
	public int maxCargo;//最大库存值
	public int baseCargo;//库存基准值
	public Bussinstation(int id, int value, int Reserve, int maxCargo ,float inti , boolean need,int exp) 
	{
		this.name = n[id];
		this.need = need;
		this.name_full = nf[id];
		this.Status = 3;
		this.value = value;
		this.Reserve = Reserve;
		this.exp = exp;
		this.maxCargo = maxCargo;
		this.baseCargo = maxCargo;
		this.Cargo = (int)((float)this.maxCargo * inti);				
	}
	/*
	public int Cargochange(int num)
	{
			int re =0;
			this.Cargo = num +this.Cargo ;
			if(num < 0)
			{
				re = (- num);
			}
			if(this.Cargo>this.maxCargo)
			{
				re = this.Cargo - this.maxCargo;
				this.Cargo = this.maxCargo;
			}
			if(this.Cargo < 0)
			{
				re = (-num)+this.Cargo ;
				this.Cargo  = 0;
			}

			return re;
	}
	public int Cargocut(int num)
	{
		if(num > this.Cargo)
		{
			int re  =this.Cargo;
			this.Cargo  = 0;
			return re;
		}
		else
		{
			this.Cargo  = this.Cargo - (int)((float)this.maxCargo *0.2f);
			return (int)((float)this.maxCargo *0.2f);
		}
	}
	public boolean isfull()
	{
		if(this.Cargo == this.maxCargo)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean isnull()
	{
		if(this.Cargo == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int blanceCargo(int num, int Produce)
	{
		float mult = (Reserve-num)/ Reserve;
		if(mult>1)
		{
			mult = 1;
		}
		int change =(int)((float)this.maxCargo *mult*0.2f);
		int rr = this.Cargochange(change);
		int re = Produce -change +rr;
		return re;
	}
	public float getCargomult()
	{
		return ((float)this.Cargo/(float)this.maxCargo );
	}
	*/
	public String getfullname()
	{
		return name_full;
	}
	public int getvalue()
	{
		return value+Status-3;
	}
	public int getStatus()
	{
		return Status;
	}
	public void setStatus(int ss)
	{
		Status = ss;
	}
	public void addStatus(int ss)
	{
		Status += ss;
	}
	public int getReserve()
	{
		return Reserve;
	}
	public void setReserve(int rr)
	{
		Reserve = rr;
	}
	public boolean isneed()
	{
		return need;
	}
	public int getexp()
	{
		return exp;
	}
	public static String [] n = { 
									"SU",
									"AA",
									"AB",
									"AC",
									"AD",
									"AE",
									};
	public static String [] nf = { 
									"合成食品",
									"合成兴奋剂",
									"精密机械零件",
									"咖啡",
									"压缩能源核心",
									"全息影像艺术品",
									};
}
