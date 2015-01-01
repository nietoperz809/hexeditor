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
public class Opcode
{
    public final String code;
    public final MODE addr;
    int length;

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
    
    
    
    static public Opcode[] opcodes = new Opcode[]
    {
	new Opcode("BRK",MODE.IMPL), new Opcode("ORA",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ORA",MODE.ZP), new Opcode("ASL",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("PHP",MODE.IMPL), new Opcode("ORA",MODE.IMM), new Opcode("ASL",MODE.ACC), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ORA",MODE.ABS), new Opcode("ASL",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BPL",MODE.REL), new Opcode("ORA",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ORA",MODE.ZPX), new Opcode("ASL",MODE.ZPX), new Opcode(null,MODE.IMPL),
	new Opcode("CLC",MODE.IMPL), new Opcode("ORA",MODE.ABSY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ORA",MODE.ABSX), new Opcode("ASL",MODE.ABSX), new Opcode(null,MODE.IMPL),
	new Opcode("JSR",MODE.ABS), new Opcode("AND",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("BIT",MODE.ZP), new Opcode("AND",MODE.ZP), new Opcode("ROL",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("PLP",MODE.IMPL), new Opcode("AND",MODE.IMM), new Opcode("ROL",MODE.ACC), new Opcode(null,MODE.IMPL),
	new Opcode("BIT",MODE.ABS), new Opcode("AND",MODE.ABS), new Opcode("ROL",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BMI",MODE.REL), new Opcode("AND",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("AND",MODE.ZPX), new Opcode("ROL",MODE.ZPX), new Opcode(null,MODE.IMPL),
	new Opcode("SEC",MODE.IMPL), new Opcode("AND",MODE.ABSY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("AND",MODE.ABSX), new Opcode("ROL",MODE.ABSX), new Opcode(null,MODE.IMPL),
	new Opcode("RTI",MODE.IMPL), new Opcode("EOR",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("EOR",MODE.ZP), new Opcode("LSR",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("PHA",MODE.IMPL), new Opcode("EOR",MODE.IMM), new Opcode("LSR",MODE.ACC), new Opcode(null,MODE.IMPL),
	new Opcode("JMP",MODE.ABS), new Opcode("EOR",MODE.ABS), new Opcode("LSR",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BVC",MODE.REL), new Opcode("EOR",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("EOR",MODE.ZPX), new Opcode("LSR",MODE.ZPX), new Opcode(null,MODE.IMPL),
	new Opcode("CLI",MODE.IMPL), new Opcode("EOR",MODE.ABSY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("EOR",MODE.ABSX), new Opcode("LSR",MODE.ABSX), new Opcode(null,MODE.IMPL),
	new Opcode("RTS",MODE.IMPL), new Opcode("ADC",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ADC",MODE.ZP), new Opcode("ROR",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("PLA",MODE.IMPL), new Opcode("ADC",MODE.IMM), new Opcode("ROR",MODE.ACC), new Opcode(null,MODE.IMPL),
	new Opcode("JMP",MODE.IND), new Opcode("ADC",MODE.ABS), new Opcode("ROR",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BVS",MODE.REL), new Opcode("ADC",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ADC",MODE.ZPX), new Opcode("ROR",MODE.ZPX), new Opcode(null,MODE.IMPL),
	new Opcode("SEI",MODE.IMPL), new Opcode("ADC",MODE.ABSY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("ADC",MODE.ABSX), new Opcode("ROR",MODE.ABSX), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("STA",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("STY",MODE.ZP), new Opcode("STA",MODE.ZP), new Opcode("STX",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("DEY",MODE.IMPL), new Opcode(null,MODE.IMPL), new Opcode("TXA",MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("STY",MODE.ABS), new Opcode("STA",MODE.ABS), new Opcode("STX",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BCC",MODE.REL), new Opcode("STA",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("STY",MODE.ZPX), new Opcode("STA",MODE.ZPX), new Opcode("STX",MODE.ZPY), new Opcode(null,MODE.IMPL),
	new Opcode("TYA",MODE.IMPL), new Opcode("STA",MODE.ABSY), new Opcode("TXS",MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("STA",MODE.ABSX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("LDY",MODE.IMM), new Opcode("LDA",MODE.INDX), new Opcode("LDX",MODE.IMM), new Opcode(null,MODE.IMPL),
	new Opcode("LDY",MODE.ZP), new Opcode("LDA",MODE.ZP), new Opcode("LDX",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("TAY",MODE.IMPL), new Opcode("LDA",MODE.IMM), new Opcode("TAX",MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("LDY",MODE.ABS), new Opcode("LDA",MODE.ABS), new Opcode("LDX",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BCS",MODE.REL), new Opcode("LDA",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("LDY",MODE.ZPX), new Opcode("LDA",MODE.ZPX), new Opcode("LDX",MODE.ZPY), new Opcode(null,MODE.IMPL),
	new Opcode("CLV",MODE.IMPL), new Opcode("LDA",MODE.ABSY), new Opcode("TSX",MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("LDY",MODE.ABSX), new Opcode("LDA",MODE.ABSX), new Opcode("LDX",MODE.ABSY), new Opcode(null,MODE.IMPL),
	new Opcode("CPY",MODE.IMM), new Opcode("CMP",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("CPY",MODE.ZP), new Opcode("CMP",MODE.ZP), new Opcode("DEC",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("INY",MODE.IMPL), new Opcode("CMP",MODE.IMM), new Opcode("DEX",MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("CPY",MODE.ABS), new Opcode("CMP",MODE.ABS), new Opcode("DEC",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BNE",MODE.REL), new Opcode("CMP",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("CMP",MODE.ZPX), new Opcode("DEC",MODE.ZPX), new Opcode(null,MODE.IMPL),
	new Opcode("CLD",MODE.IMPL), new Opcode("CMP",MODE.ABSY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("CMP",MODE.ABSX), new Opcode("DEC",MODE.ABSX), new Opcode(null,MODE.IMPL),
	new Opcode("CPX",MODE.IMM), new Opcode("SBC",MODE.INDX), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("CPX",MODE.ZP), new Opcode("SBC",MODE.ZP), new Opcode("INC",MODE.ZP), new Opcode(null,MODE.IMPL),
	new Opcode("INX",MODE.IMPL), new Opcode("SBC",MODE.IMM), new Opcode("NOP",MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode("CPX",MODE.ABS), new Opcode("SBC",MODE.ABS), new Opcode("INC",MODE.ABS), new Opcode(null,MODE.IMPL),
	new Opcode("BEQ",MODE.REL), new Opcode("SBC",MODE.INDY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("SBC",MODE.ZPX), new Opcode("INC",MODE.ZPX), new Opcode(null,MODE.IMPL),
	new Opcode("SED",MODE.IMPL), new Opcode("SBC",MODE.ABSY), new Opcode(null,MODE.IMPL), new Opcode(null,MODE.IMPL),
	new Opcode(null,MODE.IMPL), new Opcode("SBC",MODE.ABSX), new Opcode("INC",MODE.ABSX), new Opcode(null,MODE.IMPL)
    };

    Opcode(String c, MODE a)
    {
        code = c;
        addr = a;
        length = addrModeLen[a.ordinal()];
    }
}
