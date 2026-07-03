# 682. Baseball Game

## Intuition

The problem asks us to maintain a history of valid scores while processing operations one by one.

Since every operation only depends on the **most recent scores**, a **Stack** is the perfect data structure.

---

## Approach

* Create a `Stack<Integer>` to store valid scores.
* Traverse every operation:

  * **Integer**

    * Convert it using `Integer.parseInt()`.
    * Push it into the stack.
  * **"C"**

    * Remove the previous valid score using `pop()`.
  * **"D"**

    * Double the previous score using `peek() * 2` and push it.
  * **"+"**

    * Need the sum of the last two scores.
    * Pop the top score (`val1`).
    * Peek the second score (`val2`).
    * Push `val1` back (to restore the stack).
    * Push `val1 + val2`.

After processing all operations, pop every element from the stack and add them to get the final answer.

---

## Why Stack?

A stack naturally supports:

* Accessing the latest score (`peek()`).
* Removing the latest score (`pop()`).
* Adding a new score (`push()`).

Since every operation works on the most recent records, Stack is the ideal choice.

---

## Key Observation

For the `"+"` operation:

```text
Stack:
[5, 10]

val1 = pop() -> 10
val2 = peek() -> 5

push(10)        // restore
push(15)        // new score

Final:
[5, 10, 15]
```

The temporary `pop()` is only to access the second last score.

---

## Time Complexity

* Processing operations: **O(n)**
* Calculating final sum: **O(n)**

Overall: **O(n)**

---

## Space Complexity

* Stack stores at most all scores.

**O(n)**

---

## Pattern Learned

> **Whenever a problem requires repeatedly accessing, removing, or updating the most recent elements, think of using a Stack.**

Common Stack operations:

* Previous element
* Undo operation
* Remove last
* Double last
* Sum of last two
