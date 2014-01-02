package puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataStructure.Stack;

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
  
  /**Reverse the string by words*/
  public String reverseStringApproachOne(String string){
	
	StringBuffer word = new StringBuffer();
	Stack<String> wordStack = new Stack<String>();
	for(int i = 0; i < string.length(); i++){
	  if(string.charAt(i) == ' '){
		String token = word.toString();
		wordStack.push(token);
		wordStack.push(" ");
		word = new StringBuffer();
	  }
	  else{
		word.append(string.charAt(i));
	  }
	}
	if(word.toString() != null) wordStack.push(word.toString());

	StringBuffer b = new StringBuffer();
	while(wordStack.getSize() > 0){
	  try {
		String token = wordStack.pull();
		b.append(token);
	  } catch (Exception e) {
		break;
	  }
	}
	return b.toString();
  }
  
  public String reverseStringBetterApproach(String string){
	int start = 0, end = 0;
	
	char[] str = this.reverseWord(string.toCharArray(), start, string.length()-1);
	
	while(end < string.length()){
	  if(str[end] != ' '){
		start = end;
		while(end < string.length() && str[end] != ' '){
		  end++;
		}
		
		end--;
		
		this.reverseWord(str, start, end);
	  }
	  end++;
	}
	
	return new String(str);
  }
  
  /**Reverse the string by chars*/
  public char[] reverseWord(char[] word, int start, int end) throws NullPointerException{
	if(word == null) throw new NullPointerException();
	if(start == end) return word;
	
	for(int i = 0; i < (end - start) / 2 + 1; i++){
	  char temp = word[start + i];
	  word[start + i] = word[end -i];
	  word[end - i] = temp;
	}
	
	return word;
  }

  public String intToString(int num){
	boolean isNegative = false;
	if(num < 0) isNegative = true;
	
	String out = "";
	int abs = Math.abs(num);
	int divisor = 1;
	//special cases:
	if(abs == 0) return "0";
	if(abs == 1) out = "1";
	
	while(abs > divisor){
	  divisor *= 10;
	  int token= (abs % divisor) / (divisor / 10);
	  out = token + out;
	}
	
	if(isNegative) out = "-" + out;
	return out;
  }
  
  public static void main(String[] args) {
	StringProblems test = new StringProblems();

	System.out.println("#1: findLongestSubstring");
	String common = test.findLongestSubstring("StringOne", "TwoString");
	System.out.println(common);
	System.out.println();
	
	System.out.println("#2: findFirstNonRepeatedChar");
	try {
	  char c = test.findFirstNonRepeatedChar("AAALLeexxb");
	  System.out.println("First non-repeated char: " + c);
	} catch (Exception e) {
	 System.out.println(e.getMessage());
	}
	System.out.println();
	
	System.out.println("#3: removeChars");
	String s = "Battle of the Vowels: Hawaii vs. Grozny";
	String remove = "aeiou";
	s = test.removeChars(s, remove);
	System.out.println(s);
	System.out.println();
	
	System.out.println("#4: reverseStringBetterApproach");
	String s2 = "Do or do not, there is no try.";
	//s2 = " try.";
	//s2 = new String(test.reverseWord(s2.toCharArray(), 0, s2.length()-1) );
	s2 = test.reverseStringBetterApproach(s2);
	System.out.println(s2);
	System.out.println();
	
	System.out.println("#5: intToString");
	String s3 = test.intToString(-2);
	System.out.println(s3);
  }

}
