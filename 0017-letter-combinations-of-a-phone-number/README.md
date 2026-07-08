# 17. Letter Combinations of a Phone Number (Medium)

## Pattern

**Backtracking (Recursion)**

This is a classic backtracking problem where we:

* Choose one letter for the current digit.
* Move to the next digit.
* Once all digits are processed, store the generated string.
* Backtrack by removing the last chosen letter and try the next one.

---

## Idea

Every digit maps to multiple characters.

Example:

```
2 -> abc
3 -> def
4 -> ghi
5 -> jkl
6 -> mno
7 -> pqrs
8 -> tuv
9 -> wxyz
```

For each digit:

* Pick one character.
* Recursively solve for the remaining digits.
* Remove the chosen character (Backtrack).
* Repeat for the next character.

---

## Steps

1. If the input string is empty, return an empty list.
2. Store the phone keypad mapping in an array.
3. Start recursion from index `0`.
4. At each digit:

   * Get all letters corresponding to that digit.
   * Iterate over every letter.
   * Append it to the current string.
   * Recurse for the next digit.
   * Remove the appended letter (Backtracking).
5. When `index == digits.length()`, add the current string to the answer.

---

## Code

```java
class Solution {

    private void backtrack(String digits, int index,
                           StringBuilder curr,
                           String[] map,
                           List<String> ans) {

        // Base Case
        if (index == digits.length()) {
            ans.add(curr.toString());
            return;
        }

        // Current digit's letters
        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {

            // Choose
            curr.append(ch);

            // Explore
            backtrack(digits, index + 1, curr, map, ans);

            // Undo (Backtrack)
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {

        List<String> ans = new ArrayList<>();

        if (digits.length() == 0)
            return ans;

        String[] map = {
            "",     //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
        };

        backtrack(digits, 0, new StringBuilder(), map, ans);

        return ans;
    }
}
```

---

## Dry Run (`digits = "23"`)

```
Start

curr = ""
index = 0

Digit 2 -> abc
```

### Choose 'a'

```
curr = "a"
index = 1

Digit 3 -> def
```

Choose `d`

```
curr = "ad"

index = 2
Base Case

Answer = ["ad"]
```

Backtrack

```
curr = "a"
```

Choose `e`

```
curr = "ae"

Base Case

Answer = ["ad","ae"]
```

Backtrack

```
curr = "a"
```

Choose `f`

```
curr = "af"

Base Case

Answer = ["ad","ae","af"]
```

Backtrack

```
curr = "a"

Finished all letters of digit 3

Backtrack again

curr = ""
```

Now choose `b`

```
bd
be
bf
```

Backtrack

Choose `c`

```
cd
ce
cf
```

Final Answer

```
[
ad ae af
bd be bf
cd ce cf
]
```

---

## Recursion Tree

```
                    ""
                     |
          ---------------------
          |         |         |
          a         b         c
        / | \     / | \     / | \
       d  e  f   d  e  f   d  e  f
```

Every root-to-leaf path forms one valid combination.

---

## Why Backtracking?

When we finish one combination:

```
af
```

we don't create a new string.

Instead we simply remove the last character.

```
af
↓

a
```

Then try

```
ae
↓

ad
```

This "choose → recurse → remove" process is called **Backtracking**.

---

## Time Complexity

Let

* `n = digits.length()`
* Each digit has at most **4** letters.

```
Time = O(4^n × n)
```

Reason:

* Up to `4^n` combinations.
* Each completed string takes `O(n)` to copy into the answer.

---

## Space Complexity

```
Recursion Stack = O(n)

Answer List = O(4^n × n)
```

---

## Key Java Concepts

### Convert digit to array index

```java
digits.charAt(index) - '0'
```

Example

```
'2' - '0' = 2
```

---

### Get letters

```java
String letters = map[digits.charAt(index) - '0'];
```

---

### Iterate over every letter

```java
for(char ch : letters.toCharArray())
```

---

### Append

```java
curr.append(ch);
```

---

### Remove last character

```java
curr.deleteCharAt(curr.length()-1);
```

This is the **Backtracking step**.

---

## Backtracking Formula

```
Choose
↓

Explore (Recursive Call)

↓

Undo Choice (Backtrack)
```

Java template:

```java
for(...) {

    // Choose
    ...

    // Explore
    backtrack(...);

    // Undo
    ...
}
```

---

## Revision Keywords

**Phone Mapping → Recursion → Backtracking → Choose → Explore → Remove → Base Case → Add Combination**

### Backtracking Checklist

✅ Base case

✅ Get choices

✅ Loop through choices

✅ Choose

✅ Recursive call

✅ Undo choice

This template works for problems like **Subsets**, **Permutations**, **Combination Sum**, **N-Queens**, **Generate Parentheses**, and many other backtracking questions.
