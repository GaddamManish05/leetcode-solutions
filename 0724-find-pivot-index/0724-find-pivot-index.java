class Solution {
    public int pivotIndex(int[] nums) {
        int[] leftSum = new int[nums.length];
        int[] rightSum = new int[nums.length];

        int sum = 0;

        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
            leftSum[i] = sum;
        }

        sum = 0;

        for(int i = nums.length - 1; i >= 0; i--){
            sum += nums[i];
            rightSum[i] = sum;
        }

        for(int i = 0 ; i < nums.length; ++i){
            if(leftSum[i] == rightSum[i]){
                return i;
            }
        }
        return -1;
    }
}