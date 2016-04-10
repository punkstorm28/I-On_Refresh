import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Test;

/**
 * Created by vyomkeshjha on 04/04/16.
 */
public class BirrLogout {
    @Test
    public void IonLogout() throws Exception {
        try (final WebClient webClient = new WebClient()) {

            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://172.16.16.16/24online/webpages/client.jsp?fromlogout=true");

            final HtmlForm form = page1.getFirstByXPath("//form[@action='/24online/servlet/E24onlineHTTPClient']");
            System.out.println(form.toString());
            final HtmlSubmitInput button = form.getInputByName("logout");

            final HtmlPage page2 = button.click();

            // Change the value of the text field
            // textField.setValueAttribute("root");

            // Now submit the form by clicking the button and get back the second page.
            // final HtmlPage page2 = button.click();
        }
    }
    public static void main(String... args) throws Exception {
        new BirrLogout().IonLogout();
    }

}
