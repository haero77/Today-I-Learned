package hello.core.member.domain;

public enum Grade {

    BASIC,
    VIP;

    public boolean isVip() {
        return this == VIP;
    }

}
