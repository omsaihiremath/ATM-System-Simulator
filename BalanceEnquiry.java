package BankmanagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    JLabel text;
    String pinchange;

    BalanceEnquiry(String pinchange) {
        this.pinchange = pinchange;
        setLayout(null);
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/BankmanagementSystem/icons/atm.jpg"));
        Image i1 = icon1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(i1);
        JLabel label = new JLabel(icon2);
        label.setBounds(0, 0, 900, 900);
        add(label);

        back = new JButton("BACK ");
        back.setBounds(420, 420, 100, 25);
        back.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        back.addActionListener(this);
        label.add(back);

        Conn c = new Conn();
        int balance = 0;
        try {
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinchange + "'");

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        text = new JLabel("Your Balance is Rs " + balance);
        text.setBounds(170, 300, 400, 30);
        text.setForeground(Color.white);
        text.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        label.add(text);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transaction(pinchange).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }

}
