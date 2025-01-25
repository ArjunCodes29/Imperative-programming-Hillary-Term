/* Question 6
Write a non-recursive function
def fib(n : Int) : Int = ...
that calculates the nth Fibonacci number f ib(n). You should give an invariant for your code. */

object qu6 {
    def fib(n : Int) : Int = {
        // non recursive... oH NO
        require(n>=0)
        if (n==0)0
        else if (n==1) 1
        else {
            var last1 = 1
            // the value of the last fibonaci number computed before current one
            var last2 = 0
            // the value of the last to last fibonaci number computed before the current one  (ie the last1 of last1)
            val bound = n -1
            // since we are starting iteration from fib(2)
            var i = 0
            var temp = last1 + last2
            // Invariant I: temp = fib(i+2) && 0<= i < n-1
            // this just helps out, prob in cleaner code i wouldnt need this but its late and I want to get this working
            while (i<bound) {
                // I && i<n
                temp = last1 + last2
                // temp = fib(i+1) && i<n-1
                last2 = last1
                last1 = temp
                i += 1
                // I
            }
            // I && i= n-1
            // temp = fib(i +2)
            temp

        }
        


    }
        def main(args: Array[String]): Unit = {
        println("Enter a non-negative integer to calculate its Fibonacci number:")
        try {
            val input = scala.io.StdIn.readInt()
            if (input < 0) {
                println("Please enter a non-negative integer")
            } else {
                val result = fib(input)
                println(s"The ${input}th Fibonacci number is: $result")
            }
        } catch {
            case _: NumberFormatException => 
                println("Invalid input. Please enter a valid integer")
        }
    }
}

/* arjun@Arjuns-MacBook-Air pset 1 % scala qu6.scala
Compiling project (Scala 3.6.3, JVM (23))
Compiled project (Scala 3.6.3, JVM (23))
Enter a non-negative integer to calculate its Fibonacci number:
10
The 10th Fibonacci number is: 55
arjun@Arjuns-MacBook-Air pset 1 % 
 */