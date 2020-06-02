package CRUD3.CRUD3.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
public class MyParseTable {
    @Id
    public String id;
    public String userName;
    public String parseStatus;
    public String countParsedData;
    public String countUpdatedData;
    public String paramParsed;
    public String parseDate;


    public String getParamParsed() {
        return paramParsed;
    }

    public void setParamParsed(String paramParsed) {
        this.paramParsed = paramParsed;
    }


    public MyParseTable() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParseDate() {
        return parseDate;
    }

    public void setParseDate(String datePars) {
        this.parseDate = datePars;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParseStatus() {
        return parseStatus;
    }

    public void setParseStatus(String parseStatus) {
        this.parseStatus = parseStatus;
    }

    public String getCountParsedData() {
        return countParsedData;
    }

    public void setCountParsedData(String countParsedData) {
        this.countParsedData = countParsedData;
    }

    public String getCountUpdatedData() {
        return countUpdatedData;
    }

    public void setCountUpdatedData(String countUpdatedData) {
        this.countUpdatedData = countUpdatedData;
    }
}
