
public interface Instruction {
<<<<<<< HEAD
    public String toBinary();
    public void run();
    public int getCPI();
=======
    String toBinary();
    void run();
    String getIns();
    boolean dependsOn(String register);
    String getDestReg();
    boolean stallFlag = false;
    Integer squashCount = 0;
>>>>>>> 61c5c4f05ca2f8e135027f57281c9c4e80f68cdc
}
