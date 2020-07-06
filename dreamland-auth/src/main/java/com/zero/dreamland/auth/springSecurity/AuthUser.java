package com.zero.dreamland.auth.springSecurity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.dreamland.biz.system.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AuthUser implements UserDetails {


    private SysUser user;

    private List<String> dataScopes;

    //权限
//    @JsonIgnore
//    private final List<GrantedAuthority> authorities;

    //权限
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public Set<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getUserName();
    }


    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return user.getEnabled();
    }


    //获得当前登陆用户对应的对象。
/*   public static AuthUser getCurUser() {
        AuthUser userDetails = (AuthUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println("当前用户:" + userDetails);
        return userDetails;
    }*/


}
