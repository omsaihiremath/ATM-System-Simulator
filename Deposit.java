package BankmanagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    JButton depo, back;
    String pinnumber;
    JTextField amount;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/BankmanagementSystem/icons/atm.jpg"));
        Image i1 = icon1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(i1);
        JLabel label = new JLabel(icon2);
        label.setBounds(0, 0, 900, 900);
        add(label);

        JLabel text = new JLabel("PLEASE ENTER THE MONEY YOU WANT TO DEPOSIT");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 12));
        text.setBounds(180, 300, 700, 35);
        label.add(text);

        amount = new JTextField();
        amount.setBounds(160, 340, 350, 35);
        label.add(amount);

        depo = new JButton("DEPOSIT");
        depo.setBounds(170, 415, 150, 30);
        depo.addActionListener(this);
        label.add(depo);

        back = new JButton("BACK");
        back.setBounds(350, 415, 150, 30);
        back.addActionListener(this);
        label.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setLayout(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == depo) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount");
            } else {
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values('" + pinnumber + "','" + date + "','Deposit','" + number
                            + "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "RS " + number + " has been successfully deposited!");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("");

    }

}
