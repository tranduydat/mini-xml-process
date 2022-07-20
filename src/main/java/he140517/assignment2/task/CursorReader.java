package he140517.assignment2.task;

import he140517.assignment2.model.Detail;
import he140517.assignment2.model.Item;
import he140517.assignment2.model.ItemMap;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author dattdhe140517
 */
public class CursorReader {

    public ItemMap getAllItems(String filePath) {
        ItemMap items = null;
        Item currentItem = null;
        String currentText = null;
        int currentDetailChildId = 0;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileReader fileReader;

        try {
            fileReader = new FileReader(filePath);
            XMLStreamReader streamReader = factory.createXMLStreamReader(fileReader);

            while (streamReader.hasNext()) {
                int category = streamReader.next();

                switch (category) {
                    case XMLStreamConstants.START_ELEMENT:
                        String tagName = streamReader.getLocalName();
                        if ("database".equals(tagName)) {
                            // Init new item list
                            items = new ItemMap();
                        }

                        if ("item".equals(tagName)) {
                            Integer id = Integer.parseInt(streamReader.getAttributeValue(0));
                            String clientName = streamReader.getAttributeValue(1);
                            currentItem = new Item(id, clientName);
                        }

                        if ("detail".equals(tagName)) {
                            if (currentItem != null) {
                                currentDetailChildId = Integer.parseInt(streamReader.getAttributeValue(0));
                                currentItem.getDetails().getMap().put(currentDetailChildId,
                                        new Detail(currentDetailChildId));
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        currentText = streamReader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        String endTag = streamReader.getLocalName();

                        switch (endTag) {
                            case "name":
                                if (currentItem != null) {
                                    currentItem.getDetails().getMap()
                                            .get(currentDetailChildId)
                                            .setName(currentText);
                                }

                                break;

                            case "amount":
                                if (currentItem != null) {
                                    currentItem.getDetails().getMap()
                                            .get(currentDetailChildId)
                                            .setAmount(Integer.parseInt(currentText));
                                }
                                break;

                            case "item":
                                items.getMap().put(currentItem.getId(), currentItem);
                                break;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(CursorReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return items;
    }
}
