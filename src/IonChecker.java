import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.omg.CORBA.Object;

/**
 * Created by vyomkeshjha on 02/04/16.
 */
public class IonChecker implements Runnable
{
    static Boolean delayChangeFlag=false;
    static int changableSecTime;

    static boolean browserChangeFlag=false;
    static int ChangedbrowserCoordinate;

    private int secondsTime;
    private int browserCoordinate;

    String[] Tokens;
    SeleniumLoginFox login1;
    SeleniumLoginWinch login2;
    SeleniumLoginCh login3;
    BirrLogin login4;
    /**
     * Firefox gets one
     * Chrome gets two
     * Chrome for mac gets three
     */

    IonChecker()
    {
        FileRead delayUpdater = new FileRead();
        Tokens = delayUpdater.getTokens();
        secondsTime = Integer.parseInt(Tokens[2]);
        browserCoordinate =Integer.parseInt(Tokens[3]);
        System.out.println(Tokens[3]+"   "+browserCoordinate);
    }
    @Override
    public void run() {

        checkRefresh();
    }
    public void checkRefresh() {
        while (true){
        if(delayChangeFlag ==true)
        {
            secondsTime=changableSecTime;
            delayChangeFlag=false;
        }
            if(browserChangeFlag==true)
            {
                browserCoordinate=ChangedbrowserCoordinate;
                System.out.println("browser coord changed");
                browserChangeFlag=false;
            }


        Boolean isConnected=false;

        isConnected=hostAvailabilityCheck();
        System.out.println("is the computer is connected? :"+isConnected);



        if(isConnected==false) {
            System.out.println("browser coord " + browserCoordinate);

            if (browserCoordinate == 1) {
                login1 = new SeleniumLoginFox();
                try {
                    login1.setUp();
                    login1.testIOnLogin();
                    login1.tearDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (browserCoordinate == 2) {
                login2 = new SeleniumLoginWinch();
                try {
                    login2.setUp();
                    login2.testIOnLogin();
                    login2.tearDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (browserCoordinate == 3)
            {
                login3 = new SeleniumLoginCh();
                try {
                    login3.setUp();
                    login3.testIOnLogin();
                    login3.tearDown();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (browserCoordinate == 4)
            {
                login4 = new BirrLogin(Tokens[0],Tokens[1]);
                try {
                    login4.IonLogin();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


        }

        try {
            Thread.sleep(secondsTime*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}

    public  boolean hostAvailabilityCheck() {
        String title;
        try {
            // /*
            Document doc = Jsoup.connect("http://www.google.com").get();
            title=doc.title();



        }
        catch (Exception e)
        {

            e.printStackTrace();
            return false;
        }
        System.out.println(title);
        if(!title.equals("Google"))
        {

            return false;
        }


        return true;
    }
}
