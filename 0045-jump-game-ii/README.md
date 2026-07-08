# 45. Jump Game II (Medium)

## Pattern

**Recursion + Memoization (Top-Down DP)**

Unlike Jump Game I (where we return `true/false`), here we need the **minimum number of jumps** required to reach the last index.

---

# Idea

At every index `i`:

* We can jump from `1` to `nums[i]` positions.
* Recursively calculate the minimum jumps from every reachable index.
* Choose the minimum among them.
* Add **1** for the current jump.

---

## DP Meaning

```text
dp[i] = Minimum jumps required to reach the last index from index i.
```

Initially

```text
dp[i] = -1
```

means not computed.

---

# Recurrence

```text
score(i)

=

1 + min(

score(i+1),

score(i+2),

...

score(i+nums[i])

)
```

---

## Steps

1. Start recursion from index `0`.
2. If we already reached the last index

   * Return `0`.
3. If already computed

   * Return `dp[i]`.
4. Try every possible jump.
5. Take the minimum answer.
6. Store it inside DP.
7. Return the stored value.

---

# Code

```java
class Solution {

    private static int score(int i, int[] nums, int[] dp) {

        // Base Case
        if (i >= nums.length - 1)
            return 0;

        // Memoization
        if (dp[i] != -1)
            return dp[i];

        int minScore = Integer.MAX_VALUE;

        // Try every jump
        for (int j = 1; j <= nums[i]; j++) {

            int min = score(i + j, nums, dp);

            if (min != Integer.MAX_VALUE)
                minScore = Math.min(minScore, 1 + min);
        }

        return dp[i] = minScore;
    }

    public int jump(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        return score(0, nums, dp);
    }
}
```

---

# Dry Run

### Example

```text
nums = [2,3,1,1,4]
```

Start

```text
score(0)
```

Index 0

```text
Can jump

↓

1

↓

2
```

---

### Jump to index 1

```text
score(1)
```

Index 1

```text
Can jump

↓

2

↓

3

↓

4
```

---

### Jump to index 4

```text
score(4)

Reached last index

Return 0
```

Backtrack

```text
score(1)

=

1 + 0

=

1
```

Store

```text
dp[1]=1
```

---

### Back to index 0

```text
Using index 1

1 + dp[1]

=

2 jumps
```

---

### Jump to index 2

```text
score(2)
```

Index 2

```text
↓

3

↓

4
```

Needs more jumps

```text
score(2)=2
```

---

Take minimum

```text
min(2,3)

=

2
```

Store

```text
dp[0]=2
```

Answer

```text
2
```

---

# DP Visualization

```text
Index

0 1 2 3 4

↓

dp

2 1 2 1 0
```

Meaning

```text
From index 4

Need 0 jumps

From index 3

Need 1 jump

From index 1

Need only 1 jump

From index 0

Need 2 jumps
```

---

# Why Memoization?

Without DP

```text
score(3)

is computed

many times.
```

With DP

```text
First computation

↓

Store in dp

↓

Future calls

↓

Return immediately
```

---

# Time Complexity

Worst case

```text
O(n²)
```

Reason

* Every index is solved once.
* Each index may try many jumps.

---

# Space Complexity

```text
DP Array = O(n)

Recursion Stack = O(n)

Total = O(n)
```

---

# Key Concepts

### Base Case

```java
if(i >= nums.length-1)
    return 0;
```

---

### Memoization

```java
if(dp[i]!=-1)
    return dp[i];
```

---

### Try Every Jump

```java
for(int j=1;j<=nums[i];j++)
```

---

### Update Minimum

```java
minScore=Math.min(minScore,1+min);
```

---

# Difference from Jump Game I

## Jump Game I

```text
Question

Can I reach the end?

Answer

true / false
```

Recurrence

```text
OR of all jumps
```

---

## Jump Game II

```text
Question

Minimum jumps to reach the end?
```

Recurrence

```text
Minimum of all jumps
```

---

# Revision Keywords

**Recursion → Memoization → Try Every Jump → Minimum of All Choices → Store Answer → Return**

---

# Interview Note

This solution correctly uses **Top-Down Dynamic Programming (Memoization)**.

However, the optimal solution for this problem is a **Greedy** approach.

| Approach                  | Time        | Space    |
| ------------------------- | ----------- | -------- |
| Recursion                 | Exponential | O(n)     |
| Memoization (Top-Down DP) | O(n²)       | O(n)     |
| Tabulation (Bottom-Up DP) | O(n²)       | O(n)     |
| **Greedy (Optimal)**      | **O(n)**    | **O(1)** |

For learning DP, this memoized solution is excellent because it clearly shows the recurrence. In interviews, it's valuable to explain this approach first and then optimize it to the greedy solution.
