package BankmanagementSystem;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
//import java.util.*;
//import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    long random;
    JTextField pan, aadhar;
    JButton next;
    JRadioButton existyes, existno, citizenyes, citizenno;
    JComboBox<String> religion, category, income, education, occupation;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION: ");
        setLayout(null);

        JLabel page = new JLabel("PAGE 2: ADDITIONAL DETAILS");
        page.setBounds(200, 80, 350, 40);
        page.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(page);

        JLabel gender = new JLabel("RELIGION: ");
        gender.setBounds(200, 130, 350, 40);
        gender.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(gender);

        String ReligionVal[] = { "Hindu", "Muslim", "Christian", "Other" };
        religion = new JComboBox<>(ReligionVal);
        religion.setBounds(340, 130, 350, 40);
        add(religion);

        JLabel name = new JLabel("CATERGORY: ");
        name.setBounds(200, 180, 350, 40);
        name.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        String CategoryVal[] = { "General", "OBC", "ST", "SC", "Other" };
        category = new JComboBox<>(CategoryVal);
        category.setBounds(340, 180, 350, 40);
        add(category);
        add(name);

        JLabel dob = new JLabel("INCOME: ");
        dob.setBounds(200, 230, 350, 40);
        dob.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(dob);
        String IncomeVal[] = { "Null", "Below 1,50,000", "Below 3,00,000", "Below 6,50,000", "Below 10,00,000",
                "10,00,000+" };
        income = new JComboBox<>(IncomeVal);
        income.setBounds(340, 230, 350, 40);
        add(income);

        JLabel fname = new JLabel("EDUCATIONAL ");
        fname.setBounds(200, 280, 350, 40);
        fname.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(fname);
        JLabel email = new JLabel("QUALIFICATION: ");
        email.setBounds(200, 295, 350, 40);
        email.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(email);
        String EducationVal[] = { "Non-Graduate", "Graduate", "Post-Graduate", "PhD", "Other " };
        education = new JComboBox<>(EducationVal);
        education.setBounds(340, 295, 350, 40);
        add(education);
        JLabel marital = new JLabel("OCCUPATION: ");
        marital.setBounds(200, 340, 350, 40);
        marital.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(marital);
        String OccupationVal[] = { "Salaried", "<Self Employed", "Business", "Student", "Retired" };
        occupation = new JComboBox<>(OccupationVal);
        occupation.setBounds(340, 340, 350, 40);
        add(occupation);

        JLabel address = new JLabel("PAN NUMBER: ");
        address.setBounds(200, 430, 350, 40);
        address.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        pan = new JTextField();
        pan.setBounds(340, 430, 350, 40);
        add(address);
        add(pan);

        JLabel state = new JLabel("AADHAR NUMBER: ");
        state.setBounds(200, 480, 350, 40);
        state.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        aadhar = new JTextField();
        aadhar.setBounds(340, 480, 350, 40);
        add(state);
        add(aadhar);

        JLabel city = new JLabel("SENIOR CITIZEN: ");
        city.setBounds(200, 530, 350, 40);
        city.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(city);
        citizenyes = new JRadioButton("yes");
        citizenyes.setBounds(360, 530, 350, 40);
        citizenno = new JRadioButton("no");
        citizenno.setBounds(500, 530, 350, 40);
        add(citizenyes);
        add(citizenno);

        ButtonGroup scitizen = new ButtonGroup();
        scitizen.add(citizenyes);
        scitizen.add(citizenno);

        JLabel pincode = new JLabel("EXISTING ACCOUNT: ");
        pincode.setBounds(200, 580, 350, 40);
        pincode.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        add(pincode);
        existyes = new JRadioButton("Yes");
        existyes.setBounds(360, 580, 350, 40);
        add(existyes);
        existno = new JRadioButton("No");
        existno.setBounds(500, 580, 350, 40);
        add(existno);

        ButtonGroup exist = new ButtonGroup();
        exist.add(existyes);
        exist.add(existno);

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
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String scitizen = null;
        if (citizenyes.isSelected()) {
            scitizen = "Yes";
        } else if (citizenno.isSelected()) {
            scitizen = "No";
        }
        String exist = null;
        if (existyes.isSelected()) {
            exist = "Yes";
        } else if (existno.isSelected()) {
            exist = "No";
        }
        String span = pan.getText();
        String saadhar = aadhar.getText();

        try {
            Conn c = new Conn();
            String query = "Insert into signuptwo values('" + formno + "','" + sreligion + "','" + scategory + "','"
                    + sincome
                    + "','" + seducation
                    + "','" + soccupation + "','" + span + "','" + saadhar + "','" + scitizen + "','" + exist + "')";
            c.s.executeUpdate(query);

            setVisible(false);
            new Signupthree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
}
