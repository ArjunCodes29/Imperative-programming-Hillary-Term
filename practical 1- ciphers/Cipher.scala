object Cipher{
  /** Bit-wise exclusive-or of two characters */
  def xor(a: Char, b: Char) : Char = (a.toInt ^ b.toInt).toChar

  /** Print ciphertext in octal */
  def showCipher(cipher: Array[Char]) =
    for(c <- cipher){ print(c/64); print(c%64/8); print(c%8); print(" ") }

  /** Read file into array */
  def readFile(fname: String) : Array[Char] = 
    scala.io.Source.fromFile(fname).toArray

  /** Read from stdin in a similar manner */
  def readStdin() = scala.io.Source.stdin.toArray

  /* ----- Functions below here need to be implemented ----- */

  /** Encrypt plain using key; can also be used for decryption */

  def encrypt(key: Array[Char], plain: Array[Char]) : Array[Char] = {
    val n = plain.size
    val size_key = key.size
    var cipher = new Array[Char](n) // Cipher would be same length as the text to encrypt
    var i = 0
    while (i<n) {
        val correct_key_index = i % size_key // The modulo allows us to basically string the keys consequetively
        cipher(i) = xor(plain(i), key(correct_key_index)) 
        i += 1
    }
    cipher
  }

  /** Try to decrypt ciphertext, using crib as a crib **/
 
  
  def tryCrib(crib: Array[Char], ciphertext: Array[Char]) : Unit = {
    val size_ciphertext = ciphertext.size
    val crib_size = crib.size
    val key_characters = new Array[Char](crib_size)
    var i = 0 //index which tracks where in the ciphertext we are

    // First loop is for iterating through the ciphertext until we find J's that work. 
    while (i < (size_ciphertext - crib_size)){
        var j = 0
        //  This is while loop is to populate the keycharacters. Because xor is cancellable, if the crib is correct, we can use it to get back the key.
        while (j< crib_size) {
            key_characters(j) = xor(ciphertext(i+j),crib(j))
            j += 1
        }

        // This part finds the smallest J such that key_characters[0,K-J) = key_characters[J, K) where K is key_chareacters.size and where J <= key_chara eters.size
        var J = findingJ(key_characters)

        // If the J was <= K -2 (and K = crib_size = key_characters.size)
        if (J <= crib_size -2){
          var key = new Array[Char](J)
          val offset = (i % J) // How many letters offset is the key? 0 means the first letter of key characters is the first letter of the key. One means the First letter of key charaters is the second letter of the key and so on.
          if (offset == 0) { // Then we can simply set key to the slice from 0,J as we know key.size = J
            key = key_characters.slice(0, J)
          } 
          else {
            val first_part_of_key = key_characters.slice(J - offset, J + J - offset ) // Slice gracefully handles index out of bounds, so we get as many key_characeters before the key potentially gets cut off
            val remaining_key_charecters = J - (key_characters.size - J + offset) // These are how many characters of the key we have remaining to get
            var second_part_of_key = new Array[Char](remaining_key_charecters) //  aand so this variable wil capture that
            if (remaining_key_charecters > 0) {
                // This slice collects the second part of the key which ends one before J- offset and starts at J - offset - remaining_key_characters 
                second_part_of_key = key_characters.slice(J - offset - remaining_key_charecters, J - offset)
            }
            
            key = first_part_of_key ++ second_part_of_key
          }  
          val decryptedString = encrypt(key, ciphertext) //encryption is symmetric!
          println(s"Key: ${new String(key)}, Decrypted String: ${new String(decryptedString)}")
          return
        }
        i += 1
    }
  }
  /* Output 
      ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scalac Cipher.scala
      warning: 1 deprecation (since 2.13.3); re-run with -deprecation for details
      1 warning
      ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crib Christmas message
      Key: RUDOLF, Decrypted String: Dear Santa, Please bring me a new bike for Christmas, love John
  */

  def findingJ(keyChars: Array[Char]) : Int =  {
    val K = keyChars.size
    var j = 1 
    var currentSmallestJ = K + 1 // setting it bigger than possibe at the start (+1 is arbitrary, K alone or indeed K-1 would do)
    while (j <= (K- 2)) {
        
        if (keyChars.slice(0,K-j-1) sameElements keyChars.slice(j,K-1)) {
            if (j < currentSmallestJ) {
                currentSmallestJ = j
            }
        }

        j += 1 
    }
    return currentSmallestJ
  }

  /** The first optional statistical test, to guess the length of the key */
<<<<<<< HEAD
  def crackKeyLen(ciphertext: Array[Char]) : Unit = ???

  /** The second optional statistical test, to guess characters of the key. */
  def crackKey(klen: Int, ciphertext: Array[Char]) : Unit = ???
=======
  
  /** Joe's notes
  "The first function, crackKeyLen, takes a cipher-text and counts the matching characters for various shifts. I made it try each shift amount from 1 to
30, so as to cover small multiples of any modest key size. For each shift
amount shift, the program counts the number of values of i for which
ciphertext(i) = ciphertext(shift+i), and prints the results. For example:7"
*/

/** Arjun's thoughts
This seems like a bunch of while loops should work (I could use for loops but I don't like them).
I want the outermost while loop to loop from 1 to 30 (remember we are assuming that the length of ciphertext will be signficantly greater than 30)
On the inner loop I would want to iterate through each index of ciphertext and add to a counter if ciphertext[inner_index] == ciphertext[ innder_index + outer_index]
The tricky thing here is ensuring there are no index out of bounds error, we can do this by having the inner loops while condition to be inner_index < cipher_text.size - outer_index
The final thing to consider is how to print out our data in a way that is like how Joe does it (so that we can use his command line tricks later on)
*/
  def crackKeyLen(ciphertext: Array[Char]) : Unit = {
    var shift_size = 1 // this will be our outer_index
    val lengthCipherText = ciphertext.size
    while(shift_size<=30) {
      var index = 0 // our inner_index
      var count_matches = 0 
      while (index < lengthCipherText - shift_size){
        if(ciphertext(index) == ciphertext(index+ shift_size)) {
          count_matches += 1
          
        }
        index +=1
      }
      
      println(s"$shift_size: $count_matches")
      shift_size += 1
      
    }
  }
/** Output for the above function looks like this 

ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crackKeyLen private1
1: 17
2: 9
3: 24
4: 9
5: 22
6: 16
7: 11
8: 9
9: 40
10: 19
11: 10
12: 11
13: 12
14: 14
15: 22
16: 11
17: 10
18: 46
19: 13
20: 15
21: 16
22: 15
23: 16
24: 17
25: 15
26: 10
27: 23
28: 16
29: 11
30: 28


Hmmmmm, it seems that 9 looks like the keylength given 9,18 and 27 seem like  local maximas (also 18 is the global maxima)

This is the output for private2

ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crackKeyLen private2
1: 17
2: 12
3: 14
4: 4
5: 12
6: 8
7: 5
8: 27
9: 8
10: 6
11: 6
12: 17
13: 9
14: 6
15: 11
16: 21
17: 17
18: 14
19: 7
20: 12
21: 4
22: 9
23: 11
24: 17
25: 9
26: 3
27: 3
28: 6
29: 2
30: 5

Here multiples of 8 are local maximas 
*/



/** Joe's notes

The function crackKey tries shifting the ciphertext by multiples of klen.
For each such shift amount s, the program again looks for indices i such
that ciphertext(i) = ciphertext(s+i). Instead of counting them, however,
the program works out what key character would explain the match if the
corresponding plain-text character were a space. It also works out which of
the klen characters of the key would be used in this position, and prints
the index and the character. To reduce the amount of output, I suppressed
characters that were not printable: that is, characters with codes that did
not fall between 32 and 127.

*/

/** Arjun's (half-baked) thoughts
An outer loop which iterates through multiples of klen (its not obvious what the stopping point for this is perhaps when m*klen > ciphertext.size
An inner loop which for each multiple of klen, looks at all the shifts that match and when a shift matches assumes its because of the spacebar being in the orignal text
Then an encryption on the ciphertext and spacebar to get a "vote" for what the key should be, and then we know which index of hte key this is because well we can either modulo
which is the crude option but a more elegant option must have something to do with indexing (ill be using modulo thank you very much)

nvm its easier to use the "elegant" option of indexing to get the key
*/


/** The second optional statistical test, to guess characters of the key. */
def crackKey(klen: Int, ciphertext: Array[Char]): Unit = {
  var shift_multiple = 1 // this will be our outer index
  val lengthCipherText = ciphertext.size

  while (klen * shift_multiple < lengthCipherText) {
    var key_index = 0 // this will be our 2nd index
    while (key_index < klen) {
      // btw we need another while loop here, specifically we have to iterate floor lengthCiphertext/ klen*shift_multiple
      var innerMostIndex = 0
      val boundOfLoop = lengthCipherText / (klen * shift_multiple) - 1

      while (innerMostIndex < boundOfLoop) {
        
        if (ciphertext(innerMostIndex * klen + key_index) == ciphertext(innerMostIndex * klen + key_index + klen * shift_multiple)) {
          val key_guess = xor(' ', ciphertext(innerMostIndex * klen + key_index))
          if (key_guess > 31 && key_guess < 128) {
            println(s"$key_index $key_guess")
          }
        }  
          
       
        innerMostIndex += 1
      }

      key_index += 1
    }
    shift_multiple += 1
  }
}

    
/**
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -decrypt KEMBERLEY private1
Ye not alzrmed, Maam, on r~ceiving ohis lett~r, by th~
appreheusion of rts contarning any;repetititn of thohe
sentim~nts, or ienewal o} those o}fers, whrch were wast nigho
so disgnsting to;you.  I lrite witsout any rntention;of painiug
you, oi humblin| myself,;by dwellrng on wihhes, whixh, for tse
happin~ss of booh, cannoo be too hoon forgttten; an the efftrt
which;the formztion and;the peruhal of thrs letter;must
occzsion shonld have yeen spar~d, had ntt my chaiacter rejuired
it;to be wrrtten and;read.  Ytu must, oherefore7 pardon ohe
freedtm with wsich I devand your;attentiou; your f~elings, R
know, wrll bestol it unwiwlingly, yut I demznd it of;your
jusoice.
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -decrypt PEMBERLEY private1
Be not alarmed, Madam, on receiving this letter, by the
apprehension of its containing any repetition of those
sentiments, or renewal of those offers, which were last night
so disgusting to you.  I write without any intention of paining
you, or humbling myself, by dwelling on wishes, which, for the
happiness of both, cannot be too soon forgotten; and the effort
which the formation and the perusal of this letter must
occasion should have been spared, had not my character required
it to be written and read.  You must, therefore, pardon the
freedom with which I demand your attention; your feelings, I
know, will bestow it unwillingly, but I demand it of your

woohooo!

now lets crack 2
justice*/

/** this is 2
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crackKeyLen private2
1: 17
2: 12
3: 14
4: 4
5: 12
6: 8
7: 5
8: 27
9: 8
10: 6
11: 6
12: 17
13: 9
14: 6
15: 11
16: 21
17: 17
18: 14
19: 7
20: 12
21: 4
22: 9
23: 11
24: 17
25: 9
26: 3
27: 3
28: 6
29: 2
30: 5
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crackKey 8  private2 | sort -n | uniq -c
      1 0 %
      1 0 +
      5 0 H
      1 1  
      4 1 O
      1 3  
      1 3 ?
      2 3 W
      1 4 k
      4 6 T
      4 7 S
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crackKey 16  private2 | sort -n | uniq -c
      1 0 H
      3 1 O
      1 2 G
      1 3  
      1 3 >
      2 3 W
      1 4 k
      2 6 T
      1 7 ]
      5 8 H
      2 9 O
      1 10 *
      1 10 G
      1 11 ?
      2 13 R
      2 15 S
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -crackKey 8  private2 | sort -n | uniq -c
      1 0 %
      1 0 +
      5 0 H
      1 1  
      4 1 O
      1 3  
      1 3 ?
      2 3 W
      1 4 k
      4 6 T
      4 7 S
ugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ scala Cipher -decrypt HOGWARTS private2
HOGWARTS SCHOOL of WITCHCRAFT and WIZARDRY
Headmaster: Albus Dumbledore (Order of Merlin, First Class, Grand
Sorc., Chf. Warlock, Supreme Mugwump, International Confed. of
Wizards)
Dear Mr Potter,
We are pleased to inform you that you have been accepted at Hogwarts
School of Witchcraft and Wizardry. Please find enclosed a list of all
necessary books and equipment. Term begins on 1 September. We await
your owl by no later than 31 July.
Yours sincerely, Minerva McGonagall. Deputy HeadmistressYugsapc2[~/Documents/Imperative-programming-Hillary-Term/practical 1- ciphers]$ 
*/
>>>>>>> 4f7462c (final commit, everything works,)

/** The main method just selects which piece of functionality to run */
  def main(args: Array[String]) = {
    // string to print if error occurs
    val errString = 
      "Usage: scala Cipher (-encrypt|-decrypt) key [file]\n"+
      "     | scala Cipher -crib crib [file]\n"+
      "     | scala Cipher -crackKeyLen [file]\n"+
      "     | scala Cipher -crackKey len [file]"

    // Get the plaintext, either from the file whose name appears in position
    // pos, or from standard input
    def getPlain(pos: Int) = 
      if(args.length==pos+1) readFile(args(pos)) else readStdin()

    // Check there are at least n arguments
    def checkNumArgs(n: Int) = if(args.length<n){println(errString); sys.exit}

    // Parse the arguments, and call the appropriate function
    checkNumArgs(1)
    val command = args(0)
    if(command=="-encrypt" || command=="-decrypt"){
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      print(new String (encrypt(key,plain)))
    }
    else if(command=="-crib"){
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      tryCrib(key, plain)
    }
    else if(command=="-crackKeyLen"){
      checkNumArgs(1); val plain = getPlain(1)
      crackKeyLen(plain)
    }      
    else if(command=="-crackKey"){
      checkNumArgs(2); val klen = args(1).toInt; val plain = getPlain(2)
      crackKey(klen, plain)
    }
    else println(errString)
  }
}