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
public class Addr6502Parser
{
    MODE parsed_mode = MODE.INVALID;
    int parsed_operand = -1;
    int parsed_instruction = -1;
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append (parsed_mode);
        sb.append (" | ");
        sb.append (parsed_operand);
        sb.append (" | ");
        sb.append (Integer.toHexString(parsed_instruction));
        return sb.toString();
    }
    
    public static final String[] Instruction = new String[]
    {
        "ADC", "AND", "ASL", "BCC", "BCS", "BEQ", "BIT", "BMI", 
        "BNE", "BPL", "BRK", "BVC", "BVS", "CLC", "CLD", "CLI",
        "CLV", "CMP", "CPX", "CPY", "DEC", "DEX", "DEY", "EOR",
        "INC", "INX", "INY", "JMP", "JSR", "LDA", "LDX", "LDY",
        "LSR", "NOP", "ORA", "PHA", "PHP", "PLA", "PLP", "ROL",
        "ROR", "RTI", "RTS", "SBC", "SEC", "SED", "SEI", "STA", 
        "STX", "STY", "TAX", "TAY", "TSX", "TXA", "TXS", "TYA"
    };
  
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

    /**
	*	A table used when calculating the machine codes for an opcode.
	*	Two indeces are used. The first is the opcode define, see 'opcodes' defines at top of this file.
	*	The second is addressing mode, see two blocks up this file.
	*	By indexing into this table the machine code for the opcode in the addressing mode specified is aquired.
	*	If a -1 is aquired it means the opcode is illegal in that addressing mode.
	*	Usage:
	*	int opcode = machineCodeTab [opcode][addressing mode]
	*	@note The aquired machine code should be written to disk as an 8-bit unsigned byte, not an integer.
	*	(The integer format is for signalling illegal opcode with a negative one.)
	*/
    public static final int[][] machineCodeMatrix = {
    //	  ACC   IMM   ZP    ZPX   ZPY   ABS   ABSX  ABSY  IMPL  REL   INDX  INDY  IND
	{ -1  , 0x69, 0x65, 0x75, -1  , 0x6d, 0x7d, 0x79, -1  , -1  , 0x61, 0x71, -1  }, // ADC
	{ -1  , 0x29, 0x25, 0x35, -1  , 0x2d, 0x3d, 0x39, -1  , -1  , 0x21, 0x31, -1  }, // AND
	{ 0x0a, -1  , 0x06, 0x16, -1  , 0x0e, 0x1e, -1  , -1  , -1  , -1  , -1  , -1  }, // ASL
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x90, -1  , -1  , -1  }, // BCC
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0xb0, -1  , -1  , -1  }, // BCS
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0xf0, -1  , -1  , -1  }, // BEQ
	{ -1  , -1  , 0x24, -1  , -1  , 0x2c, -1  , -1  , -1  , -1  , -1  , -1  , -1  }, // BIT
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x30, -1  , -1  , -1  }, // BMI
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0xd0, -1  , -1  , -1  }, // BNE
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x10, -1  , -1  , -1  }, // BPL
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x00, -1  , -1  , -1  , -1  }, // BRK
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x50, -1  , -1  , -1  }, // BVC
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x70, -1  , -1  , -1  }, // BVS
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x18, -1  , -1  , -1  , -1  }, // CLC
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0xd8, -1  , -1  , -1  , -1  }, // CLD
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x58, -1  , -1  , -1  , -1  }, // CLI
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0xb8, -1  , -1  , -1  , -1  }, // CLV
	{ -1  , 0xc9, 0xc5, 0xd5, -1  , 0xcd, 0xdd, 0xd9, -1  , -1  , 0xc1, 0xd1, -1  }, // CMP
	{ -1  , 0xe0, 0xe4, -1  , -1  , 0xec, -1  , -1  , -1  , -1  , -1  , -1  , -1  }, // CPX
	{ -1  , 0xc0, 0xc4, -1  , -1  , 0xcc, -1  , -1  , -1  , -1  , -1  , -1  , -1  }, // CPY
	{ -1  , -1  , 0xc6, 0xd6, -1  , 0xce, 0xde, -1  , -1  , -1  , -1  , -1  , -1  }, // DEC
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0xca, -1  , -1  , -1  , -1  }, // DEX
	{ -1  , -1  , -1  , -1  , -1  , -1  , -1  , -1  , 0x88, -1  , -1  , -1  , -1  }, // DEY
	{ -1  , 0x49, 0x45, 0x55, -1  , 0x4d, 0x5d, 0x59, -1  , -1  , 0x41, 0x51, -1  }, // EOR
	{ -1  , -1  , 0xe6, 0xf6, -1  , 0xee, 0xfe, -1  , -1  , -1  , -1  , -1  , -1  }, // INC
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xe8, -1  , -1  , -1  , -1  }, // INX
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xc8, -1  , -1  , -1  , -1  }, // INY
	{ -1  , -1  , -1  ,  -1 , -1  , 0x4c, -1  , -1  , -1  , -1  , -1  , -1  , 0x6c}, // JMP
	{ -1  , -1  , -1  ,  -1 , -1  , 0x20, -1  , -1  , -1  , -1  , -1  , -1  , -1  }, // JSR
	{ -1  , 0xa9, 0xa5, 0xb5, -1  , 0xad, 0xbd, 0xb9, -1  , -1  , 0xa1, 0xb1, -1  }, // LDA
	{ -1  , 0xa2, 0xa6,  -1 , 0xb6, 0xae, -1  , 0xbe, -1  , -1  , -1  , -1  , -1  }, // LDX
	{ -1  , 0xa0, 0xa4, 0xb4, -1  , 0xac, 0xbc, -1  , -1  , -1  , -1  , -1  , -1  }, // LDY
	{ 0x4a, -1  , 0x46, 0x56, -1  , 0x4e, 0x5e, -1  , -1  , -1  , -1  , -1  , -1  }, // LSR
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xea, -1  , -1  , -1  , -1  }, // NOP
	{ -1  , 0x09, 0x05, 0x15, -1  , 0x0d, 0x1d, 0x19, -1  , -1  , 0x01, 0x11, -1  }, // ORA
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x48, -1  , -1  , -1  , -1  }, // PHA
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x08, -1  , -1  , -1  , -1  }, // PHP
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x68, -1  , -1  , -1  , -1  }, // PLA
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x28, -1  , -1  , -1  , -1  }, // PLP
	{ 0x2a, -1  , 0x26, 0x36, -1  , 0x2e, 0x3e, -1  , -1  , -1  , -1  , -1  , -1  }, // ROL
	{ 0x6a, -1  , 0x66, 0x76, -1  , 0x6e, 0x7e, -1  , -1  , -1  , -1  , -1  , -1  }, // ROR
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x40, -1  , -1  , -1  , -1  }, // RTI
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x60, -1  , -1  , -1  , -1  }, // RTS
	{ -1  , 0xe9, 0xe5, 0xf5, -1  , 0xed, 0xfd, 0xf9, -1  , -1  , 0xe1, 0xf1, -1  }, // SBC
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x38, -1  , -1  , -1  , -1  }, // SEC
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xf8, -1  , -1  , -1  , -1  }, // SED
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x78, -1  , -1  , -1  , -1  }, // SEI
	{ -1  , -1  , 0x85, 0x95, -1  , 0x8d, 0x9d, 0x99, -1  , -1  , 0x81, 0x91, -1  }, // STA
	{ -1  , -1  , 0x86,  -1 , 0x96, 0x8e, -1  , -1  , -1  , -1  , -1  , -1  , -1  }, // STX
	{ -1  , -1  , 0x84, 0x94, -1  , 0x8c, -1  , -1  , -1  , -1  , -1  , -1  , -1  }, // STY
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xaa, -1  , -1  , -1  , -1  }, // TAX
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xa8, -1  , -1  , -1  , -1  }, // TAY
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0xba, -1  , -1  , -1  , -1  }, // TSX
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x8a, -1  , -1  , -1  , -1  }, // TXA
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x9a, -1  , -1  , -1  , -1  }, // TXS
	{ -1  , -1  , -1  ,  -1 , -1  , -1  , -1  , -1  , 0x98, -1  , -1  , -1  , -1  }  // TYA
//	  ACC   IMM   ZP    ZPX   ZPY   ABS   ABSX  ABSY  IMPL  REL   INDX  INDY  ABSI
    };
    
    private static int findInstruction (String instr) throws Exception
    {
        instr = instr.toUpperCase();
        for (int n=0; n<Instruction.length; n++)
        {
            if (instr.equals(Instruction[n]))
            {
                return n;
            }
        }
        throw new Exception ("Unknown opcode");
    }
    
    private static int readNumber (String in) throws Exception
    {
        try
        {
            if (in.charAt(0) == '$')
            {
                return Integer.parseInt(in.substring(1), 16);
            }
            else
            {
                return Integer.parseInt(in);
            }
        }
        catch (NumberFormatException e)
        {
            throw new Exception ("Malformed number");
        }
    }
    
    private static Addr6502Parser parseOP (String op) throws Exception
    {
        op = op.toUpperCase();
        Addr6502Parser pa = new Addr6502Parser();
        
        if (op.isEmpty())
        {
            pa.parsed_mode = MODE.IMPL;
            return pa;
        }
        
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
            if (k1 > 0 || k2 > 0)
            {
                pa.parsed_operand = readNumber (op.substring(0, k1));
                if (k1 > 0)
                    pa.parsed_mode = pa.parsed_operand > 0xff ? MODE.ABSX : MODE.ZPX;
                else if (k2 > 0)
                    pa.parsed_mode = pa.parsed_operand > 0xff ? MODE.ABSY : MODE.ZPY;
            }
            else
            {
                pa.parsed_operand = readNumber (op);
                pa.parsed_mode = pa.parsed_operand > 0xff ? MODE.ABS : MODE.ZP;
            }
            break;
        }
        
        return pa;
    }
    
    public static Addr6502Parser parse (String instr, String op) throws Exception
    {
        int rawinst = findInstruction(instr);
        Addr6502Parser pa = parseOP(op);
        pa.parsed_instruction = machineCodeMatrix[rawinst][pa.parsed_mode.ordinal()];
        if (pa.parsed_instruction == -1)
            throw new Exception ("Addr mode not allowed with this opcode");
        return pa;
    }
    
    /**
     * Test
     * @param a 
     */
    public static void main (String ...a) throws Exception
    {
        Addr6502Parser pa = parse ("bne", "*-4");
        System.out.println(pa.toString());
    }
}
