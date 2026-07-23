class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        Deque<Integer> dq = new ArrayDeque<>();

        ArrayList<Integer> list = new ArrayList<>();

        for(int right = 0; right < nums.length; ++right){
            
            // if we reach window add to list
            if (!dq.isEmpty() && dq.peekFirst() <= right - k) {
                dq.pollFirst();
            }

            // remove the elements 
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]){
                dq.pollLast();
            }
            // insert index
            dq.offerLast(right);
            
            // 1 3 -1 -3 5 3 6 7 
            // 3 -1
            // 0 1 2 3

            if(right >= k - 1){
                list.add(nums[dq.peekFirst()]);
            }
        }

        int[] arr = new int[list.size()];

        for(int i = 0; i < list.size(); ++i){
            arr[i] = list.get(i);
        }
        return arr;
    }
}