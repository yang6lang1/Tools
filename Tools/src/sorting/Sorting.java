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
		String[] output = strs.clone();

		strs = mergeSort(output, 0, output.length - 1);

		return strs;
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

	public String[] mergeSort2(String[] strs){
		if(strs == null) throw new NullPointerException();
		strs = mergeSort2(0, strs.length-1, strs);
		
		return strs;
	}

	private String[] mergeSort2(int start, int end, String[] strs){
		//split
		int mid = (start + end) /2;
		String[] left = new String[mid - start + 1],
				right = new String[end - mid];

		if(end - start == 0){
			left[0] = strs[start];
			return left;
		}
		else if(end - start == 1){
			left[0] = strs[start];
			right[0] = strs[end];
		}
		else{
			left = mergeSort2(start, mid, strs);
			right = mergeSort2(mid + 1, end, strs);
		}

		//merge
		String[] mergedStrs = new String[left.length + right.length];
		int leftptr = 0, rightptr = 0;
		while(leftptr < left.length || rightptr < right.length ){
			if(left[leftptr].compareTo(right[rightptr]) <= 0){
				mergedStrs[leftptr+rightptr] = left[leftptr];
				leftptr++;
				if(leftptr >= left.length){
					while (rightptr < right.length){
						mergedStrs[leftptr+rightptr] = right[rightptr];
						rightptr++;
					}
				}
			}
			else{
				mergedStrs[leftptr+rightptr] = right[rightptr];
				rightptr++;
				if(rightptr >= right.length){
					while (leftptr < left.length){
						mergedStrs[leftptr+rightptr] = left[leftptr];
						leftptr++;
					}
				}
			}
		}

		return mergedStrs;
	}

	public void selectionSort(String[] strings){
		String temp = "";
		for (int i = 0; i < strings.length; i++){
			String maxString = strings[0];
			int maxIndex = 0;
			for(int j = 0; j < strings.length - i; j++){
				if (maxString.compareTo(strings[j]) <= 0){
					maxString = strings[j];
					maxIndex = j;
				}
			}
			temp = strings[strings.length - 1 - i];
			strings[strings.length - 1 - i] = strings[maxIndex];
			strings[maxIndex] = temp;
		}
	}

	public void quickSort(int[] a, int start, int end) throws Exception{
	    if (a == null || start > end) throw new Exception("Invalid input");
	    if (start == end) return;
	    
	    int pivotIndex = (start+end) / 2;
	    int temp;
	    
	    temp = a[pivotIndex];
	    a[pivotIndex] = a[end];
	    a[end] = temp;
	    pivotIndex = end;
	    
	    int seperator = start;
	    //partition
	    for(int i = start; i < end; i++){
	        if (a[i] < a[pivotIndex]){
	            if(i != seperator){
	               temp = a[i];
	               a[i] = a[seperator];
	               a[seperator] = temp;
	            }
	            seperator++;
	        }
	    }
	    
	    if(seperator != pivotIndex){
	        temp = a[pivotIndex];
	        a[pivotIndex] = a[seperator];
	        a[seperator] = temp;
	        pivotIndex = seperator;
	    }
	    
	    if(pivotIndex != start){
	        quickSort(a, start, pivotIndex-1);
	    }
	    
	    if(pivotIndex != end){
	        quickSort(a, pivotIndex +1 , end);
	    }
	}

	public static void main(String[] args) {
		String[] strs = {"2","4","1","3","8","5"};

		Sorting test = new Sorting();
		//strs = test.mergeSort(strs, 0, strs.length -1);
		strs = test.mergeSort2(strs);
		for(String s : strs){
			System.out.println(s);
		}
		
		System.out.println();
		int[] a = {0,01};
		try {
			test.quickSort(a, 0, a.length-1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for(int i : a){
			System.out.println(i);
		}
	}
}
