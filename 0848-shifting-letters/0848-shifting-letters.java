class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        
        int n = shifts.length;

        long[] suffix = new long[n];

        suffix[n - 1] = shifts[n - 1];

        for(int i = n - 2; i >= 0; --i){
            suffix[i] = suffix[i + 1] + shifts[i];
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; ++i){
            long curr = suffix[i];

            char ans = (char) ((s.charAt(i) - 'a' + curr) % 26 + 'a');

            sb.append(ans);
        }

        return sb.toString();

    }
}