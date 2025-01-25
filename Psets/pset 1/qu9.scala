/*Question 9
A hit in an array a[0..N) is an element that is larger than all elements to the left of it: thus
a hit occurs at an index j if whenever 0 ≤ i < j we have a(i) < a(j). Assuming N ≥ 1, write
a program (giving invariants for each loop) that counts the number of hits h in the array a.
For full marks, your code should run in time Θ(N).*/

/* Idea
Dumb, inefficient solution would be to iterate throught the array, and at each spot create a new while loop to see if its a hit.
This is insertion sort esque and is n^2, we can do much better...

All we have to know is if a given element (i) is greater than the max of all the elems indexed < i.
So we can iterate just once through the list, updating the max at each point

*/

object hits {


    def hits_On(array : Array[Int]) : Int = {
        var max = - 1 // probably want negative infinity...  lets just say my code only works on positive integers for now
        val n = array.size
        var i = 0
        var hits_count = 0
        while (i < n) {

            if (array(i) > max) {
                hits_count += 1
                max = array(i)
            }
            i += 1
        }
        hits_count - 1 // apparently the first elem doesnt count as a hit (but I think it should)

        
    }

    def main(args: Array[String]): Unit = {
        val array = Array(2, 3, 1, 5, 4, 6) // Example input array
        val result = hits_On(array) // Call the function
        println(s"Number of hits: $result") // Print the result
    }

}