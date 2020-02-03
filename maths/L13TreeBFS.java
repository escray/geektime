import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Node {
  // 结点的名称，这里使用用户 id
  public int user_id;
  // 使用哈希映射存放相连的朋友结点。哈希便于确认和某个用户是否相连。
  public HashSet<Integer> friends = null;
  // 用于存放和给定的用户结点，是几度好友
  public int degree;

  // 初始化结点
  public Node(int id) {
    user_id = id;
    friends = new HashSet<>();
    degree = 0;
  }
}

public class L13TreeBFS {

  public static Node[] init(int user_num, int relation_num) {
    Random rand = new Random();
    Node[] user_nodes = new Node[user_num];

    // 生成所有表示用户的节点
    for (int i = 0; i < user_num; i++) {
      user_nodes[i] = new Node(i);
    }

    // 生成所有表示好友关系的边
    for (int i = 0; i < relation_num; i++) {
      int friend_a_id = rand.nextInt(user_num);
      int friend_b_id = rand.nextInt(user_num);
      if (friend_a_id == friend_b_id) {
        continue;
      }

      Node friend_a = user_nodes[friend_a_id];
      Node friend_b = user_nodes[friend_b_id];

      friend_a.friends.add(friend_b_id);
      friend_b.friends.add(friend_a_id);
    }
    return user_nodes;
  }

  /**
  * @Description:    通过广度优先搜索，查找好友
  * @param user_nodes- 用户的结点；user_id- 给定的用户 ID，我们要为这个用户查找好友
  * @return void
  */
  public static void bfs(Node[] user_nodes, int user_id) {
    // 防止数组越界异常
    if (user_id > user_nodes.length) {
      return;
    }

    // 用于广度优先搜索的队列
    Queue<Integer> queue = new LinkedList<Integer>();
    // 放入初始节点
    queue.offer(user_id);
    // 存放已经被访问过的节点，防止回路
    HashSet<Integer> visited = new HashSet<>();
    visited.add(user_id);

    while(!queue.isEmpty()) {
      // 取出队列头部的第一个节点
      int current_user_id = queue.poll();
      if (user_nodes[current_user_id] == null) {
        continue;
      }
      // 遍历刚刚拿到的这个节点的所有直接连接节点，并加入队列尾部
      for (int friend_id : user_nodes[current_user_id].friends) {
        if (user_nodes[friend_id] == null) {
          continue;
        }
        if (visited.contains(friend_id)) {
          continue;
        }
        queue.offer(friend_id);
        // 记录已经访问过的节点
        visited.add(friend_id);
        // 好友度数是当前节点的好友度数再加1
        user_nodes[friend_id].degree = user_nodes[current_user_id].degree + 1;
        System.out.println(String.format("\t%d 度好友：%d", user_nodes[friend_id].degree, friend_id));
      }
    }
  }

  public static void main(String[] args){
    Node[] user_nodes = init(5, 8);
    for (Node d : user_nodes) {
      System.out.println(d.user_id + ":" + d.friends + ":" + d.degree);
    }
    bfs(user_nodes, 0);
  }
}
