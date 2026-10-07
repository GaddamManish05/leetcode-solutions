class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Maximum pile is the upper bound
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int ans = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Calculate hours needed at speed mid
            long hours = 0;

            for (int pile : piles) {
                hours += (pile + (long) mid - 1) / mid;
            }

            if (hours <= h) {
                // mid is possible
                ans = mid;
                high = mid - 1;
            } else {
                // mid is too slow
                low = mid + 1;
            }
        }

        return ans;
    }
}