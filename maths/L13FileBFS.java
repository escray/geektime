import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class File {
  public String name;
  public HashSet<File> children;
  public String parent;

  public String getPath(){
    if (name == null) {
      return null;
    }
    if (parent == null) {
      return this.name;
    }
    return this.parent.concat("/").concat(this.name);
  }

  public File(String name, String parent) {
    this.name = name;
    this.parent = parent;
    this.children = new HashSet<File>();
  }

  public void addFile(File file, String path) {
    String[] arr = path.split("/");
    boolean exist = false;
    File newFile = null;
    for (File files : file.children) {
      if (files.name.equals(arr[0])) {
        exist = true;
        newFile =  file;
      }
    }
    if (!exist) {
      newFile = new File(arr[0], file.getPath());
      file.children.add(newFile);
    }
    if (arr.length > 1) {
      addFile(newFile, path.substring(path.indexOf("/")+1));
    }
  }

  public void widthSearch(File file) {
    Queue<File> queue = new LinkedList<File>();
    queue.offer(file);
    while(!queue.isEmpty()) {
      File iterator = queue.poll();
      System.out.println(iterator.getPath());
      for (File child : iterator.children) {
        queue.offer(child);
      }
    }
  }

  public void depthSearch(File file) {
    Stack<File> stack = new Stack<File>();
    stack.push(file);
    while(!stack.isEmpty()) {
      File iterator = stack.pop();
      System.out.println(iterator.getPath());
      for (File child : iterator.children) {
        stack.push(child);
      }
    }
  }
}

public class L13FileBFS {
  public static void main(String[] args) {
    File file = new File(null, null);
    file.addFile(file, "d:/a/b/b");
    file.addFile(file, "d:/a/b/c");
    file.addFile(file, "d:/a/b/d");
    file.addFile(file, "d:/a/b2/d");
    file.addFile(file, "d:/a/b3/d");
    file.addFile(file, "d:/a/b3/d");
    file.widthSearch(file);
  }
}
