
public class InvalidInstruction extends PipelineInstruction{
    private String ins;
    public InvalidInstruction(String ins){
        this.ins = ins;
    };

    @Override
    public String getIns() {
        return ins;
    }

    @Override
    public boolean dependsOn(String register) {
        return false;
    }

    @Override
    public void run() {
        return;
    }

    @Override
    public String toBinary() {
        return "invalid instruction: " + ins;
    }
}
