class Node:
    def __init__(self, alpha, pre, exp):
        self.alpha = alpha
        self.prefix = pre
        self.explanation = exp
        self.sons = dict()

    def find(self, alpha):
        if self.sons.get(alpha) == None:
            return False
        else:
            return True

    def create(self, alpha, son):
        self.sons[alpha] = son
        self.sons = {key:self.sons[key] for key in sorted(set(self.sons))}
        return True

    def get(self, alpha):
        return self.sons[alpha]

class DictTree:
    def __init__(self, words, alpha=''):
        self.root = Node(alpha, '', '')
        self.build(words)

    def build(self, words):
        for word in words:
            self.add(word)

    def add(self, word):
        cur_node = self.root
        for i in word:
            i = i.lower()
            pre = cur_node.prefix + cur_node.alpha

            if not cur_node.find(i):
                cur_node.create(i, Node(i, pre, ''))

            cur_node = cur_node.get(i)

        cur_node.explanation = "this is a pyeudo explanation of '%s'" % word

    def DFS_search(self, word):
        word = word.lower()
        cur_node = self.root
        i = 0
        while True:
            if i = len(word):
                if cur_node.explanation != '':
                    return cur_node.explanation
                else:
                    return "the word '%s' is not in this dictionary." % word

            if cur_node.find(word[i]):
                cur_node = cur_node.get(word[i])
                i += 1
                continue
            else:
                return "The word '%s' is not in this dictionary." % word
