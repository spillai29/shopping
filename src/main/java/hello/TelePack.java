package hello;

public class TelePack {

    private final String msg;
    private final String status;

    public TelePack(String msg, String status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
}
