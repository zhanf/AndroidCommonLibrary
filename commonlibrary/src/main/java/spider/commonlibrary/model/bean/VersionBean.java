package spider.commonlibrary.model.bean;

import java.io.Serializable;

/**
 * Created by codeest on 16/10/10.
 */

public class VersionBean implements Serializable{

    private String code;

    private String size;

    private String des;

    public String getCode() {
        return code;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

}
