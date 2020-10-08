import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Speak {

    private static Speak speak;

    private Speak() {
    }

    public static Speak getInstance() {

        if (speak == null) {
            speak = new Speak();
        }
        return speak;
    }

    public void Speak(String text, String language) throws IOException, JavaLayerException {
        InputStream sound = getAudio(text, language);
        new Player(sound).play();
    }


    public InputStream getAudio(String text, String languageOutput)
            throws IOException {
        URL url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl="
                + languageOutput + "&client=tw-ob&q="
                + text.replace(" ", "%20"));
        URLConnection urlConn = url.openConnection();
        urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US)");
        InputStream audioSrc = urlConn.getInputStream();
        return new BufferedInputStream(audioSrc);
    }
}
