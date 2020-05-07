import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedHashMapExample {
  public static void main(String[] args) {
    HashMap<Integer, Integer> m = new LinkedHashMap<>();
    m.put(3,11);
    m.put(1,12);
    m.put(5,23);
    m.put(2,22);

    printMap(m);

    HashMap<Integer, Integer> n = new LinkedHashMap<>(10, 0.75f, true);
    n.put(3,11);
    n.put(1,12);
    n.put(5,23);
    n.put(2,22);
    n.put(3,26);
    printMap(n);
    n.get(5);
    printMap(n);
  }

  private static void printMap(HashMap<Integer, Integer> map) {
    for(Map.Entry e : map.entrySet()){
      System.out.println(e.getKey());
    }
  }
}
