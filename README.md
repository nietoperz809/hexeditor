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

Simulator features
------------------
Run, reset and single step.
Display and change registers and flags.
Disassemble current instruction.

Missing
-------
Hex window save/load ops (load/save of memory)
