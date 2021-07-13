package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class MenuBar extends JMenuBar {
    JMenu fileMenu = new JMenu("File");
    JMenu searchMenu = new JMenu("Search");
    public MenuBar(){
        fileMenu.setName("MenuFile");
        searchMenu.setName("MenuSearch");
        add(fileMenu);
        addLoad();
        addSave();
        addExit();
        add(searchMenu);
        addStart();
        addPrev();
        addNext();
        addRegex();
    }
    public void addStart(){
        JMenuItem startMenu = new JMenuItem("Start search");
        startMenu.setName("MenuStartSearch");
        startMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Search search = new Search();
                search.start(); //generate a list with indexes
                IndexList.resetCurrentIdx();
                regexList.resetCurrentIdx();

                if(Search.regSearch.get()) {
                    //store regex/search values inside, find first match
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {}
                    if (IndexList.getList().size() > 0) {
                        int index = IndexList.getList().get(0);
                        //first pos if valid
                        selectText(index);
                    }
                }else{
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {}
                    if (regexList.getList().size() > 0) {
                        Map<Integer,Integer> index = regexList.getList().get(0);
                        //first pos if valid
                        selectRegexText(index);
                    }
                }
            }
        });
        searchMenu.add(startMenu);
    }
    public void addPrev(){
        JMenuItem prevMenu = new JMenuItem("Previous search");
        prevMenu.setName("MenuPreviousMatch");
        prevMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Search.regSearch.get()) {
                    if (IndexList.getList().size() > 0) {
                        int listIndex = IndexList.getCurrentIdx() - 1;
                        IndexList.setCurrentIdx(listIndex);
                        int index = IndexList.getList().get(IndexList.getCurrentIdx());

                        selectText(index);
                    }
                }else{
                    if (regexList.getList().size() > 0) {
                        int listIndex = regexList.getCurrentIdx() - 1;
                        regexList.setCurrentIdx(listIndex);
                        Map<Integer, Integer> index = regexList.getList().get(regexList.getCurrentIdx());

                        selectRegexText(index);
                    }
                }
            }
        });
        searchMenu.add(prevMenu);
    }
    public void addNext(){
        JMenuItem nextMenu = new JMenuItem("Next search");
        nextMenu.setName("MenuNextMatch");
        nextMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Search.regSearch.get()) {
                    if (IndexList.getList().size() > 0) {
                        int listIndex = IndexList.getCurrentIdx() + 1;
                        IndexList.setCurrentIdx(listIndex);
                        int index = IndexList.getList().get(IndexList.getCurrentIdx());

                        selectText(index);
                    }
                }else{
                    if (regexList.getList().size() > 0) {
                        int listIndex = regexList.getCurrentIdx() + 1;
                        regexList.setCurrentIdx(listIndex);
                        Map<Integer, Integer> index = regexList.getList().get(regexList.getCurrentIdx());

                        selectRegexText(index);
                    }
                }
            }
        });
        searchMenu.add(nextMenu);
    }
    public void addRegex(){
        JMenuItem regexMenu = new JMenuItem("Toggle regular expressions");
        regexMenu.setName("MenuUseRegExp");
        regexMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SearchPane.regexCheckbox.doClick();
            }
        });
        searchMenu.add(regexMenu);
    }


    public void addLoad(){
        JMenuItem loadMenu = new JMenuItem("Open");
        loadMenu.setName("MenuOpen");
        loadMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TextPane.getTextArea().setText("");
                String Filename = LoadPane.getFile();
                Storage.loadFromFile(Filename);
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
                String Filename = LoadPane.getFile();
                Storage.saveToFile(Filename);
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

    private void selectText(int index){
        TextPane.getTextArea().setCaretPosition(index + SearchPane.searchBar.getText().length());
        TextPane.getTextArea().select(index, index + SearchPane.searchBar.getText().length());
        TextPane.getTextArea().grabFocus();
    }

    private void selectRegexText(Map<Integer,Integer> index){
        int start = 0;
        int end = 0;
        for ( Map.Entry<Integer, Integer> entry : index.entrySet()) {
            start = entry.getKey();
            end = entry.getValue();
        }
        TextPane.getTextArea().setCaretPosition(end);
        TextPane.getTextArea().select(start, end);
        TextPane.getTextArea().grabFocus();
    }
}
