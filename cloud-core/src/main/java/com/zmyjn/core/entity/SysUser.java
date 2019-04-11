package com.zmyjn.core.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 系统用户信息
 * @author: Administrator
 * @date: 2019-03-30 11:35:57
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 状态（0：待发布，1：正常）
     */
    private String status1;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人id
     */
    private Integer createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 修改人id
     */
    private Integer updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    public void setId(Integer id){
        this.id=id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setSex(String sex){
        this.sex=sex;
    }

    public String getSex(){
        return this.sex;
    }

    public void setMobile(String mobile){
        this.mobile=mobile;
    }

    public String getMobile(){
        return this.mobile;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setAvatar(String avatar){
        this.avatar=avatar;
    }

    public String getAvatar(){
        return this.avatar;
    }

    public void setStatus1(String status1){
        this.status1=status1;
    }

    public String getStatus1(){
        return this.status1;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setCreateBy(Integer createBy){
        this.createBy=createBy;
    }

    public Integer getCreateBy(){
        return this.createBy;
    }

    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdateBy(Integer updateBy){
        this.updateBy=updateBy;
    }

    public Integer getUpdateBy(){
        return this.updateBy;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }


}


