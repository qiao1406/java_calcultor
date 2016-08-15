package Calculator;

import java.util.regex.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CalFrame extends JFrame {

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
	private JButton addButton = new JButton("+");
	private JButton minusButton = new JButton("-");
	private JButton mulButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton equalButton = new JButton("=");
	private JButton clearButton = new JButton("Clear");

	private JTextArea rst = new JTextArea();

	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();

	public CalFrame() {

		rst.setSize(250, 50);
//		rst.setBounds(0, 0, 250, 50);
		rst.setEditable(false);

		num0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "0");
			}
		});
		num1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "1");
			}
		});
		num2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "2");
			}
		});
		num3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "3");
			}
		});
		num4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "4");
			}
		});
		num5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "5");
			}
		});
		num6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "6");
			}
		});
		num7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "7");
			}
		});
		num8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "8");
			}
		});
		num9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "9");
			}
		});
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "+");
			}
		});
		minusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "-");
			}
		});
		mulButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "*");
			}
		});
		divButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText(rst.getText() + "/");
			}
		});
		equalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = rst.getText();
				String regex = "\\d+[\\+\\-\\*\\/]\\d+";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(cmd);
				if ( m.find() ) {
					
					String opRegex = "[\\+\\-\\*\\/]";
					String[] s = Pattern.compile(opRegex).split(cmd);
					Matcher m1 = Pattern.compile(opRegex).matcher(cmd);
					
					int num1 = Integer.parseInt(s[0]);
					int num2 = Integer.parseInt(s[1]);
					m1.find();
					String op = String.valueOf( cmd.charAt( m1.start() ) );
					String result = Calculate.cal(num1, num2, op);
					rst.setText(result);
				}
			}
		});
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rst.setText("");
			}
		});
		
		jp1.add(rst);

		jp2.setLayout(new GridLayout(4, 4));
		// line-1
		jp2.add(num7);
		jp2.add(num8);
		jp2.add(num9);
		jp2.add(addButton);
		// line-2
		jp2.add(num4);
		jp2.add(num5);
		jp2.add(num6);
		jp2.add(minusButton);
		// line-3
		jp2.add(num1);
		jp2.add(num2);
		jp2.add(num3);
		jp2.add(mulButton);
		// line-4
		jp2.add(num0);
		jp2.add(equalButton);
		jp2.add(clearButton);
		jp2.add(divButton);
		

		add(BorderLayout.NORTH, jp1);
		add(BorderLayout.SOUTH, jp2);
		
		setSize(300, 400);
		setTitle("QiaoCalculator v1.0");
		pack();
		setVisible(true);
	}

}
