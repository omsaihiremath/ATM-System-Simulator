package BankmanagementSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
//import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton sibutton, subutton, cbutton;
    JTextField cardno;
    JPasswordField p;

    Login() {

        setTitle("ATM MACHINE");
        setLayout(null);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/BankmanagementSystem/icons/logo.jpg"));
        Image i1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(i1);
        JLabel label = new JLabel(icon2);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to the ATM");
        text.setBounds(200, 30, 350, 40);
        text.setFont(new Font("Osward", Font.ITALIC | Font.BOLD, 30));
        add(text);

        JLabel cardnumber = new JLabel("CARD NUMBER: ");
        cardnumber.setBounds(150, 120, 300, 40);
        cardnumber.setFont(new Font("Osward", Font.ITALIC | Font.BOLD, 16));
        add(cardnumber);
        cardno = new JTextField();
        cardno.setBounds(300, 120, 250, 40);
        add(cardno);

        JLabel pin = new JLabel("PIN:");
        pin.setBounds(150, 160, 300, 40);
        pin.setFont(new Font("Osward", Font.ITALIC | Font.BOLD, 16));
        add(pin);
        p = new JPasswordField();
        p.setBounds(300, 160, 250, 40);
        add(p);

        sibutton = new JButton("SIGN IN");
        sibutton.setBounds(300, 200, 100, 40);
        sibutton.setBackground(Color.gray);
        sibutton.setForeground(Color.black);
        sibutton.addActionListener(this);
        add(sibutton);

        cbutton = new JButton("CLEAR");
        cbutton.setBounds(450, 200, 100, 40);
        cbutton.setBackground(Color.BLACK);
        cbutton.setForeground(Color.black);
        cbutton.addActionListener(this);
        add(cbutton);

        subutton = new JButton("SIGN IN");
        subutton.setBounds(300, 250, 250, 40);
        subutton.setBackground(Color.BLACK);
        subutton.setForeground(Color.black);
        subutton.addActionListener(this);
        add(subutton);

        getContentPane().setBackground(Color.white);

        setSize(800, 500);
        setLocation(400, 300);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cbutton) {
            cardno.setText("");
            p.setText("");

        } else if (ae.getSource() == sibutton) {
            Conn conn = new Conn();
            String cardnumber = cardno.getText();
            String pinnumber = p.getText();
            String query = "Select * from login where cardnumber = '" + cardnumber + "' and pinnumber = '"
                    + new String(p.getPassword()) + "'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transaction(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect input Card number or password");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == subutton) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Login();
    }

}
