import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @param s 起始顶点
 * @author kaiser
 * @date 2020-05-03
 * <p>
 * 广度优先搜索
 * @return
 */
public class ThreeDegreeRelation {
    private int v;
    private LinkedList<Integer> adj[];

    public ThreeDegreeRelation(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public List<Integer> threeDegreeRelationBFS(int s) {
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        List<Integer> result = new ArrayList<>();
        // 记录每个顶点到起始顶点的距离
        int[] degree = new int[v];
        // 记录总共遍历的次数
        int cycleNum = 0;
        // 广度优先搜索，遍历整张图
        while (!queue.isEmpty()) {
            Integer w = queue.poll();
            // 顶点离起始顶点距离超过 3 时则退出
            if (degree[w] == 3) {
                continue;
            }
            // 遍历其关注的顶点
            for (Integer q : adj[w]) {
                // 没有访问过的顶点进行记录
                if (!visited[q]) {
                    visited[q] = true;
                    // 添加到队列
                    queue.add(q);
                    // 记录当前顶点到起始顶点的距离，查找其前驱顶点的距离
                    degree[q] = degree[w] + 1;
                    cycleNum++;
                    // 记录结果
                    result.add(q);
                }
            }
        }
        List<Integer> three = new ArrayList<>();
        List<Integer> deg = new ArrayList();
        for (int i = 1; i < degree.length; i++) {
            deg.add(degree[i]);
            if (degree[i] == 3) {
                three.add(i);
            }
        }

        System.out.println("Cycle in Three Degree Relationship by BFS is: " + cycleNum);
        System.out.println("BFS:    " + result);
        System.out.println("Degree: " + deg);

        return three;
    }

    /**
     * 深度优先搜索
     * <p>
     *
     * @param s 起始顶点
     */
    public List<Integer> threeDegreeRelationDFS(int s) {
        // 采用广度优先搜索算法
        boolean[] visited = new boolean[v];
        visited[s] = true;
        List<Integer> relations = new ArrayList<>();
        // 递归寻找顶点的关系
        int cycleNum = findDegreeRelation(visited, relations, s, 0);
        System.out.println("Cycles in Three Degree Relationship by DFS is: " + cycleNum);
        return relations;
    }

    /**
     * 递归寻找顶点关系——深度优先搜索
     *
     * @param visited
     * @param relations
     * @param p
     * @param level
     * @return
     */
    private int findDegreeRelation(boolean[] visited, List<Integer> relations, Integer p, int level) {
        if (level == 3) {
            return 1;
        }
        int cycleNum = 0;
        for (int i = 0; i < adj[i].size(); i++) {
            Integer t = adj[p].get(i);
            if (!visited[t]) {
                relations.add(t);
                visited[p] = true;
                cycleNum += findDegreeRelation(visited, relations, t, level + 1);
            }
            cycleNum++;
        }
        return cycleNum;
    }

    public static void main(String[] args) {
        ThreeDegreeRelation graph = new ThreeDegreeRelation(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        List<Integer> result = graph.threeDegreeRelationBFS(0);
        System.out.println(result);

        result = graph.threeDegreeRelationDFS(0);
        System.out.println(result);
    }
}
