package kasei.web.repository.entity;


import java.util.List;

public class Log {

    private List<String> tags;
    private Integer logLevel;
    private String msg;


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
