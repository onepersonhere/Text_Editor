package editor;

import javax.swing.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setTitle("Text Editor");
        setLayout(null);
        setLocationRelativeTo(null);
        iniComponents();
        setVisible(true);

    }

    public void iniComponents(){
        JPanel loadPane = new LoadPane();
        JPanel textPane = new TextPane();
        add(loadPane);
        add(textPane);
    }
}
