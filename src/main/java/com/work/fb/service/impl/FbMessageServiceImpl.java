package com.work.fb.service.impl;

import com.work.fb.service.FbMessageService;
import com.work.fb.domain.FbMessage;
import com.work.fb.repository.FbMessageRepository;
import com.work.fb.service.dto.FbMessageDTO;
import com.work.fb.service.mapper.FbMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FbMessage.
 */
@Service
@Transactional
public class FbMessageServiceImpl implements FbMessageService{

    private final Logger log = LoggerFactory.getLogger(FbMessageServiceImpl.class);
    
    private final FbMessageRepository fbMessageRepository;

    private final FbMessageMapper fbMessageMapper;

    public FbMessageServiceImpl(FbMessageRepository fbMessageRepository, FbMessageMapper fbMessageMapper) {
        this.fbMessageRepository = fbMessageRepository;
        this.fbMessageMapper = fbMessageMapper;
    }

    /**
     * Save a fbMessage.
     *
     * @param fbMessageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FbMessageDTO save(FbMessageDTO fbMessageDTO) {
        log.debug("Request to save FbMessage : {}", fbMessageDTO);
        FbMessage fbMessage = fbMessageMapper.fbMessageDTOToFbMessage(fbMessageDTO);
        fbMessage = fbMessageRepository.save(fbMessage);
        FbMessageDTO result = fbMessageMapper.fbMessageToFbMessageDTO(fbMessage);
        return result;
    }

    /**
     *  Get all the fbMessages.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FbMessageDTO> findAll() {
        log.debug("Request to get all FbMessages");
        List<FbMessageDTO> result = fbMessageRepository.findAll().stream()
            .map(fbMessageMapper::fbMessageToFbMessageDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one fbMessage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FbMessageDTO findOne(Long id) {
        log.debug("Request to get FbMessage : {}", id);
        FbMessage fbMessage = fbMessageRepository.findOne(id);
        FbMessageDTO fbMessageDTO = fbMessageMapper.fbMessageToFbMessageDTO(fbMessage);
        return fbMessageDTO;
    }

    /**
     *  Delete the  fbMessage by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FbMessage : {}", id);
        fbMessageRepository.delete(id);
    }
}
