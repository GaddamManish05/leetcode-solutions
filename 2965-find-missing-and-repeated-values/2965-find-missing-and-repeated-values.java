class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                max = Math.max(max,grid[i][j]);
                map.put(grid[i][j],map.getOrDefault(grid[i][j],0) + 1);
            }
        }

        int repeated = -1;
        int missing = -1;
        for(int key : map.keySet()){
            if(map.get(key) > 1){
                repeated = key;
                break;
            }
        }

        for(int i = 1 ; i < max * max; ++i){
            if(!map.containsKey(i)){
                missing = i;
                break;
            }
        }

        return new int[]{repeated,missing};
    }
}