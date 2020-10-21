import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterFile {

    private static Map<String, Integer> registers = new HashMap<String,Integer>(){{
        put("$zero", 0);
        put("$0", 0);
        put("$a1", 0);
        put("$t1", 0);
        put("$t5", 0);
        put("$s1", 0);
        put("$s5", 0);
        put("$t9", 0);
        put("$v0", 0);
        put("$a2", 0);
        put("$t2", 0);
        put("$t6", 0);
        put("$s2", 0);
        put("$s6", 0);
        put("$sp", 0);
        put("$v1", 0);
        put("$a3", 0);
        put("$t3", 0);
        put("$t7", 0);
        put("$s3", 0);
        put("$s7", 0);
        put("$ra", 0);
        put("$a0", 0);
        put("$t0", 0); 
        put("$t4", 0);
        put("$s0", 0);
        put("$s4", 0);
        put("$t8", 0);
        

    }};

    private static String [] regs = new String []{
        "$0",
        "$v0",
        "$v1",
        "$a0", 
        "$a1",
        "$a2", 
        "$a3", 
        "$t0", 
        "$t1",
        "$t2", 
        "$t3", 
        "$t4",
        "$t5",
        "$t6", 
        "$t7",
        "$s0",
        "$s1", 
        "$s2", 
        "$s3", 
        "$s4",
        "$s5", 
        "$s6", 
        "$s7", 
        "$t8",
        "$t9", 
        "$sp", 
        "$ra" 
        };

    public static void writeReg(String reg, Integer val){
        registers.put(reg, val);
    }

    public static Integer getReg(String reg){
        return registers.get(reg);
    }

    public static void debugPrintReg(){
        for(String key : registers.keySet()){
            System.out.println(key + ": " + registers.get(key));
        }
        System.out.println("\n");
    }
    public static void printReg(){
        //Slater do it plz :)
        for(int i = 0; i < regs.length; i++){
            if(i % 4 == 0){
                System.out.print("\n");
            }
            System.out.print(regs[i] + "= " + registers.get(regs[i]) + "\t\t");
        }
        System.out.println("\n");

    }

    public static void clearRegisters(){
        registers = new HashMap<String, Integer>(){{
            put("$zero", 0);
            put("$0", 0);
            put("$at", 0);
            put("$v0", 0);
            put("$v1", 0);
            put("$a0", 0);
            put("$a1", 0);
            put("$a2", 0);
            put("$a3", 0);
            put("$t0", 0);
            put("$t1", 0);
            put("$t2", 0);
            put("$t3", 0);
            put("$t4", 0);
            put("$t5", 0);
            put("$t6", 0);
            put("$t7", 0);
            put("$s0", 0);
            put("$s1", 0);
            put("$s2", 0);
            put("$s3", 0);
            put("$s4", 0);
            put("$s5", 0);
            put("$s6", 0);
            put("$s7", 0);
            put("$t8", 0);
            put("$t9", 0);
            put("$k0", 0);
            put("$k1", 0);
            put("$gp", 0);
            put("$sp", 0);
            put("$fp", 0);
            put("$ra", 0);
        }};
    }
}
