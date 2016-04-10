import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.*;

/**
 * Created by vyomkeshjha on 02/04/16.
 */
public class Worker {
    SeleniumLoginCh login;
    SeleniumLoginFox loginF;


    Worker()
    {
        Boolean isConnected=false;

            isConnected=hostAvailabilityCheck();
            System.out.println("is the computer is connected? :"+isConnected);






        if(isConnected==false) {
            login = new SeleniumLoginCh();
            loginF = new SeleniumLoginFox();
            try {
                login.setUp();
                login.testIOnLogin();
                login.tearDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String... args)
    {
        new Worker();
    }

    public static boolean hostAvailabilityCheck() {
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

