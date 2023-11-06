package io.wulfcodes.blogger.rest.model.response;

import java.util.List;
import io.wulfcodes.blogger.rest.model.data.UserData;

public record UserResponse(Integer status, String message, UserData user, List<UserData> users) {
    public static UserResponse of(Integer status) {
        return new UserResponse(status, null, null, null);
    }

    public static UserResponse of(Integer status, String message) {
        return new UserResponse(status, message, null, null);
    }

    public static UserResponse of(Integer status, String message, UserData payload) {
        return new UserResponse(status, message, payload, null);
    }

    public static UserResponse of(Integer status, String message, List<UserData> payload) {
        return new UserResponse(status, message, null, payload);
    }
}
