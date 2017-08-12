package corejava;

/**
 * this is the first sample program in core Java chapter 3
 *
 * @author vibri on 7/6/2017.
 */
// the keyword 'public' is called an access modifier;
// these modifiers control the level of access other parts of
// a program have to this code

// the keyword 'class' reminds you that everything in a Java program
// lives inside a class
// CamelCase

// you need to make the file name for the source code the same as
// the name of the public class, with the extension .java appended

// according to the Java Language Specification
// the main method must be declared public

/*
public class ClassName {
  public static void main(String[] args) {
      program statements
  }
}
 */

public class FirstSample {
  // The keyword final indicates that you can assign to the variable once, and then its
  // value is set once and for all. It is customary to name constants in all uppercase.
  // It is probably more common in Java to create a constant so it’s available to multiple
  // methods inside a single class. These are usually called class constants. Set up a
  // class constant with the keywords 'static final'.
  public static final double CM_PER_INCH = 2.54;

  public static void main(String[] args) {
    // every statement must end with a semicolon
    // we are using the 'System.out' object and calling its 'println' method
    // 'object.method(parameters)'
    int realnum = 0;
    // it is considered good style to declare variables as closely as possible
    // to the point where they are first used
    if (realnum == Double.NaN) { // is never true
      System.out.println("This x is not gonna happen!");
    } else if (Double.isNaN(realnum)) { // check whether x is "not a number"
      System.out.println("This x is not gonna happen too!");
    } else {
      // Note that integer division by 0 raises an exception, whereas floating-point division
      // by 0 yields an infinite or NaN result.
      System.out.println("We will not use 'Hello, Would! JAVA\u2122" + "\n" + CM_PER_INCH);
    }
  }
}
