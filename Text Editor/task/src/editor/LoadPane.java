package editor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadPane extends JPanel {
    final int buttonSize = 30;
    public LoadPane(){
        setBounds(0,0,100,buttonSize);
        setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        //setBackground(Color.GRAY);
        addOpenButton();
        addSaveButton();
    }

    private void addSaveButton(){
        JButton saveButton = new JButton();
        saveButton = createImageIcon(saveButton, "Text Editor/task/src/editor/resources/SaveIcon.jpg");
        saveButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
        saveButton.setName("SaveButton");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Filename = getFile();
                Storage.saveToFile(Filename);
            }
        });
        add(saveButton);
    }

    private void addOpenButton(){
        JButton openButton = new JButton();
        openButton = createImageIcon(openButton, "Text Editor/task/src/editor/resources/OpenIcon.jpg");
        openButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
        openButton.setName("OpenButton");
        openButton.setFocusPainted(false);


        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Filename = getFile();
                TextPane.getTextArea().setText("");
                Storage.loadFromFile(Filename);
            }
        });
        add(openButton);
    }

    protected JButton createImageIcon(JButton button, String path) {
        try {
            Image image = ImageIO.read(new File(path)).getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);;
            button.setIcon(new ImageIcon(image));
            button.setContentAreaFilled(false);
        }catch(IOException e){e.printStackTrace();}
        return button;
    }

    public static String getFile(){
        String Filename = "";
        JFileChooser jfc = TextEditor.jfc;
        jfc.setVisible(true);
        jfc.setFileSystemView(FileSystemView.getFileSystemView());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            Filename = selectedFile.getAbsolutePath();
        }
        jfc.setVisible(false);
        return Filename;
    }

}
