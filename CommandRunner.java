public class CommandRunner {

    public static void step() {
        // also step through pipeline here
        Instruction ins = InstructionMemory.getNextInstruction();
        ins.run();
    }

}
