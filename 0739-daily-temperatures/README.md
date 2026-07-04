# 739. Daily Temperatures

## Intuition

For every day, we need to find the **next future day with a higher temperature**.

A brute force solution would check every future day for each temperature, resulting in **O(n²)**.

Instead, we can process the array once using a **Monotonic Decreasing Stack**.

---

## Key Observation

Some previous days are still "waiting" for a warmer temperature.

When the current temperature becomes warmer than one of those waiting days, we immediately know its answer.

Therefore, we keep those unresolved days in a stack.

---

## Why Store Indices Instead of Temperatures?

The answer requires the **number of days**, not the temperature difference.

We need to calculate:

```text
days = currentIndex - previousIndex
```

So the stack stores **indices**.

---

## Stack Property

The stack maintains indices whose temperatures are in **decreasing order**.

```text
Temperatures:
75
71
69
```

The top always represents the most recent unresolved temperature.

---

## Algorithm

1. Create an answer array initialized with `0`.
2. Traverse the temperature array from left to right.
3. While:

   * the stack is not empty, and
   * the current temperature is greater than the temperature at the index on the top of the stack:

     * Pop the previous index.
     * Calculate the waiting days:

       ```text
       answer[previousIndex] = currentIndex - previousIndex
       ```
4. Push the current index into the stack.
5. Any indices left in the stack have no warmer future day, so their answers remain `0`.

---

## Dry Run

```text
temperatures = [73,74,75,71,69,72,76,73]

Stack stores indices.

i = 0 (73)
Stack = [0]

i = 1 (74)
74 > 73
Pop 0
answer[0] = 1
Push 1

Stack = [1]

i = 2 (75)
75 > 74
Pop 1
answer[1] = 1
Push 2

Stack = [2]

i = 3 (71)
71 < 75
Push 3

Stack = [2,3]

i = 4 (69)
69 < 71
Push 4

Stack = [2,3,4]

i = 5 (72)
72 > 69
Pop 4
answer[4] = 1

72 > 71
Pop 3
answer[3] = 2

72 < 75
Push 5

Stack = [2,5]

i = 6 (76)
76 > 72
Pop 5
answer[5] = 1

76 > 75
Pop 2
answer[2] = 4

Push 6

Stack = [6]

i = 7 (73)
73 < 76
Push 7

Final Answer:
[1,1,4,2,1,1,0,0]
```

---

## Why the While Loop?

A single current temperature can resolve **multiple previous days**.

Example:

```text
Stack:
75
71
69

Current = 72
```

* 72 is warmer than 69 ✅
* 72 is warmer than 71 ✅
* 72 is not warmer than 75 ❌

So we continue popping until the stack becomes valid again.

---

## Time Complexity

* Each index is pushed exactly once.
* Each index is popped at most once.

**Overall:** `O(n)`

---

## Space Complexity

* Stack stores unresolved indices.

**O(n)**

---

## Pattern Learned

Whenever the problem asks for:

* Next Greater Element
* Next Smaller Element
* Previous Greater/Smaller Element
* Daily Temperatures
* Stock Span
* Largest Rectangle in Histogram

Think:

> **Use a Monotonic Stack.**

### Recognition Rule

* Need the **next/previous greater or smaller element** ➜ Monotonic Stack.
* Need the **distance between indices** ➜ Store **indices**, not values.
* One element can resolve multiple previous elements ➜ Use a **while** loop while the stack condition is satisfied.
