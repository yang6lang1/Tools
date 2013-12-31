package puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringProblems {

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
  
  /**Worst case scenario: O(n + n) = O(n)*/
  public char findFirstNonRepeatedChar(String string) throws Exception{
	/*Eg: "teeter" is 'r', "total" is 'o'
	 * Common way: for each element, try to find duplicates in the string
	 * the first char without duplicate is the answer. n + (n-1)+...+0 = N*(N-1)/2 = O(n^2)
	 * */
	Map<Character, Integer> letterMap = new HashMap<Character, Integer>();
	char c = '\0';
	
	for(int i = 0; i < string.length(); i++){
	  char temp = string.charAt(i);
	  if(letterMap.containsKey(temp)){
		int num = letterMap.get(temp);
		num++;
		letterMap.remove(temp);
		letterMap.put(temp, num);
	  }
	  else{
		letterMap.put(temp, 1);
	  }
	}
	
	boolean found = false;
	for(int i = 0; i < string.length(); i++){
	  char temp = string.charAt(i);
	  int num = letterMap.get(temp);
	  if(num == 1){
		found = true;
		c = temp;
		break;
	  }
	}
	
	if(!found){
	 throw new Exception("No non-repeated char in this string.");
	}
	
	return c;
  }

  public String removeChars(String string, String remove){
	boolean[] removeChars = new boolean[128];
	for(int i = 0; i < 128; i++){
	  removeChars[i] = false;
	}
	char[] removeArray = remove.toCharArray();
	for(int i = 0; i < removeArray.length; i++){
	  removeChars[removeArray[i]] = true;
	}
	
	StringBuffer b = new StringBuffer();
	for(int i = 0; i < string.length(); i++){
	  char c = string.charAt(i);
	  if(!removeChars[c]){
		b.append(c);
	  }
	}
	return b.toString();
  }
  
  public static void main(String[] args) {
	StringProblems test = new StringProblems();

	System.out.println("#1: ");
	String common = test.findLongestSubstring("StringOne", "TwoString");
	System.out.println(common);
	System.out.println();
	
	System.out.println("#2: ");
	try {
	  char c = test.findFirstNonRepeatedChar("AAALLeexxb");
	  System.out.println("First non-repeated char: " + c);
	} catch (Exception e) {
	 System.out.println(e.getMessage());
	}
	System.out.println();
	
	System.out.println("#3: ");
	String s = "Battle of the Vowels: Hawaii vs. Grozny";
	String remove = "aeiou";
	s = test.removeChars(s, remove);
	System.out.println(s);
	System.out.println();
  }

}
