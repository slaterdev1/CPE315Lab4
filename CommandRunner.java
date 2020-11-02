public class CommandRunner {

    private PipelineStages ps;

    public CommandRunner(PipelineStages ps) {
        this.ps = ps;
    }

    public void step(boolean printStages) {
        // also step through pipeline here
        Instruction ins = InstructionMemory.getNextInstruction();
        ps.step();
        if(printStages)
            ps.printStages();
        
        ins.run();

    }

}
