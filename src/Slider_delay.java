
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class Slider_delay extends JFrame implements Runnable{
    private static final long serialVersionUID = 1L;

    @Override
    public void run() {

        GUI();
    }

    Slider_delay()
    {
        super("Test Delay");
    }
    public void GUI() {


        setSize(250, 100);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

       // panel.setLayout(new FlowLayout(1, 100, 500));

        add(panel);

        JSlider slider = new JSlider();
        JButton changeButton= new JButton("change");

        String LastDelay = getDelayFromFile();
        JTextField num = new JTextField();
        num.setText(LastDelay);
        num.setEditable(false);
        slider.setLayout(new FlowLayout(1, 100, 200));
        slider.setMaximum(900);//maximum seconds,15 minutes
        slider.setMinimum(15);//one minute
        slider.setValue(Integer.parseInt(LastDelay));
        slider.setMajorTickSpacing(60);
        slider.setPaintTicks(true);
        slider.setSize(200, 200);

        changeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               // System.out.println("The button works");
                Integer Delay =slider.getValue();
                writeDelayToFile(Delay);
                IonChecker.delayChangeFlag=true;
                IonChecker.changableSecTime=Delay;
                Slider_delay.super.dispose();
            }
        });
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               // System.out.println(e.toString());
                Integer level =slider.getValue();
                num.setText(level.toString());
              //  System.out.println("the level is: "+level );
            }
        });


        slider.setVisible(true);
        panel.add(slider);
        panel.add(num);
        panel.add(changeButton);
        super.setVisible(true);

    }
    String getDelayFromFile()
    {
        FileRead reader = new FileRead();
        String[] Tokens = reader.getTokens();
      String delay = Tokens[2];
        return delay;
    }
    void writeDelayToFile(int Delay)
    {
        FileRead reader = new FileRead();
        String[] Tokens = reader.getTokens();


        FileWrite writer = new FileWrite(Tokens[0],  false);
        writer.Writer(";", true);
        writer.Writer(Tokens[1], true);
        writer.Writer(";", true);
        writer.Writer(String.valueOf(Delay), true);
        writer.Writer(";", true);
        writer.Writer(Tokens[3], true);
    }
    public static void main(String... args)
    {
        new Slider_delay();
    }
}
