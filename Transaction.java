package BankmanagementSystem;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener {
    JButton deposit, withdraw, fastcash, ministatement, pinchange, balance, exit;
    String pinnumber;

    Transaction(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("TRANSACTION");
        setLayout(null);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/BankmanagementSystem/icons/atm.jpg"));
        Image i1 = icon1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(i1);
        JLabel label = new JLabel(icon2);
        label.setBounds(0, 0, 900, 900);
        add(label);

        JLabel text = new JLabel("PLEASE SELECT YOUR TRANSACTION");
        text.setBounds(215, 300, 700, 35);
        text.setForeground(Color.WHITE);
        label.add(text);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        label.add(deposit);

        withdraw = new JButton("WITHDRAWS");
        withdraw.setBounds(350, 415, 150, 30);
        withdraw.addActionListener(this);
        label.add(withdraw);

        fastcash = new JButton("FAST CASH");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        label.add(fastcash);

        ministatement = new JButton("MINI STATEMENT");
        ministatement.setBounds(350, 450, 150, 30);
        ministatement.addActionListener(this);
        label.add(ministatement);

        pinchange = new JButton("PIN CHANGE");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        label.add(pinchange);

        balance = new JButton("BALANCE ENQUIRY");
        balance.setBounds(350, 485, 150, 30);
        balance.addActionListener(this);
        label.add(balance);

        exit = new JButton("EXIT");
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
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        } else if (ae.getSource() == withdraw) {
            setVisible(false);
            new Withdrawal(pinnumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new Fastcash(pinnumber).setVisible(true);
        } else if (ae.getSource() == pinchange) {
            setVisible(false);
            new Pinchange(pinnumber).setVisible(true);
        } else if (ae.getSource() == balance) {
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        } else if (ae.getSource() == ministatement) {
            new MiniStatement(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Transaction("");
    }
}