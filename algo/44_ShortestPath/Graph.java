import java.util.LinkedList;

// 有向有权图的邻接表表示
public class Graph {
    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    // 添加一条边
    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge {
        // 边的起始顶点编号
        public int sid;
        // 边的终止顶点编号
        public int tid;
        // 权重
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    // 下面这个类是为了dijkstra实现用的
    private class Vertex {
        // 顶点编号ID
        public int id;
        // 从起始顶点到这个顶点的距离
        public int dist;

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    // 因为Java提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
    private class PriorityQueue {
        // 根据vertex.dist构建小顶堆
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
                } else if (nodes[child] == null
                        || nodes[child].dist > nodes[child+1].dist ) {
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

        int findSlot(int start) {
            int slot;
            if (this.count == nodes.length) {
                resize();
                slot = this.count;
            } else {
                for (slot = start + 1; slot < nodes.length; ++slot) {
                    if (nodes[slot] == null) {
                        break;
                    }
                }
            }
            return slot;
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

        void resize() {
            Vertex[] new_data = (Vertex[]) new Vertex[2 * nodes.length];
            System.arraycopy(nodes, 0, new_data, 0, nodes.length);
            this.nodes = new_data;
        }

        public void update(Vertex vertex) {
            for (int i = 0; i < this.nodes.length; i++) {
                if (nodes[i].id == vertex.id) {
                    nodes[i] = vertex;
                }
            }
        }

        public boolean isEmpty() {
            return this.count <= 0;
        }

        // 从顶点s到顶点t的最短路径
        public void dijkstra(int s, int t) {
            // 用来还原最短路径
            int[] predecessor = new int[this.count];
            Vertex[] vertexes = new Vertex[this.count];
            for (int i = 0; i < this.count; i++) {
                vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
            }
            // 小顶堆
            PriorityQueue queue = new PriorityQueue(this.count);
            // 标记是否进入过队列
            boolean[] inqueue = new boolean[this.count];
            vertexes[s].dist = 0;
            queue.add(vertexes[s]);
            inqueue[s] = true;
            while (!queue.isEmpty()) {
                // 取堆顶元素并删除
                Vertex minVertex = queue.poll();
                // 最短路径产生了
                if (minVertex.id == t) {
                    break;
                }
                // 取出一条minVetex相连的边
                for (int i = 0; i < adj[minVertex.id].size(); i++) {
                    // 取出一条minVetex相连的边
                    Edge e = adj[minVertex.id].get(i);
                    // minVertex-->nextVertex
                    Vertex nextVertex = vertexes[e.tid];
                    // 更新next的dist
                    if (minVertex.dist + e.w < nextVertex.dist) {
                        nextVertex.dist = minVertex.dist + e.w;
                        predecessor[nextVertex.id] = minVertex.id;
                        if (inqueue[nextVertex.id] == true) {
                            // 更新队列中的dist值
                            queue.update(nextVertex);
                        } else {
                            queue.add(nextVertex);
                            inqueue[nextVertex.id] = true;
                        }
                    }
                }
            }
            // 输出最短路径
            System.out.print(s);
            print(s, t, predecessor);
        }

        private void print(int s, int t, int[] predecessors) {
            if (s == t) {
                return;
            }
            print(s, predecessors[t], predecessors);
            System.out.print("->" + t);
        }
    }

}
