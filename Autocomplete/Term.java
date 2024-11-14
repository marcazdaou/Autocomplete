import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    private String query; // Instance variable for the query string.
    private long weight; // Instance variable for the query weight.

    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null) {
            throw new NullPointerException("query is null");  // query is null
        }
        this.query = query;
        this.weight = 0;  // 0
    }

    // Constructs a term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new NullPointerException("query is null");  // query is null
        }
        if (weight < 0) {  // weight less 0
            throw new IllegalArgumentException("Illegal weight");  // illegal weight
        }
        this.query = query;
        this.weight = weight;
    }

    // Returns a string representation of this term.
    public String toString() {
        return weight + "\t" + query;  // string
    }

    // Returns a comparison of this term and other by query.
    public int compareTo(Term other) {
        return query.compareTo(other.query);  // return + 0 - int
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();  // reverse weight
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Illegal r");  // illegal r
        }
        return new PrefixOrder(r);  // return
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of terms v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
            return Long.compare(w.weight, v.weight);  // return - 0 + int
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int r; // Instance variable for prefix length.

        // Constructs a new prefix order given the prefix length.
        PrefixOrder(int r) {
            this.r = r;
        }

        // Returns a comparison of terms v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
            // a is a substring of v of length min(r, v.query.length())
            // and b is a substring of w of length min(r, w.query.length()).
            String a = v.query.substring(0, Math.min(r, v.query.length()));
            String b = w.query.substring(0, Math.min(r, w.query.length()));
            return a.compareTo(b);
        }
    }


    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
