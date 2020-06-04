import java.util.Arrays;
import java.util.LinkedList;

public class AStarSearch {
    private class Vertex {
        // 顶点编号ID
        public int id;
        // 从起始顶点，到这个顶点的距离，也就是g(i)
        public int dist;
        // 新增：f(i)=g(i)+h(i)
        public int f;
        // 新增：顶点在地图中的坐标（x, y）
        public int x, y;

        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }
    }

    private class Edge {
        public int sid;
        public int tid;
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            // 权重
            this.w = w;
        }
    }

    public AStarSearch(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    private class PriorityQueue {
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.count = v;
        }

        public Vertex poll() {
            if (this.count == 0) {
                return null;
            }
            Vertex result = nodes[0];
            remove(0);
            return result;
        }

        void remove(int index) {
            while (nodes[index] != null) {
                int child = 2 * index + 1;
                if (child >= nodes.length) {
                    nodes[index] = null;
                    break;
                }
                if (child + 1 >= nodes.length || nodes[child + 1] == null) {
                    // nothing
                } else if (nodes[child] == null || nodes[child].dist > nodes[child + 1].dist) {
                    ++child;
                    nodes[index] = nodes[child];
                    index = child;
                    --this.count;
                }
            }
        }

        public void add(Vertex vertex) {
            if (vertex == null) {
                throw new NullPointerException();
            }
            int slot = findSlot(-1);
            nodes[slot] = vertex;
            this.count++;
            bubbleUp(slot);
        }

        public void update(Vertex vertex) {
            for (int i = 0; i < this.nodes.length; i++) {
                if (nodes[i].id == vertex.id) {
                    nodes[i] = vertex;
                }
            }
        }

        public void clear() {
            Arrays.fill(nodes, null);
            count = 0;
        }

        public boolean isEmpty() {
            return this.count <= 0;
        }

        int findSlot(int start) {
            int slot;
            if (this.count == nodes.length) {
                resize();
                slot = this.count;
            } else {
                for (slot = start + 1; slot < nodes.length; slot++) {
                    if (nodes[slot] == null) {
                        break;
                    }
                }
            }
            return slot;
        }

        void resize() {
            Vertex[] new_data = (Vertex[]) new Vertex[2 * nodes.length];
            System.arraycopy(nodes, 0, new_data, 0, nodes.length);
            this.nodes = new_data;
        }

        void bubbleUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (nodes[parent].dist <= nodes[index].dist) {
                    break;
                }
                Vertex temp = nodes[index];
                nodes[index] = nodes[parent];
                nodes[parent] = temp;
                index = parent;
            }
        }
    }

    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;

    Vertex[] vertexes = new Vertex[this.v];

    public void addVertex(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }

    // Vertex表示顶点，后面有定义
    int hManhattan(Vertex v1, Vertex v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

    private void print(int s, int t, int[] predecessors) {
        if (s == t) {
            return;
        }
        print(s, predecessors[t], predecessors);
        System.out.print("-->" + t);
    }

    // 从顶点s到顶点t的路径
    public void astar(int s, int t) {
        // 用来还原路径
        int[] predecessor = new int[this.v];
        // 按照vertex的f值构建的小顶堆，而不是按照dist
        PriorityQueue queue = new PriorityQueue(this.v);
        // 标记是否进入过队列
        boolean[] inqueue = new boolean[this.v];
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            // 取堆顶元素并删除
            Vertex minVertex = (Vertex)queue.poll();
            for (int i = 0; i < adj[minVertex.id].size(); i++) {
                // 取出一条minVetex相连的边
                Edge e = adj[minVertex.id].get(i);
                // minVertex-->nextVertex
                Vertex nextVertex = vertexes[e.tid];
                // 更新next的dist,f
                if (minVertex.dist + e.w < nextVertex.dist) {
                    nextVertex.dist = minVertex.dist + e.w;
                    nextVertex.f = minVertex.dist + hManhattan(nextVertex, vertexes[t]);
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
                // 只要到达t就可以结束while了
                if (nextVertex.id == t) {
                    // 清空queue，才能推出while循环
                    queue.clear();
                    break;
                }
            }
        }
        // 输出路径
        System.out.print(s);
        // print函数请参看Dijkstra算法的实现
        print(s, t, predecessor);
    }
}

