package com.francis.platform.common.response;

/**
 * @author Francis
 */

public enum CommonCode implements ResultCode {

    SUCCESS(true, 200, "操作成功"),
    ERROR(false, -1, "business error"),
    CUSTOM_EXCEPTION(false, -2, "CUSTOM_EXCEPTION"),
    UNAUTHORIZED_ERROR(false, 400, "未认证"),
    USERNAME_AND_PASSWORD_ERROR(false, 401, "用户名或密码不正确！"),
    PERMISSION_DENIED(false, 402, "权限不足！"),


    LOGIN_ELSE_WHERE(false, 403, "您的账号在其他地方登录！"),

    CAPTCHA_ERROR(false, 404,"验证码不正确！" ),
    CAPTCHA_EXPIRE(false, 405,"验证码过期,请重新获取！" ),
    INSUFFICIENT_ROLE_LEVEL(false, 406, "权限不足,你的角色级别低于操作的角色级别"),
    THE_ROLE_NAME_ALREADY_EXISTS(false,407 ,"角色名已经存在,请修改后提交！" ),
    THE_ROLE_HAS_BEEN_ASSOCIATED(false,408 ,"角色已被用户关联,无法删除" ),
    MENU_ALREADY_EXISTS(false, 409,"菜单已经存在,请修改后提交" ),

    MENU_SUPERIOR_ERROR(false,410 ,"菜单的上级不可以为自身" ),

    MENU_NOT_EXISTS(false, 411,"菜单不存在,请检查提交信息" )
    ;


    boolean success;
    int code;
    String message;
    CommonCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
