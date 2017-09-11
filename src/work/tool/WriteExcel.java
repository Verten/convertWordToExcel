package work.tool;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import work.modle.Question;

import java.io.*;
import java.util.*;

/**
 * Created by jing1 on 2017/9/11.
 */
public class WriteExcel {

    public static void writeExcel(String filename, List<Question> questions) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sample sheet");

        Map<String, Object[]> data = new HashMap<>();

        convertListToData(data, questions);

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertListToData(Map<String, Object[]> data, List<Question> questions) {
        Integer index = 1;
        data.put("0", new Object[]{"年份", "章节", "知识点", "页码", "题型", "题目", "答案", "解析"});
        for (Question question : questions) {
            StringBuilder sb = new StringBuilder();
            String content = question.getContent();
            List<String> chooseItems = question.getChooseItem();
            sb.append(content).append("\n");
            for (String chooseItem :
                    chooseItems) {
                sb.append(chooseItem).append("\n");
            }
            data.put((++index).toString(), new Object[]{question.getYear(), question.getChapter(), question.getKnowledge(),
                    question.getPage(), question.getType(), sb.toString(), question.getAnswer(), question.getExplanation()});
        }
    }

    public static void main(String[] args) {
        WriteExcel.writeExcel("C:\\Users\\jing1\\Desktop\\花花\\201611.xls", ReadDoc.readWord("C:\\Users\\jing1\\Desktop\\花花\\data2.doc"));
    }

}
