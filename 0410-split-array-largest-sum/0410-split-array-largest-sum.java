class Solution {
    public int splitArray(int[] nums, int k) {
        
        int low = 0 , high = 0;
        for(int num : nums){
            low = Math.max(low,num);
            high += num;
        }

        int ans = high;
        int mid = 0;

        while(low <= high){
            mid = low + (high - low) / 2;

            if(canSplit(nums,mid,k)){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canSplit(int[] nums, int maxSum,int k){
        int sum = 0;
        int count = 1;

        for(int num : nums){
            if(sum + num <= maxSum){
                sum += num;
            }else{
                count++;
                sum = num;
            }
        }

        return count <= k ;
    }
}