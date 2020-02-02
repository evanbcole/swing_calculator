/*
Evan Cole
ebc180001

Programming calculator made with Swing, based on the Windows 10 calculator.

Note to grader:
I intentionally chose not to include to buttons highlighted in red on the assignment page as I felt like they were
unnecessary since we were not required to implement them.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Calculator extends JFrame implements ActionListener {
    private JButton arrow, mod, ce, clear, delete, div, A, B, seven, eight, nine, mult, C, D, four, five, six,
            minus, E, F, one, two, three, plus, openPar, closePar, plusMin, zero, dot, equals, hex, dec, oct, bin;
    private JTextArea out;
    private JFrame frame;
    private JPanel ui;

    private int first, second;
    private int operation;
    private int result;
    private int eqPress = 0;
    private int minPress= 0;
    private int multPress = 0;
    private int divPress = 0;
    private int mode = 0;


    public Calculator() {
        ui = new JPanel(new BorderLayout(5,5));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));
        out = new JTextArea(2, 25);
        out.setFont(out.getFont().deriveFont(20f));
        JTextArea sec = new JTextArea(1, 25);
        sec.setFont(sec.getFont().deriveFont(20f));

        // makes the text area uneditable so that it can't be typed in
        out.setEditable(false);

        ui.add(out, BorderLayout.NORTH);
        ui.add(sec, BorderLayout.CENTER);

        frame = new JFrame("Calculator");
        frame.setContentPane(ui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel =  new JPanel(new GridLayout(6, 6, 2, 2));

        // create JButtons
        hex = new JButton("HEX");
        dec = new JButton("DEC");
        oct = new JButton("OCT");
        bin = new JButton("BIN");
        arrow = new JButton("↑");
        mod = new JButton("MOD");
        ce = new JButton("CE");
        clear = new JButton("C");
        delete = new JButton("DEL");
        div = new JButton("÷");
        A = new JButton("A");
        B = new JButton("B");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        mult = new JButton("×");
        C = new JButton("C");
        D = new JButton("D");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        minus = new JButton("-");
        E = new JButton("E");
        F = new JButton("F");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        plus = new JButton("+");
        openPar = new JButton("(");
        closePar = new JButton(")");
        plusMin = new JButton("±");
        zero = new JButton("0");
        dot = new JButton(".");
        equals = new JButton("=");

        // create filler buttons so that buttons retain correct format despite extra space
        JButton fill1 = new JButton();
        JButton fill2 = new JButton();

        // add buttons to panel in correct order
        buttonPanel.add(fill1);
        buttonPanel.add(hex);
        buttonPanel.add(dec);
        buttonPanel.add(oct);
        buttonPanel.add(bin);
        buttonPanel.add(fill2);
        buttonPanel.add(arrow);
        buttonPanel.add(mod);
        buttonPanel.add(ce);
        buttonPanel.add(clear);
        buttonPanel.add(delete);
        buttonPanel.add(div);
        buttonPanel.add(A);
        buttonPanel.add(B);
        buttonPanel.add(seven);
        buttonPanel.add(eight);
        buttonPanel.add(nine);
        buttonPanel.add(mult);
        buttonPanel.add(C);
        buttonPanel.add(D);
        buttonPanel.add(four);
        buttonPanel.add(five);
        buttonPanel.add(six);
        buttonPanel.add(minus);
        buttonPanel.add(E);
        buttonPanel.add(F);
        buttonPanel.add(one);
        buttonPanel.add(two);
        buttonPanel.add(three);
        buttonPanel.add(plus);
        buttonPanel.add(openPar);
        buttonPanel.add(closePar);
        buttonPanel.add(plusMin);
        buttonPanel.add(zero);
        buttonPanel.add(dot);
        buttonPanel.add(equals);

        // make filler buttons invisible so it leave blank space instead of blank buttons
        fill1.setVisible(false);
        fill2.setVisible(false);

        // color used to differentiate numbers from other buttons
        Color numColor = new Color(222, 229, 239);

        // set colors
        one.setBackground(numColor);
        two.setBackground(numColor);
        three.setBackground(numColor);
        four.setBackground(numColor);
        five.setBackground(numColor);
        six.setBackground(numColor);
        seven.setBackground(numColor);
        eight.setBackground(numColor);
        nine.setBackground(numColor);
        zero.setBackground(numColor);

        /*
        Arrow and dot buttons are greyed out because they are intentionally not implemented.
        In the case of arrow it is used with functions that we were told not to implement
        For the decimal point (dot), the programming calculator on windows 10 has the decimal point greyed out and
        unavailable, so I chose to do the same.
        I decided to keep them on the calculator for visual purposes.
         */

        arrow.setEnabled(false);
        dot.setEnabled(false);


        // add actionListener for buttons
        arrow.addActionListener(this);
        mod.addActionListener(this);
        ce.addActionListener(this);
        clear.addActionListener(this);
        delete.addActionListener(this);
        div.addActionListener(this);
        A.addActionListener(this);
        B.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        mult.addActionListener(this);
        C.addActionListener(this);
        D.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        minus.addActionListener(this);
        E.addActionListener(this);
        F.addActionListener(this);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        plus.addActionListener(this);
        openPar.addActionListener(this);
        closePar.addActionListener(this);
        plusMin.addActionListener(this);
        zero.addActionListener(this);
        equals.addActionListener(this);
        hex.addActionListener(this);
        dec.addActionListener(this);
        oct.addActionListener(this);
        bin.addActionListener(this);

        ui.add(buttonPanel, BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    /*
    Implementation of actionListener for each button
    Some things to note:
    out.append(" *SOMETHING* " ) is simply adding a digit to the current number being entered. 5 append 5 becomes 55.
    When the variable "mode" is changed, it is changing the active numbering system (hex/dec/oct/bin)
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == one) {
            out.append("1");
        }

        if (e.getSource() == two) {
            out.append("2");
        }

        if (e.getSource() == three) {
            out.append("3");
        }

        if (e.getSource() == four) {
            out.append("4");
        }

        if (e.getSource() == five) {
            out.append("5");
        }

        if (e.getSource() == six) {
            out.append("6");
        }

        if (e.getSource() == seven) {
            out.append("7");
        }

        if (e.getSource() == eight) {
            out.append("8");
        }

        if (e.getSource() == nine) {
            out.append("9");
        }

        if (e.getSource() == zero) {
            out.append("0");
        }


        if (e.getSource() == A) {
            out.append("a");
            mode = 1;
        }

        if (e.getSource() == B) {
            out.append("b");
            mode = 1;
        }

        if (e.getSource() == C) {
            out.append("c");
            mode = 1;
        }

        if (e.getSource() == D) {
            out.append("d");
            mode = 1;
        }

        if (e.getSource() == E) {
            out.append("e");
            mode = 1;
        }

        if (e.getSource() == F) {
            out.append("f");
            mode = 1;
        }

        if (e.getSource() == openPar) {
            out.append("(");
        }

        if (e.getSource() == closePar) {
            out.append(")");
        }

        if (e.getSource() == plus) {
            // case for if equal has been pressed before so value will correctly carry over
            if (eqPress > 0) {
                operation = 1;
                out.setText("");
            }

            else {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first += Integer.parseInt(out.getText());
                operation = 1;
                out.setText("");
            }
        }

        if (e.getSource() == minus) {
            // case for if equal has been pressed before so value will correctly carry over
            if (eqPress > 0) {
                operation = 2;
                out.setText("");
            }

            // specific case for the first time minus is pressed so that the first number is not automatically negative
            else if (minPress == 0) {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first = Integer.parseInt(out.getText());
                operation = 2;
                out.setText("");
                minPress++;
            }

            else {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first -= Integer.parseInt(out.getText());
                operation = 2;
                out.setText("");
                minPress++;
            }
        }

        if (e.getSource() == mult) {
            if (eqPress > 0) {
                operation = 3;
                out.setText("");
            }

            // case for when mult is used for the first time so mult is not multiplied prematurely
            else if (multPress == 0) {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first = Integer.parseInt(out.getText());
                operation = 3;
                out.setText("");
                multPress++;
            }

            else {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first *= Integer.parseInt(out.getText());
                operation = 3;
                out.setText("");
                multPress++;
            }
        }

        if (e.getSource() == div) {
            if (eqPress > 0) {
                operation = 4;
                out.setText("");
            }

            // case for when div is used for the first time so first is not divided prematurely
            else if (divPress == 0) {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first = Integer.parseInt(out.getText());
                operation = 4;
                out.setText("");
                divPress++;
            }

            else {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first /= Integer.parseInt(out.getText());
                operation = 4;
                out.setText("");
                divPress++;
            }
        }

        if (e.getSource() == mod) {
            if (eqPress > 0) {
                operation = 5;
                out.setText("");
            }

            else {
                // strip ( and )
                if (out.getText().charAt(0) == '(') {
                    out.setText(out.getText().substring(1, out.getText().length() - 1));
                }

                // if mode is hex, convert to decimal
                if (mode == 1) {
                    out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
                }

                first = Integer.parseInt(out.getText());
                operation = 5;
                out.setText("");
            }
        }

        // When = is entered
        if (e.getSource() == equals) {
            // strip ( and )
            if (out.getText().charAt(0) == '(') {
                out.setText(out.getText().substring(1, out.getText().length() - 1));
            }

            // if mode is hex, convert to decimal
            if (mode == 1) {
                out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
            }

            // second operand
            second = Integer.parseInt(out.getText());
            eqPress++;  // increment eqPress so that operations do not remove the result value

            // switch case to determine operation to preform
            switch (operation) {
                case 1:
                    result = first + second;
                    break;

                case 2:
                    result = first - second;
                    break;

                case 3:
                    result = first * second;
                    break;

                case 4:
                    result = first / second;
                    break;

                case 5:
                    result = first % second;
                    break;
            }

            first = result; // set first = result so that the result can be edited and used again
            out.setText(result + "");   // have result display in the window
        }

        // clear and reset all variables
        if (e.getSource() == clear) {
            out.setText("");
            first = 0;
            second = 0;
            result = 0;
            eqPress = 0;
            multPress = 0;
            divPress = 0;
            minPress = 0;
            mode = 0;
        }

        // clear only current entry
        if (e.getSource() == ce) {
            out.setText("");
        }

        /*
        Below are are if statements that handle conversion between dec, hex, oct, and bin numbers
        The mode variable signifies the current status of the number since the same number can represent different
        values depending on which form it is in.
        0 = dec
        1 = hex
        2 = oct
        3 = bin
         */

        // hexadecimal conversion
        if (e.getSource() == hex) {
            if (out.getText().equals("")) {
                mode = 1;
            }

            if (mode == 0) {    // dec to hex
                out.setText(Integer.toHexString(Integer.parseInt(out.getText())));
            }

            if (mode == 2) {    // oct to hex
                out.setText(Integer.toHexString(Integer.parseInt(out.getText(), 8)));
            }

            if (mode == 3) {    // bin to hex
                out.setText(Integer.toHexString(Integer.parseInt(out.getText(), 2)));
            }

            mode = 1;

            // enables digits disabled by oct and dec
            two.setEnabled(true);
            three.setEnabled(true);
            four.setEnabled(true);
            five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);
            eight.setEnabled(true);
            nine.setEnabled(true);

        }

        // octal conversion
        if (e.getSource() == oct) {
            if (out.getText().equals("")) {
                mode = 2;
            }

            if (mode == 0) {    // dec to oct
                out.setText(Integer.toOctalString(Integer.parseInt(out.getText())));
            }

            if (mode == 1) {    // hex to oct
                out.setText(Integer.toOctalString(Integer.parseInt(out.getText(), 16)));
            }

            if (mode == 3) {    // bin to oct
                out.setText(Integer.toOctalString(Integer.parseInt(out.getText(), 2)));
            }

            mode = 2;

            // disables eight and nine as they are not valid octal digits
            eight.setEnabled(false);
            nine.setEnabled(false);

            // enables digits disabled by binary
            two.setEnabled(true);
            three.setEnabled(true);
            four.setEnabled(true);
            five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);

        }

        // binary conversion
        if (e.getSource() == bin) {
            if (out.getText().equals("")) {
                mode = 3;
            }

            if (mode == 0) {    // dec to bin
                out.setText(Integer.toBinaryString(Integer.parseInt(out.getText())));
            }

            if (mode == 1) {    // hex to bin
                out.setText(Integer.toBinaryString(Integer.parseInt(out.getText(), 16)));
            }

            if (mode == 2) {    // oct to bin
                out.setText(Integer.toBinaryString(Integer.parseInt(out.getText(), 8)));
            }

            mode = 3;

            // disable every digit but 1 and 0 as they are the only valid digits in binary
            two.setEnabled(false);
            three.setEnabled(false);
            four.setEnabled(false);
            five.setEnabled(false);
            six.setEnabled(false);
            seven.setEnabled(false);
            eight.setEnabled(false);
            nine.setEnabled(false);
        }

        // decimal conversion
        if (e.getSource() == dec) {
            if (out.getText().equals("")) {
                mode = 0;
            }

            if (mode == 1) {    // hex to dec
                out.setText(Integer.toString(Integer.parseInt(out.getText(), 16)));
            }

            if (mode == 2) {    // oct to dec
                out.setText(Integer.toString(Integer.parseInt(out.getText(), 8)));
            }

            if (mode == 3) {    // bin to dec
                out.setText(Integer.toString(Integer.parseInt(out.getText(), 2)));
            }

            mode = 0;

            // enables the eight and nine buttons if they were disable by oct
            two.setEnabled(true);
            three.setEnabled(true);
            four.setEnabled(true);
            five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);
            eight.setEnabled(true);
            nine.setEnabled(true);
        }

        // reverses the sign of the current value
        if (e.getSource() == plusMin) {
            int reverse = Integer.parseInt(out.getText()) * -1;
            out.setText(Integer.toString(reverse));
        }

        // backspace key. deletes one digit from the end of the string
        if (e.getSource() == delete) {
            out.setText(out.getText().substring(0, out.getText().length() - 1));
        }

    }

    public static void main(String[] args) {
        /*
        changes look and feel to "nimbus"
        this is included in a try catch block because .setLookAndFeel throws multiple exceptions related to
        the look and feel not being available, but nimbus is found in the swing package so an empty catch block is fine
         */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e){}

        // creates a calculator object, which opens the calculator window
        new Calculator();
    }
}
