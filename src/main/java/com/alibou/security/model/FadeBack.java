package com.alibou.security.model;


import com.alibou.security.template.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FadeBack extends AbstractEntity {
    private Long salonId;
    private String text;
    private Long rating;
    private Long likes;
}
