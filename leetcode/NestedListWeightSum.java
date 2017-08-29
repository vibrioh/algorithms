package leetcode;

import java.util.List;

/**
 * // This is the interface that allows for creating nested lists. // You should not implement it,
 * or speculate about its implementation
 */
interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}


public class NestedListWeightSum {

  public int depthSum(List<NestedInteger> nestedList) {
    return helper(1, nestedList);
  }

  private int helper(int depth, List<NestedInteger> nestedList) {
    int sum = 0;
    if (nestedList == null || nestedList.size() == 0) {
      return sum;
    }

    for (NestedInteger e : nestedList) {
      if (e.isInteger()) {
        sum += depth * e.getInteger();
      } else {
        sum += helper(depth + 1, e.getList());
      }
    }

    return sum;

  }
}
