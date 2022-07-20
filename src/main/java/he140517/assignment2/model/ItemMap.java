package he140517.assignment2.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dattdhe140517
 */
public class ItemMap {

    private Map<Integer, Item> map;

    public ItemMap() {
        this.map = new HashMap<>();
    }

    public Map<Integer, Item> getMap() {
        return map;
    }

    public List<Item> getList() {
        List<Item> items = new LinkedList<>();

        for (int i = this.map.size() - 1; i >= 0; i--) {
            Item item = this.map.get(i);
            items.add(item);
        }

        return items;
    }

    public void setMap(Map<Integer, Item> map) {
        this.map = map;
    }

    public String toStringById(Integer id) {
        Item item = this.map.get(id);
        if (item != null) {
            StringBuffer result = new StringBuffer();
            result.append("+ Item (id = ").append(id).append("):\n").append("Details:\n");
            result.append(item.getDetails().toStringDetailMap());
            return result.toString();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        for (Integer id : this.map.keySet()) {
            result.append(this.toStringById(id));
        }
        return result.toString();
    }

}
