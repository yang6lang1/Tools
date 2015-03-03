package puzzles;

public class AlgorithmProblems {

	public static void main (String[] args) {
		AlgorithmProblems a = new AlgorithmProblems();
		double angle = a.angelOfHandM(12, 45);
		System.out.println("Angle between hour and min bar at time 12:45 is: " + angle);
		
		String[] p = a.permutations("abc");
		for(int i = 0; i < p.length; i++) {
			System.out.println(p[i]);
		}
	}
	
	public double angelOfHandM (int hour, int min) {
	  double a,h,hour2,m;
	  hour2 = hour;
	  if (hour2 >= 12) hour2 -= 12;
	  m = (360 * min) / 60;
	  h = (hour2 / 12d) * 360d + (min / 60d) * (1d /12d) * 360d;
	  if(Math.abs(h - m) >= 180) {
	  	a = 360d - Math.abs(h - m);
	  }
	  else {
	  	a = Math.abs(h - m);
	  }

	  return a;
	}
	
	//P35:Return all permutations of a string - O(n^2)
	public String[] permutations(String s) {
		if (s == null || s.length() == 0) return null;
		if (s.length() == 1) return new String[]{s};
		
		String subs = s.substring(0, s.length()-1);
		String[] subPermutations = permutations(subs);
		String[] permutations = new String[subPermutations.length * s.length()];
		
		//fill in permutations[]
		for (int i = 0; i < subPermutations.length; i++) {
			for (int j = 0; j < s.length(); j++) {
				String newS;
				if (j == 0) {
					newS = s.charAt(s.length()-1) + subPermutations[i];
				}
				else if (j == s.length() - 1) {
					newS = subPermutations[i]+ s.charAt(s.length()-1);
				}
				else {
					newS = subPermutations[i].substring(0, j) + s.charAt(s.length()-1)
							+ subPermutations[i].substring(j, s.length()-1);
				}
			
				permutations[i*s.length() + j] = newS;
			}
		}
		
		return permutations;
	}
}
