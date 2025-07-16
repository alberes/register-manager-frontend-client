package io.github.alberes.register.manager.frontend.client.constants;

import java.util.List;
import java.util.Set;

public interface Constants {

    public static String HOME = "home";
    public static String OIDCUSER = "oidcUser";
    public static String ASC = "ASC";
    public static String ERROR = "error";
    public static String BEARER = "Bearer ";
    public static String SLASH = "/";
    public static String NAME = "name";
    public static String ROLES = "roles";
    public static List<String> ROLES_LIS = List.of("ADMIN", "USER");
    public static String USER = "user";
    public static String USERS = "users";
    public static String NEW_USER = "new-user";
    public static String CLIENT = "client";
    public static String NEW_CLIENT = "new-client";
    public static String EDIT_CLIENT = "edit-client";
    public static String CLIENT_ID = "clientId";
    public static String CLIENTS = "clients";
    public static String LIST_CLIENT = "list-client";
    public static String REGISTRATION_WITH_E_CLIENT_ID = "registrationWithClientId";
    public static String LIST_USER = "list-user";
    public static String EDIT_USER = "edit-user";
    public static String USEREMAILCONFLIT = "userEmailConflit";
    public static String SCOPES_LABEL = "scopes_label";
    public static Set<String> SCOPES_LIST = Set.of("client.write", "client.read", "client.update", "client.delete",
            "user.write", "user.read", "user.update", "user.delete", "openid", "profile", "email", "address", "phone");
    public static String COULD_NOT_UPDATE_RESOURCE = "Could not update resource ";
    public static String COULD_NOT_DELETE_RESOURCE = "Could not delete resource ";
}
