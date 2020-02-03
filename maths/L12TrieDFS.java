import java.util.HashMap;
import java.util.Stack;
import java.util.Iterator;

/**
* @Description: 前缀树的结点
*
*/
class TreeNode {
  // 结点的名称，在前缀树里是单个字母
  public char label;
  // 使用哈希映射存放子结点。哈希便于确认是否已经添加过某个字母对应的结点。
  public HashMap<Character, TreeNode> sons = null;
  // 从树的根到当前结点这条通路上，全部字母所组成的前缀。
  // 例如通路 b->o->y，对于字母 o 结点而言，前缀是 b；对于字母 y 结点而言，前缀是 bo
  public String prefix = null;
  // 词条的解释
  public String explanation = null;

  public TreeNode(char l, String pre, String exp) {
    label = l;
    prefix = pre;
    explanation = exp;
    sons = new HashMap<>();
  }
}

public class L12TrieDFS {

  // 使用栈来实现深度优先搜索
  public void dfsByStack(TreeNode root) {
    // 创建堆栈对象，其中每个元素都是 TreeNode 类型
    Stack<TreeNode> stack = new Stack<TreeNode>();
    // 初始化的时候，压入根结点
    stack.push(root);
    // 只要栈里还有结点，就继续下去
    while(!stack.isEmpty()) {
      // 弹出栈顶的结点
      TreeNode node = stack.pop();
      // 已经到达叶子结点了，输出
      if (node.sons.size() == 0) {
        System.out.println(node.prefix + node.label);
      } else {
        // 非叶子结点，遍历它的每个子结点
        Iterator<Entry<Chatacter, TreeNode>> iter = node.sons.entrySet().iterator();
        // 注意，这里使用了一个临时的栈 stackTemp
        // 这样做是为了保持遍历的顺序，和递归遍历的顺序是一致的
         // 如果不要求一致，可以直接压入 stack
        Stack<TreeNode> stackTemp = new Stack<TreeNode>();
        while(iter.hasNext()){
          stackTemp.push(iter.next().getValue());
        }
        while(!stackTemp.isEmpty()) {
          stack.push(stackTemp.pop());
        }
      }
    }
  }

  public static void main(String[] args){

  }
}
/*
// 处理当前字符串的第一个字母
char c = str.toCharArray()[0];
TreeNode found = null;

if (parant.sons.containsKey(c)) {
  found = parent.sons.get(c);
} else {
  TreeNode son = new TreeNode(c, pre, "");
  parent.sons.put(c, son);
  found = son;
}
*/
