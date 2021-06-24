import java.util.*;
/*
 * 	��� ���ڿ� �����ڴ� ����(" ")���� �����Ѵ�. 
 */
public class Operation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= MyCalculator ======== ");
		System.out.println("MyCalculator�� ����� ȯ���մϴ�.");
		while(true) {
			System.out.println("Infix�� ������ �Է��Ͻÿ�. <enter>");
			String str = sc.nextLine();
			String[] Postfix = ConvertToPostfix(str);
			System.out.print(">Postfix�� ��ȯ : ");
			for(String string : Postfix){
				if(string == null)break;
				System.out.print(string + " ");
			}
			System.out.println();					
			
			System.out.println("����� �����ұ��? (Y/N)");
			String stop = sc.nextLine();
			if(stop.equals("N")||stop.equals("n"))break;
			
			double result = operate(Postfix);
			System.out.println("��갪: " + result);
			System.out.println("����Ͻðڽ��ϱ�? (Y/N)");
			stop = sc.nextLine();
			if(stop.equals("N")||stop.equals("n"))break;
		}
		System.out.println("������ּż� �����մϴ�.");
		System.out.println("���α׷��� �����մϴ�.");
		sc.close();
	}
	
	/* Postfix�� ��ȯ�� String[]�� ���ڷ� �޾� ������ �Ѵ�.
	 * Stack�� String �� �������� �ְ� Double�� ���� �� �� �ִ�.
	 * ���� �� ��쿡 ���� ����� �ְ� ĳ������ �߸��� ��쿡 ����ó���� �Ͽ���.
	 * �׸��� ������ ����� Stack�� �ִ´�. ���������� ������ ������ ������ �����Ѵ�.
	 *  */
	
	public static double operate(String[] Postfix) {
		if(Postfix == null){
			System.out.println("������ �����ϴ�.");
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
						System.out.println("0���� ������ �����ϴ�.���α׷��� �����մϴ�.");
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
			System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
			System.exit(0);
		}
		
		
//		if(stack.isEmpty() == false){
//			System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
//			System.exit(0);
//		}
		
		double finalAnswer = (double)stack.pop();
		
		return finalAnswer;
	}
 
	/* �Է¹��� �� �� ¥�� String�� ���ڷ� �޾� ���⸦ �������� �߶��ش�.
	 * ���ڴ� �ٷ� ������ String[]�� �����ϰ�, �������� ��� Stack�� ������ �۾��Ѵ�.
	 * "("�� ��� operator ���ÿ� �ִ´�.
	 * ")"�� ��� "("�� ���ö����� ���ÿ��� pop�ϰ�, ������ String[]�� �����Ѵ�.
	 * ��Ģ������ ���, +,-�� �켱������ ����, *,/�� �켱������ ���� �Ͽ� ���ÿ� �����Ͽ��ٰ� String[]�� �����Ͽ���.
	 * */	 
	
	public static String[] ConvertToPostfix(String str) {
		if(str == null){
			System.out.println("������ �Էµ��� �ʾҽ��ϴ�. ���α׷��� �����մϴ�.");
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
						System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
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
						System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
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
						System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
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
						System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
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
					System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
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
				System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
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
			System.out.println("������ �߸��Ǿ����ϴ�. ���α׷��� �����մϴ�.");
			System.exit(0);
		}
		
		return result;
	}
}
