package BankmanagementSystem;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nTextField, fnTextField, eTextField, adTextField, sTextField, cTextField, piTextField, dobTextField;
    JButton next;
    JRadioButton male, female, single, married;
    JDateChooser dateChooser;

    SignupOne() {
        setTitle("CREDENTIALS: ");
        setLayout(null);
        Random ran = new Random();
        long random = Math.abs((ran.nextLong() % 9000L) + 1000L);
        JLabel formno = new JLabel("FORM NUMBER: " + random);
        formno.setBounds(200, 30, 350, 60);
        formno.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        add(formno);

        JLabel page = new JLabel("PAGE 1: PERSONAL DETAILS");
        page.setBounds(200, 80, 350, 40);
        page.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(page);

        JLabel gender = new JLabel("GENDER: ");
        gender.setBounds(200, 130, 350, 40);
        gender.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(340, 130, 80, 40);
        female = new JRadioButton("Female");
        female.setBounds(500, 130, 80, 40);
        add(male);
        add(female);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        JLabel name = new JLabel("NAME: ");
        name.setBounds(200, 180, 350, 40);
        name.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        nTextField = new JTextField();
        nTextField.setBounds(340, 180, 350, 40);
        add(name);
        add(nTextField);

        JLabel fname = new JLabel("FATHER'S NAME: ");
        fname.setBounds(200, 230, 350, 40);
        fname.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        fnTextField = new JTextField();
        fnTextField.setBounds(340, 230, 350, 40);
        add(fname);
        add(fnTextField);

        JLabel dob = new JLabel("DATE OF BIRTH: ");
        dob.setBounds(200, 280, 350, 40);
        dob.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        dateChooser = new JDateChooser();
        dateChooser.setBounds(340, 280, 350, 40);
        add(dob);
        add(dateChooser);

        JLabel email = new JLabel("Email Address: ");
        email.setBounds(200, 330, 350, 40);
        email.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        eTextField = new JTextField();
        eTextField.setBounds(340, 330, 350, 40);
        add(email);
        add(eTextField);

        JLabel marital = new JLabel("MARITAL STATUS: ");
        marital.setBounds(200, 380, 350, 40);
        marital.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(marital);
        single = new JRadioButton("Single");
        single.setBounds(340, 380, 80, 40);
        married = new JRadioButton("Married");
        married.setBounds(500, 380, 80, 40);
        add(single);
        add(married);

        ButtonGroup mstatus = new ButtonGroup();
        mstatus.add(single);
        mstatus.add(married);

        JLabel address = new JLabel("ADDRESS: ");
        address.setBounds(200, 430, 350, 40);
        address.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        adTextField = new JTextField();
        adTextField.setBounds(340, 430, 350, 40);
        add(address);
        add(adTextField);

        JLabel state = new JLabel("STATE: ");
        state.setBounds(200, 480, 350, 40);
        state.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        sTextField = new JTextField();
        sTextField.setBounds(340, 480, 350, 40);
        add(state);
        add(sTextField);

        JLabel city = new JLabel("CITY: ");
        city.setBounds(200, 530, 350, 40);
        city.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        cTextField = new JTextField();
        cTextField.setBounds(340, 530, 350, 40);
        add(city);
        add(cTextField);

        JLabel pincode = new JLabel("PIN CODE: ");
        pincode.setBounds(200, 580, 350, 40);
        pincode.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        piTextField = new JTextField();
        piTextField.setBounds(340, 580, 350, 40);
        add(pincode);
        add(piTextField);

        setSize(800, 800);
        setLocation(400, 300);
        setVisible(true);
        getContentPane().setBackground(Color.white);

        next = new JButton("NEXT");
        next.setBounds(620, 660, 60, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.black);
        next.addActionListener(this);
        add(next);

    }

    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random;
        String name = nTextField.getText();
        String fname = fnTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }
        String email = eTextField.getText();
        String marital = null;
        if (single.isSelected()) {
            marital = "Single";
        } else if (married.isSelected()) {
            marital = "Married";
        }
        String address = adTextField.getText();
        String state = sTextField.getText();
        String city = cTextField.getText();
        String pincode = piTextField.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Requires Name");
            } else {
                Conn c = new Conn();
                String query = "Insert into signup values('" + formno + "','" + gender + "','" + name + "','" + fname
                        + "','" + dob
                        + "','" + email + "','" + marital + "','" + address + "','" + state + "','" + city + "','"
                        + pincode + "')";
                c.s.executeUpdate(query);
                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
