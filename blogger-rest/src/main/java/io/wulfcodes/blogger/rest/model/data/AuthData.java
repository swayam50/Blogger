package io.wulfcodes.blogger.rest.model.data;

public class AuthData {
    private Boolean authenticated;
    private Integer status;
    private String message;
    private Boolean proxy;
    private String realm;

    public AuthData() {
        this.authenticated = false;
        this.status = 401;
        this.proxy = false;
        this.message = "Invalid authorization token!";
    }

    public AuthData(boolean authenticated) {
        this();
        this.authenticated = authenticated;
        this.status = authenticated ? 200 : status;
        this.message = authenticated ? "User validation successful." : message;
    }

    public AuthData(String message) {
        this();
        this.message = message;
    }

    public AuthData(String message, boolean proxy, String realm) {
        this(message);
        this.proxy = proxy;
        this.realm = realm;
    }

    public AuthData(boolean authenticated, boolean proxy, String realm) {
        this(authenticated);
        this.proxy = proxy;
        this.realm = realm;
    }

    public AuthData(int status, String message, boolean proxy, String realm) {
        this(message, proxy, realm);
        this.status = status;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public int obtainStatus() {
        return status.intValue();
    }

    public String obtainMessage() {
        return message;
    }

    public boolean isProxy() {
        return proxy;
    }

    public String obtainRealm() {
        return realm;
    }
}
