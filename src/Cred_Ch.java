/**
 *
 * @author Robocop
 */

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cred_Ch extends JPanel implements Runnable {
    String[] Tokens;

    void populateTokens()
    {
        FileRead reader = new FileRead();
        this.Tokens = reader.getTokens();
    }
    @Override
    public void run() {
        populateTokens();
        showDialog();
    }

    String LoginID;
    String Passcode;

    public static void main(String[] args) {
        new Cred_Ch().showDialog();
    }

    public void showDialog() {

        JFrame frame = new JFrame();
        GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.setVgap(20);
        gridLayout.setHgap(20);

        JPanel panelInput = new JPanel(gridLayout);
        JPanel panelButtons = new JPanel(new FlowLayout());

        JTextField Username = new JTextField(12);
        Username.setText(Tokens[0]);

        JTextField Password = new JTextField(19);
        Password.setText(Tokens[1].replaceAll("[\\x00-\\x7F]","*"));
        JLabel labelName = new JLabel("Username:");
        JLabel labelPasscode = new JLabel("Password:");

        labelName.setFont(new Font("Serif", Font.BOLD, 16));
        labelPasscode.setFont(new Font("Serif", Font.BOLD, 16));

        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPasscode.setHorizontalAlignment(SwingConstants.RIGHT);

        panelInput.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton submit, cancel;
        submit = new JButton("Change");
        cancel = new JButton("Cancel");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button is working");
                LoginID = Username.getText();
                System.out.println("L"+LoginID);
                Passcode = Password.getText();
                //Change the credentials without changing the delay


//check this part here for issues

                FileWrite writer = new FileWrite(LoginID,  false);
                writer.Writer(";", true);
                writer.Writer(Passcode, true);
                writer.Writer(";", true);
                writer.Writer(Tokens[2], true);
                writer.Writer(";", true);
                writer.Writer(Tokens[3], true);

                frame.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panelInput.add(labelName);
        panelInput.add(Username);
        panelInput.add(labelPasscode);
        panelInput.add(Password);

        panelButtons.add(submit);
        panelButtons.add(cancel);

        frame.setLayout(new BorderLayout());
        frame.add(panelInput, BorderLayout.CENTER);
        frame.add(panelButtons, BorderLayout.SOUTH);

        frame.setTitle("Change Credentials");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(500, 220));
        frame.setBackground(Color.white);
        frame.setVisible(true);
        frame.pack();

    }
}
