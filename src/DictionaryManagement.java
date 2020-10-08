import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    Dictionary Dic = Dictionary.getDictionary();

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numberOfWord;
        String target;
        String explain;
        Trie TrieWord = Dic.getTrieWord();
        System.out.print("Number of word : ");
        numberOfWord = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numberOfWord; i++) {
            System.out.print("Word " + (i + 1) + " : ");
            target = sc.nextLine();
            System.out.print("Explain : ");
            explain = sc.nextLine();
            if (CheckInput(target) == false) {
                System.out.println("Target is not accepted");
            } else {
                TrieWord.add(new Word(target, explain));
            }
        }
    }

    public void insertFromFile() {
        try {
            Scanner sc = new Scanner(new File("dictionaries.txt"));
            String target = "";
            String explain = "";
            String input;
            int first;
            Trie TrieWord = Dic.getTrieWord();
            while (sc.hasNext()) {
                input = sc.nextLine();
                first = input.indexOf('@');

                if (first != -1) {
                    TrieWord.add(new Word(target, explain));
                    target = input.substring(1);
                    explain = "";
                } else {
                    if (explain.length() == 0) {
                        explain = input;
                    } else {
                        explain = explain + "\n" + input;
                    }
                }
            }
            TrieWord.add(new Word(target, explain));
            sc.close();
            System.out.println("insert from file success");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void dictionaryLookup() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the word which you want to lookup : ");
        String WordLookup = sc.nextLine();
        Trie T = Dic.getTrieWord().find(WordLookup);
        if (T == null) {
            System.out.println("Not found");
            return;
        }
        System.out.println(T.getExplain());
    }

    public void deleteWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the word which you want to delete : ");
        String WordDelete = sc.nextLine();
        if (Dic.getTrieWord().delete(WordDelete))
            System.out.println("Done");
        else
            System.out.println("Not found");
    }

    public void changeWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the word which you want to change : ");
        String word = sc.nextLine();
        System.out.print("Old explain : ");
        String explain = sc.nextLine();
        Trie T = Dic.getTrieWord().find(word);
        if (T != null) {
            T.setExplain(explain);
        }
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter output = new FileWriter("dictionaries.txt");
            ArrayList<Word> ListOfWord = Dic.getListOfWord();
            Word word;
            for (int i = 0; i < ListOfWord.size(); i++) {
                word = ListOfWord.get(i);
                output.write("@" + word.getWord_target() + "\n" + word.getWord_explain() + "\n");
            }
            output.close();
            System.out.println("export to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckInput(String input) {
        for (int i = 0; i < input.length(); ++i) {
            int c = (int) input.charAt(i);
            if (c == 39 || c == 32 || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                continue;
            }
            return false;
        }
        return true;
    }
}
