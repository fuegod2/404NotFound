package com.notFound.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ImageneId implements Serializable {
    private static final long serialVersionUID = 307406036025109548L;
    @Column(name = "id_estampa", nullable = false)
    private Integer idEstampa;

    @Column(name = "id_individual", nullable = false)
    private Integer idIndividual;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ImageneId entity = (ImageneId) o;
        return Objects.equals(this.idIndividual, entity.idIndividual) &&
                Objects.equals(this.idEstampa, entity.idEstampa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIndividual, idEstampa);
    }

}