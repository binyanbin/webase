package com.bin.webase.domain.dto;



/**
 * 接口返回对象
 *
 * @param <T> 业务对象结构
 */
public class DTO<T> extends NoResultDTO {

    private T data;

    public DTO() {
        super(0);
    }

    public DTO(T result){
        super(0);
        setData(result);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
