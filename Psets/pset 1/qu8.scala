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

        while (B > 0) {
            val R = A % B
            A = B
            B = R
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
        while(B>0){
            if (firstIterationFlag == false) {
                coefficients_n_minus_two = coefficients_n_minus_one.clone()
                coefficients_n_minus_one = coefficients_n.clone()
            }
            
            val Quotient = A/B
            val Remainder = A % B
            coefficients_n(0) = coefficients_n_minus_two(0) - Quotient * coefficients_n_minus_one(0)
            coefficients_n(1) = coefficients_n_minus_two(1) - Quotient * coefficients_n_minus_one(1)
            A = B 
            B = Remainder
            firstIterationFlag = false
        }
        (A, coefficients_n_minus_one(0), coefficients_n_minus_one(1))

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