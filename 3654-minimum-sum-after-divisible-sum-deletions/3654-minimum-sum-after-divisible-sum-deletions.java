class Solution {
    public long minArraySum(int[] nums, int k) {
        int n = nums.length;

        long[] dp = new long[n + 1];
        dp[0] = 0;

        HashMap<Long,Long> map = new HashMap<>();
        map.put(0L,0L);

        long psum = 0;

        for(int i = 1; i <= n; ++i){
            psum += nums[i - 1];
            Long rem = psum % k;

            dp[i] = dp[i - 1] + nums[i - 1];

            if(map.containsKey(rem)){
                dp[i] = Math.min(dp[i],map.get(rem));
            }

            map.put(rem,Math.min(map.getOrDefault(rem,Long.MAX_VALUE),dp[i]));
        }
        return dp[n];
    }
}