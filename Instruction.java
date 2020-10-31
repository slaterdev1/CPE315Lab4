
public interface Instruction {
    public String toBinary();
    public void run();
    public int getCPI();
    String getIns();
    boolean dependsOn(String register);
    String getDestReg();
    boolean stallFlag = false;
    Integer squashCount = 0;
    int getTargetPcCount();
    Boolean evaluateBranch();
}
