# Autocomplete

An autocomplete engine in Java: given a list of weighted terms (for example cities weighted by population),
it returns every term that starts with a typed prefix, ranked by weight. Written for a data structures and
algorithms course.

## How it works

```
Load N (weight, term) pairs → sort lexicographically            O(N log N), once
Prefix query:
  binary search for the first and last term with that prefix    O(log N)
  copy the m matches and sort them by descending weight         O(m log m)
  print the top k
```

## Files (`Autocomplete/`)

| File | Role |
|---|---|
| `Term.java` | A (query, weight) pair with three orderings: lexicographic, by reverse weight, and by the first `r` characters (prefix order) |
| `BinarySearchDeluxe.java` | Generic `firstIndexOf` / `lastIndexOf`: binary searches that return the first or last index of a key under any `Comparator` |
| `Autocomplete.java` | `allMatches(prefix)` and `numberOfMatches(prefix)`; the `main` program loads a term file and answers prefixes typed on standard input |

## Run

Uses the course library (`stdlib`). The input file starts with the number of terms, then one `weight<TAB>term` per line.

```bash
javac Term.java BinarySearchDeluxe.java Autocomplete.java
java Autocomplete cities.txt 5      # then type a prefix, e.g. "New", to see the top 5 matches by weight
```

## Concepts

Binary search · comparators and generics · sorting · prefix matching
