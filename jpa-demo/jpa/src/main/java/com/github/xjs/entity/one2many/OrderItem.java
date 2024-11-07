package com.github.xjs.entity.one2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 CREATE TABLE `order_item` (
 `id` int NOT NULL AUTO_INCREMENT,
 `product_name` varchar(255) DEFAULT NULL,
 `product_price` double DEFAULT NULL,
 `order_no` varchar(33) NOT NULL,
 PRIMARY KEY (`id`),
 KEY `FKgf5b0gxt8n4g87h6g6qj5370` (`order_no`),
 CONSTRAINT `FKgf5b0gxt8n4g87h6g6qj5370` FOREIGN KEY (`order_no`) REFERENCES `order` (`order_no`)
 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 生成了外键
 * */
@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    @ManyToOne(cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER,
            optional = false)//order不能为空
    @JoinColumn(name = "order_no")
    private Order order;


}
