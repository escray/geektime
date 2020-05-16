

public class Trie {
    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    // 存储无意义字符
    private TrieNode root = new TrieNode('/');

    // 往 Trie 树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    // 在 Trie 树中查找一个字符串
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            // 不存在 pattern
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        // 不能完全匹配，只是前缀
        if (p.isEndingChar == false) {
            return false;
        }
        // 找到 pattern
        else {
            return true;
        }
    }
}
