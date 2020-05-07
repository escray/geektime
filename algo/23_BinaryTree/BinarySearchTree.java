import javafx.util.Pair;
import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
  public static class Node {
    private int data;
    private Node left;
    private Node right;
    public Node(int data) {
      this.data = data;
    }
  }

  private Node tree;

  public Node find(int data) {
    Node p = tree;
    while (p != null) {
      if (data < p.data) {
        p = p.left;
      } else if (data > p.data) {
        p = p.right;
      } else {
        return p;
      }
    }
    return null;
  }

  public void insert(int data) {
    if (tree == null) {
      tree = new Node(data);
      return;
    }

    Node p = tree;
    while (p != null) {
      if (data > p.data) {
        if (p.right == null) {
          p.right =  new Node(data);
          return;
        }
        p = p.right;
      } else { // data < p.data
        if (p.left == null) {
          p.left = new Node(data);
          return;
        }
        p = p.left;
      }
    }
  }

  public void delete(int data) {
    // p指向要删除的节点，初始化指向根节点
    Node p = tree;
    // pp记录的是p的父节点
    Node pp = null;
    while (p != null && p.data != data) {
      pp = p;
      if (data > p.data) {
        p = p.right;
      } else {
        p = p.left;
      }
    }
    // 没有找到
    if (p == null) {
      return;
    }

    // 要删除的节点有两个子节点
    if (p.left != null && p.right != null) {
      // 查找右子树中最小节点
      Node minP = p.right;
      // minPP表示minP的父节点
      Node minPP = p;
      while (minP.left != null) {
        minPP = minP;
        minP = minP.left;
      }
      // 将minP的数据替换到p中
      p.data = minP.data;
      // 下面就变成了删除minP了
      p = minP;
      pp = minPP;
    }

    // 删除节点是叶子节点或者仅有一个子节点
    // p的子节点
    Node child;
    if (p.left != null) {
      child = p.left;
    } else if (p.right != null) {
      child = p.right;
    } else {
      child = null;
    }

    // 删除的是根节点
    if (pp == null) {
      tree = child;
    } else if (pp.left == p) {
      pp.left = child;
    } else {
      pp.right = child;
    }
  }

  public Node findMin() {
    if (tree == null) {
      return null;
    }
    Node p = tree;
    while (p.left != null) {
      p = p.left;
    }
    return p;
  }

  public Node findMax() {
    if (tree == null) {
      return null;
    }
    Node p = tree;
    while (p.right != null) {
      p = p.right;
    }
    return p;
  }

  public int maxDepth() {
    return maxDepthInternal(tree);
  }

  private int maxDepthInternal(Node node) {
    if (node == null) {
      return 0;
    }
    int left = maxDepthInternal(node.left);
    int right = maxDepthInternal(node.right);
    return 1 + Math.max(left, right);
  }

  private int maxDepthIteration(Node node) {
    Queue<Pair<Node, Integer>> stack = new LinkedList<>();

    if ( node != null) {
      stack.add(new Pair(node, 1));
    }

    int depth = 0;
    while (!stack.isEmpty()) {
      Pair<Node, Integer> current = stack.poll();
      node = current.getKey();
      int current_depth = current.getValue();
      if (node != null) {
        depth = Math.max(depth, current_depth);
        stack.add(new Pair(node.left, current_depth + 1));
        stack.add(new Pair(node.right, current_depth + 1));
      }
    }

    return depth;
  }
}
