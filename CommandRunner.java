public class CommandRunner {

    private PipelineStages ps;

    public CommandRunner() {
        ps = new PipelineStages();
    }

    public void step(boolean printStages) {
        // also step through pipeline here
        Instruction ins = ps.step();
        if(printStages)
            ps.printStages();
        //ins.run();

    }

}
