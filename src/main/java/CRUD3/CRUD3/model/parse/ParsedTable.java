package CRUD3.CRUD3.model.parse;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ParsedTable")
public class ParsedTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date datePars;
    private String userName;
    private String parseStatus;
    private Integer countParsedData = 0;
    private Integer countUpdatedData = 0;
    private String paramParsed;

    public String getParamParsed() {
        return paramParsed;
    }

    public void setParamParsed(String paramParsed) {
        this.paramParsed = paramParsed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePars() {
        return datePars;
    }

    public void setDatePars(Date datePars) {
        this.datePars = datePars;
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

    public Integer getCountParsedData() {
        return countParsedData;
    }

    public void setCountParsedData(Integer countParsedData) {
        this.countParsedData = countParsedData;
    }

    public Integer getCountUpdatedData() {
        return countUpdatedData;
    }

    public void setCountUpdatedData(Integer countUpdatedData) {
        this.countUpdatedData = countUpdatedData;
    }
}
