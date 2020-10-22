package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}
 
	@Override
	public T top() {
		if(this.isEmpty()) {
			return null;
		} 
		else {
			return array[top];
		}
		 
	}

	@Override
	public boolean isEmpty() {
		if(this.top == -1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(this.top == array.length - 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override 
	public void push(T element) throws StackOverflowException {
		
		if(element != null) {
			if(this.isFull() == true) {
				throw new StackOverflowException();
			}
			else {
				this.top++;
				this.array[top] = element;
			}  
		} 
	} 
	
	@Override
	public T pop() throws StackUnderflowException {
		if(this.isEmpty() == true) {
			throw new StackUnderflowException();
		}
		else {
			this.top--;
			return array[top+1]; 
		}
	}

}
