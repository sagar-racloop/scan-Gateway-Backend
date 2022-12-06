package com.abdm.sharegateway.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Data
@Where(clause = "is_deleted = 'f'")
public class HipLogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String hipId;

    @Column(nullable = false)
    private Integer method;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(nullable = false)
    private byte[] imageByte;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;

    @Column(nullable = false)
    private boolean isDeleted;

}
