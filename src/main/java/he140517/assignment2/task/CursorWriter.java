package he140517.assignment2.task;

import he140517.assignment2.model.Detail;
import he140517.assignment2.model.Item;
import he140517.assignment2.model.ItemMap;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author dattdhe140517
 */
public class CursorWriter {

    public void writeItemsToXml(List<Item> items, String outpath) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = factory.createXMLStreamWriter(new FileOutputStream(outpath), "UTF-8");
            streamWriter.writeStartDocument("UTF-8", "1.0");
            streamWriter.writeCharacters("\n");
            streamWriter.writeStartElement("database");

            for (Item item : items) {
                streamWriter.writeCharacters("\n\t");

                streamWriter.writeStartElement("item");
                streamWriter.writeAttribute("id", String.valueOf(item.getId()));
                streamWriter.writeAttribute("clientName", item.getClientName());

                if (item.getDetails() != null) {
                    for (Detail detail : item.getDetails().getMap().values()) {
                        this.writeDetailToXml(streamWriter, detail);
                    }
                }

                streamWriter.writeCharacters("\n\t");
                streamWriter.writeEndElement();
            }

            streamWriter.writeCharacters("\n");
            streamWriter.writeEndElement();
            streamWriter.flush();
            streamWriter.close();
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(CursorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeDetailToXml(XMLStreamWriter streamWriter, Detail detail) {
        try {
            streamWriter.writeCharacters("\n\t\t");
            streamWriter.writeStartElement("detail");
            streamWriter.writeAttribute("child_id", detail.getChildIdAsString());
            this.writeElement(streamWriter, "name", detail.getName(), "\n\t\t\t");
            this.writeElement(streamWriter, "amount", detail.getAmountAsString(), "\n\t\t\t");
            streamWriter.writeCharacters("\n\t\t");
            streamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            Logger.getLogger(CursorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeElement(XMLStreamWriter streamWriter, String tagName, String content, String sep) {
        try {
            streamWriter.writeCharacters(sep);
            streamWriter.writeStartElement(tagName);
            streamWriter.writeCharacters(content);
            streamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            Logger.getLogger(CursorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
