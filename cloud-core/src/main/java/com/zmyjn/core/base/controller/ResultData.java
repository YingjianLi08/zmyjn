package com.zmyjn.core.base.controller;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ResultData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value="标识(成功:true 失败:false)")
    protected boolean result = true;

    @ApiModelProperty(value="数据")
    protected Object data;

    /**
     * code
     */
    @ApiModelProperty(value="code")
    protected Integer code = 0;

    /**
     * msg
     */
    @ApiModelProperty(value="msg")
    protected String msg = "操作成功";

    @ApiModelProperty(value="count")
    protected Integer count = 0;

    public ResultData(){}

    public ResultData(Object data) {
        this.data = data;
    }

    public ResultData(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
