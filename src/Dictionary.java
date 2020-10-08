import java.util.ArrayList;

public class Dictionary {

    private static Dictionary dictionary;
    private Trie TrieWord = new Trie();

    private Dictionary() {
    }

    public static Dictionary getDictionary() {
        if (dictionary == null) {
            dictionary = new Dictionary();
        }
        return dictionary;
    }

    public ArrayList<Word> getListOfWord() {
        return TrieWord.getListOfWord();
    }

    public Trie getTrieWord() {
        return TrieWord;
    }
}
