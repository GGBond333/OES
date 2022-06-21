package com.oes.gbloes.controller.util;

import lombok.Data;

@Data
public class R {
    private Boolean flag;
    private String msg;
    private Object data;

    public R(){}

    public R(Boolean flag){this.flag = flag;}

    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag,String msg){
        this.flag = flag;
        this.msg = msg;
    }

    public R(Boolean flag,String msg,Object data){
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }
}
