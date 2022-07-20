package he140517.assignment2.model;

/**
 *
 * @author dattdhe140517
 */
public class Detail {

    private String name;
    private int amount;
    private int childId;

    public Detail() {
    }

    public Detail(int childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public String getAmountAsString() {
        return String.valueOf(this.amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getChildId() {
        return childId;
    }

    public String getChildIdAsString() {
        return String.valueOf(this.childId);
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Child ID = ").append(childId);
        sb.append(": Name = ").append(name);
        sb.append(", Amount = ").append(amount);
        return sb.toString();
    }
}
