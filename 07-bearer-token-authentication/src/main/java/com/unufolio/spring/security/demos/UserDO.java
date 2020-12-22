package com.unufolio.spring.security.demos;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/27
 */
@Entity
@Table(name = "users")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String mobile;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;
    private String createBy;
    private String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "UserDO{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", nickname='" + nickname + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", createAt=" + createAt +
            ", updateAt=" + updateAt +
            ", createBy='" + createBy + '\'' +
            ", updateBy='" + updateBy + '\'' +
            '}';
    }
}
