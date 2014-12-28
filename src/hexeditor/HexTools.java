/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

/**
 *
 * @author Administrator
 */
public class HexTools
{
    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static int getHexIndex(char c)
    {
        for (int n = 0; n < digits.length; n++)
        {
            if (Character.toUpperCase(c) == Character.toUpperCase(digits[n]))
            {
                return n;
            }
        }
        return -1;
    }

    public static String toHex8(int in)
    {
        StringBuilder sb = new StringBuilder();
        sb.append (digits[(in >>> 4) & 15]);
        sb.append (digits[in & 15]);
        return sb.toString();
    }

    public static int readHex(String in)
    {
        return Integer.parseInt(in.trim(), 16);
    }

    public static String toHex16(int in)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(toHex8(in >> 8));
        sb.append(toHex8(in));
        return sb.toString();
    }
}
