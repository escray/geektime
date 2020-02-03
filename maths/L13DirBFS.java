import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class L13DirBFS {
  public String name;
  public HashSet<L13DirBFS> children;
  public String parent;

  public String getPath() {
    if (name == null) {
      return null;
    }
    if (parent == null) {
      return this.name;
    }
    return this.parent.concat("/").concat(this.name);
  }

  public L13DirBFS(String name, String parent) {
    this.name = name;
    this.parent = parent;
    this.children = new HashSet<L13DirBFS>();
  }

  public static void addFile(L13DirBFS file, String path) {
    String[] arr = path.split("/");
    boolean exist = false;
    L13DirBFS newFile = null;
    for (L13DirBFS files : file.children) {
      if (files.name.equals(arr[0])) {
        exist = true;
        newFile = files;
      }
    }
    if (!exist) {
      newFile = new L13DirBFS(arr[0], file.getPath());
      file.children.add(newFile);
    }
    if (arr.length > 1) {
      addFile(newFile, path.substring(path.indexOf("/") + 1));
    }
  }

  public static void bfs(L13DirBFS file) {
    Queue<L13DirBFS> queue = new LinkedList<L13DirBFS>();
    queue.offer(file);
    while(!queue.isEmpty()) {
      L13DirBFS iterator = queue.poll();
      System.out.println(iterator.getPath());
      for (L13DirBFS child : iterator.children ) {
        queue.offer(child);
      }
    }
  }

  public static void dfs(L13DirBFS file) {
    Stack<L13DirBFS> stack = new Stack<L13DirBFS>();
    stack.push(file);
    while(!stack.isEmpty()) {
      L13DirBFS iterator = stack.pop();
      System.out.println(iterator.getPath());
      for (L13DirBFS child : iterator.children) {
        stack.push(child);
      }
    }
  }

  public static void main(String[] args){
    L13DirBFS file = new L13DirBFS(null, null);
    addFile(file, "d:/a/b/c");
    addFile(file, "d:/a/b/c");
    addFile(file, "d:/a/b/d");
    addFile(file, "d:/a/b2/d");
    addFile(file, "d:/a/b3/d");
    addFile(file, "d:/b/b3/d");
    System.out.println("Breadth First Search");
    bfs(file);
    System.out.println("Width First Search");
    dfs(file);
  }
}
