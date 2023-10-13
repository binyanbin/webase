package com.bin.webase.domain.command;

public class Result {
    private final ResultState state;
    private final String msg;
    private Object data;

    public Result(String msg) {
        this.state = ResultState.warn;
        this.msg = msg;
    }

    public Result(ResultState state, String msg) {
        this.msg = msg;
        this.state = state;
    }

    public Result() {
        this.msg = "";
        this.state = ResultState.success;
    }

    public Result(Object data) {
        this.msg = "";
        this.state = ResultState.success;
        this.data = data;
    }

    public static Result success() {
        return new Result();
    }

    public static Result fail(String msg) {
        return new Result(ResultState.fail, msg);
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public <T> T getData(Class<T> clazz) {
        return (T) data;
    }

    public ResultState getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

}
