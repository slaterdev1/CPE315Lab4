
public interface Instruction {
    String toBinary();
    void run();
    String getIns();
    boolean dependsOn(String register);
    String getDestReg();
    boolean stallFlag = false;
    Integer squashCount = 0;
}
