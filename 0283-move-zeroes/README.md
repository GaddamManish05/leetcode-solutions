# 283. Move Zeroes (Easy)

## Pattern

**Two Pointers (Read Pointer + Write Pointer)**

This problem is about moving all non-zero elements to the front while maintaining their relative order, then filling the remaining positions with zeros.

---

# Idea

Use two pointers:

* `i` → Reads every element.
* `j` → Writes the next non-zero element.

Whenever a non-zero element is found:

* Copy it to index `j`.
* Increment `j`.

After all non-zero elements are placed, fill the remaining positions with `0`.

---

## Steps

1. Initialize `j = 0`.
2. Traverse the array using `i`.
3. If `nums[i] != 0`

   * Copy `nums[i]` to `nums[j]`
   * Increment `j`
4. After traversal, fill indices `j` to `n-1` with `0`.

---

# Code

```java
class Solution {
    public void moveZeroes(int[] nums) {

        int j = 0;   // Position to place next non-zero element

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }
}
```

---

# Dry Run

### Example

```text
nums = [0,1,0,3,12]
```

Initially

```text
i = 0
j = 0

[0,1,0,3,12]
```

---

### i = 0

```text
nums[0] = 0

Skip
```

```text
j = 0
```

---

### i = 1

```text
nums[1] = 1

Copy

nums[0] = 1
```

Array

```text
[1,1,0,3,12]
```

```text
j = 1
```

---

### i = 2

```text
nums[2] = 0

Skip
```

---

### i = 3

```text
nums[3] = 3

nums[1] = 3
```

Array

```text
[1,3,0,3,12]
```

```text
j = 2
```

---

### i = 4

```text
nums[4] = 12

nums[2] = 12
```

Array

```text
[1,3,12,3,12]
```

```text
j = 3
```

---

### Fill remaining with 0

```text
nums[3] = 0
nums[4] = 0
```

Final Array

```text
[1,3,12,0,0]
```

---

# Pointer Visualization

```text
Original

i
↓

[0,1,0,3,12]

j
↓

0
```

After copying non-zero elements

```text
[1,3,12,3,12]
         ↑
         j
```

Fill remaining

```text
[1,3,12,0,0]
```

---

# Why Does This Work?

* `i` visits every element exactly once.
* `j` always points to the next position where a non-zero element should be placed.
* Non-zero elements are copied in the same order, so their relative order is preserved.
* Remaining positions are filled with zeros.

---

# Time Complexity

```text
O(n)
```

* One pass to copy non-zero elements.
* One pass to fill zeros.

---

# Space Complexity

```text
O(1)
```

No extra array is used.

---

# Key Concepts

### Read Pointer

```java
for(int i = 0; i < nums.length; i++)
```

Visits every element.

---

### Write Pointer

```java
int j = 0;
```

Stores the position for the next non-zero element.

---

### Copy Non-Zero

```java
if(nums[i] != 0){
    nums[j] = nums[i];
    j++;
}
```

---

### Fill Remaining

```java
while(j < nums.length){
    nums[j] = 0;
    j++;
}
```

---

# Revision Keywords

**Two Pointers → Read Pointer → Write Pointer → Copy Non-Zero → Fill Remaining with Zero**

---

# Interview Note

Your solution is **O(n)** time and **O(1)** space, which satisfies the problem constraints.

There is another common solution that **swaps** non-zero elements into place instead of overwriting and then filling zeros:

```java
if(nums[i] != 0){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    j++;
}
```

This swap-based approach minimizes unnecessary writes when there are few zeros, which is what the follow-up is hinting at. Both approaches are accepted, but the swap solution performs fewer write operations in many cases.
