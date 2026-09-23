class Solution {
    private boolean cap(int[] weights , int capacity, int days){
        int sum = 0;
        int d = 1;

        for(int w : weights){
            if(capacity < sum + w){
                d++;
                sum = w;
            }else{
                sum += w;
            }
        }

        return d <= days;
    }
    public int shipWithinDays(int[] weights, int days) {
        int max = 0 , sum = 0;
        for(int num : weights){
            if(max < num){
                max = num;
            }
            sum += num;
        }

        int low = max;
        int high = sum;

        int mid = 0;

        int ans = 0;

        while(low <= high){
            mid = low + (high - low) / 2;

            if(cap(weights , mid , days)){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
}