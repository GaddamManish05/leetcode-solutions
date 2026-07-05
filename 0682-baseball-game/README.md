# 682. Baseball Game

## Intuition

The problem requires maintaining a record of valid scores while processing each operation sequentially.

Since every operation depends only on the **most recent valid scores**, a **Stack** is the ideal data structure.

- A new score is added to the top.
- The previous score can be removed.
- The last score can be doubled.
- The last two scores can be accessed to calculate their sum.

---

## Approach

1. Create a `Stack<Integer>` to store all valid scores.
2. Traverse each operation in the given array.

### Case 1: Integer Score

- Convert the string into an integer using `Integer.parseInt()`.
- Push it onto the stack.

```java
stack.push(Integer.parseInt(str));
```

---

### Case 2: `"C"`

- Remove the previous valid score.

```java
stack.pop();
```

---

### Case 3: `"D"`

- Double the previous valid score and push it.

```java
stack.push(2 * stack.peek());
```

---

### Case 4: `"+"`

The new score is the sum of the previous two valid scores.

Steps:

1. Pop the top element (`val1`).
2. Peek the second last element (`val2`).
3. Push `val1` back to restore the stack.
4. Push `val1 + val2` as the new score.

```java
int val1 = stack.pop();
int val2 = stack.peek();

stack.push(val1);
stack.push(val1 + val2);
```

After processing all operations, pop every score from the stack and add them to compute the final answer.

---

## Dry Run

### Input

```
operations = ["5","2","C","D","+"]
```

| Operation | Stack | Explanation |
|-----------|-------|-------------|
| `"5"` | `[5]` | Push 5 |
| `"2"` | `[5, 2]` | Push 2 |
| `"C"` | `[5]` | Remove 2 |
| `"D"` | `[5, 10]` | Double 5 |
| `"+"` | `[5, 10, 15]` | 10 + 5 = 15 |

Final Stack:

```
[5, 10, 15]
```

Sum:

```
5 + 10 + 15 = 30
```

Return:

```
30
```

---

## Correctness

The stack always stores the valid scores in the order they were recorded.

- **Integer** → Adds a new valid score.
- **"C"** → Removes the latest valid score.
- **"D"** → Uses the latest score to generate a new valid score.
- **"+"** → Uses the latest two valid scores to generate a new score while preserving the existing scores.

Since every operation updates the stack exactly according to the problem statement, the stack contains all valid scores after processing every operation.

Finally, summing all elements in the stack produces the required answer.

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
  - Processing each operation: `O(n)`
  - Summing all scores: `O(n)`

- **Space Complexity:** `O(n)`
  - In the worst case, the stack stores all valid scores.

---

## Java Code

```java
class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (String str : operations) {
            if (str.equals("+")) {
                int val1 = stack.pop();
                int val2 = stack.peek();

                stack.push(val1);
                stack.push(val1 + val2);
            }
            else if (str.equals("D")) {
                stack.push(2 * stack.peek());
            }
            else if (str.equals("C")) {
                stack.pop();
            }
            else {
                stack.push(Integer.parseInt(str));
            }
        }

        int sum = 0;

        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }
}
```

## Pattern Learned

> Whenever a problem requires repeatedly accessing, removing, or updating the **most recent element(s)**, a **Stack** is often the most suitable data structure.

Typical stack-based operations include:

- Undo the last action
- Remove the most recent element
- Access the latest element
- Double the latest value
- Compute using the last two values
- Maintain a history of operations
