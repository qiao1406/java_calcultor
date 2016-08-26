package Calculator;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.security.krb5.internal.ccache.CCacheInputStream;

public class Calculate {
	
	private Stack<Double> numStack = new Stack<Double>();
	private Stack<Character> sybStack = new Stack<Character>(); 
	
	public String calResult ( String equation ) {
		
		//替换乘除号
		equation = equation.replace("X", "*");
		equation = equation.replace("÷", "/");
		
		//处理负号
		equation = negativeNumTransfer(equation);
		
		if ( !checkFormat(equation) ) {
			return "算式格式错误";
		}
		
		equation += "#";
		StringBuffer tempNum = new StringBuffer();
		StringBuffer exp = new StringBuffer().append(equation);
		
		while ( exp.length() != 0 ) {
			
			String temp = exp.substring(0,1);
			exp.delete(0, 1);
			
			if( isNum(temp) )  { // temp是数字
				tempNum.append(temp);
			}
//			else if ( exp.length() > 1 && temp.equals("-") && 
//					isNum(exp.substring(1, 2)) ) {
//				System.out.println("xxx");
//				tempNum.append(temp);
//			}
			else { // temp不是数字
				
				if (!"".equals(tempNum.toString())) {
					// 当表达式的第一个符号为括号
					double num = Double.parseDouble(tempNum.toString());
					numStack.push(num);
					tempNum.delete(0, tempNum.length());
				}
				// 用当前取得的运算符与栈顶运算符比较优先级：若高于，则因为会先运算，放入栈顶；若等于，因为出现在后面，
				// 所以会后计算，所以栈顶元素出栈，取出操作数运算；若小于，则同理，取出栈顶元素运算，将结果入操作数栈。

				// 判断当前运算符与栈顶元素优先级，取出元素，进行计算(因为优先级可能小于栈顶元素，还小于第二个元素等等，需要用循环判断)
				while ( !compare(temp.charAt(0)) && (!sybStack.empty()) ) {
					double a = numStack.pop();
					double b = numStack.pop();
					char ope = sybStack.pop();
					
					// 进行简单的计算
					if( simpleCal(ope, a, b) == false ) {
						return "除数不能为0";
					}
					
				}
				
				// 判断当前运算符与栈顶元素优先级， 如果高，或者低于平，计算完后，将当前操作符号，放入操作符栈
				refreshSybStack(temp);
				
			}
			
		}
		
		return getResultStr(numStack.pop());
	}
	
	private void refreshSybStack ( String temp) {
		if (temp.charAt(0) != '#') {
			sybStack.push(new Character(temp.charAt(0)));
			if (temp.charAt(0) == ')') {// 当栈顶为'('，而当前元素为')'时，则是括号内以算完，去掉括号
				sybStack.pop();
				sybStack.pop();
			}
		}
	} 
	
	private boolean simpleCal ( char ope, double a, double b ) {
		
		double result = 0;
		
		switch (ope) {
		case '+':
			result = b + a;
			numStack.push(result);
			break;
		case '-':
			result = b - a;
			numStack.push(result);
			break;
		case '*':
			result = b * a;
			numStack.push(result);
			break;
		case '/':
			
			if ( a == 0.0 ) {
				return false;
			}
			else {
				result = b / a;
				numStack.push(result);
				break;
			}
			
		}
		
		return true;
	}
	
	private String negativeNumTransfer( String equation ) {
		// 处理算式，将表示负数的部分进行改动，转成calResult方法支持的 
//		char[] c = equation.toCharArray();
		
		if( equation.length() <= 1 ) {
			return equation;
		}
		
		StringBuffer str = new StringBuffer().append(equation);
		
		for ( int i = 0; i < str.length()-1; ++i ) {
			
			if( !str.substring(i, i+1).equals("-") ) {
				continue;
			}
			
			if ( i == 0 ) {
				char temp = str.charAt(1);
				if( isNumChar(temp) || isDecimalPoint(temp) || isLeftBracket(temp) ) {
					str.insert(0, "0");
					i++;
				}
			}
			else {
				char last = str.charAt(i-1);
				char next = str.charAt(i+1);
				
				if( isLeftBracket(last) &&
					( isNumChar(next) || isDecimalPoint(next) || isLeftBracket(next) ) ) {
					str.insert(i, "0");
					i++;
				}
			}
		}
				
		
		return str.toString();
	}
	
	
	
	private boolean checkFormat ( String equation ) {
		char[] c = equation.toCharArray();
		int singleBracket = 0;
		
		for( int i = 0; i < c.length; ++i ) {
			
			if( isLeftBracket(c[i]) ) {
				singleBracket++;
			}
			if ( isRightBracket(c[i]) ) {
				singleBracket--;
			}
			
			if ( i == 0 ) { //第1个元素只能是[0-9]或者是左括号
				if( !isLeftBracket(c[i]) && !isNumChar(c[i]) ) {
					return false;
				}
			}
			else if ( isNumChar(c[i]) || isDecimalPoint(c[i]) ) { //数字左边不能是右括号
				if ( isRightBracket(c[i-1]) ) {
					return false;
				}
			}
			else if( isLeftBracket(c[i]) )  { // 左括号的左边不能是数字和右括号
				if ( isNumChar(c[i-1]) || isDecimalPoint(c[i-1]) || isRightBracket(c[i-1]) ) {
					return false;
				}
			}
			else {  // 右括号和四则运算符的左边只能是数字或者右括号
				if ( !isNumChar(c[i-1]) && !isRightBracket(c[i-1]) ) {
					return false;
				}
			}
			
		}
		
		return singleBracket == 0;
	}

	private static boolean isNum ( String temp ) {
		return temp.matches("[0-9]") || temp.equals(".");
	}
	
	private static boolean isLeftBracket ( char c ) {
		return c == '(';
	}
	
	private static boolean isRightBracket ( char c ) {
		return c == ')';
	}
	
	private static boolean isDecimalPoint ( char c ) {
		return c == '.';
	}
	
	private static boolean isNumChar ( char c ) {
		return ( c >= '0' && c <= '9' );
	}

	private boolean compare (char str) {
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
	
	private String getResultStr ( double result ) {
		StringBuffer s = new StringBuffer().append( result + "" );
		
		if ( s.substring(s.length() - 2).equals(".0") ) {
			s.delete( s.length()-2 , s.length() );
		}
		
		return s.toString();
	}
	
}
