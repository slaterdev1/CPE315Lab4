
public class ShiftTypeInstruction extends PipelineInstruction{
    /***
     * Data class for the R type instructions:
     *  and, or, add, sub
     *
     */

    private String ins;
    private String rd;
    private String rs;
    private String rt;
    private String shamt;
    private int CPI = 1;

    public ShiftTypeInstruction(String ins, String insStr){
        this.ins = ins;
        String[] tokens = insStr.split(",");
        rd = tokens[0].substring(ins.length());
        rs = "00000";
        rt = tokens[1];
        shamt = tokens[2];

    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) + " ");
        res.append(rs + " ");
        res.append(InstructionLookup.getReg(rt) + " ");
        res.append(InstructionLookup.getReg(rd) + " ");
        res.append(DecimalToBinary.convertToBinary(shamt, 5));
        res.append(InstructionLookup.getFunc(ins) + " ");
        return res.toString();
    }

    @Override
    public String getIns() {
        return ins;
    }

    @Override
    public boolean dependsOn(String register) {
        return register.equals(rt);
    }

    @Override
    public void run() {
        RegisterFile.writeReg(rd, Integer.parseInt(rt) << Integer.parseInt(shamt));
    }

    @Override
    public String toString() {
        return "ShiftTypeInstruction{" +
                "ins='" + ins + '\'' +
                ", rd='" + rd + '\'' +
                ", rt='" + rt + '\'' +
                ", shamt='" + shamt + '\'' +
                '}';
    }

    public int getCPI()
    {
        return this.CPI;
    }

    public String getRS(){
        return this.rs;
    }

    public String getRT(){
        return this.rt;
    }
}
