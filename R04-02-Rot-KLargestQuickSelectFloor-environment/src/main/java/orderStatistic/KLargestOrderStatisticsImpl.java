package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		
		Comparable[] result = null;
		
		if(k>=1 && k<=array.length) {
			
			result = new Comparable[array.length - k];
			T element = orderStatistics(array, k);
			int control = 0;
			
			for (int i = 0; i < array.length; i++) {
				if(array[i].compareTo(element) > 0) {
					result[control] = array[i];
					control++;
				}
			}
		}
		else {
			result = new Comparable[1];
		}
		
		return (T[]) result;
	
	}
		
	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		
		quickSort(array, 0, array.length - 1);
		
		if(k<1) {
			return null;
		}
		
		else if (k>=1 && k<=array.length) {
			return array[k-1];
		}
		else {
			return null;
		}
	}
	
	private void quickSort(T[] array, int begin, int end) {
		
		if(begin >= end) {
			return;
		}
		
		int pivot = selectPivot(array, begin, end);
		quickSort(array, begin, pivot-1);
		quickSort(array, pivot+1, end);
		
	}

	private int selectPivot(T[] array, int begin, int end) {
		int i = begin;
		T pivot = array[begin];
		
		for (int j = i+1; j <= end; j++) {
			if(array[j].compareTo(pivot) <= 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		
		Util.swap(array, begin, i);
		return i;
	}
}
