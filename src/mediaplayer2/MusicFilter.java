package mediaplayer2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 *
 * @author ryanremer

 
/* ImageFilter.java is used by FileChooserDemo2.java. */
public class MusicFilter extends FileFilter {
 
    //Accept all directories and all gif, jpg, tiff, or png files.
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
 
        String extension = Utils.getExtension(f);
        
        if (extension != null)
        {
            if (extension.equals(Utils.mp3))
              {
                    return true;
              } 
            else
            {
                return false;
            }
        }
 
        return false;
    }
 
    //The description of this filter
    @Override
    public String getDescription() {
        return "Mp3 File";
    }
}


