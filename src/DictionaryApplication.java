import com.sun.deploy.panel.JSmartTextArea;
import javazoom.jl.decoder.JavaLayerException;
import marytts.modules.synthesis.Voice;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryApplication {
    private JFrame App = new JFrame("Dictionary");
    private JLabel header1 = new JLabel("DICTIONARY", SwingConstants.CENTER);
    private JLabel header2 = new JLabel();
    private JLabel header3 = new JLabel();
    private JList List = new JList();
    private JLabel backGround = new JLabel();
    private JTextField search = new JTextField();
    private JTextField writeEnglish = new JTextField();
    private JTextArea writeVietnamese = new JTextArea();
    private JSmartTextArea readEnglish = new JSmartTextArea();
    private JSmartTextArea readVietnamese = new JSmartTextArea();
    private JScrollPane s_W_English = new JScrollPane(writeEnglish);
    private JScrollPane s_W_Vietnamese = new JScrollPane(writeVietnamese);
    private JScrollPane s_R_English = new JScrollPane(readEnglish);
    private JScrollPane s_R_Vietnamese = new JScrollPane(readVietnamese);
    private JScrollPane s_List = new JScrollPane(List);
    private JScrollPane s_Search = new JScrollPane(search);
    private JButton searchButton = new JButton();
    private JButton addButton = new JButton();
    private JButton googleButton = new JButton();
    private JButton tickButton = new JButton();
    private JButton speakButton = new JButton();
    private JButton usButton = new JButton();
    private JButton ukButton = new JButton();
    private JButton deleteButton = new JButton();
    private JButton changeButton = new JButton();
    private JButton buttonRunning = null;
    private static boolean checkChange = false;
    private Dictionary Dic = Dictionary.getDictionary();
    private ArrayList<Word> ListOfWord = new ArrayList<>();
    private Font bigFont = new Font("Times New Roman", 3, 25);
    private Font font = new Font("Times New Roman", 3, 20);
    private Font miniFont = new Font("Times New Roman", 3, 15);

    public DictionaryApplication() {
        InitApp();
        RunApp();
    }

    public void InitApp() {
        App.setLayout(null);
        App.setBounds(300, 10, 850, 720);
        backGround.setSize(850, 720);
        App.setResizable(false);
        backGround.setIcon(new ImageIcon(getClass().getResource("/data/Bg.jpg")));
        App.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        App.getContentPane().add(backGround);
        InitHeader();
        InitSearch();
        InitGoogle();
        InitAdd();
        InitInput();
        InitList();
        InitReadEnglish();
        InitReadVietnamese();
        InitWriteEnglish();
        InitWriteVietnamese();
        InitSpeak();
        InitGgSpeak();
        InitDelete();
        InitChange();
        InitTick();
        App.setVisible(true);
    }

    public void RunApp() {
        runSearch();
        runList();
        runSearchWord();
        runReadEnglish();
        runChange();
        runDelete();
        runSpeak();
        runGoogle();
        runAdd();
        runTick();
        runGgSpeak();
        UpdateFileInput();
    }

    public void InitHeader() {
        header1.setBounds(0, 0, 850, 50);
        header1.setBackground(Color.darkGray);
        header1.setForeground(Color.BLUE);
        header1.setOpaque(true);
        header1.setFont(font);
        header2.setBounds(300, 80, 100, 20);
        header2.setForeground(Color.YELLOW);
        header2.setBorder(null);
        header2.setFont(miniFont);
        header3.setBounds(300, 180, 100, 20);
        header3.setForeground(Color.YELLOW);
        header3.setBorder(null);
        header3.setFont(miniFont);
        backGround.add(header1);
        backGround.add(header2);
        backGround.add(header3);
    }

    public void InitSearch() {
        searchButton.setBounds(0, 100, 250, 50);
        searchButton.setIcon(new ImageIcon(getClass().getResource("/data/search.png")));
        searchButton.setText(format("Tra từ", 15));
        searchButton.setFont(bigFont);
        searchButton.setForeground(Color.BLUE);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setBorder(null);
        searchButton.setBackground(Color.darkGray);
        backGround.add(searchButton);
    }

    private void InitGoogle() {
        googleButton.setBounds(0, 200, 250, 50);
        googleButton.setIcon(new ImageIcon(getClass().getResource("/data/google.png")));
        googleButton.setText(format("Google", 15));
        googleButton.setFont(bigFont);
        googleButton.setForeground(Color.YELLOW);
        googleButton.setFocusPainted(false);
        googleButton.setContentAreaFilled(false);
        googleButton.setBackground(Color.darkGray);
        googleButton.setBorder(null);
        backGround.add(googleButton);
    }

    private void InitGgSpeak() {
        usButton.setBounds(700, 120, 140, 40);
        ukButton.setBounds(700, 270, 140, 40);
        Icon icon = new ImageIcon(getClass().getResource("/data/speak.png"));
        usButton.setIcon(icon);
        ukButton.setIcon(icon);
        usButton.setText(format("US", 6));
        ukButton.setText(format("UK", 6));
        usButton.setFont(miniFont);
        ukButton.setFont(miniFont);
        usButton.setForeground(Color.YELLOW);
        ukButton.setForeground(Color.YELLOW);
        usButton.setFocusPainted(false);
        ukButton.setFocusPainted(false);
        usButton.setContentAreaFilled(false);
        ukButton.setContentAreaFilled(false);
        usButton.setBackground(Color.darkGray);
        ukButton.setBackground(Color.darkGray);
        usButton.setBorder(null);
        ukButton.setBorder(null);
    }

    private void InitAdd() {
        addButton.setBounds(0, 300, 250, 50);
        addButton.setIcon(new ImageIcon(getClass().getResource("/data/add.png")));
        addButton.setText(format("Thêm từ", 15));
        addButton.setFont(bigFont);
        addButton.setForeground(Color.GREEN);
        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorder(null);
        addButton.setBackground(Color.darkGray);
        backGround.add(addButton);
    }

    private void InitInput() {
        s_Search.setBounds(300, 100, 400, 70);
        s_Search.setBorder(null);
        search.setBorder(null);
        search.setFont(font);
    }

    private void InitList() {
        s_List = new JScrollPane(List);
        s_List.setBounds(300, 200, 400, 450);
        List.setFont(font);
        s_List.setBorder(null);
        List.setBorder(null);
    }

    private void InitReadEnglish() {
        s_R_English.setBounds(300, 100, 400, 70);
        readEnglish.setFont(font);
        s_R_English.setBorder(null);
        readEnglish.setBorder(null);
    }

    public void InitReadVietnamese() {
        s_R_Vietnamese.setBounds(300, 200, 400, 450);
        readVietnamese.setFont(font);
        s_R_Vietnamese.setBorder(null);
        readVietnamese.setBorder(null);
    }

    public void InitWriteEnglish() {
        s_W_English.setBounds(300, 100, 400, 70);
        writeEnglish.setFont(font);
        s_W_English.setBorder(null);
        writeEnglish.setBorder(null);
    }

    public void InitWriteVietnamese() {
        s_W_Vietnamese.setBounds(300, 200, 400, 450);
        writeVietnamese.setFont(font);
        s_W_Vietnamese.setBorder(null);
        writeVietnamese.setBorder(null);
    }

    public void InitTick() {
        tickButton.setBounds(700, 120, 140, 40);
        Icon icon = new ImageIcon(getClass().getResource("/data/tick.png"));
        tickButton.setIcon(icon);
        tickButton.setText(format("Add", 6));
        tickButton.setFont(miniFont);
        tickButton.setForeground(Color.YELLOW);
        tickButton.setFocusPainted(false);
        tickButton.setContentAreaFilled(false);
        tickButton.setBackground(Color.darkGray);
        tickButton.setBorder(null);
    }

    public void InitSpeak() {
        speakButton.setBounds(700, 120, 140, 40);
        Icon icon = new ImageIcon(getClass().getResource("/data/speak.png"));
        speakButton.setIcon(icon);
        speakButton.setText(format("Speak", 6));
        speakButton.setFont(miniFont);
        speakButton.setForeground(Color.YELLOW);
        speakButton.setFocusPainted(false);
        speakButton.setContentAreaFilled(false);
        speakButton.setBackground(Color.darkGray);
        speakButton.setBorder(null);
    }

    public void InitDelete() {
        deleteButton.setBounds(700, 270, 140, 40);
        deleteButton.setIcon(new ImageIcon(getClass().getResource("/data/delete.png")));
        deleteButton.setText(format("Delete", 6));
        deleteButton.setFont(miniFont);
        deleteButton.setForeground(Color.YELLOW);
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBackground(Color.darkGray);
        deleteButton.setBorder(null);
    }

    public void InitChange() {
        changeButton.setBounds(700, 420, 140, 40);
        changeButton.setIcon(new ImageIcon(getClass().getResource("/data/change.png")));
        changeButton.setText(format("Change", 6));
        changeButton.setFont(miniFont);
        changeButton.setForeground(Color.YELLOW);
        changeButton.setFocusPainted(false);
        changeButton.setContentAreaFilled(false);
        changeButton.setBackground(Color.darkGray);
        changeButton.setBorder(null);
    }


    public void runDelete() {
        deleteButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String target = readEnglish.getText();
                int choose = JOptionPane.showConfirmDialog(App, "Do you want to delete this word?", "", JOptionPane.YES_NO_OPTION);
                if (choose != JOptionPane.YES_OPTION) return;
                Trie T = Dic.getTrieWord();
                T.delete(target);
                UpdateList(T);
                deleteButton.setContentAreaFilled(false);
                ModeSearch();
                JOptionPane.showConfirmDialog(App, "Success", "", JOptionPane.CLOSED_OPTION);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setContentAreaFilled(false);
            }
        });
    }

    public void runSearch() {
        searchButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (buttonRunning != searchButton) {
                    if (buttonRunning != null) {
                        buttonRunning.setContentAreaFilled(false);
                    }
                    ModeSearch();
                } else {
                    ModeDefault();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (buttonRunning != searchButton)
                    searchButton.setContentAreaFilled(false);
            }
        });
    }

    public void runReadEnglish() {
        readEnglish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ModeSearch();
            }
        });
    }

    private void runChange() {
        changeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkChange == false) {
                    writeVietnamese.setText(readVietnamese.getText());
                    ModeChange();
                    return;
                }
                int choose = JOptionPane.showConfirmDialog(App, "Change this word?", "", JOptionPane.YES_NO_OPTION);
                if (choose == JOptionPane.YES_OPTION) {
                    String newExplain = writeVietnamese.getText();
                    if (newExplain.length() == 0) {
                        JOptionPane.showConfirmDialog(App, "New explain is empty", "", JOptionPane.CLOSED_OPTION);
                        return;
                    }
                    String text = readEnglish.getText();
                    Trie T = Dic.getTrieWord().find(text);
                    T.setExplain(newExplain);
                    readVietnamese.setText(writeVietnamese.getText());
                    int index = List.getSelectedIndex();
                    ListOfWord.get(index).setWord_explain(newExplain);
                    JOptionPane.showConfirmDialog(App, "Success", "", JOptionPane.CLOSED_OPTION);
                }
                ModeRead();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                changeButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (checkChange == false)
                    changeButton.setContentAreaFilled(false);
            }
        });
    }

    public void runSearchWord() {
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String input = search.getText();
                Trie T = Dic.getTrieWord();
                T = T.find(input);
                UpdateList(T);
            }
        });
        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Search(e);
            }

            public void Search(KeyEvent e) {
                String input = search.getText();
                Trie T = Dic.getTrieWord().find(input);
                UpdateList(T);
            }
        });
    }

    public void runList() {
        List.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = List.getSelectedIndex();
                if (index == -1) {
                    readVietnamese.setText("");
                } else {
                    Word word = ListOfWord.get(index);
                    String output = word.getWord_explain();
                    output = output.replace(" /", "\n/");
                    output = output.replace("/ ", "/\n");
                    ModeRead();
                    readEnglish.setText(word.getWord_target());
                    readVietnamese.setText(output);
                }
            }
        });
    }

    public void runGgSpeak() {
        usButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = writeEnglish.getText();
                try {
                    Speak.getInstance().Speak(text, "en-us");
                } catch (IOException ioException) {
                    //JOptionPane.showConfirmDialog(App, "Internet connected fail", "", JOptionPane.CLOSED_OPTION);
                } catch (JavaLayerException javaLayerException) {
                    //JOptionPane.showConfirmDialog(App, "Internet connected fail", "", JOptionPane.CLOSED_OPTION);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                usButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                usButton.setContentAreaFilled(false);
            }
        });
        ukButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = writeEnglish.getText();
                try {
                    Speak.getInstance().Speak(text, "en-uk");
                } catch (IOException ioException) {
                    //JOptionPane.showConfirmDialog(App, "Internet connected fail", "", JOptionPane.CLOSED_OPTION);
                } catch (JavaLayerException javaLayerException) {
                    //JOptionPane.showConfirmDialog(App, "Internet connected fail", "", JOptionPane.CLOSED_OPTION);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ukButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ukButton.setContentAreaFilled(false);
            }
        });
    }

    public void runSpeak() {
        speakButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String Text = readEnglish.getText() + writeEnglish.getText();
                TextToSpeech tts = new TextToSpeech();
                tts.setVoice("dfki-poppy-hsmm");
                tts.speak(Text, 1.0f, false, false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                speakButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                speakButton.setContentAreaFilled(false);
            }
        });
    }

    public void runGoogle() {
        googleButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (buttonRunning != googleButton) {
                    if (buttonRunning != null) {
                        buttonRunning.setContentAreaFilled(false);
                    }
                    modeGoogle();
                    return;
                }
                ModeDefault();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                googleButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (buttonRunning != googleButton) {
                    googleButton.setContentAreaFilled(false);
                }
            }
        });

        writeEnglish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonRunning != googleButton) return;
                String input = writeEnglish.getText();
                try {
                    String output = GgTranslator.Translate(input);
                    readVietnamese.setText(output);
                } catch (Exception exception) {
                    JOptionPane.showConfirmDialog(App, "Internet connected fail", "", JOptionPane.CLOSED_OPTION);
                }
            }
        });
    }

    public void runAdd() {
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (buttonRunning != addButton) {
                    if (buttonRunning != null) {
                        buttonRunning.setContentAreaFilled(false);
                    }
                    ModeAdd();
                    return;
                }
                ModeDefault();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (buttonRunning != addButton) {
                    addButton.setContentAreaFilled(false);
                }
            }
        });
    }

    public void runTick() {
        tickButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String target = writeEnglish.getText();
                String explain = writeVietnamese.getText();
                int index = JOptionPane.showConfirmDialog(App, "Do you want to add this word?", "", JOptionPane.YES_NO_OPTION);
                if (index == JOptionPane.YES_OPTION) {
                    if (target.length() == 0) {
                        JOptionPane.showConfirmDialog(App, "Target is empty", "", JOptionPane.CLOSED_OPTION);
                        return;
                    }
                    if (explain.length() == 0) {
                        JOptionPane.showConfirmDialog(App, "Explain is empty", "", JOptionPane.CLOSED_OPTION);
                        return;
                    }
                    Trie T = Dic.getTrieWord();
                    if (CheckInput(target) == false) {
                        JOptionPane.showConfirmDialog(App, "Target is not accepted", "", JOptionPane.CLOSED_OPTION);
                        return;
                    } else {
                        T.add(new Word(target, explain));
                        JOptionPane.showConfirmDialog(App, "Success", "Add word", JOptionPane.CLOSED_OPTION);
                    }
                }
                writeEnglish.setText("");
                writeVietnamese.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                tickButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tickButton.setContentAreaFilled(false);
            }
        });
    }

    public void ModeAdd() {
        buttonRunning = addButton;
        backGround.remove(s_R_English);
        backGround.remove(s_R_Vietnamese);
        backGround.remove(s_Search);
        backGround.remove(s_List);
        backGround.remove(ukButton);
        backGround.remove(usButton);
        backGround.remove(deleteButton);
        backGround.remove(changeButton);
        backGround.remove(speakButton);
        header2.setText("Target");
        header3.setText("Explain");
        writeEnglish.setText("");
        writeVietnamese.setText("");
        backGround.add(s_W_English);
        backGround.add(s_W_Vietnamese);
        backGround.add(tickButton);
        App.repaint();
        App.setVisible(true);
    }

    public void modeGoogle() {
        buttonRunning = googleButton;
        backGround.remove(s_R_English);
        backGround.remove(s_W_Vietnamese);
        backGround.remove(s_Search);
        backGround.remove(s_List);
        backGround.remove(tickButton);
        backGround.remove(deleteButton);
        backGround.remove(changeButton);
        backGround.remove(speakButton);
        header2.setText("English");
        header3.setText("Vietnamese");
        writeEnglish.setText("");
        readVietnamese.setText("");
        backGround.add(s_W_English);
        backGround.add(s_R_Vietnamese);
        backGround.add(usButton);
        backGround.add(ukButton);
        App.repaint();
        App.setVisible(true);
    }

    public void ModeChange() {
        checkChange = true;
        backGround.remove(s_R_Vietnamese);
        backGround.add(s_W_Vietnamese);
        App.repaint();
        App.setVisible(true);
    }

    public void ModeRead() {
        checkChange = false;
        deleteButton.setContentAreaFilled(false);
        changeButton.setContentAreaFilled(false);
        speakButton.setContentAreaFilled(false);
        backGround.remove(s_W_Vietnamese);
        backGround.remove(s_W_English);
        backGround.remove(s_List);
        backGround.remove(s_Search);
        header2.setText("Target");
        header3.setText("Explain");
        backGround.add(s_R_English);
        backGround.add(s_R_Vietnamese);
        backGround.add(deleteButton);
        backGround.add(changeButton);
        backGround.add(speakButton);
        App.repaint();
        App.setVisible(true);
    }

    public void ModeSearch() {
        buttonRunning = searchButton;
        List.clearSelection();
        backGround.add(s_List);
        backGround.add(s_Search);
        header2.setText("Search");
        header3.setText("Search Results");
        backGround.remove(s_R_English);
        backGround.remove(s_R_Vietnamese);
        backGround.remove(s_W_English);
        backGround.remove(s_W_Vietnamese);
        backGround.remove(tickButton);
        backGround.remove(ukButton);
        backGround.remove(usButton);
        backGround.remove(deleteButton);
        backGround.remove(changeButton);
        backGround.remove(speakButton);
        App.repaint();
        App.setVisible(true);
    }

    public void ModeDefault() {
        header2.setText("");
        header3.setText("");
        buttonRunning = null;
        backGround.remove(s_R_English);
        backGround.remove(s_R_Vietnamese);
        backGround.remove(s_W_English);
        backGround.remove(s_W_Vietnamese);
        backGround.remove(s_Search);
        backGround.remove(s_List);
        backGround.remove(tickButton);
        backGround.remove(ukButton);
        backGround.remove(usButton);
        backGround.remove(deleteButton);
        backGround.remove(changeButton);
        backGround.remove(speakButton);
        App.repaint();
        App.setVisible(true);
    }

    public String format(String input, int size) {
        input = "  " + input;
        while (input.length() < size) {
            input = input + " ";
        }
        return input;
    }

    public void UpdateList(Trie T) {
        DefaultListModel listModel = new DefaultListModel();
        if (T != null) {
            ListOfWord = T.getListOfWord();
            for (int i = 0; i < ListOfWord.size(); ++i) {
                listModel.addElement(ListOfWord.get(i).getWord_target());
            }
        }
        List.setModel(listModel);
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

    public void UpdateFileInput() {
        App.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new DictionaryManagement().dictionaryExportToFile();
            }
        });
    }

    public static void main(String[] args) {
        new DictionaryManagement().insertFromFile();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DictionaryApplication();
            }
        });
    }
}