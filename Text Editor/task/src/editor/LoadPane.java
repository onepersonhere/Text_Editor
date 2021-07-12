package editor;

import javax.swing.*;
import java.awt.*;

public class LoadPane extends JPanel {
    public LoadPane(){
        setBounds(40,30,495,35);
        setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        //setBackground(Color.GRAY);
        addTextField();
        addSaveButton();
        addLoadButton();
    }

    private void addTextField(){
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(265,30));
        textField.setName("FilenameField");
        add(textField);
    }

    private void addSaveButton(){
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100,30));
        saveButton.setName("SaveButton");
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Arial", Font.BOLD, 17));
        add(saveButton);
    }

    private void addLoadButton(){
        JButton loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(100,30));
        loadButton.setName("LoadButton");
        loadButton.setFocusPainted(false);
        loadButton.setFont(new Font("Arial", Font.BOLD, 17));
        add(loadButton);
    }
}
