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
public class Master extends AbstractEntity {
    private Long salonId;
    private String name;
    @Enumerated
    private Position position;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private MasterImages image;
}
