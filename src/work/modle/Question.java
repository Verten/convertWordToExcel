package work.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jing1 on 2017/9/11.
 */
public class Question {
    private String year = "201705";
    private String chapter = "";
    private String knowledge = "";
    private String page = "";
    private String type = "";
    private String content = "";
    private List<String> chooseItem = new ArrayList<>();
    private String answer = "";
    private String explanation = "";

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getChapter() {
        return chapter;

    }

    public List<String> getChooseItem() {
        return chooseItem;
    }

    public void setChooseItem(List<String> chooseItem) {
        this.chooseItem = chooseItem;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
