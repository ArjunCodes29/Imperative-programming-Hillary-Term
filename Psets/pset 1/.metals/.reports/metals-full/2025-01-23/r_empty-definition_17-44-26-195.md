error id: _empty_/qu3$package.max().i().
file://<HOME>/Documents/GitHub/Oxford%20courses/Imperative%20programming/Psets/pset%201/qu3.scala
empty definition using pc, found symbol in pc: _empty_/qu3$package.max().i().
semanticdb not found
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
/** Return max of a
 * Post: returns max(a) */

 def max(a : Array[Int]) : Int = {
    val n = a.size
    var currentMax = -1 // Assuming Array will have positive integers (if not I could always make this minus infinity)
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

```

#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/qu3$package.max().i().