package BankmanagementSystem;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.sql.*;
//import com.mysql.cj.protocol.Resultset;
import java.util.Date;

import java.awt.event.*;

public class Fastcash extends JFrame implements ActionListener {
    JButton deposit, withdraw, fastcash, ministatement, pinchange, balance, exit;
    String pinnumber;

    Fastcash(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("TRANSACTION");
        setLayout(null);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/BankmanagementSystem/icons/atm.jpg"));
        Image i1 = icon1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(i1);
        JLabel label = new JLabel(icon2);
        label.setBounds(0, 0, 900, 900);
        add(label);

        JLabel text = new JLabel("PLEASE SELECT WITHDRAWAL AMOUNT");
        text.setBounds(215, 300, 700, 35);
        text.setForeground(Color.WHITE);
        label.add(text);

        deposit = new JButton("Rs 100");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        label.add(deposit);

        withdraw = new JButton("Rs 200");
        withdraw.setBounds(350, 415, 150, 30);
        withdraw.addActionListener(this);
        label.add(withdraw);

        fastcash = new JButton("Rs 500");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        label.add(fastcash);

        ministatement = new JButton("Rs 1000");
        ministatement.setBounds(350, 450, 150, 30);
        ministatement.addActionListener(this);
        label.add(ministatement);

        pinchange = new JButton("Rs 1500");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        label.add(pinchange);

        balance = new JButton("Rs 2000");
        balance.setBounds(350, 485, 150, 30);
        balance.addActionListener(this);
        label.add(balance);

        exit = new JButton("BACK");
        exit.setBounds(170, 520, 150, 30);
        exit.addActionListener(this);
        label.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3);
            Conn c = new Conn();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource() != exit && balance < Integer.parseInt(amount)) {
                    JOptionPane.showConfirmDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "Insert into bank values('" + pinnumber + "','" + date + "','Withdrawal','" + amount
                        + "')";
                c.s.executeUpdate(query);
                JOptionPane.showConfirmDialog(null, "Rs " + amount + " Has been debited Successfully!");

                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Fastcash("");
    }
}