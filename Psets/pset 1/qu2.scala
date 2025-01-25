/** Calculate sum of a 
  *  Post: returns sum(a)
  */
def findSum( a: Array[Int]) : Int = {
    val n = a.size
    var total = 0; var i = n-1
    // Invariant I: total = sum(a[i..(n-1)) &&  0<= i <= n
    while (i >= 0){
        // I && i>= 0
        total += a(i)
        // total = sum(a[i-1..n)) && i>0
        i -= 1
        // I
        }
        // I && i=n
        // total = sum(a[0..n)
    total
}

// Yeah I pretty much just copied slight 17 from lecture02 slides and replaced the parts which meant that i goes from 0 to n-1 with 
// code that makes i go from n-1 to


// Self correcting answer
// Apparently you should index to n not n-1, ah its just because they subtract 1 from i on the before adding to the total,
// then it should be identical!
// they mentioened that variant was i, i mean yeah that is obvious- ig i will do that next time. als
// ah also i should have used a closed interval instead of open interval (if open then i should have used n as my end indice)