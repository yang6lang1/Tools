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

	/**Program a function that takes in two words and checks if the first is a substring of the second.*/
	public boolean checkSubstring(String s1, String s2){
		boolean isSubString = false;

		List<Integer> occurs = new ArrayList<Integer>();
		char c = s1.charAt(0);
		for(int i = 0; i < s2.length(); i++){
			if(c == s2.charAt(i)){
				occurs.add(i);
			}
		}

		for(Integer index : occurs){
			if(index + s1.length() > s2.length()) continue;

			boolean match = true;
			for(int i = 0; i < s1.length(); i++){
				if(s1.charAt(i) == s2.charAt(index + i)){
					match = true;
				}
				else{
					match = false;
					break;
				}
			}
			if(match) {
				return true;
			}
		}

		return isSubString;
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

	public int[] countCharacterOccurance(String s){
		int[] count = new int[128];
		for(int i = 0 ; i < count.length; i++){
			count[i] = 0;
		}

		char[] charArray = s.toCharArray();
		for(char c : charArray){
			count[c] ++;
		}

		return count;
	}

	public String removeDuplicateChars(String s){
		
		if (s == null) return null;
		char[] str =s.toCharArray();
		int length = str.length;
		if (length < 2) return s;

		int[] buffer = new int[8]; //8x32 = 256 bits
		for(int i = 0 ; i < 8; i ++){
			buffer[i] = 0;
		}

		int count = 0;
		int i = 0;//i tracks where the c is in the array
		while(count < str.length){
			char c = str[count];
			int index = ((int)c) / 32;
			int offset = ((int)c) % 32;
			if (( buffer[index] & (1 << offset) ) == 0) {
				str[i] = str[count]; 
				++i; 
				buffer[index] |= (1 << offset);
			} 
			count++;
		}
		
		StringBuffer sb = new StringBuffer();
		for(int j = 0; j < i; j++){
			sb.append(str[j]);
		}
		return sb.toString();
	}
	
	public String replaceSpaceWithSubstring(String s, String sub){
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == ' '){
				count++;
			}
		}
		
		int newSize = s.length()- count + sub.length() * count;
		char[] s2 = new char[newSize];
		int p = 0;//this represents an offset start counting from the back of the string
		for(int i = s.length() -1; i >= 0; i--){
			if(s.charAt(i) == ' '){
				for(int j = 0 ; j < sub.length() ; j++){
					s2[newSize - 1 - p - j] = sub.charAt(sub.length() - 1 - j);
				}
				p += sub.length();
			}else{
				s2[newSize - 1 - p] = s.charAt(i);
				p ++;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(char c : s2){
			sb.append(c);
		}
		return sb.toString();
	}

	public void rotateMatrixBy90(int[][] img){
		if (img.length != img[0].length) return; //square matrix only
		
		for(int layer = 0 ; layer < (img.length/2) ; layer++){//layers
			for(int offset = layer; offset < img.length -1 - layer ; offset++){//offset
				int temp = img[layer][offset];
				//left -> top
				img[layer][offset] = img[img.length-1-offset][layer];
				//bot -> left
				img[img.length-1-offset][layer] = img[img.length-1-layer][img.length-1-offset];
				//right -> bot
				img[img.length-1-layer][img.length-1-offset] = img[offset][img.length-1-layer];
				//top -> right
				img[offset][img.length-1-layer] = temp;
			}
		}
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
		s2 = test.reverseStringBetterApproach(s2);
		System.out.println(s2);
		System.out.println();

		System.out.println("#5: intToString");
		String s3 = test.intToString(-2);
		System.out.println(s3);
		System.out.println();

		System.out.println("#6: checkSubstring");
		String s4 = "string";
		String s5 = "This is a string lalala.";
		System.out.println(test.checkSubstring(s4, s5));
		System.out.println();

		System.out.println("#7: countCharacterOccurance");
		String s6 = "abcdefghijklmnopqqqqqqqqrstuvwxYZD1dd222104879x";
		int[] count = test.countCharacterOccurance(s6);
		System.out.println("How many time has q occured? "+ count['q']);
		System.out.println();

		System.out.println("#8: removeDuplicateChars");
		String s7 = "aabbbbbbccddeeffggxx";
		String out7 = test.removeDuplicateChars(s7);
		System.out.println(out7);
		String s7_1 = "abcd";
		String out7_1 = test.removeDuplicateChars(s7_1);
		System.out.println(out7_1);
		String s7_2 = "ababababacedecd";
		String out7_2 = test.removeDuplicateChars(s7_2);
		System.out.println(out7_2);
		String s7_3 = null;
		String out7_3 = test.removeDuplicateChars(s7_3);
		System.out.println(out7_3);
		String s7_4 = "aaaaa";
		String out7_4 = test.removeDuplicateChars(s7_4);
		System.out.println(out7_4);
		System.out.println();
		
		System.out.println("#9: replaceSpaceWithSubstring");
		String s8 = "This is a st    irng!";
		String s8_sub = "_____";
		String s8_out = test.replaceSpaceWithSubstring(s8, s8_sub);
		System.out.println(s8_out);
		System.out.println();
		
		System.out.println("# 10: rotateMatrix");
		int[][] image = new int [4][4];
		int count_10 = 1;
		System.out.println("Before Transformation");
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){
				image[i][j] = count_10;
				count_10 ++;
				System.out.print("["+i+","+j+"]\t" + image[i][j]+",\t");
			}
			System.out.println();
		}
		System.out.println("After Transformation");
		test.rotateMatrixBy90(image);
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){
				System.out.print("["+i+","+j+"]\t" + image[i][j]+",\t");
			}
			System.out.println();
		}
	}
}
