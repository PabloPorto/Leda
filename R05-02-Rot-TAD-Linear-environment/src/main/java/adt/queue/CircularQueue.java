package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	} 

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element != null) {
			if(this.head == -1 && this.tail == -1) {
				this.head = 0;
				this.tail = 0;
				array[this.tail] = element;
			}
			else if( this.isFull()) {
				throw new QueueOverflowException();
			}
			else {
				this.tail = (this.tail + 1) % array.length;
				array[this.tail] = element;
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		// In this case there is only 1 element in my queue;
		else if(this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
			return array[this.head+1];
		} 
		else {
			T element = array[this.head];
			
			this.head = (this.head+1) % array.length;
			return element;
		}
	}

	@Override 
	public T head() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			return array[this.head];
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.head == this.tail);
	}

	@Override
	public boolean isFull() {
		return ((this.tail + 1) % array.length == this.head);
	}

}
