package indi.tom.dataStructure.tree;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 * @Description: The joke is the zipped file is larger than the original file :)
 * @Author: Tom
 * @Date: 2020-12-21 20:40
 */
public class ZipUnzipFileWithHuffmanCode {

    @Test
    public void test01(){
        zipFile("D:\\Java\\IdeaProjects\\DataStructures&Algroithm\\resources\\HuffmanCode.java","D:\\Java\\IdeaProjects\\DataStructures&Algroithm\\resources\\HuffmanCode_zipped.java");
        unzipFile("D:\\Java\\IdeaProjects\\DataStructures&Algroithm\\resources\\HuffmanCode_zipped.java","D:\\Java\\IdeaProjects\\DataStructures&Algroithm\\resources\\HuffmanCode_unZipped.java");

    }


    public void zipFile(String sourceFile, String destFile)  {
        try(FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ){
            //read file to byte array, then get the huffman code and zipped bytes with the class we created earlier.
            int size = fileInputStream.available();
            byte[] bytes = new byte[size];
            fileInputStream.read(bytes);
            HashMap<Byte, String> huffmanCodeMap = new HuffmanCode().createHuffmanCodeMap(bytes);
            byte[] zippedBytes = new ZipUnzipByteArrayWithHuffmanCode().zip(bytes, huffmanCodeMap);
            //write huffman code and byte array to des file.
            objectOutputStream.writeObject(huffmanCodeMap);
            objectOutputStream.writeObject(zippedBytes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unzipFile(String sourceFile, String destFile){
        try(FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ){
            HashMap<Byte, String> huffmanCodeMap = (HashMap<Byte, String>)objectInputStream.readObject();
            byte[] bytes = (byte[])objectInputStream.readObject();

            byte[] unzippedBytes = new ZipUnzipByteArrayWithHuffmanCode().unzip(bytes, huffmanCodeMap);
            fileOutputStream.write(unzippedBytes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
