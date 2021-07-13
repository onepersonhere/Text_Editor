package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//TODO: add Search JMenu
public class MenuBar extends JMenuBar {
    JMenu fileMenu = new JMenu("File");
    public MenuBar(){
        fileMenu.setName("MenuFile");
        add(fileMenu);
        addLoad();
        addSave();
        addExit();
    }
    public void addLoad(){
        JMenuItem loadMenu = new JMenuItem("Load");
        loadMenu.setName("MenuLoad");
        loadMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //String filename = LoadPane.getTextField().getText();
                TextPane.getTextArea().setText("");
                //Storage.loadFromFile(filename);
            }
        });
        fileMenu.add(loadMenu);
    }
    public void addSave(){
        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.setName("MenuSave");
        saveMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //String filename = LoadPane.getTextField().getText();
                //Storage.saveToFile(filename);
            }
        });
        fileMenu.add(saveMenu);
    }
    public void addExit(){
        fileMenu.addSeparator();
        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.setName("MenuExit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);
    }
}
