class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();

        int left = 0;
        int size = 0;

        for(int i = 0 ; i < s.length(); ++i){
            char curr = s.charAt(i);
            
            map.put(curr, map.getOrDefault(curr,0) +1);

            while(map.get(curr) > 1){
                char leftchar = s.charAt(left);

                map.put(leftchar,map.get(leftchar) - 1);
                left++;
            }

            size = Math.max(size,i - left + 1);

        }

        return size;
    }
}