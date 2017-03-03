package com.work.fb.service;

import com.work.fb.service.dto.FbMessageDTO;
import java.util.List;

/**
 * Service Interface for managing FbMessage.
 */
public interface FbMessageService {

    /**
     * Save a fbMessage.
     *
     * @param fbMessageDTO the entity to save
     * @return the persisted entity
     */
    FbMessageDTO save(FbMessageDTO fbMessageDTO);

    /**
     *  Get all the fbMessages.
     *  
     *  @return the list of entities
     */
    List<FbMessageDTO> findAll();

    /**
     *  Get the "id" fbMessage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FbMessageDTO findOne(Long id);

    /**
     *  Delete the "id" fbMessage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
