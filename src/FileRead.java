import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class FileRead {

    String fileName= "pref.pf";


    static String[] Tokens;

    public FileRead() {
    	//System.out.println("inside file read" );


        String line;

        try {

            FileReader fileReader = new FileReader(fileName);
            //System.out.println("file open");
            BufferedReader bufferedReader = new BufferedReader(fileReader); //wrapping it in it

            while ((line = bufferedReader.readLine()) != null) {
                Tokens = line.split(";");
                //printTokens(Tokens);
                break;
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println(
                    "Unable to open file sucker '"
                    + fileName + "'" +"    ");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
        }
        catch(Exception e){
        	System.out.println("exception is "+e.toString());
        }

    }

    public void printTokens(String[] tokens) {
        for (int k = 0; k < tokens.length; k++) {
            System.out.println(tokens[k]);

        }

    }

    public String[] getTokens() {

        return Tokens;
    }
public static void main(String... args)
{
    FileRead read = new FileRead();
}

}
