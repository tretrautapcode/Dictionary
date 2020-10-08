import java.util.ArrayList;

public class Trie {
    private Trie[] childen = new Trie[29];
    private String explain = "";
    private String word = "";
    private int count = 0;

    public void setExplain(String input) {
        explain = input;
    }

    public String getExplain() {
        return explain;
    }

    private String format(String input) {
        input = input.trim();
        input = input.toLowerCase();
        while (input.indexOf("  ") != -1)
            input = input.replace("  ", " ");
        input = input.replace(" ", "_");
        input = input.replace("'", "`");
        return input;
    }

    public void add(Word WordAdd) {
        Trie p = this;
        int k;
        String input = WordAdd.getWord_target();
        input = format(input);
        for (int i = 0; i < input.length(); ++i) {
            k = (int) (input.charAt(i) - '_');
            if (p.childen[k] == null) {
                p.childen[k] = new Trie();
            }
            p = p.childen[k];
            p.count++;
        }
        p.word = WordAdd.getWord_target();
        if (p.explain.length() == 0) {
            p.explain = WordAdd.getWord_explain();
        } else {
            p.explain = explain + WordAdd.getWord_explain();
        }
    }

    public Trie find(String input) {
        input = format(input);
        Trie p = this;
        int k;
        for (int i = 0; i < input.length(); ++i) {
            k = (int) (input.charAt(i) - '_');
            if (k < 0 || k > 28) {
                return null;
            }
            if (p.childen[k] == null) {
                return null;
            }
            p = p.childen[k];
        }
        return p;
    }

    public boolean delete(String input) {
        input = format(input);
        Trie p = this.find(input);
        if (p == null) {
            return false;
        }
        p = this;
        int k;
        for (int i = 0; i < input.length(); ++i) {
            k = (int) (input.charAt(i) - '_');
            p.childen[k].count--;
            if (p.childen[k].count == 0) {
                deleteTrie(p.childen[k]);
                p.childen[k] = null;
                return true;
            }
            p = p.childen[k];
        }
        p.explain = "";
        return true;
    }

    private void deleteTrie(Trie T) {
        for (int i = 0; i < 29; ++i) {
            if (T.childen[i] != null) {
                deleteTrie(T.childen[i]);
                T.childen[i] = null;
            }
        }
    }

    public ArrayList<Word> getListOfWord() {
        ArrayList<Word> listOfWord = new ArrayList<>();
        listed(this, listOfWord);
        return listOfWord;
    }

    private void listed(Trie T, ArrayList<Word> listOfWord) {
        if (T.explain.length() != 0) {

            listOfWord.add(new Word(T.word, T.explain));
        }
        for (int i = 0; i < 29; ++i) {
            if (T.childen[i] != null)
                listed(T.childen[i], listOfWord);
        }
    }
}
