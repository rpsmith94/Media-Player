/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer2;
/**
 *
 * @author ryanremer
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import org.w3c.dom.*;

import org.xml.sax.SAXException;

public class MusicList
{
    String filePath = "/Users/ryanremer/Desktop/file.xml";
    int maxID = 0;
    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    Document doc;
    XPathFactory xpathfactory;
    XPath xpath;
    NodeList nodes;
    
   public MusicList() throws ParserConfigurationException, SAXException, IOException
   {
      docFactory = DocumentBuilderFactory.newInstance(); 
      docBuilder = docFactory.newDocumentBuilder();
      doc = docBuilder.parse(filePath);
      xpathfactory = XPathFactory.newInstance();
      xpath = xpathfactory.newXPath();
      
      docFactory.setNamespaceAware(true); 
   }
    
   public void add(String songLocation,String name,String artist,String album) throws TransformerConfigurationException, TransformerException, SAXException, IOException
   {
       
        
                                 
            System.out.println(doc.getLastChild());
            
	    Node rootElement = doc.getFirstChild();
            NodeList list = doc.getElementsByTagName("Song");
            maxID = list.getLength();
            System.out.println(maxID);
            Element song = doc.createElement("Song");
            rootElement.appendChild(song);
            
            Attr attr = doc.createAttribute("id");
            attr.setValue(Integer.toString(maxID+1));
            song.setAttributeNode(attr);
            
            Element songName = doc.createElement("songName"); 
            songName.appendChild(doc.createTextNode(name));
            song.appendChild(songName);
            
            Element artistName = doc.createElement("artistName");
            artistName.appendChild(doc.createTextNode(artist));
            song.appendChild(artistName);
            
            Element albumName = doc.createElement("albumName");
            albumName.appendChild(doc.createTextNode(album));
            song.appendChild(albumName);
            
            Element songLocationElement = doc.createElement("location");
            songLocationElement.appendChild(doc.createTextNode(songLocation));
            song.appendChild(songLocationElement);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/Users/ryanremer/Desktop/file.xml"));
            
            transformer.transform(source, result);
 
            System.out.println("File saved!");
            
   }
        
   
   
   public Object[][] searchBYsong(String search) throws XPathExpressionException
   {
       MapVariableResolver vr = new MapVariableResolver() ;
       vr.setVariable("myVar",search);
       xpath.setXPathVariableResolver(vr);
       
       XPathExpression expr = xpath.compile("/music/Song[contains(songName,$myVar)]");
       nodes = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);
       
       
       Visables[][] vis = new Visables[nodes.getLength()+1][1];
       VisableArtist[][] visArt = new VisableArtist[1][nodes.getLength()];
       Object [][] obj = new Object[nodes.getLength()][2];
       
       for(int i = 0;i<nodes.getLength();i++)
       {
           
           vis[i][0]= new Visables();
           visArt[0][i] = new VisableArtist();
           obj[i][0] = new Object();
           obj[i][1] = new Object();
           
       }
       for (int i = 0; i < nodes.getLength(); i++)
       {
          
           
           vis[i][0].setName(nodes.item(i).getChildNodes().item(0).getTextContent());
           vis[i][0].setArtist(nodes.item(i).getChildNodes().item(1).getTextContent());
           vis[i][0].setAlbum(nodes.item(i).getChildNodes().item(2).getTextContent());
           vis[i][0].setLocation(nodes.item(i).getChildNodes().item(3).getTextContent());
           obj[i][0]=vis[i][0];
           
           visArt[0][i].getName(nodes.item(i).getChildNodes().item(0).getTextContent());
           visArt[0][i].setArtist(nodes.item(i).getChildNodes().item(1).getTextContent());
           visArt[0][i].setAlbum(nodes.item(i).getChildNodes().item(2).getTextContent());
           visArt[0][i].setLocation(nodes.item(i).getChildNodes().item(3).getTextContent());
           obj[i][1] = visArt[0][i];
       }
       
       
       return obj;
   }
   public NodeList searchByalbum(String search) throws XPathExpressionException
   {
       MapVariableResolver vr = new MapVariableResolver() ;
       vr.setVariable("myVar", search);
       xpath.setXPathVariableResolver(vr);
       
           XPathExpression expr = xpath.compile("/music/Song[contains(albumName,$myVar)]");
       nodes = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);
       for (int i = 0; i < nodes.getLength(); i++)
       {
           System.out.println(nodes.item(i).getChildNodes().item(2).getTextContent());
       }
       
       
       return nodes;
       
   }
   public NodeList searchByartist(String search) throws XPathExpressionException 
   {
       MapVariableResolver vr = new MapVariableResolver() ;
       vr.setVariable("myVar", search);
       xpath.setXPathVariableResolver(vr);
       
       XPathExpression expr = xpath.compile("/music/Song[contains(artistName,$myVar)]");
       nodes = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);
       for (int i = 0; i < nodes.getLength(); i++)
       {
           System.out.println(nodes.item(i).getChildNodes().item(2).getTextContent());
       }
       
       
       return nodes;
   }
    
}


class MapVariableResolver implements XPathVariableResolver
{
  // local store of variable name -> variable value mappings
  Map<String, String> variableMappings = new HashMap<String, String>();

  // a way of setting new variable mappings 
  public void setVariable(String key, String value)  {
    variableMappings.put(key, value);
  }

  // override this method in XPathVariableResolver to 
  // be used during evaluation of the XPath expression      
  @Override
  public Object resolveVariable(QName varName) {
    // if using namespaces, there's more to do here
    String key = varName.getLocalPart();
    return variableMappings.get(key);
  }
}