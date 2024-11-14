import java.util.Arrays;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    private Term[] terms;  // terms

    // Constructs an autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException("terms is null");  // term is null
        }
        this.terms = new Term[terms.length];  // copy of term
        for (int i = 0; i < terms.length; i++) {
            this.terms[i] = terms[i];
        }
        Arrays.sort(this.terms);  // sort this term
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("prefix is null");  // prefix is null
        }
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));  // first index prefix
        if (firstIndex == -1) {
            return new Term[0];  // return empty if no matches found
        }
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));  // last index prefix
        int n = lastIndex - firstIndex + 1;  // calculate
        Term[] matches = new Term[n];  // create new array
        for (int i = 0; i < n; i++) {  // copy
            matches[i] = terms[firstIndex + i];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());  // return reverse order
        return matches;  // return
    }


    // Returns the number of terms that start with prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("prefix is null");  // prefix is null
        }
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));
        if (firstIndex == -1) {
            return 0;  // return 0 if not found
        }
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));
        return lastIndex - firstIndex + 1;  // calculate
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
        Autocomplete autocomplete = new Autocomplete(terms);
        StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            String msg = " matches for \"" + prefix + "\", in descending order by weight:";
            if (results.length == 0) {
                msg = "No matches";
            } else if (results.length > k) {
                msg = "First " + k + msg;
            } else {
                msg = "All" + msg;
            }
            StdOut.printf("%s\n", msg);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println("  " + results[i]);
            }
            StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        }
    }
}
