package com.bin.webase.exception;

/**
 * @author :yanbin
 */
public enum ErrorCode {
    /*
      接口异常
     */
    ArgumentsIncorrect(10001, "参数不正确"),
    NoFunctionID(10002, "没有权限操作"),
    NoSetFunction(10003, "没有设置权限"),
    NoWebContent(10004, "上下文不存在"),
    NoBindOpenId(10005, "未绑定openId"),
    InvalidAccessToken(10010, "会话已失效,请重新登录"),
    IllegalStateException(10011, "业务状态不正确"),
    NullPointerException(10012, "对象不存在"),
    UnKnowException(10014, "发生未知异常"),
    NoHandlerFound(10015, "无效的请求路径"),
    UserLoginFail(10016, "账号被禁用或删除,无法登陆"),
    bootFail(10017, "服务启动失败"),
    WrongSmsCode(10019, "验证码错误"),
    WrongPassword(10020, "密码错误"),
    QuickOperate(10022, "操作过快,请稍后"),
    InterfaceContent(10023, "接口调用错误"),
    ServiceException(10024, "服务异常"),
    DomainKeyIsNotExists(10025, "业务主键不存在"),
    UnitWorkCommitFail(10026, "工作单元提交失败"),
    StateMachineExecuteFail(10027, "状态执行错误"),
    StateMachineSameGroupId(10028, "存在相同的操作"),
    NoData(10029, "数据不存在"),
    NoStateMachine(10029, "未找到状态机"),
    IOException(20005, "IO异常"),
    LoginError(20006, "登陆异常"),
    UsedSignature(20007, "签名已使用"),
    RedisLockException(20008, "获取锁异常"),
    NotFoundCommandReceiver(20009, "未找到命令执行者"),
    NotFoundImage(20010, "未找到图片"),
    PayFail(20011, "支付失败"),
    ServerBusy(20021, "系统正忙，请稍后再试"),
    InvalidDevice(20022, "无效的设备"),
    RequestDataIsNull(20023, "post数据为空"),
    InvalidSign(20007, "无效的签名"),
    UserLimit(20008, "限制客户端恶意访问"),
    ;

    private final Integer code;
    private final String reasoning;

    ErrorCode(Integer code, String reasoning) {
        this.code = code;
        this.reasoning = reasoning;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @return the reasoning
     */
    public String getReasoning() {
        return reasoning;
    }


}
