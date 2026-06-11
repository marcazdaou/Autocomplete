# Autocomplete System

A high-performance autocomplete engine in **Java** that processes **10,000+ query terms** with **O(log N)** prefix search using binary search and weight-based ranking.

## How It Works

```
User types prefix "New"
       ↓
Binary search → locate first & last matching term in sorted array   O(log N)
       ↓
Extract matches → slice subarray of candidates
       ↓
Rank by weight descending → return top-k results                    O(k log k)
       ↓
"New York City (8,175,133)"  "Newark (277,140)"  "New Haven (129,779)"
```

## Features

- Handles datasets with **10,000+ weighted terms**
- **O(log N)** prefix lookup — scales to arbitrarily large datasets
- **Weight-based ranking** — highest-weight results appear first
- Custom `Comparator` classes for flexible sort ordering
- Works with any weighted dataset (cities, words, web queries, products)

## Complexity

| Operation | Time Complexity |
|---|---|
| Load & sort terms | O(N log N) |
| Prefix search | O(log N) |
| Top-k ranking | O(k log k) |
| Overall query | O(log N + k log k) |

## Key Classes

| Class | Role |
|---|---|
| `Term.java` | Stores a (string, weight) pair; implements Comparators |
| `BinarySearchDeluxe.java` | Binary search returning first/last index of a key |
| `Autocomplete.java` | Orchestrates search + ranking |

## Technologies

- **Java**
- Binary search (custom `firstIndexOf` / `lastIndexOf`)
- `Comparator`-based term ranking

## Build & Run

```bash
javac Term.java BinarySearchDeluxe.java Autocomplete.java

# Run with a dataset and k (number of results)
java Autocomplete cities.txt 5
```

```
Query: New
→ New York City      8175133
→ New Los Angeles    3971883
→ Newark              277140
→ New Haven           129779
→ New Britain          71538
```