package uk.co.v2systems.framework.files;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.co.v2systems.framework.utils.KeyValuePair;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

/**
 * Created by I&T Lab User on 20/07/2015.
 */
public class CustomXml {

    Document doc;
            
    public void createXml(){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

        }catch (Exception e){
            System.out.println("Exception in createXML.createXml :: " + e);
        }
    }

    public void addXmlTag(String tagName, List<KeyValuePair> attributes, String strParentXmlTag){
        addXmlTag(tagName,attributes,strParentXmlTag,0);
    }

    public void addXmlTag(String tagName, List<KeyValuePair> attributes, String strParentXmlTag, int occurrence){
        Element parentXmlTag=null;
        NodeList nodeList = doc.getElementsByTagName(strParentXmlTag);
        if(nodeList!=null)
            parentXmlTag = (Element) nodeList.item(occurrence);
        addXmlTag(tagName,attributes,parentXmlTag);
    }

    public void addXmlTag(String tagName, List<KeyValuePair> attributes, Element parentXmlTag){
        try {
            Element xmlElement = doc.createElement(tagName);
            //initialise xmlElement
            if(attributes!=null){
                for (int i = 0; i < attributes.size(); i++) {
                    if (!(attributes.get(i).getKey().equalsIgnoreCase("") && attributes.get(i).getValue().equalsIgnoreCase("")))
                        xmlElement.setAttribute(attributes.get(i).getKey(), attributes.get(i).getValue());
                }
            }
            if (parentXmlTag != null)
                parentXmlTag.appendChild(xmlElement);
            else { //preventing having more than one root xml element
                if (doc.getFirstChild() == null)
                    doc.appendChild(xmlElement);
            }
        }catch(Exception e){
            System.out.println("Exception in createXML.addXmlTag :: " + e);
        }
    }

    public void printXml(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            //print on the screen
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source,result);
        }catch(Exception e){
            System.out.println("Exception in createXML.printXml :: " + e);
        }

    }

}
