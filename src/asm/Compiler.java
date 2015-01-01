/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm;

import util.HexTools;
import hexeditor.HexView;
import java.util.TreeMap;

/**
 *
 * @author Administrator
 */
public class Compiler
{
    private int program_counter = 0;
    private final HexView hex;
    TreeMap<String, Integer> labels = new TreeMap<>();
    
    /**
     * Constructor
     *
     * @param h HexView that receives output
     */
    public Compiler(HexView h)
    {
        hex = h;
    }

    class Partitioner 
    {
        public String label;
        public String cmd;
        public String args;
        
        Partitioner (String in)
        {
            in = in.trim().toUpperCase();
            int sep = in.indexOf(' ');
            if (sep == -1) // only one part
            {
                if (in.charAt(in.length()-1) == ':')  // label?                
                    label = in;
                else
                    cmd = in;
                return;
            }
            String temp = in.substring(0, sep); // fist part
            in = in.substring(sep+1).trim();  // rest
            if (temp.charAt(temp.length()-1) == ':')  // label?
            {
                label = temp;
                sep = in.indexOf(' ');
                if (sep == -1) // no args?
                {
                    cmd = in;
                    return;
                }
                cmd = in.substring(0, sep);
                args = in.substring(sep+1).replaceAll("\\s", "");
            }
            else // no label
            {
                cmd = temp;
                args = in.replaceAll("\\s", "");
            }
        }
    }
    
    /**
     * Compiles single line
     * @param label Label
     * @param cmd Opcode
     * @param args Argument
     * @param pass 1 or 2
     * @throws Exception If smth. goes wrong
     */
    private void compile (Partitioner sort, int pass) throws Exception
    {
        int val;
        if (pass == 1 && sort.label != null)
        {
            if (null != labels.put(sort.label, program_counter))
                throw new Exception ("Label "+sort.label+" already exists");
        }
        if (sort.cmd == null) // only label
            return;
        switch (sort.cmd)
        {
            case ".ORG":
                val = HexTools.readHex6502(sort.args);
                program_counter = val;
                break;

            case ".BYT":
                String[] bytes = sort.args.split(",");
                for (String byte1 : bytes)
                {
                    val = HexTools.readHex6502Byte(byte1);
                    if (pass == 2)
                        hex.setByteInMemory(program_counter, val);
                    program_counter++;
                }
                break;

            default:
                ASM6502 p = new ASM6502 (labels, program_counter, pass);
                p.parse(sort.cmd, sort.args);
                if (pass == 2)
                    hex.setByteInMemory(program_counter, p.parsed_instruction);
                program_counter++;
                if (p.parsed_length >= 2)
                {
                    if (pass == 2)
                        hex.setByteInMemory(program_counter, p.parsed_operand);
                    program_counter++;
                }
                if (p.parsed_length == 3)
                {
                    if (pass == 2)
                        hex.setByteInMemory(program_counter, p.parsed_operand >>> 8);
                    program_counter++;
                }
                break;
        }
    }

    /**
     * Compiles whole file
     *
     * @param text Multiple lines of 6502 statements inluding directives .org
     * and .byt
     * @throws Exception
     */
    public void compile(String text) throws Exception
    {
        labels.clear();
        String[] lines = text.split("\n");

        System.out.println("Pass 1");
        program_counter = 0;
        for (String txt1 : lines)
        {
            Partitioner sort = new Partitioner (txt1);
            compile (sort, 1);
        }

        System.out.println("Pass 2");
        program_counter = 0;
        for (String txt1 : lines)
        {
            Partitioner sort = new Partitioner (txt1);
            compile (sort, 2);
        }
    }
}
