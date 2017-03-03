package com.work.fb.web.rest;

import com.work.fb.MonolithApp;

import com.work.fb.domain.FbMessage;
import com.work.fb.repository.FbMessageRepository;
import com.work.fb.service.FbMessageService;
import com.work.fb.service.dto.FbMessageDTO;
import com.work.fb.service.mapper.FbMessageMapper;
import com.work.fb.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FbMessageResource REST controller.
 *
 * @see FbMessageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonolithApp.class)
public class FbMessageResourceIntTest {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REPLY_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_REPLY_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_REPLY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_REPLY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_ON = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_ON = "BBBBBBBBBB";

    @Autowired
    private FbMessageRepository fbMessageRepository;

    @Autowired
    private FbMessageMapper fbMessageMapper;

    @Autowired
    private FbMessageService fbMessageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFbMessageMockMvc;

    private FbMessage fbMessage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FbMessageResource fbMessageResource = new FbMessageResource(fbMessageService);
        this.restFbMessageMockMvc = MockMvcBuilders.standaloneSetup(fbMessageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FbMessage createEntity(EntityManager em) {
        FbMessage fbMessage = new FbMessage()
                .userid(DEFAULT_USERID)
                .messageId(DEFAULT_MESSAGE_ID)
                .replyMessage(DEFAULT_REPLY_MESSAGE)
                .replyStatus(DEFAULT_REPLY_STATUS)
                .createdOn(DEFAULT_CREATED_ON);
        return fbMessage;
    }

    @Before
    public void initTest() {
        fbMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createFbMessage() throws Exception {
        int databaseSizeBeforeCreate = fbMessageRepository.findAll().size();

        // Create the FbMessage
        FbMessageDTO fbMessageDTO = fbMessageMapper.fbMessageToFbMessageDTO(fbMessage);

        restFbMessageMockMvc.perform(post("/api/fb-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fbMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the FbMessage in the database
        List<FbMessage> fbMessageList = fbMessageRepository.findAll();
        assertThat(fbMessageList).hasSize(databaseSizeBeforeCreate + 1);
        FbMessage testFbMessage = fbMessageList.get(fbMessageList.size() - 1);
        assertThat(testFbMessage.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testFbMessage.getMessageId()).isEqualTo(DEFAULT_MESSAGE_ID);
        assertThat(testFbMessage.getReplyMessage()).isEqualTo(DEFAULT_REPLY_MESSAGE);
        assertThat(testFbMessage.getReplyStatus()).isEqualTo(DEFAULT_REPLY_STATUS);
        assertThat(testFbMessage.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    }

    @Test
    @Transactional
    public void createFbMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fbMessageRepository.findAll().size();

        // Create the FbMessage with an existing ID
        FbMessage existingFbMessage = new FbMessage();
        existingFbMessage.setId(1L);
        FbMessageDTO existingFbMessageDTO = fbMessageMapper.fbMessageToFbMessageDTO(existingFbMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFbMessageMockMvc.perform(post("/api/fb-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingFbMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FbMessage> fbMessageList = fbMessageRepository.findAll();
        assertThat(fbMessageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFbMessages() throws Exception {
        // Initialize the database
        fbMessageRepository.saveAndFlush(fbMessage);

        // Get all the fbMessageList
        restFbMessageMockMvc.perform(get("/api/fb-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fbMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].messageId").value(hasItem(DEFAULT_MESSAGE_ID.toString())))
            .andExpect(jsonPath("$.[*].replyMessage").value(hasItem(DEFAULT_REPLY_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].replyStatus").value(hasItem(DEFAULT_REPLY_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())));
    }

    @Test
    @Transactional
    public void getFbMessage() throws Exception {
        // Initialize the database
        fbMessageRepository.saveAndFlush(fbMessage);

        // Get the fbMessage
        restFbMessageMockMvc.perform(get("/api/fb-messages/{id}", fbMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fbMessage.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.messageId").value(DEFAULT_MESSAGE_ID.toString()))
            .andExpect(jsonPath("$.replyMessage").value(DEFAULT_REPLY_MESSAGE.toString()))
            .andExpect(jsonPath("$.replyStatus").value(DEFAULT_REPLY_STATUS.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFbMessage() throws Exception {
        // Get the fbMessage
        restFbMessageMockMvc.perform(get("/api/fb-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFbMessage() throws Exception {
        // Initialize the database
        fbMessageRepository.saveAndFlush(fbMessage);
        int databaseSizeBeforeUpdate = fbMessageRepository.findAll().size();

        // Update the fbMessage
        FbMessage updatedFbMessage = fbMessageRepository.findOne(fbMessage.getId());
        updatedFbMessage
                .userid(UPDATED_USERID)
                .messageId(UPDATED_MESSAGE_ID)
                .replyMessage(UPDATED_REPLY_MESSAGE)
                .replyStatus(UPDATED_REPLY_STATUS)
                .createdOn(UPDATED_CREATED_ON);
        FbMessageDTO fbMessageDTO = fbMessageMapper.fbMessageToFbMessageDTO(updatedFbMessage);

        restFbMessageMockMvc.perform(put("/api/fb-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fbMessageDTO)))
            .andExpect(status().isOk());

        // Validate the FbMessage in the database
        List<FbMessage> fbMessageList = fbMessageRepository.findAll();
        assertThat(fbMessageList).hasSize(databaseSizeBeforeUpdate);
        FbMessage testFbMessage = fbMessageList.get(fbMessageList.size() - 1);
        assertThat(testFbMessage.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testFbMessage.getMessageId()).isEqualTo(UPDATED_MESSAGE_ID);
        assertThat(testFbMessage.getReplyMessage()).isEqualTo(UPDATED_REPLY_MESSAGE);
        assertThat(testFbMessage.getReplyStatus()).isEqualTo(UPDATED_REPLY_STATUS);
        assertThat(testFbMessage.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingFbMessage() throws Exception {
        int databaseSizeBeforeUpdate = fbMessageRepository.findAll().size();

        // Create the FbMessage
        FbMessageDTO fbMessageDTO = fbMessageMapper.fbMessageToFbMessageDTO(fbMessage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFbMessageMockMvc.perform(put("/api/fb-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fbMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the FbMessage in the database
        List<FbMessage> fbMessageList = fbMessageRepository.findAll();
        assertThat(fbMessageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFbMessage() throws Exception {
        // Initialize the database
        fbMessageRepository.saveAndFlush(fbMessage);
        int databaseSizeBeforeDelete = fbMessageRepository.findAll().size();

        // Get the fbMessage
        restFbMessageMockMvc.perform(delete("/api/fb-messages/{id}", fbMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FbMessage> fbMessageList = fbMessageRepository.findAll();
        assertThat(fbMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FbMessage.class);
    }
}
