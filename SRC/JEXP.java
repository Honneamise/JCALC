import java.util.*;

class JEXP
{
    public static Stack<Character> stack = null;

    public static int OperatorPrecedence(char op)
    {
        switch (op)
        {
            case '+':
            case '-':
            return 0;

            case '*':
            case '/':
            return 1;

            case '^':
            return 2;

            case '(': 
            case ')':
            return 3; 
        }

        return -1;//not an operator

    }

    public static String InfixToPostfix(String infix)
    {  
        stack = new Stack<Character>();

        char str[] = infix.toCharArray();

        String postfix = "";

        int count = 0;

        while(count<str.length)
        {  
            //Print the operand as they arrive.
            if(OperatorPrecedence(str[count])==-1)
            {
                while(count<str.length && OperatorPrecedence(str[count])==-1)
                {
                    postfix += str[count];
                    count++;
                }
                postfix += ' ';
                continue;
            }
            
            //If the stack is empty or contains a left parenthesis on top, push the incoming operator on to the stack.
            if(stack.empty() || stack.peek()=='(')
            {
                stack.push(str[count]);
                count++;
                continue;
            }
            
            //If the incoming symbol is '(', push it on to the stack.
            if(str[count]=='(')
            {
                stack.push(str[count]);
                count++;
                continue;
            }

            //If the incoming symbol is ')', pop the stack and print the operators until the left parenthesis is found.
            if(str[count]==')')
            {
                while(stack.peek()!='(')
                {
                    postfix += stack.pop();
                    postfix += ' ';
                }

                stack.pop();
                count++;
                continue;
            }

            //If the incoming symbol has higher precedence than the top of the stack, push it on the stack.
            if(OperatorPrecedence(str[count])>OperatorPrecedence(stack.peek()))
            {
                stack.push(str[count]);
                count++;
                continue;
            }
            
            //If the incoming symbol has lower precedence than the top of the stack, pop and print the top of the stack. 
            //Then test the incoming operator against the new top of the stack.
            if(OperatorPrecedence(str[count])<OperatorPrecedence(stack.peek()))
            {
                postfix += stack.pop();
                postfix += ' ';
                continue;
            }

            //If the incoming operator has the same precedence with the top of the stack then use the associativity rules. 
            if(OperatorPrecedence(str[count])==OperatorPrecedence(stack.peek()))
            {
                //If the associativity is from left to right then pop and print the top of the stack then push the incoming operator. 
                postfix += stack.pop();
                postfix += ' ';
                stack.push(str[count]);
                count++;
                continue;

                //If the associativity is from right to left then push the incoming operator.
                /*stack.push(val);
                count++;
                continue;*/
            }

        }

        //At the end of the expression, pop and print all the operators of the stack.
        while(!stack.empty())
        {
            char c = stack.pop();

            postfix += c;
            postfix += ' ';
        }

        return postfix;
    }

    
}
