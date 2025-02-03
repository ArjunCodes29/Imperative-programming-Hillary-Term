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
  def crackKeyLen(ciphertext: Array[Char]) : Unit = ???

  /** The second optional statistical test, to guess characters of the key. */
  def crackKey(klen: Int, ciphertext: Array[Char]) : Unit = ???

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