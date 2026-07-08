# 3227. Find Missing and Repeated Values (Easy)

## Idea

* Traverse the `n × n` grid.
* Store the frequency of each number in a `HashMap`.
* The number with frequency `2` is the **repeated** number.
* The number that does not exist in the map is the **missing** number.

---

## Steps

1. Create a `HashMap<Integer, Integer>` to store frequencies.
2. Traverse the matrix and update the frequency of every element.
3. Iterate through the map:

   * Frequency > 1 → Repeated number.
4. Iterate from `1` to `n²`:

   * Number not present in the map → Missing number.
5. Return `{repeated, missing}`.

---

## Code

```java
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = grid.length;

        // Count frequency
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(grid[i][j], map.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        int repeated = -1;
        int missing = -1;

        // Find repeated number
        for (int key : map.keySet()) {
            if (map.get(key) == 2) {
                repeated = key;
                break;
            }
        }

        // Find missing number
        for (int i = 1; i <= n * n; i++) {
            if (!map.containsKey(i)) {
                missing = i;
                break;
            }
        }

        return new int[]{repeated, missing};
    }
}
```

---

## Complexity

* **Time:** `O(n²)`

  * Traverse matrix → `O(n²)`
  * Traverse map → `O(n²)` (at most `n²` keys)
  * Check numbers from `1` to `n²` → `O(n²)`

* **Space:** `O(n²)`

  * HashMap stores frequencies of up to `n²` numbers.

---

## Key Java Methods

```java
map.put(num, map.getOrDefault(num, 0) + 1); // Count frequency

map.containsKey(x); // Check if number exists

map.keySet(); // Iterate through all keys
```

---

## Important Mistake I Made

❌ I used:

```java
for (int i = 1; i < max * max; i++)
```

This is incorrect because `max` is the largest value **present** in the grid, not necessarily `n`.

✅ Correct:

```java
int n = grid.length;

for (int i = 1; i <= n * n; i++) {
    ...
}
```

Always iterate from **1 to `n²`**, since the numbers are guaranteed to lie in that range.

---

## Revision Keywords

**HashMap → Frequency Count → Repeated = Frequency 2 → Missing = Not Present → Return `{Repeated, Missing}`**
