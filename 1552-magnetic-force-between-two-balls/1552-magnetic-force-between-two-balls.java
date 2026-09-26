class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length - 1;
        int low = 1;
        int high = position[n] - position[0];

        int mid = 0;
        int ans = -1;

        while(low <= high){
            mid = low + (high - low) / 2;

            if(canReturnMax(position,mid,m)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canReturnMax(int[] position , int gap , int m){
        int last = position[0];
        int count = 1;
        for(int i = 1; i < position.length; ++i){
            int pos = position[i];
            if(pos - last >= gap){
                count++;
                last = pos;
            }
        }

        if(count >= m) return true;
        else return false;
    }
}