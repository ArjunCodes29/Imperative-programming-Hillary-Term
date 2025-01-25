/*Question 8
(a) Write a program that, given two positive integers m and n, computes their greatest
common divisor a.
(b) Now adapt your program to also calculate integers x and y such that a = mx + ny.
Give an invariant for each loop in the programs.
*/



object findGCD {

    // Assume a >= b (we can make a function that does not need to assume this using this but that's not the essence of this question)
    // Post gcd(a,b)
    def euclids(a : Int, b : Int): Int = {
        var A = a
        var B = b

        while (B > 0) {
            var ACopy = a
            A = B
            B = ACopy % B
        }
        A

    }
    /* I see so now when we want to get the coefficients x and y, we must also track are quotient q.
 * I think writing down an Invariant I, could help not sure what it would be
 * The question does say loops so maybe I need more than one loop?
 * Yeah I mean the inefficient stupid thing is to have two loops, track the quotients in the first loop in a
 *  list and then use them to find integers */


    // Pre: Assume a>= b are ints
    // Post: gcd of a and b as well as integers x y satisfying gcd(a,b) = xa + yb
    def euclidsTrack(a : Int, b : Int): Int = {
        var A = a
        var B = b
        var quotients = ArrayBuffer[Int]()


        while (smallerNumber > 0 )
    }
}