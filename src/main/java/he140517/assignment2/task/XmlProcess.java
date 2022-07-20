/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package he140517.assignment2.task;

import he140517.assignment2.model.Item;
import he140517.assignment2.model.ItemMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tranduydat
 */
public class XmlProcess {

    public ItemMap runReadXml(String filePath) {
        CursorReader cursorReader = new CursorReader();
        ItemMap items = cursorReader.getAllItems(filePath);

        System.out.println("\nRead " + items.getMap().size() + " items in file: " + filePath + "\n---");

        return items;
    }

    public void runWriteXml(ItemMap readItemsFromFile, ItemMap enteredItemsFromConsole, String outputPath) {
        List<Item> items = new LinkedList<>();
        System.out.println(readItemsFromFile);
        System.out.println(enteredItemsFromConsole);

        if (readItemsFromFile != null) {
            items.addAll(readItemsFromFile.getMap().values());
        }

        Collections.reverse(items);

        if (enteredItemsFromConsole != null) {
            List<Item> tmpList = new LinkedList<>();
            tmpList.addAll(enteredItemsFromConsole.getMap().values());
            Collections.reverse(tmpList);
            items.addAll(tmpList);
        }

        System.out.println("2. Write " + items.size() + " items to " + outputPath);

        CursorWriter cursorWrite = new CursorWriter();
        cursorWrite.writeItemsToXml(items, outputPath);

        System.out.println("SUCCESS!");
    }
}
