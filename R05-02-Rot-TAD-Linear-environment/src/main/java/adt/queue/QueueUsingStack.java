package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	private int count;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override 
	public void enqueue(T element) throws QueueOverflowException {
		
		if (stack1.isFull())
			throw new QueueOverflowException();

		try {
			this.stack1.push(element);
			this.count++;
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = null;
		if(stack1.isEmpty()) {
			throw new QueueUnderflowException();
		}
		else {
			for (int i = 0; i < count; i++) {
				try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace(); 
				}
				try {
					result = stack2.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			this.count --;
			for (int i = 0; i < count; i++) {
				try {
					stack1.push(stack2.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			
			return result;
		}
	}

	@Override
	public T head() {
		T result = null;
		if(stack1.isEmpty()) {
			return null;
		}
		
		else {
			for (int i = 0; i < count; i++) {
				try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}
				result = stack2.top();
			}
			
			return result;
		}
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
