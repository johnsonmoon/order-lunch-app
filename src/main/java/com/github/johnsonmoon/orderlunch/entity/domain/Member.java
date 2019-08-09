package com.github.johnsonmoon.orderlunch.entity.domain;

import javax.persistence.*;

/**
 * java类作用描述
 *
 * @author: langlang
 * @date: 2019-07-31 15:47
 */
@Entity
@Table(name = "`members`")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`age`")
    private Integer age;

    @Column(name = "`sex`")
    private String sex;

    @Column(name = "`phone`")
    private String phone;

    @Column(name = "`job`")
    private String job;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
