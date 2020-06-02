package CRUD3.CRUD3.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
@Data
public class ReturnString {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;
    public String info;
    public boolean disabledStart;
    public boolean disabledStop;


    public ReturnString() {
    }

    public ReturnString(String info, boolean disabledStart, boolean disabledStop) {
        this.info = info;
        this.disabledStart = disabledStart;
        this.disabledStop = disabledStop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isDisabledStart() {
        return disabledStart;
    }

    public void setDisabledStart(boolean disabledStart) {
        this.disabledStart = disabledStart;
    }

    public boolean isDisabledStop() {
        return disabledStop;
    }

    public void setDisabledStop(boolean disabledStop) {
        this.disabledStop = disabledStop;
    }
}
