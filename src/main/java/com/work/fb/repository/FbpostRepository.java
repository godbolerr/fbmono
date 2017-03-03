package com.work.fb.repository;

import com.work.fb.domain.Fbpost;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fbpost entity.
 */
@SuppressWarnings("unused")
public interface FbpostRepository extends JpaRepository<Fbpost,Long> {

}
