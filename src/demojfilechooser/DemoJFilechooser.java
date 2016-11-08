package demojfilechooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

public class DemoJFilechooser extends JFrame{
    JMenuBar mnuBar;
    JMenu mnuFile;
    JMenuItem mnuOpen;
    JMenuItem mnuSave;
    JMenuItem mnuExit;
    JTextArea txtData;
    JLabel lb;
    
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
        mnuExit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        mnuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               moFile();
            }
        });
        
        mnuOpen.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        
        mnuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               luuFile();
            }
        });
        mnuSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
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
//         get bound of txtData
//        Rectangle rec = txtData.getBounds();
//         Mô tả một hình ảnh với dữ liệu hình ảnh
//        BufferedImage bufferedImage = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
//         lấy tấm hình vẽ lên txtData
//        txtData.paint(bufferedImage.getGraphics());
//        
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        
//        if (chooser.showSaveDialog(DemoJFilechooser.this) == JFileChooser.APPROVE_OPTION)
//        {
//            File f = chooser.getSelectedFile();
//            try {
//                // Vẽ image với định dạng png
//                ImageIO.write(bufferedImage, "png", f);
//                JOptionPane.showMessageDialog(null, "Save success");
//            } catch (IOException ex) {
//                
//            }
//        }

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
               return f.getAbsolutePath().endsWith(".png") 
                       || f.getAbsolutePath().endsWith(".jpg") 
                       || f.getAbsolutePath().endsWith(".gif");
            }

            @Override
            public String getDescription() {
               return ".png, .jpg, .gif";
            }
        });
       if (chooser.showSaveDialog(DemoJFilechooser.this) == JFileChooser.APPROVE_OPTION)
       {
           File f = chooser.getSelectedFile();
           ImageIcon imageIcon = new ImageIcon(f.getAbsolutePath());
           Rectangle rec = txtData.getBounds();
           Image image = imageIcon.getImage().getScaledInstance(rec.width, rec.height, Image.SCALE_DEFAULT);
           imageIcon = new ImageIcon(image);
           lb.setIcon(imageIcon);
       } 
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
        
        lb = new JLabel();
        lb.setPreferredSize(new Dimension(200,0));
//        No set up color 
//        lb.setBackground(Color.BLUE);
        container.add(lb, BorderLayout.WEST);
        
        
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
