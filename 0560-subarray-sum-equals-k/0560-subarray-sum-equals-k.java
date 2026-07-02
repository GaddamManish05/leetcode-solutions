class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int len = 0;

        if(nums.length == 1 && k == 0) return 0;

        HashMap<Integer,Integer> map = new HashMap<>();


        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];

            if(sum == k) len++;

            if(map.containsKey(sum - k)){
                len += map.get(sum - k);
            }
            
            map.put(sum,map.getOrDefault(sum,0) + 1);
        }
        return len;
        
    }
}