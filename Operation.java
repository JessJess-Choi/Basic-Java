import java.util.*;
/*
 * 	모든 숫자와 연산자는 공백(" ")으로 구분한다. 
 */
public class Operation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= MyCalculator ======== ");
		System.out.println("MyCalculator를 사용을 환영합니다.");
		while(true) {
			System.out.println("Infix로 수식을 입력하시오. <enter>");
			String str = sc.nextLine();
			String[] Postfix = ConvertToPostfix(str);
			System.out.print(">Postfix로 변환 : ");
			for(String string : Postfix){
				if(string == null)break;
				System.out.print(string + " ");
			}
			System.out.println();					
			
			System.out.println("계산을 시작할까요? (Y/N)");
			String stop = sc.nextLine();
			if(stop.equals("N")||stop.equals("n"))break;
			
			double result = operate(Postfix);
			System.out.println("계산값: " + result);
			System.out.println("계속하시겠습니까? (Y/N)");
			stop = sc.nextLine();
			if(stop.equals("N")||stop.equals("n"))break;
		}
		System.out.println("사용해주셔서 감사합니다.");
		System.out.println("프로그램을 종료합니다.");
		sc.close();
	}
	
	/* Postfix로 변환된 String[]을 인자로 받아 연산을 한다.
	 * Stack에 String 이 있을수도 있고 Double이 있을 수 도 있다.
	 * 따라서 그 경우에 따라 계산해 주고 캐스팅이 잘못될 경우에 예외처리를 하였다.
	 * 그리고 연산한 결과를 Stack에 넣는다. 마지막으로 연산이 끝나면 정답을 리턴한다.
	 *  */
	
	public static double operate(String[] Postfix) {
		if(Postfix == null){
			System.out.println("수식이 없습니다.");
			System.exit(0);
		}
		
		MakeStack stack = new MakeStack();
		double operator1,operator2;
		
		
		
		try{
			for(String str : Postfix){
				if(str == null)break;
				if(str.equals("+")){
					if(stack.peek() instanceof String)
						operator1 = Double.parseDouble((String)stack.pop());
					else
						operator1 = (Double)stack.pop();
					if(stack.peek() instanceof String)
						operator2 = Double.parseDouble((String)stack.pop());
					else
						operator2 = (Double)stack.pop();
					stack.push(operator1 + operator2);
				}
					
				else if(str.equals("-")){
					if(stack.peek() instanceof String)
						operator1 = Double.parseDouble((String)stack.pop());
					else
						operator1 = (Double)stack.pop();
					if(stack.peek() instanceof String)
						operator2 = Double.parseDouble((String)stack.pop());
					else
						operator2 = (Double)stack.pop();
					stack.push(-operator1 + operator2);
				}
					
				else if(str.equals("*")){
					if(stack.peek() instanceof String)
						operator1 = Double.parseDouble((String)stack.pop());
					else
						operator1 = (Double)stack.pop();
					if(stack.peek() instanceof String)
						operator2 = Double.parseDouble((String)stack.pop());
					else
						operator2 = (Double)stack.pop();
					stack.push(operator1 * operator2);
				}
					
				else if(str.equals("/")){
					if(stack.peek() instanceof String)
						operator1 = Double.parseDouble((String)stack.pop());
					else
						operator1 = (Double)stack.pop();
					if(stack.peek() instanceof String)
						operator2 = Double.parseDouble((String)stack.pop());
					else
						operator2 = (Double)stack.pop();
					if(operator1 == 0.0){
						System.out.println("0으로 나눌수 없습니다.프로그램을 종료합니다.");
						System.exit(0);
					}
					else
						stack.push(operator2 / operator1);
				}
				else	
					stack.push(str);
//				System.out.print(stack.peek() + " ");
			}
		}
		
		catch(Exception E){
			System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		
		
//		if(stack.isEmpty() == false){
//			System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
//			System.exit(0);
//		}
		
		double finalAnswer = (double)stack.pop();
		
		return finalAnswer;
	}
 
	/* 입력받은 한 줄 짜리 String을 인자로 받아 띄어쓰기를 기준으로 잘라준다.
	 * 숫자는 바로 리턴할 String[]에 저장하고, 연산자의 경우 Stack을 생성해 작업한다.
	 * "("의 경우 operator 스택에 넣는다.
	 * ")"의 경우 "("가 나올때까지 스택에서 pop하고, 리턴할 String[]에 저장한다.
	 * 사칙연산의 경우, +,-는 우선순위를 낮게, *,/는 우선순위를 높게 하여 스택에 저장하였다가 String[]에 저장하였다.
	 * */	 
	
	public static String[] ConvertToPostfix(String str) {
		if(str == null){
			System.out.println("수식이 입력되지 않았습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		

		
		StringTokenizer token = new StringTokenizer(str," ");
		String[] stringToken = new String[token.countTokens()];
		int index = 0;
		while(token.hasMoreTokens()){
			stringToken[index++] = token.nextToken();
		}
		
		String[] result = new String[str.length() + 30];
		MakeStack operator = new MakeStack();
		int resultIndex = 0;
		
		for(String string : stringToken){
			if(string.equals("+")){
				if(operator.isEmpty()){
					if(resultIndex != 0)
						operator.push(string);
					else{
						System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
						System.exit(0);
					}
				}
				else{
					while(true){
						if(operator.isEmpty()||operator.peek().equals("("))break;
						if(operator.peek().equals("+") || operator.peek().equals("-")||operator.peek().equals("*")||operator.peek().equals("/"))
							result[resultIndex++] = (String)operator.pop();
					}
					operator.push(string);
				}
			}
			else if(string.equals("-")){
				if(operator.isEmpty()){
					if(resultIndex != 0)
						operator.push(string);
					else{
						System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
						System.exit(0);
					}
				}
				else{
					while(true){
						if(operator.isEmpty()||operator.peek().equals("("))break;
						if(operator.peek().equals("+") || operator.peek().equals("-")||operator.peek().equals("*")||operator.peek().equals("/"))
							result[resultIndex++] = (String)operator.pop();

					}
					operator.push(string);
				}
			}
			else if(string.equals("*")){
				if(operator.isEmpty()){
					if(resultIndex != 0)
						operator.push(string);
					else{
						System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
						System.exit(0);
					}
				}
				else{
					if(operator.peek().equals("+")||operator.peek().equals("-")){
						operator.push(string);
					}
					else{
						while(true){
							if(operator.isEmpty() || operator.peek().equals("(") || operator.peek().equals("+") || operator.peek().equals("-"))break;
							result[resultIndex++] = (String)operator.pop();
						}
						operator.push(string);
					}
				}
			}
			else if(string.equals("/")){
				if(operator.isEmpty()){
					if(resultIndex != 0)
						operator.push(string);
					else{
						System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
						System.exit(0);
					}
				}
				else{
					if(operator.peek().equals("+")||operator.peek().equals("-")){
						operator.push(string);
					}
					else{
						while(true){
							if(operator.isEmpty()||operator.peek().equals("(") || operator.peek().equals("+") || operator.peek().equals("-"))break;
							result[resultIndex++] = (String)operator.pop();
						}
						operator.push(string);
					}
				}
			}
			else if(string.equals("(")){
				if(resultIndex == 0 || operator.peek().equals("(") || operator.peek().equals("+") || operator.peek().equals("-") || operator.peek().equals("*") || operator.peek().equals("/"))
					operator.push(string);
				else{
					System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
					System.exit(0);
				}
			}
				
			else if(string.equals(")")){
				while(!operator.peek().equals("(")){
					result[resultIndex++] = (String)operator.pop();
				}
				operator.pop();
			}
			else{
				result[resultIndex++] = string;
			}
		}
		while(!operator.isEmpty()){
			if(operator.peek().equals("(")){
				System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
				System.exit(0);
			}
			result[resultIndex++] = (String)operator.pop();
		}
		
		
		int countOperand=0 , countOperator=0;
		for(int i=0;i<resultIndex;i++){
			if(result[i].equals("+")||result[i].equals("-")||result[i].equals("*")||result[i].equals("/"))countOperator++;
			else countOperand++;
		}
		if(countOperand != countOperator+1){
			System.out.println("수식이 잘못되었습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		
		return result;
	}
}
