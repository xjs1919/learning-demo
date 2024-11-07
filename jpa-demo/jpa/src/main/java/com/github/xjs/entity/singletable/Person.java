package com.github.xjs.entity.singletable;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "person")// 表名默认根据实体名称取名
public class Person {

    private Integer id;
    private String name;
    private Date birthday;
    private Gender gender = Gender.MALE;
    private String info;
    private Byte[] file;
    private String imagepath;// 不希望被持久化

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto: 自动决定是自增还是序列
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 10, nullable = false, name = "personName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    // 设置日期的格式
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // 枚举类型
    // @Enumerated(EnumType.ORDINAL)//数据库保存索引值，从0开始,建议保存String类型
    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // 大文本类型
    @Lob
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    // 文件字节数组
    @Lob
    @Basic(fetch = FetchType.LAZY)
    // 延迟加载，若文件太大，使用的时候加载，否则浪费内存，推荐若超过M就该使用延迟加载
    public Byte[] getFile() {
        return file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    // 不与数据库表中的字段做映射
    @Transient
    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}