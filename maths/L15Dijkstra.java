import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class L15Dijkstra {
  // 定义节点
  public static class Node {
    public String label;
    public List<Node> children;
    public Map<String, Double> weights;
    public Node(String label) {
      this.label = label;
    }
  }

  // 初始化
  public static Node init() {
    Node start = new Node("s");
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    Node d = new Node("d");
    Node e = new Node("e");
    Node f = new Node("f");
    Node g = new Node("g");
    Node h = new Node("h");

    List<Node> children = new ArrayList<>();
    children.add(a);
    children.add(b);
    children.add(c);
    children.add(d);

    Map<String, Double> weights = new HashMap<>();
    weights.put("a", 0.5);
    weights.put("b", 0.3);
    weights.put("c", 0.2);
    weights.put("d", 0.4);

    start.children = children;
    start.weights = weights;

    children = new ArrayList<>();
    children.add(e);
    weights = new HashMap<>();
    weights.put("e", 0.3);
    a.children = children;
    a.weights = weights;

    children = new ArrayList<>();
    children.add(a);
    children.add(f);
    weights = new HashMap<>();
    weights.put("a", 0.2);
    weights.put("f", 0.1);
    b.children = children;
    b.weights = weights;

    children = new ArrayList<>();
    children.add(f);
    children.add(h);
    weights = new HashMap<>();
    weights.put("f", 0.4);
    weights.put("h", 0.8);
    c.children = children;
    c.weights = weights;

    children =  new ArrayList<>();
    children.add(c);
    children.add(h);
    weights = new HashMap<>();
    weights.put("c", 0.1);
    weights.put("h", 0.6);
    d.children = children;
    d.weights = weights;

    children = new ArrayList<>();
    children.add(g);
    weights = new HashMap<>();
    weights.put("g", 0.1);
    e.children = children;
    e.weights = weights;

    children = new ArrayList<>();
    children.add(e);
    children.add(h);
    weights = new HashMap<>();
    weights.put("e", 0.1);
    weights.put("h", 0.2);
    f.children = children;
    f.weights = weights;

    children = new ArrayList<>();
    children.add(g);
    weights = new HashMap<>();
    weights.put("g", 0.4);
    h.children = children;
    h.weights = weights;

    return start;
  }

  // 获取最小权重
  public static String findGeoWithMinWeight(Map<String, Double> mw) {
    double min = Double.MAX_VALUE;
    String label = "";
    for (Map.Entry<String, Double> entry : mw.entrySet()) {
      if (entry.getValue() < min) {
        min = entry.getValue();
        label = entry.getKey();
      }
    }
    return label;
  }

  // 更新权重
  public static void updateWeight(String key, Double value, Map<String, Double> result_mw) {
    if (result_mw.containsKey(key)) {
      if (value < result_mw.get(key)) {
        result_mw.put(key, value);
      }
    } else {
      result_mw.put(key, value);
    }
  }

  // 获取最小节点
  public static Node getMinNode(List<Node> l, String label) {
    for (Node node : l) {
      if (label.equals(node.label)) {
        return node;
      }
    }
    return null;
  }

  // 执行测试
  public static void main(String[] args){
    Node tree = init();
    Map<String, Double> mw = new HashMap<>();
    Map<String, Double> result_mw = new HashMap<>();

    List<Node> children = tree.children;
    Map<String, Double> weights = tree.weights;
    for (Map.Entry<String, Double> entry : weights.entrySet()) {
      mw.put(entry.getKey(), entry.getValue());
    }

    while (mw.size()!=0) {
      String label = findGeoWithMinWeight(mw);
      updateWeight(label, mw.get(label), result_mw);
      Node min = getMinNode(children, label);
      System.out.println("获取最小值：" + label);
      List<Node> nodes = min.children;
      if (nodes!= null && nodes.size() > 0) {
        children.addAll(nodes);
        for (Node node : nodes ) {
          mw.put(node.label, BigDecimal.valueOf(result_mw.get(label)).add(BigDecimal.valueOf(min.weights.get(node.label))).doubleValue());
        }
      }
      mw.remove(label);
    }
    System.out.println(result_mw);
  }
}
