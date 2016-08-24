package Calculator;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
	
	private Stack<Double> numStack = new Stack<Double>();
	private Stack<Character> sybStack = new Stack<Character>(); 
	
	public String calResult ( String cmd ) {
		cmd += "#";
		
		StringBuffer tempNum = new StringBuffer();
		StringBuffer exp = new StringBuffer().append(cmd);
		
		while ( exp.length() != 0 ) {
			
			String temp = exp.substring(0,1);
			exp.delete(0, 1);
			
			if( !isNum(temp) ) { // temp不是数字
				if (!"".equals(tempNum.toString())) {
					// 当表达式的第一个符号为括号
					double num = Double.parseDouble(tempNum.toString());
					numStack.push(num);
					tempNum.delete(0, tempNum.length());
				}
				// 用当前取得的运算符与栈顶运算符比较优先级：若高于，则因为会先运算，放入栈顶；若等于，因为出现在后面，所以会后计算，所以栈顶元素出栈，取出操作数运算；
				// 若小于，则同理，取出栈顶元素运算，将结果入操作数栈。

				// 判断当前运算符与栈顶元素优先级，取出元素，进行计算(因为优先级可能小于栈顶元素，还小于第二个元素等等，需要用循环判断)
				while ( !compare(temp.charAt(0)) && (!sybStack.empty()) ) {
					double a = numStack.pop();
					double b = numStack.pop();
					char ope = sybStack.pop();
					double result = 0;
					
					switch (ope) {
					// 如果是加号或者减号，则
					case '+':
						result = b + a;
						// 将操作结果放入操作数栈
						numStack.push(result);
						break;
					case '-':
						result = b - a;
						// 将操作结果放入操作数栈
						numStack.push(result);
						break;
					case '*':
						result = b * a;
						// 将操作结果放入操作数栈
						numStack.push(result);
						break;
					case '/':
						result = b / a;// 将操作结果放入操作数栈
						numStack.push(result);
						break;
					}

				}
				
				// 判断当前运算符与栈顶元素优先级， 如果高，或者低于平，计算完后，将当前操作符号，放入操作符栈
				if (temp.charAt(0) != '#') {
					sybStack.push(new Character(temp.charAt(0)));
					if (temp.charAt(0) == ')') {// 当栈顶为'('，而当前元素为')'时，则是括号内以算完，去掉括号
						sybStack.pop();
						sybStack.pop();
					}
				}
			}
			else { // temp是数字
				tempNum.append(temp);
			}
			
		}
		return numStack.pop() + "";
	}

	private static boolean isNum(String temp) {
		return temp.matches("[0-9]") || temp.equals(".");
	}

	private boolean compare(char str) {
		if ( sybStack.empty() ) {
			// 当为空时，显然 当前优先级最低，返回高
			return true;
		}
		char last = (char) sybStack.lastElement();
		// 如果栈顶为'('显然，优先级最低，')'不可能为栈顶。
		if (last == '(') {
			return true;
		}
		switch (str) {
		case '#':
			return false;// 结束符
		case '(':
			// '('优先级最高,显然返回true
			return true;
		case ')':
			// ')'优先级最低，
			return false;
		case '*': {
			// '*/'优先级只比'+-'高
			if (last == '+' || last == '-')
				return true;
			else
				return false;
		}
		case '/': {
			if (last == '+' || last == '-')
				return true;
			else
				return false;
		}
		// '+-'为最低，一直返回false
		case '+':
			return false;
		case '-':
			return false;
		}
		return true;
	}
	
}
