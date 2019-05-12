package amz;

import java.util.*;

public class InsertDeleteGetRandom {
    class RandomizedSet {
        List<Integer> nums;
        Map<Integer, Integer> pos;
        Random rand;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            nums = new ArrayList<>();
            pos = new HashMap<>();
            rand = new Random();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (pos.containsKey(val)) {
                return false;
            }
            pos.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!pos.containsKey(val)) {
                return false;
            }
            nums.set(pos.get(val), nums.get(nums.size() - 1));
            pos.put(nums.get(nums.size() - 1), pos.get(val));
            nums.remove(nums.size() - 1);
            pos.remove(val);
            return true;

        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }
}
