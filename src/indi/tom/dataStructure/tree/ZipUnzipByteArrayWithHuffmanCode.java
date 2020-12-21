package indi.tom.dataStructure.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Tom
 * @Date 2020/12/16 17:33
 * @Version 1.0
 * @Description Test using Huffman code to zip/unzip byte array.
 */
public class ZipUnzipByteArrayWithHuffmanCode {

    //if we don't use this effectiveBitNumber to record how many effective bits in last byte of
    // huffman string, it will have bug, if the last byte of huffman string is started with 0, issue will
    // happen. For example if we test string: "i like like like java do you like a javak";
    //The current program doesn't know how many bits there are in last byte belong to
    //the huffman code string. Will improve it in next demo version by adding a filed indicating
    //how many bits in last byte belong to the huffman string.
    private int effectiveBitNumber;
    private HashMap<Byte, String> huffmanCodeMap;



    @Test
    public void test01(){

        String testStr = "i like like like java do you like a javad and c/c++";

        byte[] bytes = testStr.getBytes();//40 bytes
//        System.out.println(bytes.length);
        this.huffmanCodeMap = new HuffmanCode().createHuffmanCodeMap(bytes);
        byte[] zippedBytes = zip(bytes,this.huffmanCodeMap);
        System.out.println("压缩后的结果是:" + Arrays.toString(zippedBytes) + " 长度= " + zippedBytes.length);

//        System.out.println(zippedBytes.length);

        byte[] unzippedBytes = unzip(zippedBytes,this.huffmanCodeMap);

        System.out.println(unzippedBytes.length);

        String s = new String(unzippedBytes);

        System.out.println(s);
    }



    public byte[] zip(byte[] array, HashMap<Byte, String> huffmanCodeMap){
        //new array used to save same data coded with huffman code
        byte[] newArray = null;

        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(huffmanCodeMap.get(b));
        }
        System.out.println(sb);
        if((sb.length() % 8) == 0) newArray = new byte[sb.length()/8];
        if((sb.length() % 8) != 0) {
            newArray = new byte[sb.length() / 8 + 1];
            effectiveBitNumber = sb.length() % 8;
        }

        int i = 0, j = 0;
        for (; i + 8 <= sb.length(); i += 8, j++) {
            String substring = sb.substring(i, i + 8);
            int parseInt = Integer.parseInt(substring, 2);
            newArray[j] = (byte)parseInt;
        }
        if((sb.length() % 8) != 0){
            newArray[j] = (byte)Integer.parseInt(sb.substring(i),2);
        }
        return newArray;
    }

    public byte[] unzip(byte[] bytes, HashMap<Byte, String> huffmanCodeMap){
        //1. convert bytes array to binary string
        StringBuilder sb = new StringBuilder();
        boolean isLastByte = false;
        for (int i = 0; i < bytes.length; i++) {
            isLastByte = (i == (bytes.length -1));
            String s = toBinaryString(isLastByte, bytes[i]);
            sb.append(s);
        }
        System.out.println(sb);
        //2. Convert binary string to bytes array as per the map<Byte, String>
        //2.1 convert map<Byte, String> to map<String,Byte>
        HashMap<String, Byte> stringByteHashMap = new HashMap<>();
        for (Map.Entry<Byte, String> byteStringEntry : huffmanCodeMap.entrySet()) {
            stringByteHashMap.put(byteStringEntry.getValue(), byteStringEntry.getKey());
        }
        //2.2 generate byte array as per the map<String,Byte>
        int firstCharIndex = 0;
        int lastCharIndex = 0;
        ArrayList<Byte> byteList = new ArrayList<Byte>();
        while(lastCharIndex < sb.length()){
            String substring = sb.substring(firstCharIndex, lastCharIndex + 1);
            Byte aByte = stringByteHashMap.get(substring);
            if(aByte != null){
                byteList.add(aByte);
                lastCharIndex++;
                firstCharIndex = lastCharIndex;
            }else{
                lastCharIndex++;
            }
        }
        byte[] bytes1 = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            bytes1[i] = byteList.get(i);
        }
        return bytes1;


    }
    public int getEffectiveBitNumber() {
        return effectiveBitNumber;
    }

    public void setEffectiveBitNumber(int effectiveBitNumber) {
        this.effectiveBitNumber = effectiveBitNumber;
    }

    public HashMap<Byte, String> getHuffmanCodeMap() {
        return huffmanCodeMap;
    }

    public void setHuffmanCodeMap(HashMap<Byte, String> huffmanCodeMap) {
        this.huffmanCodeMap = huffmanCodeMap;
    }

    private String toBinaryString(boolean isLastByte, byte b) {
        int temp = b;
        if (!isLastByte) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);
        if (!isLastByte) {
            String substring = s.substring(s.length() - 8);
            return substring;
        } else {
            if (temp >= 0) {
                int x = effectiveBitNumber - s.length();
                String s1 = "";
                if(x > 0){
                    for (int i = 0; i < x; i++) {
                        s1 += "0";
                    }
                }
                return s1 + s;
            } else {
                return s.substring(s.length() - 8);
            }
        }


    }

}
