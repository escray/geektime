import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
public class BST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    // search method
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.val;
            } else if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            }
        }
        return null;
    }

    public Value get(Node x, Key key){
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    // insert method
    public void put(Key key, Value val) {
        root = insert(root, key, val);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = insert(x.left, key, value);
        } else if (cmp > 0) {
            x.right = insert(x.right, key, value);
        } else {
            x.val = value;
        }
        return x;
    }

    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Key> {
        private Stack<Node> stack = new Stack<Node>();

        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        BSTIterator() {
            pushLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public void remove() {

        }

        public Key next() {
            Node x = stack.pop();
            pushLeft(x.right);
            return x.key;
        }
    }


    // recursive insert() implementation for elementary BSTs
    private Node insertBST(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(h.key);
        if (cmp == 0) {
            // associative model (no duplicate keys)
            h.val = val;
        } else if (cmp < 0) {
            h.left = insert(h.left, key, val);
        } else {
            h.right = insert(h.right, key, val);
        }
        return h;
    }

    // insert for llrb trees
    private Node insert(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED);
        }

        if (isRed(h.left) && isRed(h.right)) {
            colorFlip(h);
        }

        int cmp = key.compareTo(h.key);
        if (cmp == 0) {
            h.val = val;
        } else if (cmp < 0) {
            h.left = insert(h.left, key, val);
        } else {
            h.right = insert(h.right, key, val);
        }

        if (isRed(h.right)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            rotateRight(h);
        }

        return h;
    }

    // insert for 2-3 trees
    private Node insert23Trees(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp == 0) {
            h.val = val;
        } else if (cmp < 0) {
            h.left = insert(h.left, key, val);
        } else {
            h.right = insert(h.right, key, val);
        }

        if (isRed(h.right)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            colorFlip(h);
        }

        return h;
    }


    // find the minimum key
    public Key min() {
        Node x = root;
        while (x != null) {
            x = x.left;
        }
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    // helper method to test node color
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return (x.color == RED);
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }

    private Node colorFlip(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node h) {
        // rotate-left right-leaning reds
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        // rotate-right red-red pairs
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // split 4-nodes
        if (isRed(h.left) && isRed(h.right)) {
            colorFlip(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        colorFlip(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            colorFlip(h);
        }
        return h;
    }

    private Node moveRedLeft(Node h) {
        colorFlip(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            colorFlip(h);
        }
        return h;
    }

    private Node deleteMax(Node h) {
        // lean 3-nodes to the right
        if (isRed(h.left)) {
            h = rotateRight(h);
        }

        // remove node on bottom level
        // h must be RED by invariant
        if (h.right == null) {
            return null;
        }

        // borrow from sibling if necessary
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }

        // move down one level
        h.left = deleteMax(h.left);

        // fix right-leaning red links
        // and eliminate 4-nodes on the way up
        return fixUp(h);
    }

    public void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        // remove node on bottom level
        // h must be RED by invariant
        if (h.left == null) {
            return null;
        }

        // push red link down if necessary
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        // move down one level
        h.left = deleteMin(h.left);

        // fix right-leaning red links
        // and eleminate 4-nodes on the way up
        return fixUp(h);
    }

    public void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }
}

