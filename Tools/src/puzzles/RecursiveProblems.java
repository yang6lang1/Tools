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
  
  public final static int East = 1;
  public final static int South = 2;
  public final static int West = 3;
  public final static int North = 4;
  
  private int[][] mat;
  public String printMat(){
	if(mat == null) return "Empty matrix";
	
	String out = "";
	for(int n = 0; n < mat.length; n++){
	  for(int m = 0; m < mat[n].length; m++){
		out += "["+(n+1)+","+(m+1)+"]:" + mat[n][m]+"\t\t ";
	  }
	  out += "\n";
	}
	
	return out;
  }
  
  public void buildSpiralMatrix(int m, int n){
	//TODO: special case
	int[][] matrix = new int[n][m];
	this.fillMatrixClockwisely(matrix, -1, 0, 0, East, m);
	mat = matrix;
  }
  
  private void fillMatrixClockwisely(int[][] matrix, int startM, int startN, int startCounter, int direction, int mLength){
	//TODO: special case: startM / startN = 0?
	if(mLength == 0) return;
	if(direction > North) return;
	
	int counter = startCounter;
	int n = startN, m = startM;
	int loopCounter = matrix[0].length - mLength;
	for(int i = 0; i < mLength; i++){
	  switch(direction){
	  case East:
		m ++;
		break;
	  case South:
		n ++;
		break;
	  case West:
		m --;
		break;
	  case North:
		n --;
		break;
	  default:
		break;
	  }
	  
	  matrix[n][m] = counter;
	  counter++;
	}	
	
	direction++;
	if(direction > North) direction = East;
	int nLength = matrix.length - loopCounter - 1;
	if (nLength == 0) return;
	for(int i = 0; i < nLength; i++){
	  switch(direction){
	  case East:
		m ++;
		break;
	  case South:
		n ++;
		break;
	  case West:
		m --;
		break;
	  case North:
		n --;
		break;
	  default:
		break;
	  }
	  
	  matrix[n][m] = counter;
	  counter++;
	}
	
	//next iteration:
	direction++;
	if(direction > North) direction = East;
	this.fillMatrixClockwisely(matrix, m, n, counter, direction, mLength - 1);
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
	
	System.out.println();
	System.out.println("fill matrix: ");
	test.buildSpiralMatrix(5, 5);
	System.out.println(test.printMat());
  }
}
