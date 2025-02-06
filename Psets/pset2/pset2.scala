import org.scalatest.funsuite.AnyFunSuite


// Question 1 and 2
    /** I know that inputs to functions cant actualy be manipulated properly or have some restrictions put on them.
    At least, to the effect that If I care about changing these values I create copies. 
    Perhaps then this code will have some sort of error. 
    Naively what I think this code sohuld do is create a new pointer t which points to the same place x points to.
    Then x points to the same place y points to. T points now to the same place x pointss to which points to where y points to so every var
    is now pointing to the same value as y was initially. I don't think this will actually happen because thats not how inputs to functions work in scala
    Lets check the answer
    (a) This produces a compiler error “reassignment to val”. Parameters of procedures are
        value parameters: in other words, they are treated the same as variables introduced by
        val. Hence reassigning to them is an error. (Note: the same goes for for loops—we
        cannot reassign i inside for(i<-1 until 10){...}).
        Some languages allow variables to be passed by address (sometimes called var parameters); this introduces an interesting class of programming error, where one variable
        stands as an alias for another. Also, some languages allow the values of parameters
        to be changed, but those changes do not propogate outside the procedure, which just
        seems confusing to many. Scala allows neither of these.


    Ah this is quite interesting. So the exact error is reassignment to val and this works the same as if val x was declared at the start of the function.
    The same things happen in for loop (so it says val i <- 1) kk makes sense. Intersting stuff

    b) Intuively if a is introduced as val a, then I don't think we can change any part of it? Like the whole structure should be immutable.
    t should take on the value of a(i) which is fine. a(i) taking on the value of a(j) is something I am unsure about. I think I have done this is in questions
    and this actually does work out fine so the code would end swwapping values in index i and j around. But I am not sure why, does val before creating an array mean 
    you can still mess about with the actual values inside the array but maybe not append to the array ? Seems weirdish. Lets check the answer

    (b) This swaps the entries of a in positions i and j (assuming those are within the indices
        of a; otherwise it gives an array bounds error). The point is that a itself is a value
        parameter, but that doesn’t prevent us changing the individual entries within 

    Huh this is interesting. So a being a val doesnt imply everything inside a is a a val. There are some things to be said here but I waant to move on to more interseting questions.

    */

    // Question 2
    /** Ah so here we are not dealing with functional programming anymore — functions *can* have side effects.

    Here the nasty function is nasty in that we are elitist functional programers and ew nasty functiosn with side effects ew.
    y = 1 is a side effect of the function (something that the funciton does not return), and I would say nasty(x prints out 6 + 1= 7
    The only question is does lazy evaluation change things? Lets see so if nasty(x) happens then we must compute 2*x to get that value but we delay doing this
    until we get the value of y i think? so maybe lazy evaluation changes this and i place 30% probability on the output being 11. Lets see

    Answer to question 2 †
        Scala evaluates the arguments of + (and, indeed, of any operator) from left to right (ref. Programming in Scala, Section 5.8). Hence in nasty(x) + y, nasty(x) is evaluated before y and
        it changes the contents of var y, so the result is 2 × 3 + 1 = 7. Swapping the arguments, as
        in y + nasty(x), gives a result of 5 + 2 × 3 = 11.    

    Ah i see, so lazy does matter in some sense. But at the end of the day the order from left to right will be how at the final step evalled substitions are made in
    I don't think i fully understand lazy should revisit that.
    */





//Question 3
class TestQuestion3 extends AnyFunSuite {
    /* Hmm so what are my equivalence classes here? What are my edge cases/boundary cases? Seemingly I want to test with 6 elements, 
    Then maybe with more than 6 and with 1 character strings, multi charactered strings, and a combination. In total this gives 6 uninque equivalence classes
    (multiplying 2*3)
    */

  // 1. Test case with exactly 6 elements, each a single character
  test("6 Elements and ONLY 1 character in each string") {
    val array = Array("e", "c", "b", "f", "d", "a")
    assert(array.sorted === Array("a", "b", "c", "d", "e", "f"))
  }

  // 2. Test case with more than 6 elements, each a single character
  test("More than 6 elements and ONLY 1 character in each string") {
    val array = Array("h", "e", "c", "b", "g", "f", "d", "a")
    assert(array.sorted === Array("a", "b", "c", "d", "e", "f", "g", "h"))
  }

  // 3. Test case with exactly 6 elements, each a multi-character string
  test("6 Elements and multi-character strings") {
    val array = Array("apple", "banana", "cherry", "date", "fig", "grape")
    assert(array.sorted === Array("apple", "banana", "cherry", "date", "fig", "grape"))
  }

  // 4. Test case with more than 6 elements, each a multi-character string
  test("More than 6 elements and multi-character strings") {
    val array = Array("zebra", "apple", "banana", "grape", "orange", "mango", "cherry")
    assert(array.sorted === Array("apple", "banana", "cherry", "grape", "mango", "orange", "zebra"))
  }

  // 5. Test case with a mix of single-character and multi-character strings
  test("Mix of single-character and multi-character strings") {
    val array = Array("x", "banana", "a", "cherry", "b", "apple")
    assert(array.sorted === Array("a", "apple", "b", "banana", "cherry", "x"))
  }

    // 6. Test case with more than 6 elements and a mix of single-character and multi-character strings
  test("Mix of single-character and multi-character strings") {
    val array = Array("x", "banana", "a", "cherry", "b", "apple", "guava")
    assert(array.sorted === Array("a", "apple", "b", "banana", "cherry","guava", "x"))
  // 6. Test case including empty strings
  test("Includes empty strings in the array") {
    val array = Array("", "banana", "apple", "", "cherry", "date")
    assert(array.sorted === Array("", "", "apple", "banana", "cherry", "date"))
  }

  // Extra: Test case with just empty strings to see if it behaves as expected
  test("Only empty strings") {
    val array = Array("", "", "", "")
    assert(array.sorted === Array("", "", "", ""))
  }

  // Extra: Test case with an empty array to verify no errors occur
  test("Empty array should remain empty after sorting") {
    val array: Array[String] = Array()
    assert(array.sorted.isEmpty)
  }

  // Also have to make 2 more classes of tests: 1) For anything other than dictionary order and 2) for any other utitlity of my choice.

  // Lets use sortWith and sort with the idea of longest strings to shortest strings.
  // Some equivalence classes here are: will it handle empty strings ? Will it handle cases of different strings having the same length fine? 
  // We should test at 6 and at 7 (as the length of the lists)
  
  def sortByLengthDesc(arr: Array[String]): Array[String] = {
    arr.sortWith(_.length > _.length)
  }

  // 1. Test with different-length strings
  test("Sort by length: Different-length strings") {
    val array = Array("apple", "banana", "kiwi", "grape", "blueberry", "fig")
    assert(sortByLengthDesc(array) === Array("blueberry", "banana", "apple", "grape", "kiwi", "fig"))
  }

  // 2. Test with some strings of the same length
  test("Sort by length: Some strings of the same length") {
    val array = Array("apple", "grape", "mango", "kiwi", "pear", "fig")
    assert(sortByLengthDesc(array) === Array("apple", "mango", "grape", "kiwi", "pear", "fig"))
  }

  // 3. Test with empty strings included
  test("Sort by length: Includes empty strings") {
    val array = Array("apple", "", "banana", "kiwi", "", "fig", "New york City is a city")
    assert(sortByLengthDesc(array) === Array("New york City is a city", "banana", "apple", "kiwi", "fig", "", ""))
  }

  // 4. Test with only empty strings
  test("Sort by length: Only empty strings") {
    val array = Array("", "", "", "","","", "","", "", "", "","","", "")
    assert(sortByLengthDesc(array)) === Array("", "", "", "","","", "","", "", "", "","","", "")
  }



  // 5. Edge case: Already sorted by length
  test("Sort by length: Already sorted array") {
    val array = Array("blueberry", "banana", "apple", "kiwi", "fig","m")
    assert(sortByLengthDesc(array) === Array("blueberry", "banana", "apple", "kiwi", "fig","m"))
  }


  // Now my own utility lets try reverse.

  // Utility function to reverse an array
  def reverseArray(arr: Array[String]): Array[String] = {
    arr.reverse
  }

  // 1. Test case with exactly 6 elements
  test("Reverse: Exactly 6 elements") {
    val array = Array("a", "b", "c", "d", "e", "f")
    assert(reverseArray(array) === Array("f", "e", "d", "c", "b", "a"))
  }

  // 2. Test case with more than 6 elements
  test("Reverse: More than 6 elements") {
    val array = Array("one", "two", "three", "four", "five", "six", "seven")
    assert(reverseArray(array) === Array("seven", "six", "five", "four", "three", "two", "one"))
  }

  // 3. Test case including empty strings
  test("Reverse: Includes empty strings") {
    val array = Array("", "banana", "apple", "", "cherry", "date")
    assert(reverseArray(array) === Array("date", "cherry", "", "apple", "banana", ""))
  }

  // 4. Edge case: Already reversed array
  test("Reverse: Already reversed array") {
    val array = Array("z", "y", "x", "w", "v", "u")
    assert(reverseArray(reverseArray(array)) === array) // Double reverse should return original
  }

}

}

/* Question 4
a) when timestep is intialized, because of slight rounding errors in floating point arithmetic. timeStep = 1/numSteps +- epsilon 
(where epsilon can be upper bounded by ~ 2.2 *10^-16)

In the case of very small but negative values of epsilon (and especially when numSteps is a large value), in each iteration of the while loop
a value slightly less than 1/numSteps is added to time. Over many iterations this leads to timeStep being smaller than it actually should have been 
with no rounding error and so after the numSteps'th iteration time is still < timeEnd as the rounding erros have accumulated. 
If this is the case this would lead to the loop being executed one more time until finally time >= timeEnd. 

The key idea being even if the rounding errors are very small, over a large number of iterations they can accumulate. (in fact if you
try this code with numsteps being on the order of 10^8 you get a lot more than just numsteps+1 iterations)

b) The idea here is that, we check this time< timeEnd condition at the end of the very last iteration of the while loop. Potentially before this iteration
time could have been incredibly close to timeEnd. So for the sake of upperbounding this let's say the value of time is upper bounded by timeEnd.
This incredible closeness can be the result of rounding errors, infact even rounding errors where epsilon is positive.
So in the worst case where episolon is positive 2.2*10^16, at the last iteration we have time = time + timestep (and substituting time 
as 1 and timestep as 1/numsteps + 2.2*10^16) we get that time may be innacurate by up to 1/numsteps + 2.2*10^16

c) We could make the condition of the while loop more "tolerant". Lets say time does not have to be < then timeEnd but can be less than timeEnd - 1/numstep * 2. 
In this case the worst case is when either time is slightly more than this value in which case our innacuracy is negative and lower bounded by -1/numsteps*2 or when its slightly less than 
the value in which case are innacuracy is positive and upperbounded by 1/numsteps*2

So all in all we decrease our innaaccuracy by a factor of 2. I think there may be smarter things here which could give us even more accurate results but its not obvious to me what they are
would be happy to talk about this in the tutorial.
*/


class testQuestion4 extends AnyFunSuite {
  /*Suggesting test data that would reveal the resulting bug if any
  a) Put a test in which patt does not appear in string and the test should be false, but with this bug it would give true because we never enter the while loop in the first place.
  b) Put patt (pattern? lol) at the end of the line which should give true but with this bug will give false cus you don't iterate to the very end
  c) Put a test in which patt does not appear in line which should return false, but here will throw an index out of bounds exception
  d) Put a case where patt(1:) exists in the line but not the entire patt, it should return false but with this bug will return true
  e) just put a case where patt is indeed in the line and you get an index out of bound exception when really you should get true
  f) There is no test which can catch this bug as if everything else is fine then this is not really a bug. (== relation is a subset of >= and we will never have a case in which k > K since k starts at 0 and maximally can only be +=1d k times )

  */

  /** Does patt appear as a substring of line? */
  def search(patt: Array[Char], line: Array[Char]) : Boolean = {
    val K = patt.size; val N = line.size
    // Invariant: I: found = (line[i..i+K) = patt[0..K) for
    // some i in [0..j)) and 0 <= j <= N-K
    var j = 0; var found = false
    while(j <= N-K && !found){
      // set found if line[j..j+K) = patt[0..K)
      // Invariant: line[j..j+k) = patt[0..k)
      var k = 0
      while(k<K && line(j+k)==patt(k)) k = k+1
      found = (k==K)
      j = j+1
      }
      // I && (j=N-K+1 || found)
      // found = ( line[i..i+K) = patt[0..K) for some i in [0..N-K+1) )
      found
    }

  test("Bug (a): Setting found to true initially") {
  val patt = "abc".toCharArray
  val line = "xyzdef".toCharArray
  assert(!search(patt, line)) // Should return false, but with the bug, it returns true
  } 

  test("Bug (b): Changing <= to < in the while loop condition") {
    val patt = "def".toCharArray
    val line = "abcdef".toCharArray
    assert(search(patt, line)) // Should return true, but with the bug, it returns false
  }

  test("Bug (c): Changing N-K to N-K+1 in the while loop condition") {
    val patt = "ghi".toCharArray
    val line = "abcdef".toCharArray
    assertThrows[IndexOutOfBoundsException] {
      search(patt, line) // Should return false, but with the bug, it throws an exception
    }
  }

  test("Bug (d): Changing k = 0 to k = 1") {
    val patt = "abc".toCharArray
    val line = "xabc".toCharArray
    assert(!search(patt, line)) // Should return false, but with the bug, it returns true
  }

  test("Bug (e): Changing < to <= in the inner while loop condition") {
    val patt = "abc".toCharArray
    val line = "abcde".toCharArray
    assertThrows[IndexOutOfBoundsException] {
      search(patt, line) // Should return true, but with the bug, it throws an exception
    }
  }
}

object nonTestQuestions {
  // question 6
  def findRepeats(a: Array[Char]) : Int =  {
    var n = 0
    val sizeArray = a.size
    while (n < sizeArray){
      var i = 0
      var nValueWorks = true
      while (i<sizeArray-n){
        if(a(i) != a(n+i)){
          nValueWorks = false
        }
        i += 1
      } 
      if (nValueWorks) {
        return n
      }
      n += 1
    }
    return sizeArray
  }
  // question 7
  def exists(p : Int => Boolean, N : Int): Boolean = { // neat how Int => is kind of similar to haskell types
    var exists = false
    var j = 0 
    // Invariant I: exists = ∃i ∈ [0 . . j) `dot`  p(j) for  0<= j <= N
    // Invariant holds as [0,0) is an empty set and exists is false
    while (j < N){
      //Invariant still holds exists = ∃i ∈ [0 . . j) `dot`  p(j) for  0<= j <= N from previous step 

      if (p(j)){
        exists = true
      }
      j+= 1
      // If p(j) is newly true then exists is true and then invariant holds as we add j to the set of Integers we are considering (since j +1 is now the open upper bound)
      // and we have switched the value of exist to true
      // If p(j) was true from before then adding j to our set does not make it not true since this is an existence claim not a universal train
      // If p(j) was false before and still false then we don't change exist because we should not

    }
    return exists 
    // j <= N since j<N before being added to 1 (and we are considering integers only)
    // Invariant holds from logic above
  


}
  // question 8
    /* a)
    m >= q/p and m has to be the value which makes m the largest possible reciprocal. So in effect we are finding the minimum
     value of m such that it satifies m >= q/p. In fact this is the ceiling of q/p.
    
    > var m = (q + p -1) / p

    // don't want to find the ceiling function, the above should be fine for ints

    /* b)
    " In each iteration there's no need to reduce the new fraction p'/q' to its lowewst terms because its a ratio and things get cancelled out anwyays"
    */

    i = 0
    while (p > 0){
      m = (q + p - 1)/p  // this gives you your most up to date value of m
      p = p*m - q
      q = q* m
      // the above two equations fall otu of p/q -1/m
      d(i) = m
      i += 1 
    }

    */

    /* part c) 
    ُ  The proof lies in this theorem : a * ceiling (b/a) < a + b  which can be trivially proved as ceiling b/a < b/a + r where r is upper bounded by 1 (and can never be 1)
    ONce we get this then 
      it follows that pm - q < p as pm is less then p + q

      
    */
    /*
     part d

     maybe i should have inducted for the previous part anwyays ill induct for this one

    Wait but why should i induct when there is a seemingly straighforwad idea
    lets assume d(i) = ceiling q/p
    then we know that d(i + 1 )= ceiling q*(d(i))/ p(d(i)) - q
    here the numerator is as big as d(i) * q and the denominaotr ( as shown in a previous part c) is smalelr then the deminoator ). And since 
    so when we divide this its obviosuly going to be biger than i. Proof by obviousness. maybe induction is the rigorous way to go?
    I think no theres a rigorous simple algebraic proof along the lines i was looking for that ill find in the tute.
     */


    // qu 9
    def fastexp(base: Int, exp: Int) : Int = {
      if (exp==0) {
        return 1
      }
      if ((exp % 2) == 1){
        return fastexp(base,exp-1)*base
      }
      else {
        val t = fastexp(base,exp/2)
        return t*t
      }
     }
    def floorlog3(x : Int): Int = {
     var i = 0 // we can start at 0 since x >= 1

     
     while (true){
      if (fastexp(3,i) >=x ){
        return i -1 
      }
      i += 1
     }
    }

    // qu 10

    def eval(a: Array[Double], x: Double) : Double = {
      var sum = 0
      val n = a.size
      var i = 0 
      while (i<n){
        sum += a(i)*fastexp(x,i)
        i += 1
      }
      sum
      /* so this way of doing it means that (visibly) we have n multiplications, one multriplication for each coef * x value. But we know that under the hood
      there are more multiplications going in fastexp. fastexp is of log n time complexity.  This is a nested recurrence and one that i dont immediately
      know how to solve. but in any case there are more then n multiplications going on over here.

      I mean definately i see the obvious wasted computation, we only need to calculate x^n-1 and this gives us all the other degrees less than n-1
      as long as we store our work in an array. But if we do this then we still have 2n multiplications going on.
   
      I mean in terms of time complexity this is kind of nice cus we are down to O(n)

      But the question specifically says use only n multplicaitons. hmm.. Honestly dont see a way here unless its some crazy matrix multiplication??
      idk maybe what i have said si fine and the key idea is just to store the degrees of x to get O(n). will ask irwin in tute.
      */
    }
}
