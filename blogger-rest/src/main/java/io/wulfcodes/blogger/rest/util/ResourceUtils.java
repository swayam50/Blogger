package io.wulfcodes.blogger.rest.util;

import jakarta.ws.rs.core.UriInfo;

public class ResourceUtils {

    private ResourceUtils() {}

    public static String generateResourceInfoWithId(UriInfo uriInfo, Class<?> resourceClass, String methodName, Object id) {
        try {
            return uriInfo.getBaseUriBuilder()
                          .path(resourceClass)
                          .path(resourceClass.getMethod(methodName, id.getClass()))
                          .build(id).toString();
        } catch (NoSuchMethodException ex) {
            return "Location info unavailable";
        }
    }
}
