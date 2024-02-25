package com.alibou.security.model;


import com.alibou.security.template.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SalonsProf extends AbstractEntity {
    private long usersId;

    private String brandName;

    private String address;

    private long rating;

    private long ratingNUmber;

    private String phoneNumber;

    private String aboutSalon;

    private String timeWork;

    private long viewCount;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,cascade = CascadeType.ALL)
    private SalonImage profImage;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,cascade = CascadeType.ALL)
    private Locations locations;
}
