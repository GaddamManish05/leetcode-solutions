class Solution {
    public int trap(int[] height) {

        int n = height.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];

        leftSum[0] = height[0];

        for(int i = 1; i < n; ++i){
            leftSum[i] = Math.max(leftSum[i - 1],height[i]);
        }

        rightSum[n - 1] = height[n - 1];

        for(int i = n - 2; i >= 0; --i){
            rightSum[i] = Math.max(rightSum[i + 1],height[i]);
        }

        int water = 0;

        for(int i = 0 ; i <n ; ++i){
            water += Math.min(leftSum[i],rightSum[i]) - height[i];
        }

        return water;
    }
}