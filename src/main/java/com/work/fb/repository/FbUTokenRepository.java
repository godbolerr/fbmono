package com.work.fb.repository;

import com.work.fb.domain.FbUToken;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FbUToken entity.
 */
@SuppressWarnings("unused")
public interface FbUTokenRepository extends JpaRepository<FbUToken,Long> {

}
