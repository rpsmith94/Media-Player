/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer2;

/**
 *
 * @author ryanremer
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.xml.sax.SAXException;

    public class MediaPlayer2 {

    /**
     * @param args the command line arguments
     */
    int current = 0;
    private String location;
    private Object lock = new Object();
    private AdvancedPlayer AP;
    InputStream s = null;
    AtomicBoolean playing = new AtomicBoolean(false);
    
    
    
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    
     private int playerStatus = NOTSTARTED;
    
    
    
    public static void main(String[] args) throws TransformerConfigurationException, TransformerException, SAXException, IOException, ParserConfigurationException, XPathExpressionException, JavaLayerException 
    {
        Frame f = new Frame();
        f.setVisible(true);
        
        //MediaPlayer2 m = new MediaPlayer2();
        
        //m.play("/Users/ryanremer/Music/iTunes/iTunes Music/Music/Daft Punk/Random Access Memories/08 - Get lucky.mp3",200);
        MusicList m1= new MusicList();
        //m1.add("/Users/ryanremer/Music/iTunes/iTunes Music/Music/Daft Punk/Random Access Memories/08 - Get lucky.mp3","Get Lucky", "Daft Punk","RAM");
        //String[][] nl = m1.searchBYsong("Get Lucky");
        
    }
    
    
    public MediaPlayer2(String location) throws FileNotFoundException, JavaLayerException
    {
        this.location=location;
        s = new FileInputStream(location);
       
        
            
    }
    public MediaPlayer2() 
    {
        
    }
    public void getLocation(String location)
    {
     this.location=location;   
    }
           
    public int play(int begining) throws InterruptedException, JavaLayerException
    {
        
        //AP.skipFrames(begining);
        synchronized(lock)           
        {
           
           while(true)
            {
            
                
                if(playing.get()==true)
                {
                    
                    Thread.currentThread().join(100);                   
                    break;
                  
                }
                
                if(AP.play(1,0))
                {
                  current++;
                }
            
        
            
        }
       return current;
        
    }
    }
    public int Pause()
    {
        playing.set(true);
        AP.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) 
            {
                current = event.getFrame();
            }
        }
                );
                       
        AP.stop();
           return current;
                       
    }

      
        public void stop() 
    {
         
        playing.set(true);
        AP.close();
        System.out.println("hi");
    }
    

    
    }
    


