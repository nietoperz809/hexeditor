hexeditor
=========

JTextarea based hexeditor component

--- also contains an experimental 6502 asm/disasm and 
Patrick Meades 6502 emulator for testing.

Assembler features:
-------------------
- 2-pass.
- Knows Standard 6502 instruction set.
- .byt and .org directives.
- Labels for jmp/jsr and branches, e.g. -> lab: jmp lab:
- Load/Save the asm source.
- .byt also knows chars (.byt 'h','e','l','l','o') 

Simulator features
------------------
Run, reset and single step.
Display and change registers and flags.
Disassemble current instruction.
Hex window save/load (load/save of memory)
