package br.com.desafio.adapters.rest.advice;

public class StandardError {
    private String type;
    private Integer status;
    private String url;
    private String description;

    public StandardError(String type, Integer status, String url, String description) {
        this.type = type;
        this.status = status;
        this.url = url;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
