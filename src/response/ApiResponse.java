package response;

public class ApiResponse {
    private String message;
    private Integer code;
    private Object data;
    public ApiResponse(String message, Integer code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
    public ApiResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
