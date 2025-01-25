### Important Ideas

**Invariants in While Loops**

---

### Improvements

- **Invariants**: Write these more clearly. I feel like I did a mediocre job here and should focus on improving how I write rigorous invariants.
  
- **Syntax and Simplicity**: I suspect there might be redundant comments and overly complicated code in places, though I’m not sure where. Concrete tips on syntactic sugar would be helpful.
  
- **Consistency**: The structure of each question could be more uniform. For example:
  - Comment the actual question at the top.
  - Add comments for first thoughts, plans, etc.
  - Use consistent commit messages to document my thought process clearly.
  
- **General**: I’m writing this in a bit of a rush, so I may have missed more issues. These are just the ones that come to mind.

---

### Qu1

This was largely fine. I didn’t write down invariants for this question—maybe we can discuss that in the tutorial.

---

### Qu3

Knowing how `-∞` is represented in Scala would be helpful. I could probably Google this as well.

---

### Qu4**

I found this question difficult. I think the department has high standards for clean code. For example:
- The preference for comments feels slightly off-putting, but I should probably get in the habit of doing this.
- My functions weren’t written as cleanly as the solution to Qu4, which could be an issue. 


In general, I didn’t see anything majorly wrong with qu4 beyond these nitpicks. I’m curious about the official solution.

---

### Qu5

 I liked how Scala supports arithmetic in print statements, similar to Python—pretty nifty.

---

### Qu8*

Spent loads of time on this one (see commit history if interested). Here’s the process I followed:
1. My first idea was to take the manual approach:
   - Create lists to track `A`, `B`, and the quotient in the first pass.
   - Use a second pass for back substitution to find the coefficients.
   - This was inspired by how we solve this manually.

2. The code turned out overly complex, with frequent type errors and bugs. I decided to start over:
   - Went to the whiteboard and found the recurrence relation:
     \[
     R_n = R_{n-2} - Q \cdot R_{n-1}
     \]
   - This recurrence was the key to solving the question elegantly. It arises naturally from:
     \[
     A = Q \cdot B + R \implies R = A - Q \cdot B
     \]
     Substituting recursively, \( A \) and \( B \) can be expressed as earlier values of \( R \).

---

### Qu9

After spending so much time on Qu8, this one felt simple. The solution is just tracking the max value as you iterate through the list.
