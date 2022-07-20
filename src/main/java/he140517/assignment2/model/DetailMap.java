package he140517.assignment2.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dattdhe140517
 */
public class DetailMap {
    
    private Map<Integer, Detail> map;
    
    public DetailMap() {
        this.map = new HashMap<>();
    }
    
    public Map<Integer, Detail> getMap() {
        return map;
    }
    
    public void setMap(Map<Integer, Detail> map) {
        this.map = map;
    }
    
    public String toStringDetailMap() {
        StringBuffer result = new StringBuffer();
        for (Integer childId : this.map.keySet()) {
            Detail detail = this.map.get(childId);
            result.append("-> ").append(detail).append("\n");
        }
        return result.toString();
    }
    
    public String getStringAllKeys() {
        Set keys = this.map.keySet();
        if (keys.isEmpty()) {
            return "Empty!";
        }
        
        String keyStr = "";
        Iterator i = keys.iterator();
        while (i.hasNext()) {
            keyStr += (" " + String.valueOf(i.next()));
        }
        
        return keyStr;
    }
    
}
