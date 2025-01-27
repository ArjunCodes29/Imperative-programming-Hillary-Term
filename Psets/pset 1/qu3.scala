/** Return max of a
 * Post: returns max(a) */

 def max(a : Array[Int]) : Int = {
    val n = a.size
    require (n>=1)
    var currentMax = a(0)
    var i = 0
    // Invariant I: currentMax = max(a[0..i)) && 0<= i <=n
    // Variant currentMax
    while (i < n) {
        // I && i < n
        if (currentMax < a(i)) {
            currentMax = a(i)
        }
        // currentMax = max(a[0..i)) && i<n
        i += 1
        // I
    }
    // I && i =n
    // currentMax = max(a[0..n))
    currentMax
 }


 // Yeah so just copied the strucutre of the milk bill program except what I carred about now was tracking and returning the maximal value
 // This doesnt work for negative ints, i should probably add some case checking for this or just make it work by setting 
 // curentMax to intiiate at -inf
