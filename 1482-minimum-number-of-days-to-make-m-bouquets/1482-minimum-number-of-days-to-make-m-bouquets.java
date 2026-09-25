class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        
        if((long) m * k > bloomDay.length) return -1; 
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num : bloomDay){
            if(min > num){
                min = num;
            }
            if(max < num){
                max = num;
            }
        }

        int low = min;
        int high = max;
        int ans = -1;
        int mid = 0;

        while(low <= high){
            mid = low + (high - low ) / 2;

            if(minNum(bloomDay,mid,k,m)){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            } 
        }
        return ans;

    }
    private boolean minNum(int[] bloomDay,int day,int k,int m){
        int count = 0;
        int b = 0;
        for(int bloom : bloomDay){
            if(bloom <= day){
                count++;
                if(count == k){
                    b++;
                    count = 0;
                }
            }else{
                count = 0;
            }
        }
        if(b >= m) return true;
        else return false;
    }
}