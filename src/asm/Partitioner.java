/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm;

/**
 *
 * @author Administrator
 */
public class Partitioner
{
    public String label;
    public String cmd;
    public String args;

    Partitioner(String in)
    {
        if (in.isEmpty())
        {
            return;
        }
        in = in.trim().toUpperCase();
        int sep = in.indexOf(' ');
        if (sep == -1)
        {
            if (in.charAt(in.length() - 1) == ':')
            {
                label = in;
            }
            else
            {
                cmd = in;
            }
            return;
        }
        String temp = in.substring(0, sep); // fist part
        in = in.substring(sep + 1).trim(); // rest
        if (temp.charAt(temp.length() - 1) == ':')
        {
            label = temp;
            sep = in.indexOf(' ');
            if (sep == -1)
            {
                cmd = in;
                return;
            }
            cmd = in.substring(0, sep);
            args = in.substring(sep + 1).replaceAll("\\s", "");
        }
        else // no label
        {
            cmd = temp;
            args = in.replaceAll("\\s", "");
        }
    } 
}
