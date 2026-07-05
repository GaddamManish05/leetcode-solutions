# 88. Merge Sorted Array

## Problem Statement
You are given two sorted integer arrays `nums1` and `nums2` in non-decreasing order.

- `nums1` has a size of `m + n`.
- The first `m` elements are valid.
- The last `n` elements are extra space (`0`s) to accommodate elements from `nums2`.

Merge `nums2` into `nums1` so that the final array is sorted in non-decreasing order.

---

## Approach (Three Pointers from the End)

Since `nums1` already has enough space at the end, instead of shifting elements to the right, we start filling the array **from the last index**.

We use three pointers:

- `i` → Last valid element in `nums1` (`m - 1`)
- `j` → Last element in `nums2` (`n - 1`)
- `k` → Last position of `nums1` (`m + n - 1`)

### Algorithm

1. Initialize three pointers:
   - `i = m - 1`
   - `j = n - 1`
   - `k = m + n - 1`

2. Compare `nums1[i]` and `nums2[j]`.
   - If `nums1[i] > nums2[j]`, place `nums1[i]` at `nums1[k]`.
   - Otherwise, place `nums2[j]` at `nums1[k]`.

3. Move the corresponding pointer and decrement `k`.

4. Continue until either array is exhausted.

5. If elements remain in `nums2`, copy them into `nums1`.

6. If elements remain in `nums1`, they are already in their correct positions (copying them also works, as done in this implementation).

---

## Dry Run

### Input

```text
nums1 = [1,2,3,0,0,0]
m = 3

nums2 = [2,5,6]
n = 3
```

Initial pointers:

```text
i = 2 (3)
j = 2 (6)
k = 5
```

### Step 1

```text
3 < 6

nums1 = [1,2,3,0,0,6]

j--
k--
```

### Step 2

```text
3 < 5

nums1 = [1,2,3,0,5,6]

j--
k--
```

### Step 3

```text
3 > 2

nums1 = [1,2,3,3,5,6]

i--
k--
```

### Step 4

```text
2 == 2

nums1 = [1,2,2,3,5,6]

j--
k--
```

Now `j < 0`, so the remaining elements in `nums1` are already in place.

Final Output:

```text
[1,2,2,3,5,6]
```

---

## Correctness

- The largest remaining element is always placed at the end (`k`).
- Since both arrays are already sorted, comparing the last elements guarantees the correct placement.
- Working backwards avoids overwriting the valid elements already present in `nums1`.

---

## Complexity Analysis

### Time Complexity

- Each element is processed exactly once.

**O(m + n)**

### Space Complexity

- No extra data structure is used.

**O(1)**

---

## Java Solution

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[k] = nums2[j];
                j--;
            } else {
                nums1[k] = nums1[i];
                i--;
            }
            k--;
        }

        while (i >= 0) {
            nums1[k] = nums1[i];
            i--;
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
```
