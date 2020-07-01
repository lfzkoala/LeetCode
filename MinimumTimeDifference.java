/*
Minimum Time Difference

        Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
        Example 1:
        Input: ["23:59","00:00"]
        Output: 1

        Note:
        The number of time points in the given list is at least 2 and won't exceed 20000.
        The input time is legal and ranges from 00:00 to 23:59.
        答案：
        There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not. Then things become simple…

 */

class Solution {
    public int findMinDifference(List<String> timePoints) {
        //not bucket sort, just an array to record whether such time point exists, true or false.
        boolean[] buckets = new boolean[24*60];

        for(String timePoint: timePoints){

            int minute = Integer.parseInt(timePoint.substring(0,2), 10)*60+Integer.parseInt(timePoint.substring(3), 10);

            if(buckets[minute]){
                return 0;
            }else{
                buckets[minute] = true;
            }
        }

        int ans = Integer.MAX_VALUE, first = -1, prev = -1, current = -1;

        for(int i=0; i<buckets.length; i++){

            if(buckets[i]){
                if(first == -1){
                    first = i;
                }else{
                    current = i;
                    ans = Math.min(ans, current-prev);
                }

                prev = i;
            }


        }

        return ans = Math.min(ans, buckets.length-current+first);
    }
}

