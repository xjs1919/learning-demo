package com.github.xjs.entity.one2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * 一对多关联，关联关系维护在多的一方，负责外键的生成更新
 * */

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class Order {

    @Id
    @Column(name="order_no", length = 33)
    private String orderNo;

    @Column
    private Double amount;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE},
                fetch = FetchType.EAGER,
                mappedBy = "order")// 在关系的被维护端指定：关系维护端用来维护关系的属性
    private Set<OrderItem> items;

}
