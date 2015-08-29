package others;

public class Misc {

	public static void main(String[] args) {
		Misc m = new Misc();
		System.out.println("num of silver: "+ m.countSilverFromRMB(2));
	}

	public int countGoldFromSilver(int numSilver){
		return numSilver / 100;
	}
	
	public int countSilverFromRMB(int rmb){
		return rmb * 1000 * 100;
	}
}
