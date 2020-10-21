
public class DecimalToBinary {

    public static String convertToBinary(String decimalValue, Integer numberOfBits){
        int intDecVal = Integer.parseInt(decimalValue);
        String binaryString = Integer.toBinaryString(intDecVal);
        int cnt = 0;
        StringBuilder res = new StringBuilder();
        int sampleLoc;
        while(cnt < numberOfBits){
            sampleLoc = binaryString.length() - cnt - 1;
            if (sampleLoc < 0) {
                res.insert(0, intDecVal < 0 ? '1' : '0');
            } else {
                res.insert(0, binaryString.charAt(sampleLoc));
            }
            cnt += 1;
        }
        return res.toString();
    }
}
