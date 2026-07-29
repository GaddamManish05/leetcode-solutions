class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();


        for(int i = 0; i < asteroids.length; ++i){
            int asteroid = asteroids[i];
            // +ve values
            if(asteroid > 0) stack.push(asteroid);
            else{
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)){
                    stack.pop();
                }

                // if it reaches negative values
                if(stack.isEmpty() || stack.peek() < 0){
                    stack.push(asteroid);
                }
                // if both -ve and +ve are equal
                else if(stack.peek() == Math.abs(asteroid)){
                    stack.pop();
                }
                // current destroyed
                else{
                    continue;
                }
            }
        }
        int[] ans = new int[stack.size()];

        for(int i = stack.size() - 1; i>=0; --i){
            ans[i] = stack.pop();
        }

        return ans;
    }
}


