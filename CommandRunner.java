public class CommandRunner {

    public static void step() {
        Instruction ins = InstructionMemory.getNextInstruction();
        ins.run();
    }

}
