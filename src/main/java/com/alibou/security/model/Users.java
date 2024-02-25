package com.alibou.security.model;

import com.alibou.security.template.AbstractEntity;
import com.alibou.security.template.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users extends AbstractEntity {

    private String name;

    private String phoneNumber;

    private String district;

    private String address;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserImages userImage;

    private String userName;

    private String password;
    @Enumerated
    private Position position;
}
