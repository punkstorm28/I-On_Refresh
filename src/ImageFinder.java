import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URL;
import java.util.List;

/**
 * Created by vyomkeshjha on 04/04/16.
 */
public class ImageFinder {

  //get the images from both of the pages, put them into different ArrayLists, check for non presence in both the array lists, geth the different images, show Them
    public static void main(String[] args) throws Exception
    {

        //WebClient webClient = new WebClient(Opera);
        WebClient webClient = new WebClient();
        HtmlPage currentPage = (HtmlPage) webClient.getPage(new URL("http://172.16.16.16/24online/webpages/client.jsp?fromlogout=true"));
        //get list of all divs
        final List<?> images = currentPage.getByXPath("//img");
        for (Object imageObject : images) {
            HtmlImage image = (HtmlImage) imageObject;
            System.out.println(image.getSrcAttribute());
        }
        //webClient.closeAllWindows();
    }
}
