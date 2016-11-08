package demojfilechooser;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

public class DemoJFilechooser extends JFrame{
    JMenuBar mnuBar;
    JMenu mnuFile;
    JMenuItem mnuOpen;
    JMenuItem mnuSave;
    JMenuItem mnuExit;
    JTextArea txtData;
    
    public DemoJFilechooser(){
        addControls();
        addEvent();
    }
    
    public void addEvent(){
        mnuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThoat();
            }
        });
        
        mnuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               moFile();
            }
        });
        
        mnuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               luuFile();
            }
        });
    }
    
    
    private void moFile() {
        JFileChooser chooser = new JFileChooser();
        
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getAbsolutePath().endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "Tập tin .txt";
            }
        });
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();
            try {
                FileInputStream inputStream = new FileInputStream(selectedFile);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
              
                String line = bufferedReader.readLine();
                StringBuilder stringBuilder = new StringBuilder();
                while (line != null)
                {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine(); // Dòng kế tiếp
                }
                
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                
                txtData.setText(stringBuilder.toString());
                
            } catch (FileNotFoundException ex) {
                
            } catch (UnsupportedEncodingException ex) {
                
            } catch (IOException ex) {
                
            }
        }
    }
    
    private void luuFile() {
                
    }
    
    private void xuLyThoat() {
        int ret = JOptionPane.showConfirmDialog(null, "Are you sure exit?","Exit??", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    public void addControls(){
        setupMenu();
        
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        txtData = new JTextArea(50, 50);
        txtData.setLineWrap(true);
        txtData.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtData,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        container.add(scrollPane, BorderLayout.CENTER);
    }
    
    public void setupMenu(){
        mnuBar = new JMenuBar();
        setJMenuBar(mnuBar);
        mnuFile = new JMenu("Hệ thống");
        mnuBar.add(mnuFile);
        
        mnuOpen = new JMenuItem("Open");
        mnuOpen.setPreferredSize(new Dimension(200, 30));
        mnuSave = new JMenuItem("Save");
        mnuExit = new JMenuItem("Exit");
        
        mnuFile.add(mnuOpen);
        mnuFile.add(mnuSave);
        mnuFile.addSeparator();
        mnuFile.add(mnuExit);
    }
    public void showWindow(){
        this.setSize(900, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }
    public static void main(String[] args) {
       DemoJFilechooser demo = new DemoJFilechooser();
       demo.showWindow();
    }
    
}
