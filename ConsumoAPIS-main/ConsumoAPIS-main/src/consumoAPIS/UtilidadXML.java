package consumoAPIS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UtilidadXML {

	
	
	public Document initReadDOM() {
		org.w3c.dom.Document doc = null;
		try {
			File fichero = new File("comics.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fichero);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return doc;
	}
	
	public List<Comic> readComics(Document doc) {
        List<Comic> comics = new ArrayList<>();

        NodeList comicNodes = doc.getElementsByTagName("comic");

        for (int i = 0; i < comicNodes.getLength(); i++) {
            Node comicNode = comicNodes.item(i);
            Comic comic = getComicFromNode(comicNode);
            comics.add(comic);
        }

        return comics;
    }
	
	 private Comic getComicFromNode(Node comicNode) {
	        Comic comic = new Comic();

	        if (comicNode.getNodeType() == Node.ELEMENT_NODE) {
	            org.w3c.dom.Element comicElement = (org.w3c.dom.Element) comicNode;

	            // Configurar el objeto Comic directamente
	            comic.setId(Integer.parseInt(getTagValue(comicElement, "id")));
	            comic.setDigitalId(Integer.parseInt(getTagValue(comicElement, "digitalId")));
	            comic.setTitle(getTagValue(comicElement, "title"));
	            comic.setIssueNumber(Integer.parseInt(getTagValue(comicElement, "issueNumber")));
	            comic.setVariantDescription(getTagValue(comicElement, "variantDescription"));
	            comic.setPath_image(getTagValue(comicElement,"pathImage"));
	            comic.setFormat(getTagValue(comicElement, "format"));
	            comic.setModified(getTagValue(comicElement, "lastModified"));
	            comic.setLanguage(getTagValue(comicElement, "language"));
	            comic.setnStories(Integer.parseInt(getTagValue(comicElement, "nHistories")));
	        }

	        return comic;
	    }
	
	 private String getTagValue(org.w3c.dom.Element element, String tagName) {
	        NodeList nodeList = element.getElementsByTagName(tagName);
	        if (nodeList.getLength() > 0) {
	            return nodeList.item(0).getTextContent();
	        }
	        return "";
	    }
	 

	 public Document initializeXmlDocument() throws Exception {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        return docBuilder.newDocument();
	    }

	 public void generateXml(List<Comic> comicsList,Document doc) {
	        try {



	            // Elemento raíz
	            Element rootElement = doc.createElement("comics");
	            doc.appendChild(rootElement);

	            // Iterar sobre la lista de cómics y agregar elementos al documento XML
	            for (Comic comic : comicsList) {
	                Element comicElement = doc.createElement("comic");
	                rootElement.appendChild(comicElement);

	                // Agregar elementos específicos del cómic
	                addXmlElement(doc, comicElement, "id", String.valueOf(comic.getId()));
	                addXmlElement(doc, comicElement, "digitalId", String.valueOf(comic.getDigitalId()));
	                addXmlElement(doc, comicElement, "title", comic.getTitle());
	                addXmlElement(doc, comicElement, "issueNumber", String.valueOf(comic.getIssueNumber()));

	                // Añadir los nuevos elementos
	                addXmlElement(doc, comicElement, "pathImage", comic.getPath_image());
	                addXmlElement(doc, comicElement, "format", comic.getFormat());
	                addXmlElement(doc, comicElement, "nHistories", String.valueOf(comic.getnStories()));
	                addXmlElement(doc, comicElement, "lastModified", comic.getModified());
	                addXmlElement(doc, comicElement, "variantDescription", comic.getVariantDescription());
	                addXmlElement(doc, comicElement, "language", comic.getLanguage());
	               
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Método para agregar elementos al documento XML
	    private static void addXmlElement(Document doc, Element parentElement, String tagName, String textContent) {
	        Element element = doc.createElement(tagName);
	        element.appendChild(doc.createTextNode(textContent));
	        parentElement.appendChild(element);
	    }

	    public void writeXmlDocument(Document doc, String fileName) {
	        try {
	            // Guardar el documento XML en un archivo
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(fileName));

	            transformer.transform(source, result);

	            System.out.println("Archivo XML generado exitosamente.");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
