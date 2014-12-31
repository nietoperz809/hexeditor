hexeditor
=========

JTextarea based hexeditor component

--- also contains an experimental 6502 asm/disasm and 
Patrick Meades 6502 emulator for testing.

Assembler features:
-------------------
- 2-pass
- Knows Standard 6502 instruction set
- .byt and .org directives
- Labels for jmp/jsr and branches, e.g. -> lab: jmp lab:

Simulator features
------------------
Run and single step.
Display registers and flags.
Disassemble current instruction.

Missing
-------
File ops (load/save of asm-source)
