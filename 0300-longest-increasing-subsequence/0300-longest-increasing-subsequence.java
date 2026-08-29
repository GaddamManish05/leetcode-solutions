class Solution {
    public static int lis(int i,int pidx,int[] nums,int[][] dp){
        if(i == nums.length) return 0;
        if(dp[i][pidx + 1] != -1){
            return dp[i][pidx + 1];
        }
        int opt1 = 0;
        if(pidx == -1 || nums[pidx] < nums[i]){
            opt1 = 1 + lis(i+1,i,nums,dp);
        }

        int opt2 = lis(i+1,pidx,nums,dp);
        return dp[i][pidx + 1] = Math.max(opt1,opt2);
    } 
    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for(int i = 0; i < nums.length; ++i){
            for(int j = 0; j < nums.length; ++j){
                dp[i][j] = -1;
            }
        }
        return lis(0,-1,nums,dp);
    }
}