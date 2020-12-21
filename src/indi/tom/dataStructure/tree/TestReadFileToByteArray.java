package indi.tom.dataStructure.tree;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: Tom
 * @create: 2020-12-21 17:23
 */
public class TestReadFileToByteArray {
    /**
    *@Description:
    *@Param: 
    *@return: 
    *@Date: 17:34
     */
    @Test
    public void test() throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\Java\\IdeaProjects\\DataStructures&Algroithm\\resources\\HuffmanCode.java"));
        System.out.println(bytes.length);
    }
}
