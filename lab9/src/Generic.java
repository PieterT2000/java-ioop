// How to write a generic class
package src;

public class Generic<T> {
  private T t;

  Generic(T val) {
    t = val;
  }

  public T get() {
    return t;
  }

  public void set(T val) {
    t = val;
  }
}