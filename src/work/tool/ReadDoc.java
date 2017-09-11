/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2016 by Ericsson, all rights reserved.
 */

package work.tool;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;

public class ReadDoc {
    /**
     * 实现对word读取和修改操作
     *
     * @param filePath word模板路径和名称
     */
    public static void readwriteWord(String filePath) {
        File file = null;
        WordExtractor extractor = null;
        try
        {

            file = new File(filePath);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++)
            {
                if (fileData[i] != null)
                    System.out.println(fileData[i]);
            }
        }
        catch (Exception exep) {
            exep.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadDoc.readwriteWord("C://Users//ebinhon//Desktop//2017.doc");
    }
}
