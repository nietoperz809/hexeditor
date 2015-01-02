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

    private void setMemByte(int b, int pass) throws Exception
    {
        if (pass == 2)
        {
            hex.setByteInMemory(program_counter, b);
        }
        program_counter++;
    }

    /**
     * Compiles single line
     *
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
            {
                throw new Exception("Label " + sort.label + " already exists");
            }
        }

        if (sort.cmd == null) // only label
        {
            return;
        }

        sort.cmd = sort.cmd.toUpperCase();
        switch (sort.cmd)
        {
            case ".ORG":
                val = HexTools.readNumber(sort.args);
                program_counter = val;
                break;

            case ".BYT":
                String[] bytes = sort.args.split(",");
                for (String byte1 : bytes)
                {
                    int len = byte1.length();
                    if (len>2 && byte1.charAt(0)=='\"' && byte1.charAt(len - 1)=='\"')
                    {
                        byte[] bb = byte1.substring(1, len - 1).getBytes();
                        for (byte b : bb)
                        {
                            setMemByte(b, pass);
                        }
                    }
                    else
                    {
                        val = HexTools.readHex6502Byte(byte1);
                        setMemByte(val, pass);
                    }
                }
                break;

            default:
                ASM6502 p = new ASM6502(labels, program_counter, pass);
                p.parse(sort.cmd, sort.args);
                setMemByte(p.parsed_instruction, pass);
                if (p.parsed_length >= 2)
                {
                    setMemByte(p.parsed_operand, pass);
                }
                if (p.parsed_length == 3)
                {
                    setMemByte(p.parsed_operand >>> 8, pass);
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
            Partitioner sort = new Partitioner(txt1);
            compile(sort, 1);
        }

        System.out.println("Pass 2");
        program_counter = 0;
        for (String txt1 : lines)
        {
            Partitioner sort = new Partitioner(txt1);
            compile(sort, 2);
        }
    }
}
