/* 
Question 1
[Programming] Write short Scala functions which use just standard integer arithmetic
(* + - /) and, on integer input:
(a) square the input;
(b) compute the remainder on dividing the input by 3; and
(c) find the largest perfect square no more than the input.
How would you convince your tutor that you have run your functions and that they give the
correct answers?
This question is taken from Functional programming last term where you may have seen
a “Sheet 0” which had this question in GHCi.
*/

object qu1 {
  
  // (a) Function to square the input
  def square(n: Int): Int = {
    n * n
  }

  // (b) Function to compute the remainder when input is divided by 3
  def mod3(n: Int): Int = {
    var num = n
    while (num > 2) {
      num -= 3
    }
    while (num < 0) {
      num += 3
    }
    num
  }

  // (c) Function to find the largest perfect square no more than the input
  def largestPerfectSquare(n: Int): Int = {
    require(n >= 0)
    var x: Int = 0
    while (square(x) <= n) {
      x += 1
    }
    square(x - 1)
  }

  def main(args: Array[String]): Unit = {
    println("Testing square function:")
    println(s"square(5) = ${square(5)}")  // Should print 25

    println("\nTesting mod3 function:")
    println(s"mod3(10) = ${mod3(10)}")  // Should print 1
    println(s"mod3(-10) = ${mod3(-10)}") // Should print 2

    println("\nTesting largestPerfectSquare function:")
    println(s"largestPerfectSquare(20) = ${largestPerfectSquare(20)}")  // Should print 16
    println(s"largestPerfectSquare(0) = ${largestPerfectSquare(0)}")  // Should print 0
  }
}

/**
  * I can perhaps convince Irwin that my functions are right by reasoning about variants and Invariants in my while loops.
  * Well def square is trivially true, like thats the definition of squaring
  * mod3 is true because the while cases partition integers into negative, [0,1,2], and rest of positive integers
  * Then for each case it adds or substracts 3 as needed until the number gets to 0 1 or 2, the invariant here is the fact that
  * the mod3 of this number does not change, because subtracting/adding 3 to any number when you care about mod 3 does not change the 
  * mod3 value
  * Largest perfect squares is true because the invariant is that x^2 is less than n, 
  */