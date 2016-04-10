



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


import static javax.swing.UIManager.put;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * Created by vyomkeshjha on 02/04/16.
 */
public class Tray {

    static void createRefreshThread() {
        IonChecker chk = new IonChecker();
        Thread checkThread = new Thread(chk);
        checkThread.start();
        System.out.println("checker Thread started");

    }


    public static void main(String[] args) {
     createRefreshThread();

        try {
            setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        put("swing.boldMetal", Boolean.FALSE);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("images/bulb.gif", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a popup menu components
        MenuItem Logout = new MenuItem("Logout");
        MenuItem Login = new MenuItem("Login Command");
        MenuItem exitItem = new MenuItem("Exit");
        MenuItem exitAndLogout = new MenuItem("Logout And Exit");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem checkUsage = new MenuItem("Check Usage");
        Menu displayMenu = new Menu("Change");
        MenuItem credentials = new MenuItem("Credentials");
        MenuItem browser = new MenuItem("Default Browser");

        MenuItem delay = new MenuItem("Check Delay");

        //Add components to popup menu
        popup.add(aboutItem);
        popup.add(checkUsage);
        popup.addSeparator();

        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(credentials);
        displayMenu.add(browser);
        displayMenu.add(delay);
        popup.addSeparator();
        popup.add(Login);
        popup.add(Logout);
        popup.add(exitItem);

        popup.add(exitAndLogout);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "I-onRefresh");
            }
        });
         Login.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     FileRead reader =new FileRead();
                     String[] Tokens =reader.getTokens();
                     new BirrLogin(Tokens[0],Tokens[1]).IonLogin();
                 } catch (Exception e1) {
                     e1.printStackTrace();
                 }
             }
         });
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Created by Vyomkesh");
            }
        });
        checkUsage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileRead reader =new FileRead();
                String[] Tokens =reader.getTokens();

                UsageParser parser = new UsageParser(Tokens[0],Tokens[1]);
                Thread ParserThread = new Thread(parser);
                ParserThread.start();
            }
        });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new BirrLogout().IonLogout();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MenuItem item = (MenuItem) e.getSource();
                System.out.println(item.getLabel());
                //TrayIcon.MessageType type = null;
                System.out.println(item.getLabel());
                if ("Credentials".equals(item.getLabel())) {
                    Cred_Ch cha = new Cred_Ch();
                    Thread changeCredentialsThread=new Thread(cha);
                    changeCredentialsThread.start();

                } else if ("Default Browser".equals(item.getLabel())) {

                    BrowserSelect select = new BrowserSelect();
                    Thread selectThread = new Thread(select);
                    selectThread.start();

                }
                else if ("Check Delay".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.INFO;

                 Slider_delay delay =  new Slider_delay();
                    Thread delayGUI = new Thread(delay);
                    delayGUI.start();

                }
            }
        };

        credentials.addActionListener(listener);
        delay.addActionListener(listener);
        browser.addActionListener(listener);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        exitAndLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new BirrLogout().IonLogout();
                    System.exit(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Tray.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}