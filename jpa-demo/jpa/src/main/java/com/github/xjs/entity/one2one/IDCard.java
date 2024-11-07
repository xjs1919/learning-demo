package com.github.xjs.entity.one2one;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "id_card")
public class IDCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, length = 18)
    private String code;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER,
            optional = false,
            mappedBy = "card")
    private People people;
}
