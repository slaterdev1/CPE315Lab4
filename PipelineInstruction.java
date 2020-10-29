public abstract class PipelineInstruction implements Instruction{

    public boolean stallFlag = false;
    public Integer squashCount = 0;
    public String destReg = "$zero";

    public abstract String toBinary();
    public abstract String getIns();
    public abstract boolean dependsOn(String register);
    public abstract void run();
    @Override
    public String getDestReg() {
        return destReg;
    }

}
