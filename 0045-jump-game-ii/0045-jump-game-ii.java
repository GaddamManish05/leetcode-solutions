class Solution {
    private static int score(int i,int[] nums,int[] dp){
        if(i >= nums.length - 1) return 0;
        
        if(dp[i] != -1) {
            return dp[i];
        }
        int minScore = Integer.MAX_VALUE;

        for(int j = 1; j <= nums[i]; ++j){
            int min = score(i + j,nums,dp);

            if(min != Integer.MAX_VALUE){
                minScore = Math.min(minScore,1 + min);
            }
        }

        return dp[i] = minScore;
    }
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return score(0,nums,dp);
    }
}