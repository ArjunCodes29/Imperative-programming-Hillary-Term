/* Question 7
Write a program fragment that, given x ≥ 0 and y > 0, computes q and r such that
x = q * y + r and 0 ≤ r < y. Solve the problem using repeated subtraction. Thus the
program should be equivalent to
q = x/y; r = x%y
but without using the / (div) and % (mod) operators (or similar) operators. Give invariants
for every loop in your program. */

var count = 0

// Invariant I: xinital = count * y + x and x ≥ 0

while (x >= y) {
    x -= y 
    count += 1
    // I still holds true 
}
// I still holds true
// so count stores the value of q and x now stores the value of r