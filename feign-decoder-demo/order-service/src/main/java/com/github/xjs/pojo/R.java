package com.github.xjs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    /**
     * 0：ok
     * 1：error
     * */
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok(T data){
        return new R<>(0, "success", data);
    }

    public static <T> R<T> error(String msg){
        return new R<>(1, msg, null);
    }


}
