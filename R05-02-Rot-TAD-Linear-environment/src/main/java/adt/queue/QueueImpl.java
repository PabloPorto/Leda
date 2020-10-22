package adt.queue;

public class QueueImpl<T> implements Queue<T> {
  
	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (this.isEmpty()) {
			return null;
		}
		else {
			return array[0]; 
		}
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == array.length - 1;
	}

	private void shiftLeft() {
		
		for (int i = 0; i < tail; i++) {
			array[i] = array[i+1];
		}
		
		this.tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element != null) {
			if(this.isFull()) {
				throw new QueueOverflowException();
			}
			else if (element != null) {
				tail++; 
				array[tail] = element;
			}
		}
	}
 
	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		else { 
			
			T element = array[0];
			this.shiftLeft();
			
			return element;
		}
	}

}
