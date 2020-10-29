
public class JrTypeInstruction extends PipelineInstruction{
    /***
     * Data class for the R type instructions:
     *  and, or, add, sub
     *
     */

    private String ins;
    private String rs;
    private int CPI = 2;
    
    public JrTypeInstruction(String ins, String insStr){
        this.ins = ins;
        rs = insStr.substring(ins.length());
    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) + " ");
        res.append(InstructionLookup.getReg(rs));
        res.append("000000000000000");
        res.append(InstructionLookup.getFunc(ins));
        return res.toString();
    }

    @Override
    public String getIns() {
        return ins;
    }

    @Override
    public boolean dependsOn(String register) {
        return register.equals(rs);
    }

    @Override
    public void run() {
        //System.out.println("Running JR type instruction");
        InstructionMemory.pcCount = RegisterFile.getReg(rs);
    }

    @Override
    public String toString() {
        return "JrTypeInstruction{" +
                "ins='" + ins + '\'' +
                ", rs='" + rs + '\'' +
                '}';
    }

    public int getCPI(){
        return this.CPI;
    }
}
