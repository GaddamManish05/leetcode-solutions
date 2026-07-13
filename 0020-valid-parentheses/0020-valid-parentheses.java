class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        if(s.length() < 2) return false;

        for(char ch : s.toCharArray()){
            if(ch == '{' || ch == '(' || ch == '['){
                stack.push(ch);
            }else{
                if (stack.isEmpty()) return false;

                char top = stack.peek();

                if((top == '(' && ch == ')') || (top == '{' && ch == '}')  || (top == '[' && ch == ']') ){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }

        if(!stack.isEmpty()) return false;
        else return true;
    }
}