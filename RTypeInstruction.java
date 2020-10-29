
public class RTypeInstruction implements Instruction {
    /***
     * Data class for the R type instructions:
     *  and, or, add, sub
     *
     */

    private String ins;
    private String rd;
    private String rs;
    private String rt;
    private int CPI = 1;

    public RTypeInstruction(String ins, String insStr){
        this.ins = ins;
        String[] tokens = insStr.split(",");
        rd = tokens[0].substring(ins.length());
        rs = tokens[1];
        rt = tokens[2];
    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) + " ");
        res.append(InstructionLookup.getReg(rs) + " ");
        res.append(InstructionLookup.getReg(rt) + " ");
        res.append(InstructionLookup.getReg(rd) + " ");
        res.append("00000 ");
        res.append(InstructionLookup.getFunc(ins) + " ");
        return res.toString();
    }

    @Override
    public void run() {
        int reg1 = RegisterFile.getReg(rs);
        int reg2 = RegisterFile.getReg(rt);
        switch (ins){
            case "add":
                RegisterFile.writeReg(rd, reg1 + reg2);
                break;
            case "and":
                RegisterFile.writeReg(rd, reg1 & reg2);
                break;
            case "or":
                RegisterFile.writeReg(rd, reg1 | reg2);
                break;
            case "sub":
                RegisterFile.writeReg(rd, reg1 - reg2);
                break;
            case "slt":
                RegisterFile.writeReg(rd, reg1 < reg2 ? 1 : 0);
                break;
            default:
                System.out.println("invalid r type ins... no idea how this happened");
                break;
        }
    }

    @Override
    public String toString() {
        return "RTypeInstruction{" +
                "ins='" + ins + '\'' +
                ", rd='" + rd + '\'' +
                ", rs='" + rs + '\'' +
                ", rt='" + rt + '\'' +
                '}';
    }

    public int getCPI()
    {
        return this.CPI;
    }
}
