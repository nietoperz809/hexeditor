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
public class Opcode
{
    public final String code;
    public final ASM6502.MODE addr;
    int length;
    
    static public Opcode[] opcodes = new Opcode[]
    {
	new Opcode("BRK",ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.ZP), new Opcode("ASL",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("PHP",ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.IMM), new Opcode("ASL",ASM6502.MODE.ACC), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.ABS), new Opcode("ASL",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BPL",ASM6502.MODE.REL), new Opcode("ORA",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.ZPX), new Opcode("ASL",ASM6502.MODE.ZPX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CLC",ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ORA",ASM6502.MODE.ABSX), new Opcode("ASL",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("JSR",ASM6502.MODE.ABS), new Opcode("AND",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BIT",ASM6502.MODE.ZP), new Opcode("AND",ASM6502.MODE.ZP), new Opcode("ROL",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("PLP",ASM6502.MODE.IMPL), new Opcode("AND",ASM6502.MODE.IMM), new Opcode("ROL",ASM6502.MODE.ACC), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BIT",ASM6502.MODE.ABS), new Opcode("AND",ASM6502.MODE.ABS), new Opcode("ROL",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BMI",ASM6502.MODE.REL), new Opcode("AND",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("AND",ASM6502.MODE.ZPX), new Opcode("ROL",ASM6502.MODE.ZPX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("SEC",ASM6502.MODE.IMPL), new Opcode("AND",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("AND",ASM6502.MODE.ABSX), new Opcode("ROL",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("RTI",ASM6502.MODE.IMPL), new Opcode("EOR",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("EOR",ASM6502.MODE.ZP), new Opcode("LSR",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("PHA",ASM6502.MODE.IMPL), new Opcode("EOR",ASM6502.MODE.IMM), new Opcode("LSR",ASM6502.MODE.ACC), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("JMP",ASM6502.MODE.ABS), new Opcode("EOR",ASM6502.MODE.ABS), new Opcode("LSR",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BVC",ASM6502.MODE.REL), new Opcode("EOR",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("EOR",ASM6502.MODE.ZPX), new Opcode("LSR",ASM6502.MODE.ZPX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CLI",ASM6502.MODE.IMPL), new Opcode("EOR",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("EOR",ASM6502.MODE.ABSX), new Opcode("LSR",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("RTS",ASM6502.MODE.IMPL), new Opcode("ADC",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ADC",ASM6502.MODE.ZP), new Opcode("ROR",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("PLA",ASM6502.MODE.IMPL), new Opcode("ADC",ASM6502.MODE.IMM), new Opcode("ROR",ASM6502.MODE.ACC), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("JMP",ASM6502.MODE.IND), new Opcode("ADC",ASM6502.MODE.ABS), new Opcode("ROR",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BVS",ASM6502.MODE.REL), new Opcode("ADC",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ADC",ASM6502.MODE.ZPX), new Opcode("ROR",ASM6502.MODE.ZPX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("SEI",ASM6502.MODE.IMPL), new Opcode("ADC",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("ADC",ASM6502.MODE.ABSX), new Opcode("ROR",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("STA",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("STY",ASM6502.MODE.ZP), new Opcode("STA",ASM6502.MODE.ZP), new Opcode("STX",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("DEY",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL), new Opcode("TXA",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("STY",ASM6502.MODE.ABS), new Opcode("STA",ASM6502.MODE.ABS), new Opcode("STX",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BCC",ASM6502.MODE.REL), new Opcode("STA",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("STY",ASM6502.MODE.ZPX), new Opcode("STA",ASM6502.MODE.ZPX), new Opcode("STX",ASM6502.MODE.ZPY), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("TYA",ASM6502.MODE.IMPL), new Opcode("STA",ASM6502.MODE.ABSY), new Opcode("TXS",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("STA",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("LDY",ASM6502.MODE.IMM), new Opcode("LDA",ASM6502.MODE.INDX), new Opcode("LDX",ASM6502.MODE.IMM), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("LDY",ASM6502.MODE.ZP), new Opcode("LDA",ASM6502.MODE.ZP), new Opcode("LDX",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("TAY",ASM6502.MODE.IMPL), new Opcode("LDA",ASM6502.MODE.IMM), new Opcode("TAX",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("LDY",ASM6502.MODE.ABS), new Opcode("LDA",ASM6502.MODE.ABS), new Opcode("LDX",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BCS",ASM6502.MODE.REL), new Opcode("LDA",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("LDY",ASM6502.MODE.ZPX), new Opcode("LDA",ASM6502.MODE.ZPX), new Opcode("LDX",ASM6502.MODE.ZPY), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CLV",ASM6502.MODE.IMPL), new Opcode("LDA",ASM6502.MODE.ABSY), new Opcode("TSX",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("LDY",ASM6502.MODE.ABSX), new Opcode("LDA",ASM6502.MODE.ABSX), new Opcode("LDX",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CPY",ASM6502.MODE.IMM), new Opcode("CMP",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CPY",ASM6502.MODE.ZP), new Opcode("CMP",ASM6502.MODE.ZP), new Opcode("DEC",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("INY",ASM6502.MODE.IMPL), new Opcode("CMP",ASM6502.MODE.IMM), new Opcode("DEX",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CPY",ASM6502.MODE.ABS), new Opcode("CMP",ASM6502.MODE.ABS), new Opcode("DEC",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BNE",ASM6502.MODE.REL), new Opcode("CMP",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("CMP",ASM6502.MODE.ZPX), new Opcode("DEC",ASM6502.MODE.ZPX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CLD",ASM6502.MODE.IMPL), new Opcode("CMP",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("CMP",ASM6502.MODE.ABSX), new Opcode("DEC",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CPX",ASM6502.MODE.IMM), new Opcode("SBC",ASM6502.MODE.INDX), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CPX",ASM6502.MODE.ZP), new Opcode("SBC",ASM6502.MODE.ZP), new Opcode("INC",ASM6502.MODE.ZP), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("INX",ASM6502.MODE.IMPL), new Opcode("SBC",ASM6502.MODE.IMM), new Opcode("NOP",ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("CPX",ASM6502.MODE.ABS), new Opcode("SBC",ASM6502.MODE.ABS), new Opcode("INC",ASM6502.MODE.ABS), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("BEQ",ASM6502.MODE.REL), new Opcode("SBC",ASM6502.MODE.INDY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("SBC",ASM6502.MODE.ZPX), new Opcode("INC",ASM6502.MODE.ZPX), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode("SED",ASM6502.MODE.IMPL), new Opcode("SBC",ASM6502.MODE.ABSY), new Opcode(null,ASM6502.MODE.IMPL), new Opcode(null,ASM6502.MODE.IMPL),
	new Opcode(null,ASM6502.MODE.IMPL), new Opcode("SBC",ASM6502.MODE.ABSX), new Opcode("INC",ASM6502.MODE.ABSX), new Opcode(null,ASM6502.MODE.IMPL)
    };

    Opcode(String c, ASM6502.MODE a)
    {
        code = c;
        addr = a;
        length = ASM6502.addrModeLen[a.ordinal()];
    }
}
