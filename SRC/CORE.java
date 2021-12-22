import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CORE extends JFrame implements ActionListener
{
    JTextField input = null;
    JTextField err = null;
    
    JButton b_1 = null;
    JButton b_2 = null;
    JButton b_3 = null;
    JButton b_4 = null;
    JButton b_5 = null;
    JButton b_6 = null;
    JButton b_7 = null;
    JButton b_8 = null;
    JButton b_9 = null;
    JButton b_0 = null;

    JButton b_left = null;
    JButton b_right = null;
    JButton b_plus = null;
    JButton b_minus = null;
    JButton b_mul = null;
    JButton b_div = null;
    JButton b_equal = null;

    JPanel panel = null;

    CORE()
    {
        super();

        Font f = new Font(Font.MONOSPACED, Font.BOLD, 24);

        setTitle("JCALC");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 1.0;

        input = new JTextField();
        input.setFont(f);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        add(input, c);
        
        c.gridwidth = 1;

        b_equal = new JButton("=");
        b_equal.setFont(f);
        b_equal.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        add(b_equal, c);

        b_1 = new JButton("1");
        b_1.setFont(f);
        b_1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        add(b_1, c);

        b_2 = new JButton("2");
        b_2.setFont(f);
        b_2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        add(b_2, c);

        b_3 = new JButton("3");
        b_3.setFont(f);
        b_3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        add(b_3, c);

        b_4 = new JButton("4");
        b_4.addActionListener(this);
        b_4.setFont(f);
        c.gridx = 0;
        c.gridy = 2;
        add(b_4, c);

        b_5 = new JButton("5");
        b_5.addActionListener(this);
        b_5.setFont(f);
        c.gridx = 1;
        c.gridy = 2;
        add(b_5, c);

        b_6 = new JButton("6");
        b_6.setFont(f);
        b_6.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        add(b_6, c);

        b_7 = new JButton("7");
        b_7.setFont(f);
        b_7.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        add(b_7, c);

        b_8 = new JButton("8");
        b_8.setFont(f);
        b_8.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        add(b_8, c);

        b_9 = new JButton("9");
        b_9.setFont(f);
        b_9.addActionListener(this);
        c.gridx = 2;
        c.gridy = 3;
        add(b_9, c);

        b_0 = new JButton("0");
        b_0.setFont(f);
        b_0.addActionListener(this);
        c.gridx = 1;
        c.gridy = 4;
        add(b_0, c);

        b_left = new JButton("(");
        b_left.setFont(f);
        b_left.addActionListener(this);
        c.gridx = 0;
        c.gridy = 4;
        add(b_left, c);

        b_right = new JButton(")");
        b_right.setFont(f);
        b_right.addActionListener(this);
        c.gridx = 2;
        c.gridy = 4;
        add(b_right, c);

        b_plus = new JButton("+");
        b_plus.setFont(f);
        b_plus.addActionListener(this);
        c.gridx = 3;
        c.gridy = 1;
        add(b_plus, c);

        b_minus = new JButton("-");
        b_minus.setFont(f);
        b_minus.addActionListener(this);
        c.gridx = 3;
        c.gridy = 2;
        add(b_minus, c);

        b_mul = new JButton("*");
        b_mul.setFont(f);
        b_mul.addActionListener(this);
        c.gridx = 3;
        c.gridy = 3;
        add(b_mul, c);

        b_div = new JButton("/");
        b_div.setFont(f);
        b_div.addActionListener(this);
        c.gridx = 3;
        c.gridy = 4;
        add(b_div, c);

        err = new JTextField();
        err.setFont(f);
        err.setEditable(false);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 4;
        add(err, c);

        pack();

        setResizable(false);

        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
        err.setText("");

        if(event.getSource()==b_equal)
        {
            String postfix = JEXP.InfixToPostfix(input.getText());

            float f = 0.0f;
            try
            {
                f = JEVAL.Evaluate(postfix);
            } 
            catch (JERR e) 
            {
                err.setText(e.getMessage());
                return;
            };

            input.setText(Float.toString(f));

            return;
        }

        JButton b = (JButton)event.getSource();
        
        String str = input.getText() + b.getActionCommand() ; 

        input.setText(str);
    }

    public static void main(String[] args) 
    {     
        CORE core = new CORE();

        core.setVisible(true);
    }

}
