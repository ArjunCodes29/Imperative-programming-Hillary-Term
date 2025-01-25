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