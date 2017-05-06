package com.zhang.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cs_resources")
public class Resources {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源代码
     */
    private String pronoun;
    
    

    /**
     * 请求类型
     */
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "request_type_id")
    private Integer requestTypeId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modify_time")
    private Date lastModifyTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源地址
     *
     * @return url - 资源地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源地址
     *
     * @param url 资源地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取资源类型
     *
     * @return type - 资源类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置资源类型
     *
     * @param type 资源类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源代码
     *
     * @return pronoun - 资源代码
     */
    public String getPronoun() {
        return pronoun;
    }

    /**
     * 设置资源代码
     *
     * @param pronoun 资源代码
     */
    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    /**
     * 获取请求类型
     *
     * @return parent_id - 请求类型
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置请求类型
     *
     * @param parentId 请求类型
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return request_type_id
     */
    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    /**
     * @param requestTypeId
     */
    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_modify_time - 最后修改时间
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastModifyTime 最后修改时间
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", url=").append(url);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", pronoun=").append(pronoun);
        sb.append(", parentId=").append(parentId);
        sb.append(", requestTypeId=").append(requestTypeId);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append("]");
        return sb.toString();
    }
}