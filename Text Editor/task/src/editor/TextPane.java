package editor;

import javax.swing.*;
import java.awt.*;

public class TextPane extends JPanel {
    public TextPane(){
        setBounds(50,70,485,360);
        setLayout(new BorderLayout());
        addTextArea();
    }

    private void addTextArea(){
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        textArea.setName("TextArea");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }
}