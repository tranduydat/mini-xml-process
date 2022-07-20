package he140517.assignment2.model;

/**
 *
 * @author dattdhe140517
 */
public class Item {

    private int id;
    private String clientName;
    private DetailMap details;

    public Item() {
        this.details = new DetailMap();
    }

    public Item(int id, String clientName) {
        this();
        this.id = id;
        this.clientName = clientName;
    }

    public int getId() {
        return id;
    }

    public String getIdAsString() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public DetailMap getDetails() {
        return details;
    }

    public void setDetails(DetailMap details) {
        this.details = details;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item (id = ").append(id);
        sb.append(", clientName = ").append(clientName);
        sb.append("):\n").append(this.details);
        return sb.toString();
    }
}
