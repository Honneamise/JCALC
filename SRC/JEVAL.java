import java.util.*;

class JERR extends Exception
{
    JERR()
    {
        super();
    }

    @Override
    public String getMessage() 
    {
        return "Invalid";
    }

}

public class JEVAL 
{
    public static String [] tokens = null;

    public static Stack<Float> stack = null;

    public static float Evaluate(String postfix) throws JERR
    {
        try
        {
            stack = new Stack<Float>();
            
            tokens = postfix.split(" ");

            int count = 0;

            while(count<tokens.length)
            {
                
                if( tokens[count].isBlank() || tokens[count].isEmpty() )
                {
                    count++;
                    continue;
                }

                switch(tokens[count])
                {
                    case "(":
                    case ")":
                        break;

                    case "+":
                        stack.push( stack.pop() + stack.pop() );
                        break;

                    case "-":
                        {
                            float b = stack.pop();
                            float a = stack.pop();
                            stack.push( a - b );
                        }
                        break;

                    case "*":
                        stack.push( stack.pop() * stack.pop() );
                        break;

                    case "/":
                        {
                            float b = stack.pop();
                            float a = stack.pop();
                            stack.push( a / b );
                        }
                        break;

                    default://is a number
                        float f = Float.parseFloat(tokens[count]);
                        stack.push(f);
                        break;
                }

                count++;
            }

            return stack.pop();
        }
        catch (Exception e) { throw new JERR(); } 
    }
}
