/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2016 by Ericsson, all rights reserved.
 */

package work.tool;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import work.modle.Question;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadDoc {
    /**
     * 实现对word读取和修改操作
     *
     * @param filePath word模板路径和名称
     */
    public static List<Question> readWord(String filePath) {
        File file = null;
        WordExtractor extractor = null;
        String chooseItemRegEx = "^[A-D].{1}";
        String contentRegEx = "^\\s{0,}[\\d]+.{1}";
        Pattern chooseItemPattern = Pattern.compile(chooseItemRegEx);
        Pattern contentPattern = Pattern.compile(contentRegEx);
        Question question = null;
        List<Question> questions = new ArrayList<>();
        String data;
        boolean finsh = false;
        try {
            file = new File(filePath);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (String fileContent : fileData) {
                data = fileContent;
                if (data != null) {
                    Matcher contentMatcher = contentPattern.matcher(data);
                    Matcher chooseItemMatcher = chooseItemPattern.matcher(data);
                    if (data.equals("\r\n")) {
                        question = new Question();
                    } else if (data.contains("单项选择题")) {
                        assert question != null;
                        question.setType("单选");
                    } else if (data.contains("多项选择题")) {
                        assert question != null;
                        question.setType("多选");
                    } else if (contentMatcher.find()) {
                        assert question != null;
                        question.setContent(data.replace(contentMatcher.group(), ""));
                    } else if (chooseItemMatcher.find()) {
                        assert question != null;
                        List<String> chooseItems = question.getChooseItem();
                        chooseItems.add(data.replace(chooseItemMatcher.group(), "（" + chooseItemMatcher.group().substring(0, chooseItemMatcher.group().length() - 1) + "）"));
                        question.setChooseItem(chooseItems);
                    } else if (data.contains("答案")) {
                        assert question != null;
                        question.setAnswer(data);
                    } else if (data.contains("解析")) {
                        assert question != null;
                        question.setExplanation(data);
                        finsh = true;
                    }
                    if (finsh) {
                        questions.add(question);
                        finsh = false;
                    }
                }
            }
        } catch (Exception exep) {
            exep.printStackTrace();
        }
        return questions;
    }

    public static void main(String[] args) {
        ReadDoc.readWord("C:\\Users\\jing1\\Desktop\\花花\\data.doc");
    }
}
