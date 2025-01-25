/*Question 8
(a) Write a program that, given two positive integers m and n, computes their greatest
common divisor a.
(b) Now adapt your program to also calculate integers x and y such that a = mx + ny.
Give an invariant for each loop in the programs.
*/

import scala.collection.mutable.ArrayBuffer


object findGCD {


    // PART A
    // Assume a >= b (we can make a function that does not need to assume this using this but that's not the essence of this question)
    // Post gcd(a,b)
    def euclids(a : Int, b : Int): Int = {
        var A = a
        var B = b

        while (B > 0) {
            var ACopy = A
            A = B
            B = ACopy % B
        }
        A

    }


    /* PART B
    * I see so now when we want to get the coefficients x and y, we must also track are quotient q.
    * I think writing down an Invariant I, could help not sure what it would be
     * The question does say loops so maybe I need more than one loop?
    * Yeah I mean the inefficient stupid thing is to have two loops, track the quotients in the first loop in a
    *  list and then use them to find integers
    * Pre: Assume a>= b are ints
     * Post: gcd of a and b as well as integers x y satisfying gcd(a,b) = xa + yb */
    def euclidsTrack(a : Int, b : Int): ArrayBuffer[(Int,Int,Int)] = {
        var A = a
        var B = b
        var track_reversed = ArrayBuffer[(Int, Int, Int)]()
       // tracking A, B and quotient values (reversed as we will want the values bottom up to find coefficients)
        var quotient  = 1
        var remainder = 0

        while (B > 0 ) {
            quotient = A/B
            remainder = A % B
            track_reversed += ((A, B, quotient))
            A = B
            B = remainder
        }
        var track = track_reversed.reverse
        return track

        }

    def findCoefficients(track : ArrayBuffer[(Int, Int, Int)], i : Int): (Int,Int) = {

        val n = track.size - 1 
        val orignalA = track(n)(0)
        val orignalB = track(n)(1)
        val A  = track(i)(0)
        val B  = track(i)(1)
        val Q = track(i)(2)
        // Invariant I: gcd = coefficientCount* track(i(1)) + coeeficientCount* track(i(2)), i <= n
        // Idea is tree-recusive, can I figure out track (i(1)) in terms of other numbers until I hit a base case, similarly for track(i(2) etc.
        
        var AstoreCoefficients = ArrayBuffer[(Int,Int)](1,1)
        var BstoreCoefficients = ArrayBuffer[(Int,Int)](1,1)
        val currentCoefficient1 = 1
        val currentCoefficient2 = - Q
        if (A == orignalB){
            BstoreCoefficients(1) += 1
        }
        else if (A != orignalA) {
            AstoreCoefficients += findCoefficients(track,i+2)
        }
        else {
            //nothing
        }
        if (B != orignalB) {
            BstoreCoefficients += findCoefficients(track,i+1)
        }

        val finalX = AstoreCoefficients(0) * currentCoefficient1 + BstoreCoefficients(0)*currentCoefficient2
        val finalY = AstoreCoefficients(1) * currentCoefficient1 + BstoreCoefficients(1)*currentCoefficient2
        (finalX,finalY)

    }
    
    def main(args: Array[String]): Unit = {
        val (a, b) = (112, 34)
        val g = euclids(a, b)
        println(s"GCD of $a and $b is $g")

        val (x, y) = findCoefficients(euclidsTrack((a,b)), 1)
        println(s"GCD of $a and $b is $g, with coefficients x = $x, y = $y")
        println(s"Check: ${a}*${x} + ${b}*${y} = ${a*x + b*y}")
  }

}