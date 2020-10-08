import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    Dictionary Dic = Dictionary.getDictionary();

    public void showAllWords() {
        ArrayList<Word> ListOfWord = Dic.getListOfWord();
        if (ListOfWord.size() == 0) {
            System.out.println("Not found");
            return;
        }
        System.out.printf("%-5s| %-15s| %-15s", "No", "English", "Vietnamese");
        System.out.println();
        Word word;
        for (int index = 0; index < ListOfWord.size(); ++index) {
            word = ListOfWord.get(index);
            System.out.printf("%-5d| %-15s| %-15s", index + 1, word.getWord_target(), word.getWord_explain());
            System.out.println();
        }
    }

    public void dictionaryBasic() {
        System.out.print("Please choose one number from 1 to 3\n" +
                "   1 : insert word\n" +
                "   2 : show all word\n" +
                "   3 : exit\n");
        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        DictionaryManagement dic1 = new DictionaryManagement();
        switch (choose) {
            case "1":
                dic1.insertFromCommandline();
                dictionaryBasic();
                break;
            case "2":
                showAllWords();
                dictionaryBasic();
                break;
            case "3":
                System.exit(0);
            default:
                dictionaryBasic();
                break;
        }
    }

    public void dictionarySearcher() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the word which you want to searcher : ");
        String WordSearch = sc.nextLine();
        Trie T = Dic.getTrieWord().find(WordSearch);
        if (T == null) {
            System.out.println("Not found");
            return;
        }
        ArrayList<Word> ListOfWord = T.getListOfWord();
        if (ListOfWord.size() == 0) {
            System.out.println("Not found");
            return;
        }
        System.out.printf("%-5s%-15s%-15s", "No", "| English", "| Vietnamese");
        System.out.println();
        Word word;
        for (int index = 0; index < ListOfWord.size(); ++index) {
            word = ListOfWord.get(index);
            System.out.printf("%-5d| %-15s| %-15s", index + 1, word.getWord_target(), word.getWord_explain());
            System.out.println();
        }
    }

    public void dictionaryAdvanced() {
        DictionaryManagement dic1 = new DictionaryManagement();
        System.out.print("Please choose one number from 1 to 9\n" +
                "   1 : insert word from commandline\n" +
                "   2 : show all word\n" +
                "   3 : lookup word\n" +
                "   4 : delete word\n" +
                "   5 : change word\n" +
                "   6 : search word\n" +
                "   7 : insert word from file\n" +
                "   8 : export word to file\n" +
                "   9 : exit\n");

        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        switch (choose) {
            case "1":
                dic1.insertFromCommandline();
                dictionaryAdvanced();
                break;
            case "2":
                showAllWords();
                dictionaryAdvanced();
                break;
            case "3":
                dic1.dictionaryLookup();
                dictionaryAdvanced();
                break;
            case "4":
                dic1.deleteWord();
                dictionaryAdvanced();
                break;
            case "5":
                dic1.changeWord();
                dictionaryAdvanced();
                break;
            case "6":
                dictionarySearcher();
                dictionaryAdvanced();
                break;
            case "7":
                dic1.insertFromFile();
                dictionaryAdvanced();
                break;
            case "8":
                dic1.dictionaryExportToFile();
                dictionaryAdvanced();
                break;
            case "9":
                System.exit(0);
            default:
                dictionaryAdvanced();
                break;
        }
    }

    public static void main(String[] args) {
        new DictionaryCommandline().dictionaryAdvanced();
    }
}
