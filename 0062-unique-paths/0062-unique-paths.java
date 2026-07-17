class Solution {
    public static int paths(int i,int j,int m,int n,int[][] dp){

        if(i >= m || j >= n) return 0;

        if(i == m - 1 && j == n - 1) return 1;

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int right = 0;
        int down = 0;

        right = paths(i+1,j,m,n,dp);
        down = paths(i,j+1,m,n,dp);

        dp[i][j] = right + down;

        return right + down;
    }

    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; ++i){
            Arrays.fill(dp[i],-1);
        }

        return paths(0,0,m,n,dp);
    }
}