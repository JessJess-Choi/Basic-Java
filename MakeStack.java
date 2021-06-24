
public class MakeStack {
	private Object[] stack;
	private int howMany;
	
	public MakeStack(){
		stack = new Object[100];
		howMany = 0;
	}
	
	private void MakeNewStack(int howManyToAdd){
		Object NewStack[] = new Object[howManyToAdd];
		System.arraycopy(stack, 0, NewStack, 0, howMany);
		stack = NewStack;
	}
	
	public void push(Object data){
		if(stack.length == howMany)
			MakeNewStack(howMany + 100);
		stack[howMany++] = data;
	}
	
	public Object pop(){
		Object answer;
		
		if(howMany == 0){
			System.out.println("pop() 접근이 불가능합니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		
		answer = stack[--howMany];
		stack[howMany] = null;
		
		return answer;
	}
	
	public Object peek(){
		if(howMany == 0){
			System.out.println("peek() 접근이 불가능합니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		return stack[howMany-1];
	}
	
	public Boolean isEmpty(){
		if(howMany == 0) return true;
		return false;
	}

}
