class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Min Heap
        // int[] = {value, row, column}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Add first element of every row
        for (int row = 0; row < n; row++) {
            pq.offer(new int[]{matrix[row][0], row, 0});
        }

        // Remove smallest element k times
        while (k > 0) {

            int[] curr = pq.poll();

            int value = curr[0];
            int row = curr[1];
            int col = curr[2];

            k--;

            // Add next element from the same row
            if (col + 1 < n) {
                pq.offer(new int[]{
                    matrix[row][col + 1],
                    row,
                    col + 1
                });
            }

            // kth smallest element
            if (k == 0) {
                return value;
            }
        }

        return -1;


    }
}