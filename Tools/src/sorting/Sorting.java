package sorting;

public class Sorting {

	/**Sort the array of sorting in ascending order alpha-numerically
	 * O(n ^ 2)*/
	public String[] insertionSort(String[] strs){
		String[] output = strs.clone();
		
		for(int i = 0; i < output.length; i++){
			for(int j = i; j > 0; j--){
				if(output[j-1].compareTo(output[j]) > 0){
					String temp = output[j];
					output[j] = output[j-1];
					output[j-1] = temp;
				}
				else{
					break;
				}
			}
		}
			
		return output;
	}
	
	/**Sort the array of sorting in ascending order alpha-numerically
	 * O(nLogn)*/
	public String[] mergeSort(String[] strs){
		//TODO: figure out array and pass by reference questions
		String[] output = strs.clone();
		
		mergeSort(output, 0, output.length - 1);
		
		return output;
	}
	
	public String[] mergeSort(String[] strs, int start, int end){
		if(strs == null) throw new NullPointerException();
		
		int mid = (start + end)/2;
		String[] left = new String[mid - start + 1];
		String[] right = new String[end - mid];
		//split
		if(end - start == 0){
			left[0] = strs[start];
			right = null;
		}
		else if(end - start == 1){
			if(strs[start].compareTo(strs[end]) < 0){
				left[0] = strs[start];
				right[0] = strs[end];
			}
			else{
				left[0] = strs[end];
				right[0] = strs[start];
			}
		}
		else if(end - start > 1){
			left = mergeSort(strs, start, mid);
			right = mergeSort(strs, mid+1, end);
		}

		//merge
		int lp = 0, rp = 0;
		String[] output = new String[end - start + 1];
		if(right == null){
			return left;
		}
		
		while(lp < left.length || rp < right.length){
			if(left[lp].compareTo(right[rp]) < 0){
				output[lp+rp] = left[lp];
				lp++;
				
				if(lp >= left.length){
					while(rp < right.length){
						output[lp + rp] = right[rp];
						rp++;
					}
				}
			}
			else{
				output[lp + rp] = right[rp];
				rp++;
				
				if(rp >= right.length){
					while(lp < left.length){
						output[lp + rp] = left[lp];
						lp++;
					}
				}
			}
		}
		
		return output;
	}
	
	public static void main(String[] args) {
		String[] strs = {"String 11","String 2","String 83","String 44","String 25","String 16"};
		
		Sorting test = new Sorting();
		strs = test.mergeSort(strs, 0, strs.length -1);
		for(int i = 0; i < strs.length; i++){
			System.out.println(strs[i]);
		}
	}
}
