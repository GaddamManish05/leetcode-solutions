class Solution {
    private static int atMost(int[] nums,int k){
        int odd = 0;
        int left = 0;

        int max = 0;

        for(int right = 0; right < nums.length; ++right){
            if(nums[right] % 2 == 1) odd++;

            while(odd > k){
                if(nums[left] % 2 == 1) odd--;
                left++;
            }

            max += (right - left + 1);
        }

        return max;
    }
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums,k) - atMost(nums,k - 1);
    }
}