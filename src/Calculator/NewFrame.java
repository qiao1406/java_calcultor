package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewFrame {
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a0Button;
    private JButton decimalPoint;
    private JButton equalButton;
    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton rightBracket;
    private JButton leftBracket;
    private JButton clearButton;
    private JButton deleteButton;

    private JTextField expression;
    private JTextField result;
    private JButton copyExpButton;
    private JButton copyResButton;
    private JPanel p1;


    public NewFrame() {

        // UI Settings

        // Action Settings
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "0");
            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "1");
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "2");
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "3");
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "4");
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "5");
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "6");
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "7");
            }
        });

        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "8");
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "9");
            }
        });

        decimalPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + ".");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "+");
            }
        });
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "-");
            }
        });
        mulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "X");
            }
        });
        divButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "÷");
            }
        });
        rightBracket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + ")");
            }
        });
        leftBracket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText(expression.getText() + "(");
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expression.setText("");
                result.setText("");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( !( expression.getText().equals("")) ) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(expression.getText());
                    sb.delete(sb.length()-1 , sb.length());
                    expression.setText(sb.toString());
                }
            }
        });
        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //替换乘除号
                String cmd = expression.getText().replace("X", "*").replace("÷", "/");

                Calculate cl = new Calculate();

                String resultMsg = cl.calResult(cmd);
                if ( resultMsg.equals("算式格式错误") || resultMsg.equals("除数不能为0") ) {
                    JOptionPane.showMessageDialog(null, resultMsg, "错误", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    result.setText(resultMsg);
                }

            }
        });
        copyExpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard cl = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable t = new StringSelection(expression.getText());
                cl.setContents(t, null);
            }
        });
        copyResButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard cl = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable t = new StringSelection(result.getText());
                cl.setContents(t, null);
            }
        });


    }

    public static void main(String[] args) {
        // TODO 解决界面大小的问题

        JFrame frame = new JFrame("QiaoCalculator v3.0");
        frame.setContentPane(new NewFrame().p1);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int r = JOptionPane.showConfirmDialog(null, "Close the Calculator?", "Warning", JOptionPane.OK_OPTION);
                if ( r == JOptionPane.OK_OPTION ) {
                    System.exit(0);
                }
            }
        });
        frame.setLocation(250, 250);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }


}
