package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadPane extends JPanel {
    JTextField textField = new JTextField();
    public LoadPane(){
        setBounds(40,30,495,35);
        setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        //setBackground(Color.GRAY);
        addTextField();
        addSaveButton();
        addLoadButton();
    }

    private void addTextField(){
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Filename = textField.getText();
                Storage.saveToFile(Filename);
            }
        });
        add(saveButton);
    }

    private void addLoadButton(){
        JButton loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(100,30));
        loadButton.setName("LoadButton");
        loadButton.setFocusPainted(false);
        loadButton.setFont(new Font("Arial", Font.BOLD, 17));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Filename = textField.getText();
                TextPane.getTextArea().setText("");
                Storage.loadFromFile(Filename);
            }
        });
        add(loadButton);
    }
}
