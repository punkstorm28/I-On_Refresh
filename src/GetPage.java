/**
 * Created by vyomkeshjha on 02/04/16.
 */


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.omg.IOP.IOR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetPage {


    public String URL,Password,UserName;
    private Map<String, String> cookies = new HashMap<String, String>();
    Document Page;
    GetPage(String URL,String argUserName,String argPassword)
{
    this.URL=URL;
    this.UserName = argUserName;
    this.Password=argPassword;
}
    String loginToION() throws IOException
    {

        Connection.Response res = Jsoup.connect(URL)
                .data("username", UserName)
                .data("password", Password)
                .method(Connection.Method.POST)
                .execute();
        System.out.println("the responce is"+res);
        cookies.putAll(res.cookies());
        System.out.println("cookies: "+cookies.toString());
        Page=res.parse();
        System.out.println("page:   "+Page);


        return null;
    }
    public static void main(String... args)
    {
        GetPage getter = new GetPage("http://172.16.16.16/logout","Respect123","BT8002654");
        try {
            getter.loginToION();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}