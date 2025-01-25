/*Question 8
(a) Write a program that, given two positive integers m and n, computes their greatest
common divisor a.
(b) Now adapt your program to also calculate integers x and y such that a = mx + ny.
Give an invariant for each loop in the programs.
*/
import scala.io.StdIn.readLine


object findGCD {


    // PART A
    // Assume a >= b (we can make a function that does not need to assume this using this but that's not the essence of this question)
    // Post gcd(a,b)
    def gcdSimple(a : Int, b : Int): Int = {
        var A = a
        var B = b

        //Invariant I : gcd(A,B) = gcd(B, A % B)
        while (B > 0) {
            val R = A % B
            A = B
            B = R
            // invariant is maintained here
        }
        A

    }

    def extendedGcd(a: Int, b: Int): (Int,Int,Int) = {
        var A = a
        var B = b

        var coefficients_n_minus_two = Array[Int](1,0)
        var coefficients_n_minus_one = Array[Int](0,1)
        var coefficients_n = Array[Int](0,0)
        var firstIterationFlag = true
        //Invariant I: coefficients_n = coefficients n-2 - quotient * coefficients n-1   && 0<n < iterations
        while(B>0){
            if (firstIterationFlag == false) {
                coefficients_n_minus_two = coefficients_n_minus_one.clone()
                coefficients_n_minus_one = coefficients_n.clone()
            }
            
            val Quotient = A/B
            val Remainder = A % B
            coefficients_n(0) = coefficients_n_minus_two(0) - Quotient * coefficients_n_minus_one(0)
            coefficients_n(1) = coefficients_n_minus_two(1) - Quotient * coefficients_n_minus_one(1)
            // Invariant is maintained here
            A = B 
            B = Remainder
            firstIterationFlag = false
        }
        (A, coefficients_n_minus_one(0), coefficients_n_minus_one(1))
        // invariant is maintained

    }

    
    
    
    // Main function to allow user input (courtesy claude!)
    def main(args: Array[String]): Unit = {
        println("Welcome to the Extended GCD Calculator!")
        println("Enter two positive integers separated by a space, or 'q' to quit.")

        var continue = true
        while (continue) {
            val input = readLine("Input: ").trim

            if (input.toLowerCase == "q") {
                continue = false
                println("Exiting the program. Goodbye!")
            } else {
                val parts = input.split("\\s+")
                if (parts.length == 2 && parts.forall(_.forall(_.isDigit))) {
                    val m = parts(0).toInt
                    val n = parts(1).toInt

                    val gcd = gcdSimple(m, n)
                    val (gcdExtended, x, y) = extendedGcd(m, n)

                    println(s"The GCD of $m and $n is: $gcd")
                    println(s"The Extended GCD result is: gcd($m, $n) = $gcd = $m * ($x) + $n * ($y)")
                } else {
                    println("Invalid input. Please enter two positive integers or 'q' to quit.")
                }
            }
        }
    }
}

// Example output
/*
Welcome to the Extended GCD Calculator!
Enter two positive integers separated by a space, or 'q' to quit.
Input: 112
Invalid input. Please enter two positive integers or 'q' to quit.
Input: 112 34
The GCD of 112 and 34 is: 2
The Extended GCD result is: gcd(112, 34) = 2 = 112 * (7) + 34 * (-23)
Input: 765 450
The GCD of 765 and 450 is: 45
The Extended GCD result is: gcd(765, 450) = 45 = 765 * (3) + 450 * (-5)
Input: */