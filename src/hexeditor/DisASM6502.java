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
public class DisASM6502
{
    public static String disasm (int[] memory, int offset)
    {
        StringBuilder sb = new StringBuilder();
        Opcode op = Opcode.opcodes[memory[offset]];
        int arg = 0;
        if (op.length > 1)
            arg = memory[offset+1];
        if (op.length > 2)
            arg = arg | (memory[offset+2]<<8);
        sb.append(op.code);
        sb.append (' ');
        
        switch (op.addr)
        {
            case ACC:
            sb.append ("A");
            break;
                
            case IMM:
            sb.append ("#$");
            sb.append(HexTools.toHex8(arg));
            break;
                
            case ZP:
            sb.append ("$");
            sb.append(HexTools.toHex8(arg));
            break;
                
            case ZPX:
            sb.append ("$");
            sb.append(HexTools.toHex8(arg));
            sb.append (",X");
            break;
            
            case ZPY:
            sb.append ("$");
            sb.append(HexTools.toHex8(arg));
            sb.append (",Y");
            break;
            
            case ABS:
            sb.append ("$");
            sb.append(HexTools.toHex16(arg));
            break;
            
            case ABSX:
            sb.append ("$");
            sb.append(HexTools.toHex16(arg));
            sb.append (",X");
            break;
            
            case ABSY:
            sb.append ("$");
            sb.append(HexTools.toHex16(arg));
            sb.append (",Y");
            break;
                
            case REL:
            sb.append ("*");
            if (arg >= 0)
                sb.append ('+');
            sb.append (Integer.toString(arg));
            break;
            
            case INDX:
            sb.append ('(');
            sb.append ("$");
            sb.append(HexTools.toHex8(arg));
            sb.append (",X)");
            break;
            
            case INDY:
            sb.append ('(');
            sb.append ("$");
            sb.append(HexTools.toHex8(arg));
            sb.append ("),Y");
            break;
            
            case IND:
            sb.append ('(');
            sb.append ("$");
            sb.append(HexTools.toHex16(arg));
            sb.append (")");
            break;        
        }
        
        return sb.toString();
    }
}
