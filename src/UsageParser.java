

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vyomkeshjha on 03/04/16.
 */

public class UsageParser extends JFrame implements Runnable{
   //Calling This one shall fulfill all your desires related to extracting Data From Ion Webpage, considering you put your credentials on the atble
   public ArrayList<String> DataList;
    private String Username;
    private String Password;
UsageParser(String Username ,String Password)
{
    this.Username=Username;
    this.Password=Password;
    DataList = new ArrayList<String>();
}


    @Test
    public void submittingForm() throws IOException {
        final String Date;
        final String Package;
        final String DataAlloted;
        final String DataConsumed;
        final String RemainingData;

        final WebClient webClient = new WebClient(BrowserVersion.CHROME) ;
            webClient.getCookieManager().setCookiesEnabled(true);


            webClient.getOptions().setThrowExceptionOnScriptError(false);

            HtmlPage page1 = webClient.getPage("http://i-on.in/loginpage.aspx");
            webClient.waitForBackgroundJavaScript(5000);
            // Get the form that we are dealing with and within that form,
            // find the submit button and the field that we want to change.
            System.out.println("The forms are :"+page1.getForms());


             final HtmlForm form = page1.getFirstByXPath("//form[@action='./loginpage.aspx']");
            try{System.out.println(form.toString());}catch (NullPointerException e){
                JOptionPane.showMessageDialog(null,
                        "Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);

                dispose();
            }
            final HtmlSubmitInput button = form.getInputByName("btnSubmit");
        System.out.println("Button Data :"+button.asXml());
            final HtmlTextInput textField = form.getInputByName("txtUserName");
        System.out.println("textField :"+textField.asXml());
            final HtmlPasswordInput passcode = form.getInputByName("txtlogPassword");
        System.out.println("passcodeField :"+passcode.asXml());
            // Change the value of the text field
            textField.setValueAttribute(Username);
            textField.setText(Username);
            passcode.setValueAttribute(Password);
             passcode.setText(Password);
            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();
            webClient.waitForBackgroundJavaScript(10 * 1000);
            System.out.println("Page 2   "+page2.getTitleText());
            System.out.println("Page 2   "+page2.getPage());

        HtmlElement buttonF = page2.getHtmlElementById("btnUsage");
        System.out.println(" button F is"+buttonF);

        //going to page 3
        HtmlPage page3=buttonF.click();

        System.out.println("page 3 title :"+page3.getTitleText());
      //  System.out.println("page 3 xml :"+page3.asXml());
        /**Page 3 has all the data that I neeed
         * Now, code to parse page Three and return the data*/



        DomElement PackageElementDt = page3.getFirstByXPath("//span[@id='lblExpDt']");
        //ContentPlaceHolder1_lblPkgExp
      //  System.out.println("ExpiryDate = "+PackageElement);
         Date = PackageElementDt.getTextContent();
       // System.out.println("ExpiryDate = "+Date);

        if(Date!=null)
        DataList.add(Date);
        DomElement PackageElementTyp = page3.getFirstByXPath("//span[@id='ContentPlaceHolder1_lblPkg']");
       // System.out.println("Type "+PackageElementTyp);
        Package = PackageElementTyp.getTextContent();
        //System.out.println("PackageType = "+Package);

        if(Package!=null)
            DataList.add(Package);

        DomElement PackageElementDataAlloted = page3.getFirstByXPath("//span[@id='ContentPlaceHolder1_lblAllotedTotal']");
        DataAlloted = PackageElementDataAlloted.getTextContent();
       // System.out.println("DataAlloted is :"+DataAlloted);

        if(DataAlloted!=null)
            DataList.add(DataAlloted);

        DomElement PackageElementDataConsumed = page3.getFirstByXPath("//span[@id='ContentPlaceHolder1_lblUsedTotal']");
        DataConsumed = PackageElementDataConsumed.getTextContent();
       // System.out.println("DataConsumed is :"+DataConsumed);

        if(DataConsumed!=null)
            DataList.add(DataConsumed);

        RemainingData = String.valueOf( Float.parseFloat(DataAlloted)- Float.parseFloat(DataConsumed));
        if(RemainingData!=null)
            DataList.add(RemainingData);
       // System.out.println("The Data remaining is :"+RemainingData);

        System.out.println(DataList);
    }


    void CreateAndShowGUI(ArrayList list)   {

        JTextField Loading=null;

        if(!list.isEmpty()) {
            getContentPane().removeAll();
            repaint();
            JTextField ExpField = new JTextField();

            ExpField.setText((String) list.get(0));
            ExpField.setEditable(false);
            JTextField PlanField = new JTextField();
            PlanField.setText((String) list.get(1));
            PlanField.setEditable(false);
            JTextField DataAlloted = new JTextField();
            DataAlloted.setText(" Data Allotted :" + (String) list.get(2));
            DataAlloted.setEditable(false);
            JTextField DataUsed = new JTextField();
            DataUsed.setText(" Data Consumed :" + (String) list.get(3));
            DataUsed.setEditable(false);
            JTextField DataRemaining = new JTextField();
            DataRemaining.setText(" Data Remaining :" + (String) list.get(4));
            DataRemaining.setEditable(false);

            // DataPanel = new JPanel();
           // Panel.remove(Loading);
            JPanel Panel=new JPanel();
            Panel.add(ExpField);
            Panel.add(PlanField);
            Panel.add(DataAlloted);
            Panel.add(DataUsed);
            Panel.add(DataRemaining);
            Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
            Panel.setPreferredSize(new Dimension(300,600));
            add(Panel);
            setPreferredSize(new Dimension(300,600));

            repaint();
            setVisible(true);
        }
        else {
            Loading = new JTextField();
            JPanel Panel=new JPanel();
            Loading.setText("Loading...");
            Loading.setEditable(false);
            Panel.add(Loading);
            setPreferredSize(new Dimension(150,100));
            add(Panel);
            setVisible(true);

        }

        setTitle("Statistics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);

    }


    public static void main(String... args)
    {
        UsageParser parser = new UsageParser("BT8002654","Respect123");
        Thread ParserThread = new Thread(parser);
        ParserThread.start();
    }

    @Override
    public void run()
    {
        try {

                CreateAndShowGUI(DataList);
                submittingForm();
                dispose();

                CreateAndShowGUI(DataList);



        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                   "Error", "Connection Failed", JOptionPane.ERROR_MESSAGE);
            dispose();

            e.printStackTrace();
        }
    }
}
