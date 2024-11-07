package com.github.xjs.entity.compositepk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class AirlinePK implements Serializable {

    @Column(length = 3)
    private String startCity;

    @Column(length = 3)
    private String endCity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlinePK airlinePK = (AirlinePK) o;
        return Objects.equals(startCity, airlinePK.startCity) && Objects.equals(endCity, airlinePK.endCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startCity, endCity);
    }
}
