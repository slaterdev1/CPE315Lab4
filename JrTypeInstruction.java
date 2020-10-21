
public class JrTypeInstruction implements Instruction {
    /***
     * Data class for the R type instructions:
     *  and, or, add, sub
     *
     */

    private String ins;
    private String rs;

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
}
