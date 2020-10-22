package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) { 
		
		quickSort(array, 0, array.length - 1);
		return binarySearch(array, x, 0, array.length -1);		
		
	}

	private void quickSort(Integer[] array, int begin, int end) {

		if(begin>=end) {
			return;
		}

		Integer pivot = selectPivot(array, begin, end);
		quickSort(array,begin,pivot-1);
		quickSort(array,pivot+1,end);
		
		
	}

	private Integer selectPivot(Integer[] array, int begin, int end) {
		
		int i = begin;
		Integer pivot = array[begin];
		
		for (int j = i+1 ; j <= end; j++) {
			if(array[j] <= pivot) {
				i++;
				Util.swap(array, i, j);
			}
		}
		
		Util.swap(array, begin, i);
		
		return i;
	}

	private Integer binarySearch(Integer[] array, Integer x, int begin, int end) {
		
		int middle = (begin + end) / 2;
		
		if(begin > end && end != -1) {
			return array[end];
		}
		else if (begin > end && end == -1){
			return null;
		}
		
		if (x < array[middle]) {
			return binarySearch(array, x, begin, middle-1);
		}
		else if (x > array[middle]){
			return binarySearch(array, x, middle+1, end);
		}
		else {
			return array[middle];
		}
		
	}
}
