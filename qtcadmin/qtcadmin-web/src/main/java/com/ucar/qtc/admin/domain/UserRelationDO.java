package com.ucar.qtc.admin.domain;

import java.io.Serializable;

/**
 * @program: train
 * @description: 用户关系domin类
 * @author: guodong.zhang01
 * @create: 2018-09-20 17:44
 **/
public class UserRelationDO implements Serializable{

    //序列化的key确保序列化的准确性
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long user1Id;

    private Long user2Id;

    private String relationType;

    private Integer delfLag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getDelfLag() {
        return delfLag;
    }

    public void setDelfLag(Integer delfLag) {
        this.delfLag = delfLag;
    }

    @Override
    public String toString() {
        return "UserRelationDO{" +
                "id=" + id +
                ", user1Id=" + user1Id +
                ", user2Id=" + user2Id +
                ", relationType='" + relationType + '\'' +
                '}';
    }
}
