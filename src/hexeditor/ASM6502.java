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
public class ASM6502
{
    MODE parsed_mode = MODE.INVALID;
    int parsed_operand = -1;
    int parsed_instruction = -1;
    int parsed_length = -1;
    TreeMap<String, Integer> labels;
    int program_counter;
    int pass;
    
    public ASM6502 (TreeMap<String, Integer> l, int pc, int p)
    {
        labels = l;
        program_counter = pc;
        pass = p;
    }
    
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
    
    private static boolean isBranch (String instr)
    {
        return instr.equals ("BCC") ||
                instr.equals ("BCS") ||
                instr.equals ("BEQ") ||
                instr.equals ("BMI") ||
                instr.equals ("BNE") ||
                instr.equals ("BPL") ||
                instr.equals ("BVC") ||
                instr.equals ("BVS");
    }
   
    private static boolean isJump (String instr)
    {
        return instr.equals ("JMP") ||
               instr.equals ("JSR");
    }
    
    private void parseOP (String instr, String op) throws Exception
    {
        if (op == null || op.isEmpty())
        {
            parsed_mode = MODE.IMPL;
            return;
        }
        
        op = op.toUpperCase();
        
        if (isBranch(instr))
        {
            parsed_mode = MODE.REL;
            if (pass == 1)
                return;
            Integer addr = labels.get(op);
            if (addr != null)
            {
                int diff = addr - program_counter - 2;
                parsed_operand = diff;
                return;
            }
        }
        else if (isJump (instr))
        {
            parsed_mode = MODE.ABS;
            if (pass == 1)
                return;
            Integer addr = labels.get(op);
            if (addr != null)
            {
                parsed_operand = addr;
                return;
            }
        }
        
        int k1;
        int k2;
        switch (op.charAt(0))
        {
            case '*':
            parsed_mode = MODE.REL;
            parsed_operand = readNumber (op.substring(1));
            break;
              
            case 'A':
            parsed_mode = MODE.ACC;
            break;
                
            case '#':
            parsed_mode = MODE.IMM;
            parsed_operand = readNumber (op.substring(1));
            if (parsed_operand > 0xff)
                throw new Exception ("Number too big");
            break;
        
            case '(':
            k1 = op.indexOf(",X)");
            k2 = op.indexOf("),Y");
            if (k1 > 0)
            {
                parsed_operand = readNumber (op.substring(1, k1));
                parsed_mode = MODE.INDX;
            }
            else if (k2 > 0)
            {
                parsed_operand = readNumber (op.substring(1, k2));
                parsed_mode = MODE.INDY;
            }
            else
            {
                parsed_operand = readNumber (op.substring(1, op.length()-1));
                parsed_mode = MODE.IND;
            }
            break;

            default:
            k1 = op.indexOf(",X");
            k2 = op.indexOf(",Y");
            if (k1 > 0)
            {
                parsed_operand = readNumber (op.substring(0, k1));
                parsed_mode = parsed_operand > 0xff ? MODE.ABSX : MODE.ZPX;
            }
            else if (k2 > 0)
            {
                parsed_operand = readNumber (op.substring(0, k2));
                parsed_mode = parsed_operand > 0xff ? MODE.ABSY : MODE.ZPY;
            }
            else
            {
                parsed_operand = readNumber (op);
                parsed_mode = op.length() >=4 ? MODE.ABS : MODE.ZP;
            }
            break;
        }
    }

    private static int findOpcode (String instr, MODE addr) throws Exception
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
    
    /**
     * Parses one instruction an returns an ASM6502 Object containing the result
     * @param instr The instruction
     * @param adr The operand
     * @throws Exception If smth. gone wrong
     */
    public void parse (String instr, String adr) throws Exception
    {
        parseOP (instr, adr);
        parsed_instruction = findOpcode (instr, parsed_mode);
        parsed_length = Opcode.opcodes[parsed_instruction].length; 
    }
}
