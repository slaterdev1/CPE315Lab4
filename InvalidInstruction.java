
public class InvalidInstruction implements Instruction{
    private String ins;
    public InvalidInstruction(String ins){
        this.ins = ins;
    };

    @Override
    public void run() {
        System.out.println("lol you're running an invalid instruction: " + ins);
    }

    @Override
    public String toBinary() {
        return "invalid instruction: " + ins;
    }
}
