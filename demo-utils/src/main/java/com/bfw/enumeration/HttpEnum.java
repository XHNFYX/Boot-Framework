package com.bfw.enumeration;

public enum HttpEnum {

    CONTINUE(100,  "继续请求"),
    SWITCHING_PROTOCOLS(101,  "请切换协议"),
    PROCESSING(102,  "继续执行请求"),
    CHECKPOINT(103,  "Checkpoint"),
    OK(200,  "成功"),
    CREATED(201,  "请求已被接受，等待资源响应"),
    ACCEPTED(202,  "请求已被接受，但尚未处理"),
    NON_AUTHORITATIVE_INFORMATION(203,  "请求已成功处理，结果来自第三方拷贝"),
    NO_CONTENT(204,  "请求已成功处理，但无返回内容"),
    RESET_CONTENT(205,  "请求已成功处理，但需重置内容"),
    PARTIAL_CONTENT(206,  "请求已成功处理，但仅返回了部分内容"),
    MULTI_STATUS(207,  "请求已成功处理，返回了多个状态的XML消息"),
    ALREADY_REPORTED(208,  "响应已发送"),
    IM_USED(226,  "已完成响应"),
    MULTIPLE_CHOICES(300,  "返回多条重定向供选择"),
    MOVED_PERMANENTLY(301,  "永久重定向"),
    FOUND(302,  "临时重定向"),

    MOVED_TEMPORARILY(302,  "Moved Temporarily"),
    SEE_OTHER(303,  "当前请求的资源在其它地址"),
    NOT_MODIFIED(304,  "请求资源与本地缓存相同，未修改"),

    USE_PROXY(305,  "必须通过代理访问"),
    TEMPORARY_REDIRECT(307,  "临时重定向，同302"),
    PERMANENT_REDIRECT(308,  "永久重定向，且禁止改变http方法"),
    BAD_REQUEST(400,  "请求错误，通常是访问的域名未绑定引起"),
    UNAUTHORIZED(401,  "需要身份认证验证"),
    PAYMENT_REQUIRED(402,  "Payment Required"),
    FORBIDDEN(403,  "禁止访问"),
    NOT_FOUND(404,  "请求的内容未找到或已删除"),
    METHOD_NOT_ALLOWED(405,  "不允许的请求方法"),
    NOT_ACCEPTABLE(406,  "无法响应，因资源无法满足客户端条件"),
    PROXY_AUTHENTICATION_REQUIRED(407,  "要求通过代理的身份认证"),
    REQUEST_TIMEOUT(408,  "请求超时"),
    CONFLICT(409,  "存在冲突"),
    GONE(410,  "资源已经不存在(过去存在)"),
    LENGTH_REQUIRED(411,  "无法处理该请求"),
    PRECONDITION_FAILED(412,  "请求条件错误"),
    PAYLOAD_TOO_LARGE(413,  "请求的实体过大"),

    REQUEST_ENTITY_TOO_LARGE(413,  "Request Entity Too Large"),
    URI_TOO_LONG(414,  "请求的URI过长"),

    REQUEST_URI_TOO_LONG(414,  "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415,  "无法处理的媒体格式"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416,  "请求的范围无效"),
    EXPECTATION_FAILED(417,  "无法满足的Expect"),
    I_AM_A_TEAPOT(418,  "服务器整活"),

    INSUFFICIENT_SPACE_ON_RESOURCE(419,  "Insufficient Space On Resource"),

    METHOD_FAILURE(420,  "Method Failure"),

    DESTINATION_LOCKED(421,  "连接数超限"),
    UNPROCESSABLE_ENTITY(422,  "请求的语义错误"),
    LOCKED(423,  "当前资源被锁定"),
    FAILED_DEPENDENCY(424,  "当前请求失败"),
    TOO_EARLY(425,  "未知"),
    UPGRADE_REQUIRED(426,  "请切换到TLS/1.0"),
    PRECONDITION_REQUIRED(428,  "请求未带条件"),
    TOO_MANY_REQUESTS(429,  "并发请求过多"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431,  "请求头过大"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451,  "访问被拒绝（法律的要求）"),
    USERNAME_EXCEPTION(490,"用户名不存在"),
    USERNAME_PROHIBIT(491,"账号被锁定"),
    PASSWORD_EXCEPTION(492,"密码错误"),
    INTERNAL_SERVER_ERROR(500,  "服务器端程序错误"),
    NOT_IMPLEMENTED(501,  "服务器不支持的请求方法"),
    BAD_GATEWAY(502,  "网关无响应"),
    SERVICE_UNAVAILABLE(503,  "服务器端临时错误"),
    GATEWAY_TIMEOUT(504,  "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505,  "服务器不支持的HTTP版本"),
    VARIANT_ALSO_NEGOTIATES(506,  "服务器内部配置错误"),
    INSUFFICIENT_STORAGE(507,  "服务器无法存储请求"),
    LOOP_DETECTED(508,  "服务器因死循环而终止操作"),
    BANDWIDTH_LIMIT_EXCEEDED(509,  "服务器带宽限制"),
    NOT_EXTENDED(510,  "获取资源策略未被满足"),
    NETWORK_AUTHENTICATION_REQUIRED(511,  "需验证以许可连接"),
    Network_Connect_Timeout_Error(599,"网络连接超时");
    ;
    private Integer code;
    private String message;
    HttpEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
