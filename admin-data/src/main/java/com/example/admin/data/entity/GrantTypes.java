package com.example.admin.data.entity;

public enum GrantTypes {
    READABLE(0x1),

    CREATABLE(0x2),

    UPDATABLE(0x4),

    DELETABLE(0x8),

    ;

    private int code;
    private GrantTypes(int code) {
        this.code = code;
    }

    public static boolean isReadable(int grants) {
        return (READABLE.code & grants) != 0;
    }

    public static boolean isCreatable(int grants) {
        return (CREATABLE.code & grants) != 0;
    }

    public static boolean isModifiable(int grants) {
        return (UPDATABLE.code & grants) != 0;
    }

    public static boolean isDeletable(int grants) {
        return (DELETABLE.code & grants) != 0;
    }

    public static int grantsAll() {
        int grants = 0;
        for(GrantTypes g : values()) {
            grants |= g.code;
        }
        return grants;
    }

    public int getCode() {
        return this.code;
    }
}
