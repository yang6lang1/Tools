package puzzles;

public class RecursiveProblems {

  public int binarySearch(int[] array, int lower, int upper, int target){
	//special case: Detect unsorted array
	//special case: Detect error when lower > upper
	int mid = (lower + upper) / 2;
	if(array[mid] < target){
	  if(mid == upper) return -1; //target not in the array

	  //search right
	  return binarySearch(array, mid+1, upper, target);
	}
	else if(array[mid] > target){
	  if(mid == lower) return -1;

	  //search left
	  return binarySearch(array, lower, mid, target);
	}
	else{
	  return mid;
	}
  }

  public static void main(String[] args) {
	int[] sortedArray = new int[20];
	for(int i = 0; i < 20; i++){
	  //Build an ascending order sorted array
	  sortedArray[i] = i;
	}

	RecursiveProblems test = new RecursiveProblems();
	int index = test.binarySearch(sortedArray, 0, sortedArray.length - 1, 15);
	if(index == -1){
	  System.out.println("Did not find target integer");
	}
	else{
	  System.out.println("index: "+ index);
	}
  }
}
