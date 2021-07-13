package editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setTitle("Text Editor");
        setLayout(new BorderLayout(5,5));
        setLocationRelativeTo(null);
        iniComponents();
        setVisible(true);
        add(jfc);
    }
    public static JFileChooser jfc = new JFileChooser();
    public void iniComponents(){
        JPanel topPane = new JPanel();
        JPanel loadPane = new LoadPane();
        JPanel textPane = new TextPane();
        JPanel searchPane = new SearchPane();
        JMenuBar menuBar = new MenuBar();

        topPane.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        topPane.setPreferredSize(new Dimension(600,35));
        topPane.add(loadPane);
        topPane.add(searchPane);
        add(topPane,BorderLayout.NORTH);
        add(textPane,BorderLayout.CENTER);
        setJMenuBar(menuBar);
        jfc.setName("FileChooser");
        jfc.setVisible(false);
    }
}
