/*
Author: Damon Pullan
ID: V00891444
Date Started: Jan.22 2018
Filename: VigenereCipher.java
Details: \CSC115\ Assignment1
*/
import java.util.*;
public class VigenereCipher implements Cipher{
  private String key;
  //constructor declared here
  public VigenereCipher(String key) {
    this.key = key;
  }
  /*
    Prints out an array
    Input: Integer array and a string of text to go before the array
    Output: A message that has the text first and then the printed array
  */
  private void dumpArray(int[] array, String text) {
    System.out.print(text + " ");
    String com = "";
    for (int i = 0; i < array.length; i++) {
      System.out.print(com + array[i]);
      com = ", ";
    }
  }
  /*
    Takes a string and converts it into an integer array based off of ascii characters
    Input: The text that is going to be converted
    Output: An integer array of numbers converted from the letters in the word
  */
  private int[] stringToIntArray(String text) {
    int[] temp = new int[text.length()];
    for (int i = 0; i < text.length(); i++) {
      char character = text.charAt(i);
      int ascii = ((int) character) % 97;
      temp[i] = ascii;
    }
    return temp;
  }
  /*
    Converts an integer array into a string based off of the numbers
    and their relation to ascii code.
    Input: An integer array
    Output: A string
  */
  private String intArrayToString(int[] encodedText) {
    //converts the int into a char array
    String[] tmp = new String[encodedText.length];
    for (int i = 0; i < encodedText.length; i++) {
      int xInt = encodedText[i] + 97;
      char ch = (char) xInt;
      tmp[i] = Character.toString(ch);
    }
    //converts the char array into a string
    StringBuilder sb = new StringBuilder(tmp.length * 10);
		for (int x = 0; x < tmp.length; x++) {
			sb.append(tmp[x]);
		}
		return sb.toString();
  }
  /*
    Encrypts plaintext using the key provided and a Vigenere Cipher
    Input: The plaintext
    Output: The encoded plaintext
  */
  public String encrypt(String plaintext) {
    int[] textCipher = stringToIntArray(plaintext.toLowerCase());
    int[] keyCipher = stringToIntArray(key.toLowerCase());
    int[] tp = new int[textCipher.length];
    for (int i= 0, j = 0; i < textCipher.length; i++) {
      tp[i] = ((textCipher[i] + keyCipher[j]) % 26);
      j = ++j % key.length();
    }
    String cipher = intArrayToString(tp);
    return cipher;
  }
  /*
    Decrypts the cipher text using the key provided and a Vigenere Cipher
    Input: The cipher text
    Output: The original plaintext
  */
  public String decrypt(String ciphertext) {
    int[] textPlain = stringToIntArray(ciphertext.toLowerCase());
    int[] key2Cipher = stringToIntArray(key.toLowerCase());
    int[] t = new int[textPlain.length];
    for (int i= 0, j = 0; i < textPlain.length; i++) {
      t[i] = ((26 + textPlain[i] - key2Cipher[j]) % 26);
      j = ++j % key.length();
    }
    String plText = intArrayToString(t);
    return plText;
  }
  /*
    Setter method that can change the key for the constructor
    Input: The new key
  */
  public void setKey(String key) {
    this.key = key;
  }
  //Main method for testing purposes
  public static void main(String[] args) {
    VigenereCipher v1 = new VigenereCipher("dd");
    int[] toNums = v1.stringToIntArray("blog");
    String test0 = v1.intArrayToString(toNums);
    System.out.println(test0);
    String test = v1.encrypt("blog");
    System.out.println(test);
    String test2 = v1.decrypt("eorj");
    System.out.println(test2);
  }
}
