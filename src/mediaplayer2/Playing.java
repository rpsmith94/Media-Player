/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author ryanremer
 */
public class Playing 
{
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
     
     Playing p;
     Boolean gotValue = false;
     Boolean first  = true;
    int min,max;
    float volume = 0f;
     
     
    /*
        Playing class to control data and status of the song being currently played
    */
    public Playing() throws JavaLayerException
    {
        
        
    }
    /*
     Playing object to be initialised within the createPlayer method 
    */
    public void createPlayer(String file,float volume) throws JavaLayerException, FileNotFoundException
    {
        
                CurrentlyPlaying = true;              
               this.volume=volume;
                temp=file;
                 
                 s = new FileInputStream(file);
                
                player= new AdvancedPlayer(s);
                
                this.play();
                
                
            
    }
    /*
       Create a new thread for music being played  
       Synchronise threads so that music can be started,paused and stopped correctly
    */
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
                        
                        min = player.getMin();
                        max= player.getMax();                        
                        gotValue = true; 
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
      /*
            change the volume of the music 
            note that the value being passed in will increment the current volume
            so a value of 0 will not change the volume.
      */
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
        /*
            Value is the current frame that is being played
        */
     public Boolean getValue()
     {
      return gotValue;   
     }
}

