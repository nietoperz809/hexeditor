/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
   
    public static String toHex16(int in)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(toHex8(in >> 8));
        sb.append(toHex8(in));
        return sb.toString();
    }
    
    public static int readHex(String in) throws Exception
    {
        try
        {
            return Integer.parseInt(in.trim(), 16);
        }
        catch (Exception ex)
        {
            throw new Exception ("Not a hex value");
        }
    }
    
    public static int readNumber (String in) throws Exception
    {
        if (in.charAt(0) == '$')
            return readHex (in.substring(1));
        else if (in.charAt(0) == '\'' && in.charAt(2) == '\'')
            return (in.charAt(1));
        return Integer.parseInt(in);
    }

    public static int readHex6502Byte (String in) throws Exception
    {
        int ret = readNumber (in);
        System.out.println(ret);
        if (ret < -128 || ret > 255)
            throw new Exception("Number not byte range");
        return ret;
    }

    public static int readHex6502Word (String in) throws Exception
    {
        int ret = readNumber (in);
        if (ret < -32768 || ret > 65535)
            throw new Exception("Number not word range");
        return ret;
    }
}
