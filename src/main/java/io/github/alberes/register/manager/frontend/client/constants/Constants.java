package io.github.alberes.register.manager.frontend.client.constants;

import java.util.List;
import java.util.Set;

public interface Constants {

    String HOME = "home";
    String OIDCUSER = "oidcUser";
    String ADDRESSES = "addresses";
    String USERID = "userId";
    String LIST_ADDRESS = "list-address";
    String ADDRESS = "address";
    String NEW_ADDRESS = "new-address";
    String USER_SESSION = "userSession";
    String ERRORMESSAGE = "errorMessage";
    String ERROR_MESSAGE = "error.message";
    String ZIP_CODE = "ZIP_CODE";
    String ZIPCODE_CHANGED = "zipcode.changed";
    String MESSAGE = "message";
    String SUCCESS_MESSAGE = "success.message";
    String EDIT_ADDRESS = "edit-address";
    String PUBLICAREA = "publicArea";
    String ASC = "ASC";
    String CONTACT_ADMINISTRATOR = "contact.administrator";
    String ERROR = "error";
    String SESSIONTIMEOUT = "sessionTimeout";
    String SESSION_TIMEOUT = "session.timeout";
    String BEARER = "Bearer ";
    String LOCATION = "location";
    String SLASH = "/";
    String LOGIN = "login";
    String NAME = "name";
    String ADMIN = "ADMIN";
    String PROFILES = "profiles";
    String ROLES = "roles";
    List<String> ROLES_LIS = List.of("ADMIN", "USER");
    String INVALID_LOGIN = "invalid.login";
    String USER = "user";
    String USERS = "users";
    String NEW_USER = "new-user";
    String CLIENT = "client";
    String NEW_CLIENT = "new-client";
    String EDIT_CLIENT = "edit-client";
    String CLIENT_ID = "clientId";
    String CLIENTS = "clients";
    String LIST_CLIENT = "list-client";
    String REGISTRATION_WITH_E_CLIENT_ID = "registrationWithClientId";
    String ADDRESS_NOT_FIND = "address.not.find";
    String LIST_USER = "list-user";
    String EDIT_USER = "edit-user";
    String USEREMAILCONFLIT = "userEmailConflit";
    String USER_EMAIL_CONFLIT = "user.email.conflit";
    String APP_NAME = "app-name";
    String REGISTER_MAMANER_FRONTEND = "register-mamaner-frontend";
    Set<String> HEADERS_TO_REMOVE = Set.of("host", "content-length", "content-type", "accept");
    String SCOPES_LABEL = "scopes_label";
    Set<String> SCOPES_LIST = Set.of("client.write", "client.read", "client.update", "client.delete",
            "user.write", "user.read", "user.update", "user.delete", "openid", "profile", "email", "address", "phone");
    String COULD_NOT_UPDATE_RESOURCE = "Could not update resource ";
    String COULD_NOT_DELETE_RESOURCE = "Could not delete resource ";
}
