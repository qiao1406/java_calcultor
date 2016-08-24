package Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CalFrame extends JFrame {
	
	//配色
	private final static Color OP_COLOR = new Color(251, 150, 110);
	private final static Color NUM_COLOR = new Color(36, 147, 190);
	private final static Color EQUAL_COLOR = new Color(239, 187, 36);
	private final static Color CLR_COLOR = new Color(50, 252, 75);
	private final static Color DEL_COLOR = new Color(0, 152, 120);
	
	private final static Font FONT1 = new Font("黑体", Font.BOLD, 20);
	
	private JButton num0 = new JButton("0");
	private JButton num1 = new JButton("1");
	private JButton num2 = new JButton("2");
	private JButton num3 = new JButton("3");
	private JButton num4 = new JButton("4");
	private JButton num5 = new JButton("5");
	private JButton num6 = new JButton("6");
	private JButton num7 = new JButton("7");
	private JButton num8 = new JButton("8");
	private JButton num9 = new JButton("9");
	private JButton decimalPoint = new JButton(".");
	private JButton addButton = new JButton("+");
	private JButton minusButton = new JButton("-");
	private JButton mulButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton equalButton = new JButton("=");
	private JButton leftBracket = new JButton("(");
	private JButton rightBracket = new JButton(")");
	private JButton clearButton = new JButton("Clear");
	private JButton deleteButton = new JButton("Del");
	
	private JLabel equationLabel = new JLabel("Equation");
	private JLabel resultLabel = new JLabel("Result");
	private JTextArea equation = new JTextArea(1,30);
	private JTextArea result = new JTextArea(1,30);

	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();

	public CalFrame() {

		equation.setEditable(false);
		result.setEditable(false);
		
		//颜色,字体设置
		num0.setBackground(NUM_COLOR);
		num1.setBackground(NUM_COLOR);
		num2.setBackground(NUM_COLOR);
		num3.setBackground(NUM_COLOR);
		num4.setBackground(NUM_COLOR);
		num5.setBackground(NUM_COLOR);
		num6.setBackground(NUM_COLOR);
		num7.setBackground(NUM_COLOR);
		num8.setBackground(NUM_COLOR);
		num9.setBackground(NUM_COLOR);
		decimalPoint.setBackground(NUM_COLOR);
		
		addButton.setBackground(OP_COLOR);
		addButton.setFont(FONT1);
		minusButton.setBackground(OP_COLOR);
		minusButton.setFont(FONT1);
		mulButton.setBackground(OP_COLOR);
		mulButton.setFont(FONT1);
		divButton.setBackground(OP_COLOR);
		divButton.setFont(FONT1);
		leftBracket.setBackground(OP_COLOR);
		leftBracket.setFont(FONT1);
		rightBracket.setBackground(OP_COLOR);
		rightBracket.setFont(FONT1);
		equalButton.setBackground(EQUAL_COLOR);
		equalButton.setFont(FONT1);
		clearButton.setBackground(CLR_COLOR);
		deleteButton.setBackground(DEL_COLOR);
		
		
		//添加动作
		num0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "0");
			}
		});
		num1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "1");
			}
		});
		num2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "2");
			}
		});
		num3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "3");
			}
		});
		num4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "4");
			}
		});
		num5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "5");
			}
		});
		num6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "6");
			}
		});
		num7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "7");
			}
		});
		num8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "8");
			}
		});
		num9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "9");
			}
		});
		decimalPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + ".");
			}
		});
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "+");
			}
		});
		minusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "-");
			}
		});
		mulButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "*");
			}
		});
		divButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "/");
			}
		});
		leftBracket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + "(");
			}
		});
		rightBracket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText(equation.getText() + ")");
			}
		});
		equalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = equation.getText();
				Calculate cl = new Calculate();
				result.setText(cl.calResult(cmd));
			}
		});
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equation.setText("");
				result.setText("");
			}
		});
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if( !( equation.getText().equals("")) ) {
					StringBuffer sb = new StringBuffer();
					sb.append(equation.getText());
					sb.delete(sb.length()-1 , sb.length());
					equation.setText(sb.toString());
				}
			}
		});
		
//		jp1.setLayout(null);
		jp1.add(equationLabel);
		jp1.add(equation);
		jp1.add(resultLabel);
		jp1.add(result);
		equationLabel.setBounds(0, 0, 50, 30);
		equationLabel.setLocation(0, 0);
		equation.setBounds(50, 0, 150, 30);
		equation.setLocation(50, 0);
		resultLabel.setBounds(0, 30, 50, 30);
		resultLabel.setLocation(0, 30);
		result.setBounds(50, 30, 150, 30);
		result.setLocation(50, 30);

		jp2.setLayout(new GridLayout(4, 5));
		// line-1
		jp2.add(num7);
		jp2.add(num8);
		jp2.add(num9);
		jp2.add(addButton);
		jp2.add(leftBracket);
		// line-2
		jp2.add(num4);
		jp2.add(num5);
		jp2.add(num6);
		jp2.add(minusButton);
		jp2.add(rightBracket);
		// line-3
		jp2.add(num1);
		jp2.add(num2);
		jp2.add(num3);
		jp2.add(mulButton);
		jp2.add(clearButton);
		// line-4
		jp2.add(num0);
		jp2.add(decimalPoint);
		jp2.add(equalButton);
		jp2.add(divButton);
		jp2.add(deleteButton);
		
		jp1.setLocation(0, 0);
		jp1.setVisible(true);
		jp2.setLocation(0, 100);
		jp2.setVisible(true);
		
		setLayout( new GridLayout(2,1));
//		add(BorderLayout.NORTH, jp1);
//		add(BorderLayout.SOUTH, jp2);
		add(jp1);
		add(jp2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("QiaoCalculator v2.0");
		setSize(430, 400);
		setLocation(200, 200);
		setVisible(true);
	}
	
	public static void main ( String[] args ) {
		new CalFrame();
	}
	
}
