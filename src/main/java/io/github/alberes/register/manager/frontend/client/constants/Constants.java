package io.github.alberes.register.manager.frontend.client.constants;

import java.util.List;
import java.util.Set;

public interface Constants {

    public static final String HOME = "home";
    public static final String OIDCUSER = "oidcUser";
    public static final String ADDRESSES = "addresses";
    public static final String USERID = "userId";
    public static final String LIST_ADDRESS = "list-address";
    public static final String ADDRESS = "address";
    public static final String NEW_ADDRESS = "new-address";
    public static final String USER_SESSION = "userSession";
    public static final String ERRORMESSAGE = "errorMessage";
    public static final String ERROR_MESSAGE = "error.message";
    public static final String ZIP_CODE = "ZIP_CODE";
    public static final String ZIPCODE_CHANGED = "zipcode.changed";
    public static final String MESSAGE = "message";
    public static final String SUCCESS_MESSAGE = "success.message";
    public static final String EDIT_ADDRESS = "edit-address";
    public static final String PUBLICAREA = "publicArea";
    public static final String ASC = "ASC";
    public static final String CONTACT_ADMINISTRATOR = "contact.administrator";
    public static final String ERROR = "error";
    public static final String SESSIONTIMEOUT = "sessionTimeout";
    public static final String SESSION_TIMEOUT = "session.timeout";
    public static final String BEARER = "Bearer ";
    public static final String LOCATION = "location";
    public static final String SLASH = "/";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String ADMIN = "ADMIN";
    public static final String PROFILES = "profiles";
    public static final String ROLES = "roles";
    public static final List<String> ROLES_LIS = List.of("ADMIN", "USER");
    public static final String INVALID_LOGIN = "invalid.login";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String NEW_USER = "new-user";
    public static final String CLIENT = "client";
    public static final String NEW_CLIENT = "new-client";
    public static final String EDIT_CLIENT = "edit-client";
    public static final String CLIENT_ID = "clientId";
    public static final String CLIENTS = "clients";
    public static final String LIST_CLIENT = "list-client";
    public static final String REGISTRATION_WITH_E_CLIENT_ID = "registrationWithClientId";
    public static final String ADDRESS_NOT_FIND = "address.not.find";
    public static final String LIST_USER = "list-user";
    public static final String EDIT_USER = "edit-user";
    public static final String USEREMAILCONFLIT = "userEmailConflit";
    public static final String USER_EMAIL_CONFLIT = "user.email.conflit";
    public static final String APP_NAME = "app-name";
    public static final String REGISTER_MAMANER_FRONTEND = "register-mamaner-frontend";
    public static final Set<String> HEADERS_TO_REMOVE = Set.of("host", "content-length", "content-type", "accept");
    public static final String SCOPES_LABEL = "scopes_label";
    public static final Set<String> SCOPES_LIST = Set.of("client.write", "client.read", "client.update", "client.delete",
            "user.write", "user.read", "user.update", "user.delete", "openid", "profile", "email", "address", "phone");
    public static final String COULD_NOT_UPDATE_RESOURCE = "Could not update resource ";
    public static final String COULD_NOT_DELETE_RESOURCE = "Could not delete resource ";
}
