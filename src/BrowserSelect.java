import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by vyomkeshjha on 03/04/16.
 */
public class BrowserSelect extends JPanel implements ActionListener ,Runnable {

    static String chromeString = "Chrome";
    static String MacChromeString = "Chrome_Mac";
    static String FireFoxString = "Firefox";
    static String BrowserLess="Browserless";
    private JFrame frame;
    private JButton Done;
public void run()
{
    createAndShowGUI();

}


    public BrowserSelect() {
        super(new BorderLayout());

        //Create the radio buttons.
        JRadioButton chromeButton = new JRadioButton(chromeString);
        chromeButton.setMnemonic(KeyEvent.VK_B);
        chromeButton.setActionCommand(chromeString);

        JRadioButton MacChromeButton = new JRadioButton(MacChromeString);
        MacChromeButton.setMnemonic(KeyEvent.VK_C);
        MacChromeButton.setActionCommand(MacChromeString);

        JRadioButton FireFoxbutton = new JRadioButton(FireFoxString);
        FireFoxbutton.setMnemonic(KeyEvent.VK_D);
        FireFoxbutton.setActionCommand(FireFoxString);

        JRadioButton browserless = new JRadioButton(BrowserLess);
        browserless.setMnemonic(KeyEvent.VK_E);
        browserless.setActionCommand(BrowserLess);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(chromeButton);
        group.add(MacChromeButton);
        group.add(browserless);
        group.add(FireFoxbutton);

        //Register a listener for the radio buttons.
        chromeButton.addActionListener(this);
        MacChromeButton.addActionListener(this);
        FireFoxbutton.addActionListener(this);
        browserless.addActionListener(this);

        Done = new JButton("Done");

        Done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("done disposed");
                System.out.println();

                frame.dispose();
            }
        });
        //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(chromeButton);
        radioPanel.add(MacChromeButton);
        radioPanel.add(FireFoxbutton);
        radioPanel.add(browserless);
        radioPanel.add(Done);
        add(radioPanel, BorderLayout.LINE_START);

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /** Listens to the radio buttons */
    public void actionPerformed(ActionEvent e) {
           String ActionString = e.getActionCommand();
        System.out.println("Action string is :" +ActionString);
        if(ActionString.equals("Chrome"))
        {
            IonChecker.ChangedbrowserCoordinate=2;
            IonChecker.browserChangeFlag=true;
            //Write to preference file
            FileRead reader = new FileRead();
            String[] Tokens = reader.getTokens();


            FileWrite writer = new FileWrite(Tokens[0],  false);
            writer.Writer(";", true);
            writer.Writer(Tokens[1], true);
            writer.Writer(";", true);
            writer.Writer(Tokens[2], true);
            writer.Writer(";", true);
            writer.Writer("2", true);
        }
        else if(ActionString.equals("Chrome_Mac"))
        {
            IonChecker.ChangedbrowserCoordinate=3;
            IonChecker.browserChangeFlag=true;
            //write to preferences file
            FileRead reader = new FileRead();
            String[] Tokens = reader.getTokens();


            FileWrite writer = new FileWrite(Tokens[0],  false);
            writer.Writer(";", true);
            writer.Writer(Tokens[1], true);
            writer.Writer(";", true);
            writer.Writer(Tokens[2], true);
            writer.Writer(";", true);
            writer.Writer("3", true);
        }
       else if(ActionString.equals("Firefox"))
        {
            IonChecker.ChangedbrowserCoordinate=1;
            IonChecker.browserChangeFlag=true;
            //write to preferences file
            FileRead reader = new FileRead();
            String[] Tokens = reader.getTokens();

            FileWrite writer = new FileWrite(Tokens[0],  false);
            writer.Writer(";", true);
            writer.Writer(Tokens[1], true);
            writer.Writer(";", true);
            writer.Writer(Tokens[2], true);
            writer.Writer(";", true);
            writer.Writer("1", true);
        }//Check this part
        else if(ActionString.equals("Browserless"))
        {
            IonChecker.ChangedbrowserCoordinate=4;
            IonChecker.browserChangeFlag=true;
            //write to preferences file
            FileRead reader = new FileRead();
            String[] Tokens = reader.getTokens();


            FileWrite writer = new FileWrite(Tokens[0],  false);
            writer.Writer(";", true);
            writer.Writer(Tokens[1], true);
            writer.Writer(";", true);
            writer.Writer(Tokens[2], true);
            writer.Writer(";", true);
            writer.Writer("4", true);
        }
    }

    //call from the event dispatch thread
    private  void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Select Browser");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new BrowserSelect();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    }

