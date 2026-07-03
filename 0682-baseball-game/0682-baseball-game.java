class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for(String str : operations){
            if(str.equals("+")){
                int val1 = stack.pop();
                int val2 = stack.peek();
                stack.push(val1);
                stack.push(val1 + val2);
            }
            else if(str.equals("D")){
                stack.push(2 * stack.peek());
            }
            else if(str.equals("C")){
                stack.pop();
            }
            else{
                stack.push(Integer.parseInt(str));
            }
        }

        int sum = 0;

        while(!stack.isEmpty()){
            sum += stack.pop();
        }

        return sum;
    }
}