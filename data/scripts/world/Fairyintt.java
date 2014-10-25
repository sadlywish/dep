package data.scripts.world;


@SuppressWarnings("unchecked")
public class Fairyintt  {
	private	int i = 0;
	public int geti (){
		return i;
	}
	public String getS(){
		return Integer.toString(i);
	}
	public void seti(int a){
		i = a;
		return ;
	}
	public void add(){
		i++;
		return ;
	}
}
