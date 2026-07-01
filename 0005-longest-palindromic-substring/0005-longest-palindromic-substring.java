class Solution {

    private int start = 0;
    private int maxLen = 0;

    private void expand(String s, int left, int right) {

        while(left >= 0 &&
              right < s.length() &&
              s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        int len = right - left - 1;

        if(len > maxLen) {
            maxLen = len;
            start = left + 1;
        }
    }

    public String longestPalindrome(String s) {

        for(int i = 0; i < s.length(); i++) {

            expand(s, i, i);       // odd

            expand(s, i, i + 1);   // even
        }

        return s.substring(start, start + maxLen);
    }
}