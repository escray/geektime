import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Node {
  // 结点的名称，这里使用用户 id
  public int user_id;
  // 使用哈希映射存放相连的朋友结点。哈希便于确认和某个用户是否相连。
  public HashSet<Integer> friends = null;
  // 存放从不同用户出发，当前用户结点是第几度
  public HashMap<Integer, Integer> degrees;
  // 用于存放和给定的用户结点，是几度好友
  public int degree;

  // 初始化结点
  public Node(int id) {
    user_id = id;
    friends = new HashSet<>();
    degrees = new HashMap<>();
    degrees.put(id, 0);
    degree = 0;
  }
}

public class L14DoubleBFS {
  /**
  * @Description:    通过双向广度优先搜索，查找两人之间最短通路的长度
  * @param user_nodes- 用户的结点；user_id_a- 用户 a 的 ID；user_id_b- 用户 b 的 ID
  * @return void
  */
  public static int bi_bfs(Node[] user_nodes, int user_id_a, int user_id_b) {
    // 防止数组越界的异常
    if (user_id_a > user_nodes.length || user_id_b > user_nodes.length) {
      return -1;
    }
    // 两个用户是同一人，直接返回 0
    if (user_id_a == user_id_b) {
      return 0;
    }

    // 队列 a，用于从用户 a 出发的广度优先搜索
    Queue<Integer> queue_a = new LinkedList<Integer>();
    // 队列 b，用于从用户 b 出发的广度优先搜索
    Queue<Integer> queue_b = new LinkedList<Integer>();
    // 放入初始结点
    queue_a.offer(user_id_a);
    // 存放已经被访问过的结点，防止回路
    HashSet<Integer> visited_a = new HashSet<>();
    visited_a.add(user_id_a);

    // 放入初始结点
    queue_b.offer(user_id_b);
    // 存放已经被访问过的结点，防止回路
    HashSet<Integer> visited_b = new HashSet<>();
    visited_b.add(user_id_b);

    // max_degree 的设置，防止两者之间不存在通路的情况
    int degree_a = 0, degree_b = 0, max_degree = 20;
    while((degree_a + degree_b) < max_degree) {
      // 沿着 a 出发的方向，继续广度优先搜索 degree + 1 的好友
      degree_a++;
      getNextDegreeFriend(user_id_a, user_nodes, queue_a, visited_a, degree_a);
      // 判断到目前为止，被发现的 a 的好友，和被发现的 b 的好友，两个集合是否存在交集
      if (hasOverlap(visited_a, visited_b)) {
        return (degree_a + degree_b);
      }
      // 沿着 b 出发的方向，继续广度优先搜索 degree + 1 的好友
      degree_b++;
      getNextDegreeFriend(user_id_b, user_nodes, queue_b, visited_b, degree_b);
      // 判断到目前为止，被发现的 a 的好友，和被发现的 b 的好友，两个集合是否存在交集
      if (hasOverlap(visited_a, visited_b)) {
        return (degree_a + degree_b);
      }
    }
    // 广度优先搜索超过 max_degree 之后，仍然没有发现 a 和 b 的重叠，认为没有通路
    return -1;
  }

  // 判断两个集合是不是有交集
  public static boolean hasOverlap(HashSet<Integer> visited_a, HashSet<Integer> visited_b) {
    if (visited_a.isEmpty() || visited_b.isEmpty()) {
      return false;
    }
    for (int user_id_a : visited_a) {
      if (visited_b.contains(user_id_a)) {
        return true;
      }
    }
    return false;
  }

  // 根据给定的队列，查找和起始点相距度数为指定值的所有好友
  public static void getNextDegreeFriend(int user_id_a, Node[] user_nodes, Queue<Integer> queue_a, HashSet<Integer> visited_a, int degree_a) {
    if (user_nodes[user_id_a] == null) {
      return;
    }
    Node current_node = user_nodes[user_id_a];
    HashSet<Integer> friends = current_node.friends;
    if (friends.isEmpty()) {
      return;
    }
    HashMap<Integer, Integer> degrees = current_node.degrees;
    for (int f_user_id : friends) {
      queue_a.offer(f_user_id);
      visited_a.add(f_user_id);
      degrees.put(f_user_id, degree_a + 1);
    }
  }

  // 初始化节点数组
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

  public static void main(String[] args){
    Node[] user_nodes = init(5, 8);
    for (Node d : user_nodes) {
      System.out.println(d.user_id + ":" + d.friends + ":" + d.degrees);
    }
    int len = bi_bfs(user_nodes, 0, 1);
    System.out.println("distance: " + len);
  }

}
