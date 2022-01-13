package az.xazar.mspermission.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum PermissionEnum {
    USER, ADMIN, HR, DEPARTMENT;

    @JsonValue
    public String toLower() {
        return this.toString().toLowerCase(Locale.ENGLISH);
    }
}
