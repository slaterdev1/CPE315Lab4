import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryFile {

    private static int[] memory = new int[8192];

    public static int getMem(Integer index){
        return memory[index];
    }

    public static void writeMem(Integer index, Integer val){
        memory[index] = val;
    }


    public static void debugPrintMem(){
        System.out.println("the memory will be printed!");
        int newline = 50;
        int newlinecnt = 0;
        int i = 0;
        Map<Integer, Integer> nonZero = new HashMap<>();
        for(int val : memory){
            if(val != 0){
                nonZero.put(i, val);
            }
            newlinecnt += 1;
            i += 1;
            if (newlinecnt == newline) {
                if(!nonZero.isEmpty()) {
                    for (int non : nonZero.keySet()) {
                        System.out.print(non + ":" + nonZero.get(non) + " ");
                    }
                    System.out.println("\n-------------i = " + i + "---------");
                }
                nonZero.clear();
                newlinecnt = 0;
            }
        }
        System.out.println("\n");
    }

    public static void clearMem(){
        memory = new int[8192];
    }

    public static void printMem(int start, int end){
        //Slater do it plz :)
        for(int i = start; i <= end; i++){
            System.out.println("[" + i + "]= " + memory[i]);
        }
    }

}
