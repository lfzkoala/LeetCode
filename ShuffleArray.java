/*
Shuffle an Array
        Shuffle a set of numbers without duplicates.

        Example:

// Init an array with set 1, 2, and 3.
        int[] nums = {1,2,3};
        Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
        solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
        solution.reset();

// Returns the random shuffling of array [1,2,3].
        solution.shuffle();

        方法：

 */
import java.util.Random;
class Solution {

    private int[] array;
    private Random randomizer;

    public Solution(int[] nums) {
        array = nums;
        randomizer = new java.util.Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return array;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {

        int[] temp = array.clone();
        for(int j = 1; j<temp.length; j++){
            int i = randomizer.nextInt(j+1);
            swap(temp, i, j);
        }

        return temp;

    }

    private void swap(int[] temp, int i, int j){
        int t = temp[i];
        temp[i] = temp[j];
        temp[j] = t;
    }

}

/*
followup: what if the result of shuffle must be different the previous one?

 */
