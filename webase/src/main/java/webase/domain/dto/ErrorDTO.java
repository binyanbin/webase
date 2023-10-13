package webase.domain.dto;

public class ErrorDTO extends NoResultDTO{

    private String msg;

    public ErrorDTO() {
    }

    public ErrorDTO(Integer code, String msg) {
        super(code);
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
