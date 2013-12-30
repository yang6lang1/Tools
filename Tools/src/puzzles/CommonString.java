package puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonString {

	private String[] strings = {
			"String1",
			"String2",
			"Stringsssssss",
			"StringWithRandomEnding",
			"String with spaces",
			"Some other strings"
	};

	class Item{
		public char c;
		public int posInOne;
	}

	public String findLongestSubstring(String s1, String s2){
		List<Integer> occurs = new ArrayList<>();
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == s2.charAt(s2.length()-1)) {
				occurs.add(i);
			}
		}

		Collections.reverse(occurs);
		
		for(int index : occurs) {
			boolean equals = true;
			for(int i = index; i >= 0; i--) {
				if (s1.charAt(index-i) != s2.charAt(s2.length() - i - 1)) {
					equals = false;
					break;
				}
			}
			if(equals) {
				return s1.substring(0,index+1);
			}
		}

		return null;
	}

	public static void main(String[] args) {
		CommonString test = new CommonString();

		String common = test.findLongestSubstring("StringOne", "TwoString");
		System.out.println(common);
	}

}
