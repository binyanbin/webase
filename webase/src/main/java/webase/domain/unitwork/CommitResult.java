package webase.domain.unitwork;

public class CommitResult {
    private final boolean success;
    private final String msg;

    public static final CommitResult SUCCESS = new CommitResult(true, "");

    public CommitResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public CommitResult(String msg) {
        this.success = false;
        this.msg = msg;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }


}
