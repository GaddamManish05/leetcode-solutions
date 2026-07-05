# 724. Find Pivot Index

## Approach: Prefix Sum Arrays (Left Sum & Right Sum)

### Intuition

The pivot index is the position where the sum of all elements to the left is equal to the sum of all elements to the right.

Instead of calculating the left and right sums for every index repeatedly, we precompute them using two prefix sum arrays:

- `leftSum[i]` stores the sum of elements from index `0` to `i`.
- `rightSum[i]` stores the sum of elements from index `i` to `n-1`.

Since both arrays include `nums[i]`, comparing them directly works because the current element is present on both sides and effectively cancels out.

---

## Algorithm

1. Create two arrays:
   - `leftSum[]`
   - `rightSum[]`
2. Traverse from left to right to build `leftSum`.
3. Traverse from right to left to build `rightSum`.
4. Traverse the array once more:
   - If `leftSum[i] == rightSum[i]`, return `i`.
5. If no such index exists, return `-1`.

---

## Dry Run

### Input

```
nums = [1, 7, 3, 6, 5, 6]
```

### Step 1: Build `leftSum`

| Index | Running Sum | leftSum |
|------:|------------:|---------:|
| 0 | 1 | 1 |
| 1 | 8 | 8 |
| 2 | 11 | 11 |
| 3 | 17 | 17 |
| 4 | 22 | 22 |
| 5 | 28 | 28 |

```
leftSum = [1, 8, 11, 17, 22, 28]
```

---

### Step 2: Build `rightSum`

| Index | Running Sum | rightSum |
|------:|------------:|----------:|
| 5 | 6 | 6 |
| 4 | 11 | 11 |
| 3 | 17 | 17 |
| 2 | 20 | 20 |
| 1 | 27 | 27 |
| 0 | 28 | 28 |

```
rightSum = [28, 27, 20, 17, 11, 6]
```

---

### Step 3: Compare Both Arrays

| Index | leftSum | rightSum | Result |
|------:|---------:|----------:|:------:|
| 0 | 1 | 28 | ❌ |
| 1 | 8 | 27 | ❌ |
| 2 | 11 | 20 | ❌ |
| 3 | 17 | 17 | ✅ Return 3 |

---

## Correctness

For every index `i`:

```
leftSum[i] = nums[0] + nums[1] + ... + nums[i]
```

```
rightSum[i] = nums[i] + nums[i+1] + ... + nums[n-1]
```

If

```
leftSum[i] == rightSum[i]
```

then

```
nums[0] + ... + nums[i]
=
nums[i] + ... + nums[n-1]
```

Subtracting `nums[i]` from both sides gives:

```
nums[0] + ... + nums[i-1]
=
nums[i+1] + ... + nums[n-1]
```

Thus, the sum of elements on the left equals the sum of elements on the right, so `i` is a valid pivot index.

Since the array is checked from left to right, the first matching index is the leftmost pivot index.

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
  - Build `leftSum`: `O(n)`
  - Build `rightSum`: `O(n)`
  - Compare both arrays: `O(n)`

- **Space Complexity:** `O(n)`
  - `leftSum[]`
  - `rightSum[]`

---

## Java Code

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int[] leftSum = new int[nums.length];
        int[] rightSum = new int[nums.length];

        int sum = 0;

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            leftSum[i] = sum;
        }

        sum = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            rightSum[i] = sum;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (leftSum[i] == rightSum[i]) {
                return i;
            }
        }

        return -1;
    }
}
```
