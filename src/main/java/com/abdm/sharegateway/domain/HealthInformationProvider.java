package com.abdm.sharegateway.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "is_deleted = 'f'")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"hipId", "counterId"}, name = "Unique constraint violation"))
public class HealthInformationProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hipId;

    @Column(nullable = false)
    private String counterId;

    @Column(nullable = false)
    private String tokenUrl;

    @URL
    @Column(nullable = false)
    private String searchUrl;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer method;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = true)
    private byte[] imageByte;

    @URL
    @Column(nullable = false)
    private String createPatientUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @CreationTimestamp
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @UpdateTimestamp()
    private Date dateUpdated;

    private boolean isDeleted;
}
