package fss.api;

public class ApiResponse<T> {

    private String status;
    private T data;
    private String message;

    public ApiResponse(T data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "Operation successful", "success");
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message, "error");
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
