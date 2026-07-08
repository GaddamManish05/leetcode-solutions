# 55. Jump Game (Medium)

## Pattern

**Recursion + Memoization (Top-Down Dynamic Programming)**

The idea is to check whether we can reach the last index from the current index.

Instead of solving the same index repeatedly, store the answer in a DP array.

---

## Idea

At every index:

* We can jump from `1` to `nums[i]` steps.
* If **any** jump reaches the last index, return `true`.
* Otherwise return `false`.

Memoize the result for every index.

---

## DP Meaning

```java
dp[i] = -1  -> Not computed

dp[i] = 1   -> Can reach the end

dp[i] = 0   -> Cannot reach the end
```

---

## Steps

1. Start recursion from index `0`.
2. If we reach or cross the last index → return `true`.
3. If already computed, return stored answer.
4. Try every jump from `1` to `nums[i]`.
5. If any recursive call returns `true`

   * Store `dp[i]=1`
   * Return `true`
6. If all jumps fail

   * Store `dp[i]=0`
   * Return `false`

---

## Code

```java
class Solution {

    private static boolean isjump(int i, int[] nums, int[] dp) {

        // Base Case
        if (i >= nums.length - 1)
            return true;

        // Already solved
        if (dp[i] != -1)
            return dp[i] == 1;

        // Try every possible jump
        for (int j = 1; j <= nums[i]; j++) {

            if (isjump(i + j, nums, dp)) {

                dp[i] = 1;
                return true;
            }
        }

        dp[i] = 0;
        return false;
    }

    public boolean canJump(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        return isjump(0, nums, dp);
    }
}
```

---

# Dry Run

## Example 1

```text
nums = [2,3,1,1,4]
```

Start

```text
isjump(0)
```

Index 0 can jump

```text
1 step -> index 1

2 steps -> index 2
```

Try index 1

```text
isjump(1)
```

Index 1

```text
nums[1]=3

Can jump to

2

3

4
```

Try index 2

```text
isjump(2)
```

Index 2

```text
nums[2]=1

Jump to index 3
```

Index 3

```text
nums[3]=1

Jump to index 4
```

Index 4

```text
Reached last index

Return true
```

Now recursion returns

```text
Index 4 -> true

Index 3 -> dp[3]=1

Index 2 -> dp[2]=1

Index 1 -> dp[1]=1

Index 0 -> dp[0]=1
```

Final Answer

```text
true
```

---

## Example 2

```text
nums = [3,2,1,0,4]
```

Index 0

```text
Can jump to

1

2

3
```

Try index 1

```text
Can jump

2

3
```

Try index 2

```text
Jump to 3
```

Index 3

```text
nums[3]=0

No jumps possible

Return false
```

Backtracking

```text
dp[3]=0

↓

dp[2]=0

↓

dp[1]=0
```

Now from index 0

Try jump to index 2

Already computed

```text
dp[2]=0
```

Try jump to index 3

Already computed

```text
dp[3]=0
```

All paths failed

```text
dp[0]=0

Return false
```

---

## Memoization Benefit

Without DP

```text
Index 3

↑

Called many times
```

With DP

```text
First call

↓

Store answer

↓

Every future call

↓

Return immediately
```

---

## Time Complexity

For every index

```text
Try at most nums[i] jumps
```

Worst case

```text
O(n²)
```

---

## Space Complexity

```text
DP Array = O(n)

Recursion Stack = O(n)

Total = O(n)
```

---

## Key Concepts

### Base Case

```java
if(i >= nums.length - 1)
    return true;
```

---

### Memoization

```java
if(dp[i] != -1)
    return dp[i] == 1;
```

---

### Try Every Jump

```java
for(int j = 1; j <= nums[i]; j++)
```

---

### Store Result

```java
dp[i] = 1;
```

or

```java
dp[i] = 0;
```

---

## DP State

```text
Index

↓

Can I reach the last index from here?
```

---

## Recurrence

```text
canJump(i)

=

OR of

canJump(i+1)

canJump(i+2)

...

canJump(i+nums[i])
```

If any returns **true**

↓

Current index is also **true**

---

## Revision Keywords

**Recursion → Memoization → Try Every Jump → Any Path Works → Store Result → Return**

---

## Note (Interview Insight)

This solution is correct and uses **Top-Down Dynamic Programming**, but it is **not the optimal solution**.

* **Current approach (Recursion + Memoization):**

  * Time: **O(n²)** (worst case)
  * Space: **O(n)**

* **Optimal approach (Greedy):**

  * Time: **O(n)**
  * Space: **O(1)**

For interviews and LeetCode, it's good to know both. The recursive memoized version helps understand the problem, while the greedy approach is the expected optimal solution.
