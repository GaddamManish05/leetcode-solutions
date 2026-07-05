# 682. Baseball Game

## Intuition

The problem requires us to process a sequence of operations while keeping track of all **valid scores**.

Since every operation affects only the **most recent score(s)**, a **Stack** is the most suitable data structure.

The stack allows us to:

- Add a new score.
- Remove the latest score.
- Access the latest score.
- Access the last two scores to compute a new score.

---

## Approach

- Create a `Stack<Integer>` to store all valid scores.
- Traverse each operation in the given array.

### Case 1: Integer

If the current operation is a number:

- Convert it into an integer.
- Push it onto the stack.

```java
stack.push(Integer.parseInt(str));
```

---

### Case 2: `"C"`

The `"C"` operation invalidates the previous score.

Simply remove the top element from the stack.

```java
stack.pop();
```

---

### Case 3: `"D"`

The `"D"` operation records **double** the previous valid score.

Use `peek()` to access the latest score and push its double.

```java
stack.push(2 * stack.peek());
```

---

### Case 4: `"+"`

The `"+"` operation records the sum of the previous two valid scores.

Since a stack only provides direct access to the top element:

1. Pop the latest score (`val1`).
2. Peek the second latest score (`val2`).
3. Push `val1` back to restore the original stack.
4. Push `val1 + val2`.

```java
int val1 = stack.pop();
int val2 = stack.peek();

stack.push(val1);
stack.push(val1 + val2);
```

---

After processing all operations, every valid score is stored in the stack.

Pop every element and compute their total sum.

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
| `"C"` | `[5]` | Remove the previous score (2) |
| `"D"` | `[5, 10]` | Double the previous score (5 × 2) |
| `"+"` | `[5, 10, 15]` | Sum of the last two scores (10 + 5) |

Final Stack

```
[5, 10, 15]
```

Final Score

```
5 + 10 + 15 = 30
```

Output

```
30
```

---

## Correctness

The algorithm maintains a stack containing **only valid scores**.

- Integer → Adds a new valid score.
- `"C"` → Removes the latest valid score.
- `"D"` → Adds twice the latest valid score.
- `"+"` → Adds the sum of the latest two valid scores while preserving the existing scores.

Each operation is performed exactly as described in the problem statement.

After processing all operations, the stack contains every valid score exactly once.

Summing all elements in the stack therefore gives the correct final score.

---

## Complexity Analysis

### Time Complexity

- Processing each operation: **O(n)**
- Summing all scores: **O(n)**

**Overall:** `O(n)`

---

### Space Complexity

The stack stores all valid scores.

**O(n)**

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

---

## Pattern Learned

> Whenever a problem requires repeatedly accessing, removing, or updating the **most recent element(s)**, consider using a **Stack**.

Common stack patterns include:

- Undo operations
- Remove the latest element
- Access the latest element
- Modify the latest element
- Compute using the last two elements
- Maintain a history of operations
- Backtracking and expression evaluation
