### Important ideas

Invariants in while loops


### Improvements 
Invariant write more clearly. I think I did a mid job here. Probably should get better at writing rigorous invariants.

Syntax and simpleness, I think maybe I had redundnat comments plus maybe overly complicated code in some places? Not sure where. Concrete tips on stuff like syntactic sugar would be nice

More consitency with like how i structure each question (eg comment the actual quesiton at the top, then comment first thoguhts etc). More consistent commits so you can see my thought process

Possibly a lot more writing this in a bit of a rush so cant enumerate.

### Qu1
This was largely fine, I think for the mod question the case of negative numbers might be something that people could miss, as they should be handled differently.

I didn't write down invariants for this, maybe we can go over that in the tute. 

### Qu3
Yeah so knowing how -inf is represented in scala would be nice, I could also google this up.

### Qu4**
I found this hard, I reckon the dep has high standards for clean code. Some things like the preference for many comments I find slighly off putting but this is somethign I should probably get in the habbit of. I don't think any of my functions were written as cleanly as qu4 and so this might be an issue. Concrete feedback on where I could have written cleaner more documented code would be nice.

Yeah in general I didn't see anything wrong with qu4 beyond what I mentioned and those were nitpicks to be honest. Curious as to what the solution is.

### Qu5
Yeah printing was nice, I like how you can do print arithmetic like in python, nifty.

### Qu8*
Spent loads of time on this (see commit history if you want). Basically my first idea was to do the most obvious thing which is to create lists tracking A,B and the quotient value in the first pass. And have a second pass where you back substitute to find the coefficients. This is because, this is the way we do it manually and so I wanted to action-to-action transcribe it into code.

The code was really complicated and I kept running into type errors and other bugs. So I started afresh, went to the whiteboard and found the reccurence relation **Rn = Rn-2 - Q* Rn-1** which was the key to solving this question quite elegantly. I mean essentially the reccurence falls out of the obvious equaiton A = QB + R => R = A -QB , and then because R gets subbed into B which later gets subbed into A, then A can be written as R two iterations ago and B can be written as R in the last iteration.

### Qu9

After spending loads of time on 8, this was i think simple? Just track the max value as you iterate through the list.