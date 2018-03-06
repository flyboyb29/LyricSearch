package nlp.musicsearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Jsaon Hall
 */
public final class Main extends javax.swing.JFrame {

    private final String filesongs = "songs.ser";
    private final String filewords = "words.ser";
    private Map<String, File> theSongMap;
    private Map<String, Double> songSmoothedProb = new HashMap<>();
    private final File start = new File("src\\nlp\\musicsearch");
    private WordLib theWordLibrary;
    private final double theLambda = 0.75;
    private final String theDefault = "DeFaUlT";
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        
        songSmoothedProb.put(theDefault, 1.0);
        loadSerializedObjects();
        fillList(jList2, theSongMap.keySet().toArray());
        fillList(lstTraindSongs, theSongMap.keySet().toArray());
        txtWordInfo.append(theWordLibrary.getWordInfo());
        txtSongCount.setText(String.valueOf(theSongMap.size()));
        txtWordCount.setText(String.valueOf(theWordLibrary
                .getTotalWordCount()));
        
        System.out.println(theSongMap.size());
    }
    
    
    /**
     * 
     */
    public void loadSerializedObjects() {
        // Deserialization
        try {   
            try ( // Reading the object from a file
                FileInputStream file = new FileInputStream(filesongs); 
                ObjectInputStream in = new ObjectInputStream(file)) {
                // Method for deserialization of object
                theSongMap = (HashMap)in.readObject();
            } 
            System.out.println("Object has been deserialized ");
        } catch(Exception ex) {
            theSongMap = new HashMap<>();
            loadSongs();
        }
        
        try {   
            try ( // Reading the object from a file
                FileInputStream file = new FileInputStream(filewords); 
                ObjectInputStream in = new ObjectInputStream(file)) {
                // Method for deserialization of object
                theWordLibrary = (WordLib)in.readObject();
            } 
            System.out.println("Object has been deserialized ");
        } catch(Exception ex) {
            theWordLibrary = new WordLib();
            preTrain();
        }
        

    }
    
    private void preTrain() {
        theSongMap.keySet().stream().forEach((key) -> {
            theWordLibrary.trainSong(key, readFile(theSongMap.get(key)));
        });
    }
    
    /**
     * Use this to fill a JList
     * 
     * @param listToFill is the JList that you wish to fill
     * @param info the information that you wish to fill the list with.
     */
    private void fillList(JList listToFill, Object[] info) {
        DefaultListModel dim = new DefaultListModel();
        for (Object info1 : info) {
            dim.addElement(info1);
        }
        listToFill.setModel(dim);
    }
    
    /**
     * A helper method to read in a file.
     * 
     * @param theInput The file that we want to read in.
     * @return A string that represents the file that we just read in.
     */
    private String readFile(File theInput) {
            StringBuilder inputFromFile = new StringBuilder();

            InputStream  inputStream;

            try {
                inputStream = new FileInputStream(theInput);
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                int c;
                while ((c = reader.read()) != -1) {
                    inputFromFile.append((char) c);
                }
                inputStream.close();
                
            } catch (Exception e) {
                    e.printStackTrace();
            }

            return inputFromFile.toString();
    }
    
    /**
     * used to load all of the songs into the map that contains there name and
     * file location if it has not been saved before.
     */
    private void loadSongs() {
        
        File[] listOfFiles = start.listFiles();
        File[] listOfSongs;
        String[] names = start.list();
        StringBuilder sb;
       
        for (int i = 0; i < names.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                listOfSongs = listOfFiles[i].listFiles();
                for (File listOfSong : listOfSongs) {
                    sb = new StringBuilder();
                    sb.append(names[i]);
                    sb.append(" - ");
                    sb.append(listOfSong.getName().substring(0,
                            listOfSong.getName().length() - 4));
                    //System.out.println(sb.toString());
                    theSongMap.put(sb.toString(), listOfSong);
                }   
            }
        }
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        Training = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstSongsFound = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtChoosenSong = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLyrics = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstTraindSongs = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtWordInfo = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSongCount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtWordCount = new javax.swing.JTextField();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Search");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setText("Find");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        lstSongsFound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstSongsFoundMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lstSongsFound);

        txtChoosenSong.setColumns(20);
        txtChoosenSong.setRows(5);
        jScrollPane5.setViewportView(txtChoosenSong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        Training.addTab("Music Search", jPanel1);

        txtLyrics.setColumns(20);
        txtLyrics.setRows(5);
        jScrollPane3.setViewportView(txtLyrics);

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        Training.addTab("Song List", jPanel2);

        lstTraindSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstTraindSongsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lstTraindSongs);

        jLabel1.setText("Trained Songs:");

        txtWordInfo.setColumns(20);
        txtWordInfo.setRows(5);
        jScrollPane7.setViewportView(txtWordInfo);

        jLabel2.setText("NLP Information/Stats:");

        jLabel3.setText("Song Count:");

        txtSongCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSongCountActionPerformed(evt);
            }
        });

        jLabel4.setText("Word Count:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSongCount))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWordCount)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSongCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtWordCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        Training.addTab("Trained NLP Info", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Training)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Training, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Training.getAccessibleContext().setAccessibleName("Music Search");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        txtLyrics.setText(readFile(theSongMap.get
            (jList2.getSelectedValue().toString())));
    }//GEN-LAST:event_jList2MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("closing");
        // Serialization 
        try {   
            try (FileOutputStream file = new FileOutputStream(filesongs);
                    ObjectOutputStream out = new ObjectOutputStream(file)) {
                // Method for serialization of object
                out.writeObject(theSongMap);
            }
            System.out.println("Object has been serialized");
        } catch(Exception ex) {
            System.out.println("IOException is caught");
        }
        
        try {   
            try (FileOutputStream file = new FileOutputStream(filewords);
                    ObjectOutputStream out = new ObjectOutputStream(file)) {
                // Method for serialization of object
                out.writeObject(theWordLibrary);
            }
            System.out.println("Object has been serialized");
        } catch(Exception ex) {
            System.out.println("IOException is caught");
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtSongCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSongCountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSongCountActionPerformed

    private void lstTraindSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstTraindSongsMouseClicked
        
        String song = lstTraindSongs.getSelectedValue().toString();
        txtWordInfo.setText(theWordLibrary.getSongInfo(song));
        txtWordCount.setText(String.valueOf(theWordLibrary
                .getSongWordCount(song)));
    }//GEN-LAST:event_lstTraindSongsMouseClicked

    private Map<String, Double> mergeMaps (Map<String, Double> m1
            , Map<String, Double> m2) {
        final Map<String, Double> temp = new HashMap<>();
        
        double s1 = m1.get(theDefault);
        double s2 = m2.get(theDefault);
        
        temp.putAll(m1);
        
        temp.keySet().stream().forEach((key) -> {
            if (m2.containsKey(key)) {
                if (!key.equals(theDefault)) {
                    temp.replace(key, temp.get(key) * m2.get(key));
                }
            } else {
                temp.replace(key, temp.get(key) * s2);
            }
        });
        
        m2.keySet().stream().filter((key) -> (!temp.containsKey(key)))
                .forEach((key) -> {
            temp.put(key, s1 * m2.get(key));
        });
        
        temp.replace(theDefault, s1 * s2);
        
        return temp;
    }
    
    private Map<String, Double> getHashMap(String myWord) {
        final Map<String, Double> temp = new HashMap<>();
        final double smooth = (1 - theLambda) * theWordLibrary.getWordProb(myWord);
        final String[] songList = theWordLibrary.getSongList(myWord);
        temp.put(theDefault, smooth);
        
        for (String key: songList) {
            temp.put(key, theLambda * theWordLibrary
                    .getWordSongProb(myWord, key) + smooth);
        }
        
        return temp;
    }
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Scanner search = new Scanner(txtSearch.getText());
        int wordCount = 0;
        boolean failed = false;
        
        String currentWord;
        
        resetMap();
        clearList(lstSongsFound);
        
        while (search.hasNext()) {
            wordCount++;
            currentWord = search.next();
            if (theWordLibrary.hasWord(currentWord)) {
                songSmoothedProb = mergeMaps(songSmoothedProb
                    , getHashMap(currentWord));
            } else {
                if (wordCount == 1 && !search.hasNext()) {
                    showMessageDialog(null, 
                            "I am sorry but no song contains the word - " 
                                    + currentWord);
                    failed = true;
                }
            }
        }
        
        songSmoothedProb.remove(theDefault);
        
        if (!failed) {
            fillList(lstSongsFound, songSmoothedProb.keySet().toArray());
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void resetMap() {
        songSmoothedProb = new HashMap<>();
        songSmoothedProb.put(theDefault, 1.0);
    }
    
    private void lstSongsFoundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstSongsFoundMouseClicked
        txtChoosenSong.setText(readFile(theSongMap.get
            (lstSongsFound.getSelectedValue().toString())));
    }//GEN-LAST:event_lstSongsFoundMouseClicked

    private void clearList(JList listToFill) {
        DefaultListModel listmodel = new DefaultListModel();
        listmodel.clear();
        listToFill.setModel(listmodel);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Training;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JList lstSongsFound;
    private javax.swing.JList lstTraindSongs;
    private javax.swing.JTextArea txtChoosenSong;
    private javax.swing.JTextArea txtLyrics;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSongCount;
    private javax.swing.JTextField txtWordCount;
    private javax.swing.JTextArea txtWordInfo;
    // End of variables declaration//GEN-END:variables
}
