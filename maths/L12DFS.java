import java.util.HashMap;

public class L12DFS{
  /**
   * @Description: 前缀树的结点
   */
  class TreeNode {
    public char label;
    public HashMap<Character, TreeNode> sons = null;
    public String prefix = null;
    public String explanation = null;

    // 初始化结点
    public TreeNode(char l, String pre, String exp) {
      label = l;
      prefix = pre;
      explanation = exp;
      sons = new HashMap<>();
    }

    public TreeNode build(String str, String exp, String pre, TreeNode root) {
      if ("".equals(str)) {
        this.explanation = exp;
        return root;
      }
      // 处理当前字符串的第一个字母
      char c = str.toCharArray()[0];
      TreeNode found = null;

      // 如果字母结点已经存在于当前父结点之下，找出它。否则就新生成一个
      if (this.sons.containsKey(c)) {
        found = this.sons.get(c);
      } else {
        TreeNode son = new TreeNode(c, pre, "");
        this.sons.put(c, son);
        found = son;
      }

      return found.build(str.substring(1), exp, pre+str.substring(0, 1), root);
    }

    public TreeNode build(String str, String exp){
      return this.build(str, exp, "", this);
    }

    public String search(String str) {
      if ("".equals(str)) {
        return null;
      }

      TreeNode found = this;
      char[] chars = str.toCharArray();
      for (char c : chars) {
        if (!found.sons.containsKey(c)) {
          return null;
        } else {
          found = found.sons.get(c);
        }
      }

      return found.explanation;
    }
  }

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode((char)0, "", "");
    treeNode.build("hello", "你好").build("helloworld", "你好世界");
    System.out.println(treeNode.search("hello"));
  }

}
