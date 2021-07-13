package editor;

import javax.swing.*;
import java.awt.*;

public class TextPane extends JPanel {
    static JTextArea textArea = new JTextArea();
    public TextPane(){
        //setBounds(10,50,570,385);
        setLayout(new BorderLayout());
        addTextArea();
    }

    private void addTextArea(){
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        textArea.setName("TextArea");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

    public static JTextArea getTextArea() {
        return textArea;
    }
}
