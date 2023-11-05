package io.wulfcodes.blogger.rest.model.response;

public record RegistrationResponse(Integer status, String message) {

    public static RegistrationResponse of(Integer status) {
        return new RegistrationResponse(status, null);
    }

    public static RegistrationResponse of(Integer status, String message) {
        return new RegistrationResponse(status, message);
    }

}
