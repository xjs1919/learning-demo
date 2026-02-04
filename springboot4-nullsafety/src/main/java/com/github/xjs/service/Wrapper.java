package com.github.xjs.service;

import org.jspecify.annotations.Nullable;

public class Wrapper<T extends @Nullable Object> {
    private @Nullable T value;
    public Wrapper(@Nullable T value){
        this.value = value;
    }
    public @Nullable T unwrap(){
        return this.value;
    }
}
