/******************************************************************************
 *  Compilation:  javac RabinKarp.java
 *  Execution:    java RabinKarp pat txt
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  Las Vegas version of the Rabin-Karp algorithm.
 *
 *  % java RabinKarp abracadabra abacadabrabracabracadabrabrabracad
 *  pattern: abracadabra
 *  text:    abacadabrabracabracadabrabrabracad
 *  match:                 abracadabra
 *
 *  % java RabinKarp rab abacadabrabracabracadabrabrabracad
 *  pattern: rab
 *  text:    abacadabrabracabracadabrabrabracad
 *  match:           rab
 *
 *  % java RabinKarp bcara abacadabrabracabracadabrabrabracad
 *  pattern: bcara
 *  text:         abacadabrabracabracadabrabrabracad
 *
 *  %  java RabinKarp rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java RabinKarp abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/

import java.math.BigInteger;
import java.util.Random;

/**
 *  The {@code RabinKarp} class finds the first occurrence of a pattern string
 *  in a text string.
 *  <p>
 *  This implementation uses the Rabin-Karp algorithm.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/53substring">Section 5.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class RabinKarp {
    // the pattern  // needed only for Las Vegas
    private String pat;
    // pattern hash value
    private long patHash;
    // pattern length
    private int m;
    // a large prime, small enough to avoid long overflow
    private long q;
    // radix
    private int R;
    // R^(M-1) % Q
    private long RM;

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R       the alphabet size
     */
    public RabinKarp(char[] pattern, int R) {
        this.pat = String.valueOf(pattern);
        this.R = R;
        throw new UnsupportedOperationException("Operation not supported yet");
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public RabinKarp(String pat) {
        // save pattern (needed only for Las Vegas)
        this.pat = pat;
        R = 256;
        m = pat.length();
        q = longRandomPrime();

        // precompute R^(m-1) % q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }
        patHash = hash(pat, m);
    }

    // Compute hash for key[0..m-1].
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    // Monte Carlo version: always return true
    // private boolean check(int i) {
    //    return true;
    //}

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; n if no such match
     */
    public int search(String txt) {
        int n = txt.length();
        if (n < m) {
            return n;
        }
        long txtHash = hash(txt, m);
        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0)) {
            return 0;
        }

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + q - RM*txt.charAt(i-m) % q) % q;
            txtHash = (txtHash * R + txt.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset)) {
                return offset;
            }
        }
        // no match
        return n;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static int RK(String text, String pattern) {
        int m = text.length();
        int n = patter.length();
        int[] hash = new int[m - n + 1];
        int[] table = new int[26];
        char[] txt = text.toCharArray();
        char[] pat = text.toCharArray();
        int s = 1;
        //将26的次方存储在一个表里，取的时候直接用,虽然溢出，但没啥问题
        for (int j = 0; j < 26; j++) {
            table[j] = s;
            s*=26;
        }
        for (int i = 0; i <= m - n; i++) {
            s = 0;
            for (int j = 0; j < n; j++) {
                s += (txt[i + j] - 'a') * table[n - 1 - j];
            }
            hash[i] = s;
        }
        s = 0;
        for (int j = 0; j < n; j++) {
            s += (pat[j] - 'a') * table[n - 1 - j];
        }
        for (int j = 0; j < m - n + 1; j++) {
            if (hash[j] == s) {
                return j;
            }
        }
        return -1;
    }

    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];

        RabinKarp search = new RabinKarp(pat);
        int offset = search.search(txt);

        // print result
        System.out.println("text:    " + txt);

        // from brute force search method
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
    }
}
