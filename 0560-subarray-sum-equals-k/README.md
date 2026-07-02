# 560. Subarray Sum Equals K

**Difficulty:** Medium

## Problem Statement

Given an integer array `nums` and an integer `k`, return the **total number of contiguous subarrays** whose sum is exactly equal to `k`.

A **subarray** is a contiguous, non-empty sequence of elements within an array.

### Example 1

```text
Input:
nums = [1,1,1]
k = 2

Output:
2
```

### Example 2

```text
Input:
nums = [1,2,3]
k = 3

Output:
2
```

---

# Approach: Prefix Sum + HashMap

## Key Observation

Instead of calculating the sum of every possible subarray (which takes **O(n²)** time), we maintain a **running (prefix) sum** while traversing the array only once.

Let,

```text
currentPrefixSum = Sum of elements from index 0 to i
```

Suppose the sum of a subarray ending at the current index is `k`.

Then,

```text
Subarray Sum = Current Prefix Sum − Previous Prefix Sum
```

Therefore,

```text
Current Prefix Sum − Previous Prefix Sum = k
```

Rearranging,

```text
Previous Prefix Sum = Current Prefix Sum − k
```

This means that at every index, if a prefix sum equal to:

```text
currentPrefixSum - k
```

has already been encountered, then a valid subarray ending at the current index exists.

---

# Why HashMap?

A `HashMap` is used to efficiently store previously encountered prefix sums.

```text
Key   → Prefix Sum
Value → Frequency of the Prefix Sum
```

We store the **frequency** instead of just the index because the same prefix sum can occur multiple times.

Each occurrence represents a different starting position for a valid subarray.

---

# Why Initialize with `0 → 1`?

Before processing any element, we insert:

```text
0 → 1
```

This represents an **empty prefix** before the array begins.

It allows us to correctly count subarrays that start from index `0`.

For example,

```text
nums = [3]
k = 3
```

When the current prefix sum becomes `3`,

```text
currentPrefixSum - k = 0
```

Since `0` already exists in the map, the subarray `[3]` is counted correctly.

---

# Algorithm

1. Initialize a running prefix sum as `0`.
2. Store `(0 → 1)` in the HashMap.
3. Traverse the array.
4. Update the current prefix sum.
5. Compute `currentPrefixSum - k`.
6. If this value exists in the HashMap, add its frequency to the answer.
7. Store/update the current prefix sum in the HashMap.
8. Return the total count of valid subarrays.

---

# Complexity Analysis

| Complexity |    Value |
| ---------- | -------: |
| Time       | **O(n)** |
| Space      | **O(n)** |

---

# Key Takeaways

* Prefix Sum helps compute subarray sums efficiently.
* The identity

```text
Current Prefix Sum − Previous Prefix Sum = k
```

is the foundation of the solution.

* A `HashMap` enables constant-time lookup of previous prefix sums.
* Storing **frequencies** ensures all valid subarrays are counted, even when the same prefix sum appears multiple times.
* Initializing the map with `(0 → 1)` correctly handles subarrays starting from index `0`.

> **Pattern to Remember:**
> Whenever a problem asks for the **number of subarrays with a target sum**, especially when the array may contain **negative numbers**, think **Prefix Sum + HashMap**.
