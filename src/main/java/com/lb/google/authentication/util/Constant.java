package com.lb.google.authentication.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String AUTH_TOKEN_NAME = "Authorization";
    public static final String TENANT_ID = "X-Loblaw-Tenant-ID";
    public static final String TENANT_ID_MISSING="Required request header 'X-Loblaw-Tenant-ID' for method parameter type String is not present";
}
