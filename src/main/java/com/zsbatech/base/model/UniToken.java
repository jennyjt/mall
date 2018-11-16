package com.zsbatech.base.model;

import com.zsbatech.base.utils.CommonUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 验证实体类
 * <p>
 * Created with IntelliJ IDEA.
 * Description:
 * User: Anzai
 * Date: 2018-01-05
 * Time: 10:06
 */
public class UniToken implements Serializable {
    private static final long serialVersionUID = 2687180341537772470L;

    private Long userId;
    private String username;
    private List<Long> role;
    private String token;
    private Long expireTime;

    public Long getUserId() {
        return userId;
    }

    public UniToken setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UniToken setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<Long> getRole() {
        return role;
    }

    public UniToken setRole(List<Long> role) {
        this.role = role;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UniToken setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public UniToken setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public boolean containsRole(Long roleId) {
        if (!CommonUtils.isEmpty(role)) {
            for (Object tmpRoleId : role) {
                if (roleId == Long.valueOf(tmpRoleId.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "UniToken{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
