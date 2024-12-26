package BankmanagementSystem;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
//import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class Signupthree extends JFrame implements ActionListener {

    JRadioButton savings, current, fd, recurring;
    JCheckBox atm, ibanking, mbanking, alerts, cbook, estatement, ack;
    JButton submit, cancel;
    String formno;

    Signupthree(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION");
        setLayout(null);

        JLabel page = new JLabel("PAGE 3: ACCOUNT DETAILS");
        page.setBounds(280, 80, 350, 40);
        page.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(page);

        JLabel accounttype = new JLabel("ACCOUT TYPE: ");
        accounttype.setBounds(200, 150, 350, 40);
        accounttype.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(accounttype);

        savings = new JRadioButton("Savings Account");
        savings.setBounds(340, 140, 350, 40);
        add(savings);

        current = new JRadioButton("Current Account");
        current.setBounds(510, 140, 350, 40);
        add(current);

        fd = new JRadioButton("Fixed Deposit Account");
        fd.setBounds(340, 180, 350, 40);
        add(fd);

        recurring = new JRadioButton("Recurring Account");
        recurring.setBounds(510, 180, 350, 40);
        add(recurring);

        ButtonGroup acc = new ButtonGroup();
        acc.add(savings);
        acc.add(current);
        acc.add(fd);
        acc.add(recurring);

        JLabel card = new JLabel("CARD NUMBER: ");
        card.setBounds(200, 230, 350, 40);
        card.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-3124 ");
        number.setBounds(360, 230, 350, 40);
        number.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(number);

        JLabel Pinnumber = new JLabel("PIN: ");
        Pinnumber.setBounds(200, 280, 350, 40);
        Pinnumber.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(Pinnumber);

        JLabel pnumber = new JLabel("XXXX ");
        pnumber.setBounds(360, 280, 350, 40);
        pnumber.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(pnumber);

        JLabel services = new JLabel("SERVICES REQUIRED: ");
        services.setBounds(200, 330, 350, 40);
        services.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(services);

        atm = new JCheckBox("ATM CARD");
        atm.setBounds(340, 350, 350, 40);
        ibanking = new JCheckBox("INTERNET BANKING");
        ibanking.setBounds(510, 350, 350, 40);
        mbanking = new JCheckBox("MOBILE BANKING");
        mbanking.setBounds(340, 390, 350, 40);
        alerts = new JCheckBox("EMAIL & SMS ALERTS");
        alerts.setBounds(510, 390, 350, 40);
        cbook = new JCheckBox("CHEQUE BOOK");
        cbook.setBounds(340, 430, 350, 40);
        estatement = new JCheckBox("E-STATEMENT");
        estatement.setBounds(510, 430, 350, 40);
        ack = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge");
        ack.setBounds(160, 480, 650, 20);
        add(atm);
        add(ibanking);
        add(mbanking);
        add(alerts);
        add(cbook);
        add(estatement);
        add(ack);

        // ButtonGroup servicerequirements = new ButtonGroup();
        // servicerequirements.add(atm);
        // servicerequirements.add(ibanking);
        // servicerequirements.add(mbanking);
        // servicerequirements.add(alerts);
        // servicerequirements.add(cbook);
        // servicerequirements.add(estatement);

        submit = new JButton("SUBMIT");
        submit.setBounds(620, 660, 80, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.black);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(550, 660, 80, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);
        add(cancel);

        setSize(800, 800);
        setLocation(400, 300);
        setVisible(true);
        getContentPane().setBackground(Color.white);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String acc = null;
            if (savings.isSelected()) {
                acc = "Savings Account";
            } else if (current.isSelected()) {
                acc = "Current Account";
            } else if (fd.isSelected()) {
                acc = "Fixed Deposit Account";
            } else if (recurring.isSelected()) {
                acc = "Recurring Deposit";
            }
            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 523589400000000L);
            String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 9000L);

            String facility = "";
            if (atm.isSelected()) {
                facility = facility + " ATM CARD";
            } else if (ibanking.isSelected()) {
                facility = facility + " INTERNET BANKING";
            } else if (mbanking.isSelected()) {
                facility = facility + " MOBILE BANKING";
            } else if (alerts.isSelected()) {
                facility = facility + " EMAIL & SMS ALERTS";
            } else if (cbook.isSelected()) {
                facility = facility + " CHEQUE BOOK";
            } else if (estatement.isSelected()) {
                facility = facility + " E-STATEMENT";
            }

            try {
                if (acc.equals("")) {
                    JOptionPane.showMessageDialog(null, "Pick an Account type!");
                } else {
                    Conn conn = new Conn();
                    String query1 = "Insert into signupthree values('" + formno + "', '" + acc + "','" + cardnumber
                            + "','" + pinnumber + "','" + facility + "')";
                    String query2 = "Insert into login values('" + formno + "', '" + cardnumber + "','" + pinnumber
                            + "')";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Cardnumber: " + cardnumber + "\n Pin: " + pinnumber);
                    setVisible(false);
                    new Deposit(pinnumber).setVisible(false);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Signupthree("");
    }

}
