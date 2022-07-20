package he140517.assignment2.task;

import he140517.assignment2.config.Constant;
import he140517.assignment2.model.Detail;
import he140517.assignment2.model.DetailMap;
import he140517.assignment2.model.Item;
import he140517.assignment2.model.ItemMap;
import he140517.assignment2.utils.UserInputUtils;

/**
 *
 * @author dattdhe140517
 */
public class App {

    protected ItemMap readItemsFromFile;
    protected ItemMap enteredItems;
    private XmlProcess xmlProcess;

    public App() {
        this.readItemsFromFile = new ItemMap();
        this.enteredItems = new ItemMap();
        this.xmlProcess = new XmlProcess();
    }

    public void showAndRunByChoice() {
        String greetMsg = "Welcome to my assignment 2!";
        System.out.println(greetMsg);

        String menuMsg = "Please choose an option"
                + "\n1. Read an XML documentation with StAX parser."
                + "\n2. Add a new item object, then write it down to XML."
                + "\n3. Exit";

        int status = 0;
        while (true) {
            System.out.println(menuMsg);
            int choice = UserInputUtils.getIntAndCheck("Your choice: ", 1, 3);
            status = this.runByChoice(choice);
            if (status == Constant.BACK_CHOICE) {
                continue;
            } else if (status == Constant.EXIT_CHOICE) {
                break;
            }
        }
    }

    private int runByChoice(int choice) {
        if (choice == Constant.READ_CHOICE) {
            String filePath = UserInputUtils.getAndCheckExistFilePath("Enter file path (" + Constant.DEFAULT_FILEPATH + "): ");
            if (filePath == "back") {
                return -1;
            }

            this.readItemsFromFile = this.xmlProcess.runReadXml(filePath);
            System.out.println(readItemsFromFile.toString());
            System.out.println();
        } else if (choice == Constant.WRITE_CHOICE) {
            int numberOfItems = UserInputUtils.getIntAndCheck("Enter the number of items to add: ", 0, 1000);
            this.enteredItems = this.getItems(numberOfItems);
            String outputPath = UserInputUtils.getString("Enter output path ( " + Constant.DEFAULT_OUTPUTPATH + "): ");

            this.xmlProcess.runWriteXml(this.readItemsFromFile, this.enteredItems, outputPath);

            System.out.println();
        } else if (choice == Constant.EXIT_CHOICE) {
            System.out.println("Bye!");
            System.out.println();
            return Constant.EXIT_CHOICE;
        } else {
            System.err.print("Error when running by choice (choice = " + choice + ")");
        }

        return -1;
    }

    protected ItemMap getItems(int numberOfItems) {
        if (numberOfItems == 0) {
            return null;
        }

        ItemMap items = new ItemMap();
        for (int i = 1; i <= numberOfItems; i++) {
            System.out.println("Enter item " + i);
            int id = UserInputUtils.getIntAndCheck("Enter ID: ", 0, Integer.MAX_VALUE);
            String clientName = UserInputUtils.getString("Enter client name: ");
            Item item = new Item();
            item.setId(id);
            item.setClientName(clientName);
            DetailMap details = getDetailsEachItem(item);
            item.setDetails(details);

            items.getMap().put(id, item);
        }

        return items;
    }

    protected DetailMap getDetailsEachItem(Item item) {
        int numberOfDetails = UserInputUtils.getIntAndCheck("Enter the number of details (item id = " + item.getIdAsString() + "): ", 0, Integer.MAX_VALUE);

        if (numberOfDetails == 0) {
            return null;
        }

        DetailMap details = new DetailMap();

        for (int i = 1; i <= numberOfDetails; i++) {
            System.out.println("Add detail " + i);
            Detail detail = this.getDetailAndCheck(item, details);
            details.getMap().put(detail.getChildId(), detail);
        }

        return details;
    }

    private Detail getDetailAndCheck(Item item, DetailMap details) {
        boolean doesExist = false;
        int childId = -1;

        while (!doesExist) {
            childId = UserInputUtils.getIntAndCheck("Enter childId: ", 0, Integer.MAX_VALUE);

            doesExist = details.getMap().containsKey(childId);
            if (doesExist) {
                System.err.println("Detail with childId = " + childId + " does exist. Please don't enter any value in this set\nPlease try again!");
                continue;
            } else {
                break;
            }
        }

        String name = UserInputUtils.getString("Enter name: ");
        int amount = UserInputUtils.getIntAndCheck("Enter amount: ", 0, Integer.MAX_VALUE);

        Detail detail = new Detail();
        detail.setChildId(childId);
        detail.setName(name);
        detail.setAmount(amount);

        return detail;
    }
}
