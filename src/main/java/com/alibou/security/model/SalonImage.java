package com.alibou.security.model;


import com.alibou.security.template.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SalonImage extends AbstractEntity {
    private Long id;
    private String originalName;
    private String contentType;
    private byte[] imageByte;
}
