class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int mid = 0;

        int first = -1;
        int sec = -1;

        while(left <= right){
            mid = left + (right - left) / 2;

            if(nums[mid] >= target){
                if(nums[mid] == target){
                    first = mid; 
                }
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        left = 0;
        right = nums.length - 1;

        while(left <= right){
            mid = left + (right - left) / 2;

            if(nums[mid] <= target){
                if(nums[mid] == target){
                    sec = mid;
                }
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return new int[]{first, sec};
    }
}