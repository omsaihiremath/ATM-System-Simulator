package BankmanagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pinchange extends JFrame implements ActionListener {
    JPasswordField input, input2;
    JButton change, back;
    String pinnumber;

    Pinchange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/BankmanagementSystem/icons/atm.jpg"));
        Image i1 = icon1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(i1);
        JLabel label = new JLabel(icon2);
        label.setBounds(0, 0, 900, 900);
        add(label);

        JLabel text = new JLabel("PIN CHANGE ");
        text.setForeground(Color.white);
        text.setBounds(280, 290, 500, 25);
        text.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        label.add(text);

        JLabel pintext = new JLabel("NEW PIN: ");
        pintext.setForeground(Color.white);
        pintext.setBounds(200, 320, 500, 25);
        pintext.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        label.add(pintext);

        input = new JPasswordField();
        input.setBounds(305, 320, 200, 25);
        input.setForeground(Color.black);
        label.add(input);

        JLabel repin = new JLabel("CONFIRM PIN: ");
        repin.setForeground(Color.white);
        repin.setBounds(200, 360, 500, 25);
        repin.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        label.add(repin);

        input2 = new JPasswordField();
        input2.setBounds(305, 360, 200, 25);
        input2.setForeground(Color.black);
        label.add(input2);

        change = new JButton("CHANGE ");
        change.setBounds(150, 420, 100, 25);
        change.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        change.addActionListener(this);
        label.add(change);

        back = new JButton("BACK ");
        back.setBounds(420, 420, 100, 25);
        back.setFont(new Font("arial", Font.BOLD | Font.ITALIC, 15));
        back.addActionListener(this);
        label.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = input.getText();
                String rpin = input2.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Confirm pin does not match the new pin!");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please input a new pin");
                    return;

                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please input a new re-pin");
                    return;

                }

                Conn conn = new Conn();
                String query1 = "update bank set pin = '" + rpin + "' where pin ='" + pinnumber + "'";
                String query2 = "update login set pinnumber = '" + rpin + "' where pinnumber ='" + pinnumber + "'";
                String query3 = "update signupthree set pinnumber = '" + rpin + "' where pinnumber ='" + pinnumber
                        + "'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, "PIN CHNAGED SUCCESSFULLY!");

                setVisible(false);
                new Transaction(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Pinchange("").setVisible(true);
    }
}
