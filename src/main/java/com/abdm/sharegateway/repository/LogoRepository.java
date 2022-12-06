package com.abdm.sharegateway.repository;

import com.abdm.sharegateway.domain.HipLogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogoRepository extends JpaRepository<HipLogo, Long> {
    Optional<HipLogo> findByHipId(String hipId);

    Long countByHipId(String hipId);
}
