package mediaplayer2;

import java.awt.GraphicsConfiguration;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Node;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import javazoom.jl.decoder.JavaLayerException;
import org.farng.mp3.TagException;
import org.w3c.dom.NodeList;
import javax.swing.JOptionPane;
import javazoom.jl.player.advanced.AdvancedPlayer;

import org.xml.sax.SAXException;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryanremer
 */
public class Frame extends javax.swing.JFrame {

    /**
     * Creates new form Frame
     */
    Boolean playPause = false;
    Float volume = 0.0f;
    int volumeMin;
    int volumeMax;
    MusicList ML;
    Boolean gotValue = false;
    Playing p =null;
    Boolean playing = false;
    JSlider source;
    final Object lock2 = new Object();
    public Frame() throws ParserConfigurationException, SAXException, IOException, JavaLayerException
    {
        initComponents();
        ML = new MusicList();
       // MP = new MediaPlayer2();
       p = new Playing();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Import");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(30);
        jSlider1.setMinimum(-30);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(0);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Artist"
            }
        ));
        jTable1.setEnabled(false);
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(44, 44, 44)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jButton1)
                        .add(18, 18, 18)
                        .add(jButton2))
                    .add(jSlider1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 85, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(jButton3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(63, 63, 63)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButton1)
                            .add(jButton2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSlider1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(61, 61, 61)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButton3)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 321, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(playPause == false)
        {
            p.pause();
            playPause = true;
        }
        else
        {
            playPause= p.resume();
                    
                
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       String box =  jTextField1.getText();
        try {
            
            this.boxWriter(ML.searchBYsong(box),jTable1);
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        
        if(evt.getClickCount() == 2)
        {
            
        int a = jTable1.rowAtPoint(evt.getPoint());
        int b = jTable1.columnAtPoint(evt.getPoint());
        Object o = jTable1.getValueAt(a,b);
        System.out.println(o.getClass());
        
        Visables v = null;
        VisableArtist va = null;

        if(o.getClass().toString().equals("class mediaplayer2.Visables")==true)
         {
              v = (Visables) o; 
               
            try 
            {              
               int d,e;
                    if(playing == false)
                    {
                         playing = true;
                                
                                //p.createPlayer(v.getLocation(),volume);
                                this.createPlayer(v.getLocation(),volume);
                        try {
                            this.Sync();
                       /* while(p.gotValue == false)
                        {
                            System.out.println("Wasting time");
                        }*/
                        } 
                        catch (InterruptedException ex) {
                            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                                
                                d = this.getMin();
                                e = this.getMax();
                         System.out.println(d+"Boooo "+e);
                         jSlider1.setMinimum(d);
                         jSlider1.setMaximum(e);
                         jSlider1.setValue((d+e)/2);
                         
                         jSlider1.addChangeListener(new javax.swing.event.ChangeListener() 
                         {
                            @Override
                                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                jSlider1StateChanged(evt);
                           }
                         });
                         
                         
                          
                         
                    }
                    else
                    {
                       
                       p.close();
                       p = new Playing();
                       p.createPlayer(v.getLocation(),volume);
                       
                    }
                    
               
               
            }   
            catch (JavaLayerException ex)
            {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (FileNotFoundException ex) 
            {                
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            
           
         }
        else
        {
              va = (VisableArtist) o;
              
              //MP.getLocation(va.getLocation()); 
            
                //p.createPlayer(va.getLocation(),0);
            
            
               
              
        }
              
        
        }
        
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
         fc.addChoosableFileFilter(new MusicFilter());
        fc.showOpenDialog(Frame.this);
        File newFile = null;
        
        try 
        {
            if(fc.getSelectedFile() != null)
            {
                newFile = fc.getSelectedFile();                
                TagReader tr = new TagReader(newFile);
                ML.add(newFile.getPath(),tr.getSong(), tr.getArtist(), tr.getAlbum());
            }
        
            
        } catch (TagException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (TransformerConfigurationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(playing == true && playPause == true)
        {
                 p.close();
        }
    }//GEN-LAST:event_formWindowClosing
      
    
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt)
    {
        // TODO add your handling code here:
        //System.out.println(jSlider1.getMinimum());
        //System.out.println(jSlider1.getMaximum());
        
        
        volume=(float) jSlider1.getValue();
    } 

    
    public void Sync() throws InterruptedException
    {
        synchronized(lock2)
        {
            lock2.wait();   
        }
        System.out.println("free");
        
    }

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try 
                {
                    new Frame().setVisible(true);
                } 
                catch (ParserConfigurationException ex) 
                {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (SAXException ex) 
                {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (JavaLayerException ex)
                {
                    
                    }
            }
            
            
            
           
        });
    }
    public void boxWriter(Object[][] str,JTable jtf)
    {
        
        int columns =2;
        jTable1.setModel(new javax.swing.table.DefaultTableModel(str,new String [] {
                "Name", "Artist"
            }));
        
             
                

               
                        
                   
                   
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
        
/*public synchronized void getMinMax() throws InterruptedException
{
    while(p.getValue()==false)
    {
        wait();
        System.out.println("kok");
    }
    
    
}*/

    InputStream s = null;
    private MediaPlayer2 mp3Player = null;
    private Thread playerThread;
    private Boolean CurrentlyPlaying = false;
    int current=0;
    String temp = null;
    AdvancedPlayer player = null;
     private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    final private Object PlayerLock = new Object();
    
     private int playerStatus = NOTSTARTED;
     
     
     
     Boolean first  = true;
    int min,max;
    float Newvolume = 0f;
     
     
      
    
    public void createPlayer(String file,float volume) throws JavaLayerException, FileNotFoundException
    {
        
                CurrentlyPlaying = true;
               // mp3Player = new MediaPlayer2(file);                        
              //playerThread = new Thread();
               // playerThread.start();
               this.Newvolume=volume;
                temp=file;
                 
                 s = new FileInputStream(file);
                
                player= new AdvancedPlayer(s);
                
                this.play();
                
                
            
    }
    public void play() 
    {
        synchronized(PlayerLock) 
        {
            switch (playerStatus) 
            {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                @Override
                        public void run() {
                            playInternal();
                        }
                    };
                    final Thread  t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();                   
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;   
                    
            }
        }
        
    }

    /*public void stop()
    {
       mp3Player.stop();
       
    }*/
    /*public void pause()
    {
     current= mp3Player.Pause();   
    }
    /*public void resume() throws JavaLayerException, InterruptedException, FileNotFoundException
    {
        this.createPlayer(temp);
    }*/
    
    
    private synchronized void playInternal()
    {
        while (playerStatus != FINISHED) {
            try
            {
               synchronized(lock2)
               {
                if (!player.play(1,volume)) 
                {
                    
                    break;
                }
                    if(first == true)
                    {
                        first = false;
                        
                        
                    }
                    else
                    {
                        lock2.notifyAll();
                        min = player.getMin();
                        max= player.getMax();                        
                        gotValue = true; 
                        
                    }
               } 
                        
                      
                    
                
                    
            } catch (final JavaLayerException e) 
            {
                break;
            }
            // check if paused or terminated
            synchronized (PlayerLock) 
            {
                
                while (playerStatus == PAUSED)
                {
                    try 
                    {
                        PlayerLock.wait();
                    } 
                    catch (final InterruptedException e)
                    {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }
    public void close() 
    {
        synchronized (PlayerLock) 
        {
            playerStatus = FINISHED;
        }
        try 
        {
            player.close();
            
        } 
        catch (final Exception e) 
        {
            // ignore, we are terminating anyway
        }
    }
     public void stop()
     {
        synchronized (PlayerLock) 
        {
            playerStatus = FINISHED;
            PlayerLock.notifyAll();
        }
    }
      public boolean resume() 
      {
        synchronized (PlayerLock)
        {
            if (playerStatus == PAUSED) 
            {
                playerStatus = PLAYING;
                PlayerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }
      public boolean pause() 
      {
        synchronized(PlayerLock)
        {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }
      public void changeVolume (float volume)
      {
          this.volume = volume;
      }
      public int getMin()
        {
         return min;   
        }
        public int getMax()
        {
            return max;    
        }
     public Boolean getValue()
     {
      return gotValue;   
     }
}


