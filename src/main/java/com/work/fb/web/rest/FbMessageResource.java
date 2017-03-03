package com.work.fb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.work.fb.service.FbMessageService;
import com.work.fb.web.rest.util.HeaderUtil;
import com.work.fb.service.dto.FbMessageDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing FbMessage.
 */
@RestController
@RequestMapping("/api")
public class FbMessageResource {

    private final Logger log = LoggerFactory.getLogger(FbMessageResource.class);

    private static final String ENTITY_NAME = "fbMessage";
        
    private final FbMessageService fbMessageService;

    public FbMessageResource(FbMessageService fbMessageService) {
        this.fbMessageService = fbMessageService;
    }

    /**
     * POST  /fb-messages : Create a new fbMessage.
     *
     * @param fbMessageDTO the fbMessageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fbMessageDTO, or with status 400 (Bad Request) if the fbMessage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fb-messages")
    @Timed
    public ResponseEntity<FbMessageDTO> createFbMessage(@RequestBody FbMessageDTO fbMessageDTO) throws URISyntaxException {
        log.debug("REST request to save FbMessage : {}", fbMessageDTO);
        if (fbMessageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fbMessage cannot already have an ID")).body(null);
        }
        FbMessageDTO result = fbMessageService.save(fbMessageDTO);
        return ResponseEntity.created(new URI("/api/fb-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fb-messages : Updates an existing fbMessage.
     *
     * @param fbMessageDTO the fbMessageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fbMessageDTO,
     * or with status 400 (Bad Request) if the fbMessageDTO is not valid,
     * or with status 500 (Internal Server Error) if the fbMessageDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fb-messages")
    @Timed
    public ResponseEntity<FbMessageDTO> updateFbMessage(@RequestBody FbMessageDTO fbMessageDTO) throws URISyntaxException {
        log.debug("REST request to update FbMessage : {}", fbMessageDTO);
        if (fbMessageDTO.getId() == null) {
            return createFbMessage(fbMessageDTO);
        }
        FbMessageDTO result = fbMessageService.save(fbMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fbMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fb-messages : get all the fbMessages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fbMessages in body
     */
    @GetMapping("/fb-messages")
    @Timed
    public List<FbMessageDTO> getAllFbMessages() {
        log.debug("REST request to get all FbMessages");
        return fbMessageService.findAll();
    }

    /**
     * GET  /fb-messages/:id : get the "id" fbMessage.
     *
     * @param id the id of the fbMessageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fbMessageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fb-messages/{id}")
    @Timed
    public ResponseEntity<FbMessageDTO> getFbMessage(@PathVariable Long id) {
        log.debug("REST request to get FbMessage : {}", id);
        FbMessageDTO fbMessageDTO = fbMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fbMessageDTO));
    }

    /**
     * DELETE  /fb-messages/:id : delete the "id" fbMessage.
     *
     * @param id the id of the fbMessageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fb-messages/{id}")
    @Timed
    public ResponseEntity<Void> deleteFbMessage(@PathVariable Long id) {
        log.debug("REST request to delete FbMessage : {}", id);
        fbMessageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
