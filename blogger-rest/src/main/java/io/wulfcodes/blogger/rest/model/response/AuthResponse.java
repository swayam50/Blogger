package io.wulfcodes.blogger.rest.model.response;

public record AuthResponse(Integer status, String message) {

    public static AuthResponse of(Integer status) {
        return new AuthResponse(status, null);
    }

    public static AuthResponse of(Integer status, String message) {
        return new AuthResponse(status, message);
    }

}
