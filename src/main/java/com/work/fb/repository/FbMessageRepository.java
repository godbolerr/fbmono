package com.work.fb.repository;

import com.work.fb.domain.FbMessage;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FbMessage entity.
 */
@SuppressWarnings("unused")
public interface FbMessageRepository extends JpaRepository<FbMessage,Long> {

}
