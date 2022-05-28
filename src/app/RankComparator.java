package app;

import java.util.Comparator;

public class RankComparator implements Comparator<Caddie> {

  /**
   * The compare method returns positive values if the rank of c1
   * is greater than the rank of c2.
   * The Rank levels in increasing order are: C, B, AB, A, A/PRO, PRO.
   * These are modeled by an Enum Object, which encapsulates data types that model a 
   * set of discrete values.
   * 
   * @param c1 first caddie to be compared
   * @param c2 second caddie to be compared
   * @return positive int if {@code c1 > c2}, 0 if {@code c1 == c1}, negative int otherwise
   */
  public int compare(Caddie c1, Caddie c2) {
    return c1.getRank().ordinal() - c2.getRank().ordinal();
  }
}