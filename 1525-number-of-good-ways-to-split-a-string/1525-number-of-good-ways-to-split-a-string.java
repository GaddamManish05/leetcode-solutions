class Solution {
    public int numSplits(String s) {
        HashMap<Character,Integer> left = new HashMap<>();
        HashMap<Character,Integer> right = new HashMap<>();

        int ans = 0;

        for(char ch : s.toCharArray()){
            right.put(ch,right.getOrDefault(ch,0) + 1);
        }

        if(right.size() == 1){
            return s.length() - 1;
        }

        for(char ch : s.toCharArray()){
            left.put(ch,left.getOrDefault(ch,0) + 1);

            right.put(ch,right.get(ch) - 1);

            if(right.get(ch) == 0){
                right.remove(ch);
            }

            if(left.size() == right.size()){
                ans++;
            }
        }

        return ans;
    }
}