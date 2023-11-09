package io.wulfcodes.blogger.rest.model.value;

public enum AuthenticationFormat {
    PASS_THROUGH("PassThrough", "Pass ", "True or False"),
    BASIC("Basic", "Basic ", "Username:Password"),
    JWT("JWT", "Bearer ", "Check Claims");

    private String format;
    private String scheme;
    private String realm;

    private AuthenticationFormat(String format, String scheme, String realm) {
        this.format = format;
        this.scheme = scheme;
        this.realm = realm;
    }

    public String getFormat() {
        return this.format;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getRealm() {
        return String.format("%s realm=\"%s\"", scheme, realm);
    }
}
