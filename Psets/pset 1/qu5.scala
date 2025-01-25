object qu5 {
    // Post: the nth Fibonacci number
    def fib(n: Int) : Int = {
        require(n>=0)
        if (n==0) 0
        else if (n==1) 1
        else {
            fib(n-1) + fib(n-2)
        }
    }

    def fibVerbose(n: Int, depth: Int): Int = {
        require(n >= 0)
        
        println("| " * depth + "fib(" + n + ")")
        
        val result = if (n == 0) {
            0
        } else if (n == 1) {
            1
        } else {
            fibVerbose(n-1, depth + 1) + fibVerbose(n-2, depth + 1)
        }
        
        println("| " * depth + "= " + result)
        
        result
    }
    def main(args: Array[String]): Unit = {
        /*
        println("Fibonacci of 0: " + fib(0))
        println("Fibonacci of 1: " + fib(1))
        println("Fibonacci of 5: " + fib(5))
        println("Fibonacci of 10: " + fib(10))
        
        arjun@Arjuns-MacBook-Air pset 1 % scala qu5.scala
        Compiling project (Scala 3.6.3, JVM (23))
        Compiled project (Scala 3.6.3, JVM (23))
        Fibonacci of 0: 0
        Fibonacci of 1: 1
        Fibonacci of 5: 5
        Fibonacci of 10: 55
        arjun@Arjuns-MacBook-Air pset 1 % 
         */
        println("Calculating fibonacci of 3:")
        fibVerbose(3, 0)


    }
}

/* Calculating fibonacci of 3:
fib(3)
| fib(2)
| | fib(1)
| | = 1
| | fib(0)
| | = 0
| = 1
| fib(1)
| = 1
= 2 */