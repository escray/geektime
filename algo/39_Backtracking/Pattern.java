public class Pattern {
    private boolean matched = false;
    // 正则表达式
    private char[] pattern;
    // 正则表达式长度
    private int plen;

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    // 文本串及长度
    public boolean match(char[] text, int tlen) {
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

    private void rmatch(int ti, int pj, char[] text, int tlen) {
        // 如果已经匹配了，就不要继续递归了
        if (matched) {
            return;
        }
        // 正则表达式到结尾了
        if (pj == plen) {
            // 文本串也到结尾了
            if (ti == tlen) {
                matched = true;
            }
            return;
        }
        // * 匹配任意个字符
        if (pattern[pj] == '*') {
            for (int k = 0; k < tlen - ti; k++) {
                rmatch(ti + k, pj + 1, text, tlen);
            }
        }
        // ? 匹配 0 个或者 1 个字符
        else if (pattern[pj] == '?') {
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        }
        // 纯字符匹配才行
        else if (ti < tlen && pattern[pj] == text[ti]) {
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }
}
