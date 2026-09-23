package com.crm.mystater.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MyProperties.class)
@ConfigurationProperties(prefix = "my.properties")
public class MyProperties {
    private String name;
    private Integer id;
    private  String url;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
