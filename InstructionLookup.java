
import java.util.HashMap;
import java.util.Map;

public class InstructionLookup {

    private static Map<String,String> opLookup = new HashMap<String,String>(){{
        put("and", "000000");
        put("or", "000000");
        put("add", "000000");
        put("addi", "001000");
        put("sub", "000000");
        put("sll", "000000");
        put("slt", "000000");
        put("beq", "000100");
        put("bne", "000101");
        put("lw", "100011");
        put("sw", "101011");
        put("j", "000010");
        put("jr", "000000");
        put("jal", "000011");
    }};

    private static Map<String,String> funcLookup = new HashMap<String,String>(){{
        put("and", "100100");
        put("or", "100101");
        put("add", "100000");
        put("sub", "100010");
        put("sll", "000000");
        put("slt", "101010");
        put("jr", "001000");
    }};

    private static Map<String,String> regLookup = new HashMap<String,String>(){{
        put("$zero", "00000");
        put("$0", "00000");
        put("$at", "00001");
        put("$v0", "00010");
        put("$v1", "00011");
        put("$a0", "00100");
        put("$a1", "00101");
        put("$a2", "00110");
        put("$a3", "00111");
        put("$t0", "01000");
        put("$t1", "01001");
        put("$t2", "01010");
        put("$t3", "01011");
        put("$t4", "01100");
        put("$t5", "01101");
        put("$t6", "01110");
        put("$t7", "01111");
        put("$s0", "10000");
        put("$s1", "10001");
        put("$s2", "10010");
        put("$s3", "10011");
        put("$s4", "10100");
        put("$s5", "10101");
        put("$s6", "10110");
        put("$s7", "10111");
        put("$t8", "11000");
        put("$t9", "11001");
        put("$k0", "11010");
        put("$k1", "11011");
        put("$gp", "11100");
        put("$sp", "11101");
        put("$fp", "11110");
        put("$ra", "11111");

    }};

    public static String getOpCode(String ins){
        return opLookup.get(ins);
    }

    public static String getFunc(String ins){
        return funcLookup.get(ins);
    }

    public static String getReg(String ins){
        return regLookup.get(ins);
    }
}
