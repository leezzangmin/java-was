package model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Memo {
    private Integer id;
    private  String writer;
    private  String content;
    private  String date;

    public Memo(String writer, String content) {
        this.writer = writer;
        this.content = content;
        generateDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private void generateDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.date = formatter.format(date);
    }
}
