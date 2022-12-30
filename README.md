#  A CLI emulator for the Intel 4004
### Instructions to use:
<br>
1 - Each line are a block of code, it's mean one line for each object code:
<ul>
    <ul>1. 20 A2</ul>
    <ul>2. DA </ul>
</ul>

2 - All code must be ended with 00(NOP) or the emulator will throw a Exception

<br>

3 - Every object code are composed by OPERATION+MODIFIER and sometimes OPERATION+MODIFIER+VALUE. Insert anything out of this formule will generate a exception, and use a operation than need a VALUE without this value will throw a exception too

<br>

4 - In end the program will print a list of informations about of 4004:
- Accumulator (In decimal)
- Program Counter (In decimal)
- Registers Pairs (Each pair are represented by two numbers in decimal)
- Carry (Bool)
- All Object code (ROM)
