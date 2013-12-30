package test;

public class PlayAround {

	public PlayAround(){
		test();
	}
	
	public void test(){
		String x = "helloworld";
		giveMeString(x);
		System.out.println(x);
	}
	
	public String giveMeString(String x){
		x = "This is string";
		return x;
	}
	
	public static void main(String[] args){
		PlayAround test = new PlayAround();
	}
}
