import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GgTranslator {
    public static String Translate(String text) throws IOException {
        if (text.length() == 0) return text;
        URL url = new URL("https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=vi&dt=t&q="
                + text.replace(" ", "%20"));
        URLConnection urlConn = url.openConnection();
        urlConn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US)");
        Scanner sc = new Scanner(urlConn.getInputStream());
        String ans = "";
        while (sc.hasNext()) {
            ans = ans + sc.nextLine();
        }
        int first = ans.indexOf('"') + 1;
        int second = ans.indexOf('"', first);
        ans = ans.substring(first, second);
        return ans;
    }
}
