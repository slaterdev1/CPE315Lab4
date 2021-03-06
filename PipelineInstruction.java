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

    @Override
    public int getCPI() {
        return 1;
    }

    @Override
    public int getTargetPcCount(){
        return -1; //overriden by j and branch types
    }

    @Override
    public Boolean evaluateBranch() {
        return false;
    }
}
