/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

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

    private void compile (String label, String cmd, String args, int pass) throws Exception
    {
        int val;
        if (pass == 1 && label != null)
        {
            if (null != labels.put(label, program_counter))
                throw new Exception ("Label "+label+" already exists");
        }
        switch (cmd)
        {
            case ".ORG":
                val = HexTools.readHex6502(args);
                program_counter = val;
                break;

            case ".BYT":
                String[] bytes = args.split(",");
                for (String byte1 : bytes)
                {
                    val = HexTools.readHex6502Byte(byte1);
                    if (pass == 2)
                        hex.setByteInMemory(program_counter, val);
                    program_counter++;
                }
                break;

            default:
                ASM6502 p = ASM6502.parse(cmd, args);
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
        program_counter = 0;
        String[] lines = text.split("\n");
        out:
        for (String txt1 : lines)
        {
            int sep = txt1.indexOf(' '); // First blank
            String cmd;
            String args = null;
            if (sep == -1)
            {
                cmd = txt1.trim().toUpperCase();
            }
            else
            {
                cmd = txt1.substring(0, sep).trim().toUpperCase();
                args = txt1.substring(sep).replaceAll("\\s", "").toUpperCase();
            }

            if (cmd.isEmpty())
            {
                continue;
            }
            compile (null, cmd, args, 2);
        }
    }
}
