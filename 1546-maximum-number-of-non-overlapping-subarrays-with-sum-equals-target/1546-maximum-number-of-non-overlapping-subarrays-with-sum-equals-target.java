class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();


        int sum = 0;
        int ans = 0;
        set.add(0);

        for(int i = 0 ; i < nums.length; ++i){
            // add to sum;
            sum += nums[i];
            // if target = currSum - prefixSum
            // then prefixSum = currsum - target
            if(set.contains(sum - target)){
                ans++;
                sum = 0;
                set.clear();
            }

            set.add(sum);
        }

        return ans;
    }
}