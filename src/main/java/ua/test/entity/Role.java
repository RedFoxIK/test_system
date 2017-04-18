package ua.test.entity;

public enum Role {
    TUTOR(1), STUDENT(2);

    private int num;

    Role(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    public static Role getRole(int num) {
        for ( Role role: Role.values() ) {
            if ( role.num == num ) {
                return role;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
