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
public class Compiler
{
    private int origin = 0;
    private final HexView hex;

    public Compiler(HexView h)
    {
        hex = h;
    }

    public void compile(String text) throws Exception
    {
        origin = 0;
        String[] lines = text.split("\n");
        out:
        for (String txt1 : lines)
        {
            int sep = txt1.indexOf(' ');
            String cmd;
            String args = null;
            if (sep == -1)
            {
                cmd = txt1;
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

            int val;
            switch (cmd)
            {
                case ".ORG":
                    val = HexTools.readHex6502(args);
                    if (val == -1)
                    {
                        System.out.println("wrong arg");
                        break out;
                    }
                    origin = val;
                    break;

                case ".BYT":
                    String[] bytes = args.split(",");
                    for (String byte1 : bytes)
                    {
                        val = HexTools.readHex6502Byte(byte1);
                        try
                        {
                            hex.setByteInMemory(origin, val);
                            origin++;
                        }
                        catch (Exception ex)
                        {
                            System.out.println("Byte inser err");
                            break out;
                        }
                    }
                    break;

                default:
                    ASM6502 p = ASM6502.parse(cmd, args);
                    hex.setByteInMemory(origin, p.parsed_instruction);
                    origin++;
                    if (p.parsed_length == 3)
                    {
                        hex.setByteInMemory(origin, p.parsed_operand);
                        origin++;
                        hex.setByteInMemory(origin, p.parsed_operand >>> 8);
                        origin++;
                    }
                    if (p.parsed_length == 2)
                    {
                        hex.setByteInMemory(origin, p.parsed_operand);
                        origin++;
                    }
            }
        }
    }
}
