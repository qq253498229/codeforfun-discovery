package cn.codeforfun.client.bean;

import lombok.Data;

@Data
public class Target<T> {
    private Class<T> type;
    private String name;
    private String url;

}
