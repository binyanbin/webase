package com.bin.webase.domain.bqo;



/**
 * 业务查询对象
 *
 */
public class BQO<T> extends NoResultBQO {

    private T data;

    public BQO() {
        super(0);
    }

    public BQO(T result){
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
