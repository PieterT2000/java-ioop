package src;

import src.Generic;

public class Main {
  public static void main(String[] args) {
    System.out.println("In main");
    Generic<String> myStr = new Generic<String>("Hello");
    System.out.println(myStr.get());
  }
}