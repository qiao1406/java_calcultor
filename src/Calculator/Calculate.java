package Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
	
	public static String calResult ( String cmd ) {
		String regex = "\\d+[\\+\\-\\*\\/]\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(cmd);
		System.out.println(cmd);
		if ( m.find() ) {
			
			String opRegex = "[\\+\\-\\*\\/]";
			String[] s = Pattern.compile(opRegex).split(cmd);
			Matcher m1 = Pattern.compile(opRegex).matcher(cmd);
			
			int num1 = Integer.parseInt(s[0]);
			int num2 = Integer.parseInt(s[1]);
			m1.find();
			String op = String.valueOf( cmd.charAt( m1.start() ) );
			String result = cal(num1, num2, op);
			return cmd + " = " + result;
		}
		else {
			return "输入错误";
		}
	}

	public static String cal(int n1, int n2, String op) {

		String resultStr = "";
		int resultInt;
		double resultDouble;

		switch (op) {
		case "+":
			resultInt = n1 + n2;
			resultStr = String.valueOf(resultInt);
			break;

		case "-":
			resultInt = n1 - n2;
			resultStr = String.valueOf(resultInt);
			break;

		case "*":
			resultInt = n1 * n2;
			resultStr = String.valueOf(resultInt);
			break;

		case "/":
			
			if( n2 == 0 ) {
				resultStr = "错误，除数不能为0";
			}
			else {
				double d1 = (double)n1;
				double d2 = (double)n2;
				resultDouble = d1/d2;
				resultStr = String.valueOf(resultDouble);
			}
			
			
			break;

		default:
			resultStr = "Unknown Error";
			break;
		}

		return resultStr;
	}

}
