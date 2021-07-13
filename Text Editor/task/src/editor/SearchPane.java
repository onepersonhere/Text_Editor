package editor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SearchPane extends JPanel {
    final int buttonSize = 30;
    public SearchPane(){
        //setBackground(Color.GRAY);
        setPreferredSize(new Dimension(500,buttonSize));
        setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        addSearchBar();
        addSearchButton();
        addPrevButton();
        addNextButton();
        addRegexCheckbox();
    }
    public static JTextField searchBar = new JTextField();
    private void addSearchBar(){
        searchBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        searchBar.setPreferredSize(new Dimension(280,buttonSize));
        searchBar.setName("SearchField");
        add(searchBar);
    }
    private void addSearchButton(){
        JButton searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
        searchButton.setName("StartSearchButton");
        searchButton.setFocusPainted(false);
        searchButton = createImageIcon(searchButton,"Text Editor/task/src/editor/resources/search.jpg");
        searchButton.addActionListener(new ActionListener() {
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
        add(searchButton);
    }

    private void addPrevButton(){
        JButton prevButton = new JButton();
        prevButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
        prevButton.setName("PreviousMatchButton");
        prevButton.setFocusPainted(false);
        prevButton = createImageIcon(prevButton,"Text Editor/task/src/editor/resources/prev.jpg");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //prev (if at the start, go to last)
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
        add(prevButton);
    }

    private void addNextButton(){
        JButton nextButton = new JButton();
        nextButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
        nextButton.setName("NextMatchButton");
        nextButton.setFocusPainted(false);
        nextButton = createImageIcon(nextButton,"Text Editor/task/src/editor/resources/next.png");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //next (if at last, go to start)
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
        add(nextButton);
    }
    public static JCheckBox regexCheckbox = new JCheckBox("Use regex");
    private void addRegexCheckbox(){
        regexCheckbox.setPreferredSize(new Dimension(100,buttonSize));
        regexCheckbox.setName("UseRegExCheckbox");
        regexCheckbox.setFocusPainted(false);
        regexCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    //use regex
                    Search.regSearch.set(false);
                }else{
                    //disable regex
                    Search.regSearch.set(true);
                }
            }
        });
        add(regexCheckbox);
    }

    protected JButton createImageIcon(JButton button, String path) {
        try{
            Image image = ImageIO.read(new File(path)).getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);;
            button.setIcon(new ImageIcon(image));
            button.setContentAreaFilled(false);
        }catch(IOException e){e.printStackTrace();}
        return button;
    }

    private void selectText(int index){
        TextPane.getTextArea().setCaretPosition(index + searchBar.getText().length());
        TextPane.getTextArea().select(index, index + searchBar.getText().length());
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
