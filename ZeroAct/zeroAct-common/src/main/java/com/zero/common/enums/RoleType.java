package com.zero.common.enums;

/**
 * 角色类型
 *
 * @author zero
 */

public enum RoleType {
    ADMIN("1", "超级管理员", "admin"),
    COMMON("2", "普通角色", "common"),
    ;

    private final String roleId;
    private final String roleName;
    private final String roleKey;

    RoleType(String roleId, String roleName, String roleKey) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleKey = roleKey;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

}
