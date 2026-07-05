# 523. Continuous Subarray Sum

## Approach: Prefix Sum + HashMap

### Intuition

A subarray sum from index `i + 1` to `j` is:

```
prefixSum[j] - prefixSum[i]
```

For this subarray to be a multiple of `k`:

```
(prefixSum[j] - prefixSum[i]) % k == 0
```

This can be rewritten as:

```
prefixSum[j] % k == prefixSum[i] % k
```

This means if two prefix sums have the **same remainder when divided by `k`**, then the sum of the elements between them is divisible by `k`.

Therefore, while traversing the array:

- Compute the running prefix sum.
- Compute `remainder = prefixSum % k`.
- Store the **first occurrence** of every remainder in a `HashMap`.
- If the same remainder appears again and the distance between the two indices is at least `2`, then a valid subarray exists.

We initialize the map with:

```
0 -> -1
```

This handles cases where the subarray starting from index `0` itself has a sum divisible by `k`.

---

## Algorithm

1. Create a `HashMap` to store `(remainder, first index)`.
2. Insert `(0, -1)` into the map.
3. Traverse the array while maintaining the prefix sum.
4. Compute:

   ```
   remainder = prefixSum % k
   ```

5. If the remainder already exists:
   - Check whether the subarray length is at least `2`.
   - If yes, return `true`.
6. Otherwise, store the remainder with its current index.
7. If no valid subarray is found, return `false`.

---

## Dry Run

**Input**

```
nums = [23, 2, 4, 6, 7]
k = 6
```

| Index | Number | Prefix Sum | Remainder | HashMap | Result |
|------:|--------:|-----------:|----------:|:--------|:------|
| -1 | - | 0 | 0 | {0:-1} | Initialization |
| 0 | 23 | 23 | 5 | {0:-1, 5:0} | Store |
| 1 | 2 | 25 | 1 | {0:-1, 5:0, 1:1} | Store |
| 2 | 4 | 29 | 5 | remainder already exists at index 0 | Length = 2 → **Return true** |

Subarray:

```
[2, 4]
```

Sum:

```
2 + 4 = 6
```

which is divisible by `6`.

---

## Correctness

If two prefix sums produce the same remainder when divided by `k`, then their difference is divisible by `k`.

Suppose:

```
prefixSum1 % k = r
prefixSum2 % k = r
```

Then,

```
(prefixSum2 - prefixSum1) % k = 0
```

Hence, the subarray between these two prefix sums has a sum that is a multiple of `k`.

Since we store the **first occurrence** of every remainder, we maximize the possible subarray length and correctly detect any valid subarray of length at least `2`.

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(min(n, k))` (or `O(n)` in the worst case)

---

## Java Code

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int remainder = sum % k;

            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }

        return false;
    }
}
```
