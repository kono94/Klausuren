
public class MergeOutput {
	public static int[] intArr = {8,7,6,5,4,3,2,1};
	static void merge(int[] field){
		merg_help(field, 0 , field.length-1);
	}
	static void merg_help(int[] field, int iLeft, int iRight){
		if(iLeft < iRight){
			final int MIDDLE = (iLeft + iRight) /2;
			merg_help(field, iLeft, MIDDLE);
			merg_help(field, MIDDLE +1, iRight);
			merge(iLeft, MIDDLE, iRight);
			for(int i=0; i< field.length; ++i){
				System.out.print(field[i] + "\t");
			}
			System.out.println();
		}
	}
	
	static void merge(int l, int q, int r){
		 int[] arr = new int[intArr.length]; 
	        int i, j; 
	        for (i = l; i <= q; i++) { 
	            arr[i] = intArr[i]; 
	        } 
	        for (j = q + 1; j <= r; j++) { 
	            arr[r + q + 1 - j] = intArr[j]; 
	        } 
	        i = l; 
	        j = r; 
	        for (int k = l; k <= r; k++) { 
	            if (arr[i] <= arr[j]) { 
	                intArr[k] = arr[i]; 
	                i++; 
	            } else { 
	                intArr[k] = arr[j]; 
	                j--; 
	            } 
	        } 
	}
public static void main(String[] args) {
	
	merge(intArr);
}
}
