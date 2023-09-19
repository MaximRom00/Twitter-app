package by.rom.xapp.domain.user;

public enum RoleType {
    ROLE_USER("User"),
    ROLE_ADMIN("Administrator");

    private final String name;

    RoleType(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
