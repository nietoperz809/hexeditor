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
public class ASM6502
{
    MODE parsed_mode = MODE.INVALID;
    int parsed_operand = -1;
    int parsed_instruction = -1;
    int parsed_length = -1;
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append ("Mode:");
        sb.append (parsed_mode);
        sb.append (" Opcode:");
        sb.append (parsed_instruction);
        sb.append (" Operand:");
        sb.append (Integer.toHexString(parsed_operand));
        sb.append (" Len:");
        sb.append (parsed_length);
        return sb.toString();
    }
    
    public static enum MODE 
    {
    	ACC,	// A            - accumulator
	IMM,	// #vv          - immediate
	ZP,	// $vv          - zero page
	ZPX,	// $vv,y        - zero page, x
	ZPY,	// $vv,y        - zero page, y
	ABS,	// $vvvv        - absolute
	ABSX,	// $vvvv,x      - absolute, x
	ABSY,	// $vvvv,y      - absolute, y
	IMPL,	// NONE         - implied
	REL,	// *+4          - relative
	INDX,	// ($vv,x)      - indirect, x
	INDY,	// ($vv),y      - indirect, y
	IND,	// ($vvvv)      - indirect
        INVALID
    }

    public static final int addrModeLen[] = 
    {
	1, // accumulator
	2, // immediate
	2, // zero page
	2, // zero page, x
	2, // zero page, y
	3, // absolute
	3, // absolute, x
	3, // absolute, y
	1, // implied
	2, // relative
	2, // indirect, x
	2, // indirect, y
	3  // indirect
    };
    
    private static int readNumber (String in) throws Exception
    {
        try
        {
            if (in.charAt(0) == '$')
            {
                return Integer.parseInt(in.substring(1), 16) & 0xffff;
            }
            else
            {
                return Integer.parseInt(in) & 0xffff;
            }
        }
        catch (NumberFormatException e)
        {
            throw new Exception ("Malformed number");
        }
    }
    
    private static ASM6502 parseOP (String op) throws Exception
    {
        ASM6502 pa = new ASM6502();
        
        if (op == null || op.isEmpty())
        {
            pa.parsed_mode = MODE.IMPL;
            return pa;
        }
        
        op = op.toUpperCase();
        
        int k1;
        int k2;
        switch (op.charAt(0))
        {
            case '*':
            pa.parsed_mode = MODE.REL;
            pa.parsed_operand = readNumber (op.substring(1));
            break;
              
            case 'A':
            pa.parsed_mode = MODE.ACC;
            break;
                
            case '#':
            pa.parsed_mode = MODE.IMM;
            pa.parsed_operand = readNumber (op.substring(1));
            if (pa.parsed_operand > 0xff)
                throw new Exception ("Number too big");
            break;
        
            case '(':
            k1 = op.indexOf(",X)");
            k2 = op.indexOf("),Y");
            if (k1 > 0)
            {
                pa.parsed_operand = readNumber (op.substring(1, k1));
                pa.parsed_mode = MODE.INDX;
            }
            else if (k2 > 0)
            {
                pa.parsed_operand = readNumber (op.substring(1, k2));
                pa.parsed_mode = MODE.INDY;
            }
            else
            {
                pa.parsed_operand = readNumber (op.substring(1, op.length()-1));
                pa.parsed_mode = MODE.IND;
            }
            break;

            default:
            k1 = op.indexOf(",X");
            k2 = op.indexOf(",Y");
            if (k1 > 0)
            {
                pa.parsed_operand = readNumber (op.substring(0, k1));
                pa.parsed_mode = pa.parsed_operand > 0xff ? MODE.ABSX : MODE.ZPX;
            }
            else if (k2 > 0)
            {
                pa.parsed_operand = readNumber (op.substring(0, k2));
                pa.parsed_mode = pa.parsed_operand > 0xff ? MODE.ABSY : MODE.ZPY;
            }
            else
            {
                pa.parsed_operand = readNumber (op);
                pa.parsed_mode = op.length() >=4 ? MODE.ABS : MODE.ZP;
            }
            break;
        }
        
        return pa;
    }

    static int findOpcode (String instr, MODE addr) throws Exception
    {
        instr = instr.toUpperCase();
        for (int n=0; n<Opcode.opcodes.length; n++)
        {
            if (instr.equals(Opcode.opcodes[n].code) && addr.equals(Opcode.opcodes[n].addr))
            {
                return n;
            }
        }
        throw new Exception ("No such opcode");
    }
    
    public static ASM6502 parse (String instr, String adr) throws Exception
    {
        ASM6502 pa = parseOP(adr);
        pa.parsed_instruction = findOpcode (instr, pa.parsed_mode);
        pa.parsed_length = Opcode.opcodes[pa.parsed_instruction].length; 
        return pa;
    }
}
