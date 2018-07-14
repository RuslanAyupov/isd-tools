package com.unicredit.isd.web.rest;

import com.unicredit.isd.IsdtoolsApp;

import com.unicredit.isd.domain.Alarm;
import com.unicredit.isd.repository.AlarmRepository;
import com.unicredit.isd.service.AlarmService;
import com.unicredit.isd.web.rest.errors.ExceptionTranslator;
import com.unicredit.isd.service.dto.AlarmCriteria;
import com.unicredit.isd.service.AlarmQueryService;

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


import static com.unicredit.isd.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AlarmResource REST controller.
 *
 * @see AlarmResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IsdtoolsApp.class)
public class AlarmResourceIntTest {

    private static final String DEFAULT_APPL = "AAAAAAAAAA";
    private static final String UPDATED_APPL = "BBBBBBBBBB";

    private static final String DEFAULT_BFUNC = "AAAAAAAAAA";
    private static final String UPDATED_BFUNC = "BBBBBBBBBB";

    private static final String DEFAULT_CHGJ = "AAAAAAAAAA";
    private static final String UPDATED_CHGJ = "BBBBBBBBBB";

    private static final String DEFAULT_CI = "AAAAAAAAAA";
    private static final String UPDATED_CI = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FMAIL = "AAAAAAAAAA";
    private static final String UPDATED_FMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FSMS = "AAAAAAAAAA";
    private static final String UPDATED_FSMS = "BBBBBBBBBB";

    private static final String DEFAULT_FTLG = "AAAAAAAAAA";
    private static final String UPDATED_FTLG = "BBBBBBBBBB";

    private static final String DEFAULT_HPSM = "AAAAAAAAAA";
    private static final String UPDATED_HPSM = "BBBBBBBBBB";

    private static final String DEFAULT_INFRASTRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_INFRASTRUCTURE = "BBBBBBBBBB";

    private static final String DEFAULT_MESSTEXT = "AAAAAAAAAA";
    private static final String UPDATED_MESSTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_SITNAME = "AAAAAAAAAA";
    private static final String UPDATED_SITNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SITTYPE = "AAAAAAAAAA";
    private static final String UPDATED_SITTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SMS = "AAAAAAAAAA";
    private static final String UPDATED_SMS = "BBBBBBBBBB";

    private static final String DEFAULT_TLG = "AAAAAAAAAA";
    private static final String UPDATED_TLG = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_HPSM_OVERRIDE = "AAAAAAAAAA";
    private static final String UPDATED_HPSM_OVERRIDE = "BBBBBBBBBB";

    @Autowired
    private AlarmRepository alarmRepository;

    

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private AlarmQueryService alarmQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlarmMockMvc;

    private Alarm alarm;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlarmResource alarmResource = new AlarmResource(alarmService, alarmQueryService);
        this.restAlarmMockMvc = MockMvcBuilders.standaloneSetup(alarmResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alarm createEntity(EntityManager em) {
        Alarm alarm = new Alarm()
            .appl(DEFAULT_APPL)
            .bfunc(DEFAULT_BFUNC)
            .chgj(DEFAULT_CHGJ)
            .ci(DEFAULT_CI)
            .desc(DEFAULT_DESC)
            .email(DEFAULT_EMAIL)
            .fmail(DEFAULT_FMAIL)
            .fsms(DEFAULT_FSMS)
            .ftlg(DEFAULT_FTLG)
            .hpsm(DEFAULT_HPSM)
            .infrastructure(DEFAULT_INFRASTRUCTURE)
            .messtext(DEFAULT_MESSTEXT)
            .sitname(DEFAULT_SITNAME)
            .sittype(DEFAULT_SITTYPE)
            .sms(DEFAULT_SMS)
            .tlg(DEFAULT_TLG)
            .url(DEFAULT_URL)
            .hpsm_override(DEFAULT_HPSM_OVERRIDE);
        return alarm;
    }

    @Before
    public void initTest() {
        alarm = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlarm() throws Exception {
        int databaseSizeBeforeCreate = alarmRepository.findAll().size();

        // Create the Alarm
        restAlarmMockMvc.perform(post("/api/alarms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alarm)))
            .andExpect(status().isCreated());

        // Validate the Alarm in the database
        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeCreate + 1);
        Alarm testAlarm = alarmList.get(alarmList.size() - 1);
        assertThat(testAlarm.getAppl()).isEqualTo(DEFAULT_APPL);
        assertThat(testAlarm.getBfunc()).isEqualTo(DEFAULT_BFUNC);
        assertThat(testAlarm.getChgj()).isEqualTo(DEFAULT_CHGJ);
        assertThat(testAlarm.getCi()).isEqualTo(DEFAULT_CI);
        assertThat(testAlarm.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testAlarm.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAlarm.getFmail()).isEqualTo(DEFAULT_FMAIL);
        assertThat(testAlarm.getFsms()).isEqualTo(DEFAULT_FSMS);
        assertThat(testAlarm.getFtlg()).isEqualTo(DEFAULT_FTLG);
        assertThat(testAlarm.getHpsm()).isEqualTo(DEFAULT_HPSM);
        assertThat(testAlarm.getInfrastructure()).isEqualTo(DEFAULT_INFRASTRUCTURE);
        assertThat(testAlarm.getMesstext()).isEqualTo(DEFAULT_MESSTEXT);
        assertThat(testAlarm.getSitname()).isEqualTo(DEFAULT_SITNAME);
        assertThat(testAlarm.getSittype()).isEqualTo(DEFAULT_SITTYPE);
        assertThat(testAlarm.getSms()).isEqualTo(DEFAULT_SMS);
        assertThat(testAlarm.getTlg()).isEqualTo(DEFAULT_TLG);
        assertThat(testAlarm.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testAlarm.getHpsm_override()).isEqualTo(DEFAULT_HPSM_OVERRIDE);
    }

    @Test
    @Transactional
    public void createAlarmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alarmRepository.findAll().size();

        // Create the Alarm with an existing ID
        alarm.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlarmMockMvc.perform(post("/api/alarms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alarm)))
            .andExpect(status().isBadRequest());

        // Validate the Alarm in the database
        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkApplIsRequired() throws Exception {
        int databaseSizeBeforeTest = alarmRepository.findAll().size();
        // set the field null
        alarm.setAppl(null);

        // Create the Alarm, which fails.

        restAlarmMockMvc.perform(post("/api/alarms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alarm)))
            .andExpect(status().isBadRequest());

        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = alarmRepository.findAll().size();
        // set the field null
        alarm.setUrl(null);

        // Create the Alarm, which fails.

        restAlarmMockMvc.perform(post("/api/alarms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alarm)))
            .andExpect(status().isBadRequest());

        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlarms() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList
        restAlarmMockMvc.perform(get("/api/alarms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alarm.getId().intValue())))
            .andExpect(jsonPath("$.[*].appl").value(hasItem(DEFAULT_APPL.toString())))
            .andExpect(jsonPath("$.[*].bfunc").value(hasItem(DEFAULT_BFUNC.toString())))
            .andExpect(jsonPath("$.[*].chgj").value(hasItem(DEFAULT_CHGJ.toString())))
            .andExpect(jsonPath("$.[*].ci").value(hasItem(DEFAULT_CI.toString())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].fmail").value(hasItem(DEFAULT_FMAIL.toString())))
            .andExpect(jsonPath("$.[*].fsms").value(hasItem(DEFAULT_FSMS.toString())))
            .andExpect(jsonPath("$.[*].ftlg").value(hasItem(DEFAULT_FTLG.toString())))
            .andExpect(jsonPath("$.[*].hpsm").value(hasItem(DEFAULT_HPSM.toString())))
            .andExpect(jsonPath("$.[*].infrastructure").value(hasItem(DEFAULT_INFRASTRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].messtext").value(hasItem(DEFAULT_MESSTEXT.toString())))
            .andExpect(jsonPath("$.[*].sitname").value(hasItem(DEFAULT_SITNAME.toString())))
            .andExpect(jsonPath("$.[*].sittype").value(hasItem(DEFAULT_SITTYPE.toString())))
            .andExpect(jsonPath("$.[*].sms").value(hasItem(DEFAULT_SMS.toString())))
            .andExpect(jsonPath("$.[*].tlg").value(hasItem(DEFAULT_TLG.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].hpsm_override").value(hasItem(DEFAULT_HPSM_OVERRIDE.toString())));
    }
    

    @Test
    @Transactional
    public void getAlarm() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get the alarm
        restAlarmMockMvc.perform(get("/api/alarms/{id}", alarm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alarm.getId().intValue()))
            .andExpect(jsonPath("$.appl").value(DEFAULT_APPL.toString()))
            .andExpect(jsonPath("$.bfunc").value(DEFAULT_BFUNC.toString()))
            .andExpect(jsonPath("$.chgj").value(DEFAULT_CHGJ.toString()))
            .andExpect(jsonPath("$.ci").value(DEFAULT_CI.toString()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.fmail").value(DEFAULT_FMAIL.toString()))
            .andExpect(jsonPath("$.fsms").value(DEFAULT_FSMS.toString()))
            .andExpect(jsonPath("$.ftlg").value(DEFAULT_FTLG.toString()))
            .andExpect(jsonPath("$.hpsm").value(DEFAULT_HPSM.toString()))
            .andExpect(jsonPath("$.infrastructure").value(DEFAULT_INFRASTRUCTURE.toString()))
            .andExpect(jsonPath("$.messtext").value(DEFAULT_MESSTEXT.toString()))
            .andExpect(jsonPath("$.sitname").value(DEFAULT_SITNAME.toString()))
            .andExpect(jsonPath("$.sittype").value(DEFAULT_SITTYPE.toString()))
            .andExpect(jsonPath("$.sms").value(DEFAULT_SMS.toString()))
            .andExpect(jsonPath("$.tlg").value(DEFAULT_TLG.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.hpsm_override").value(DEFAULT_HPSM_OVERRIDE.toString()));
    }

    @Test
    @Transactional
    public void getAllAlarmsByApplIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where appl equals to DEFAULT_APPL
        defaultAlarmShouldBeFound("appl.equals=" + DEFAULT_APPL);

        // Get all the alarmList where appl equals to UPDATED_APPL
        defaultAlarmShouldNotBeFound("appl.equals=" + UPDATED_APPL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByApplIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where appl in DEFAULT_APPL or UPDATED_APPL
        defaultAlarmShouldBeFound("appl.in=" + DEFAULT_APPL + "," + UPDATED_APPL);

        // Get all the alarmList where appl equals to UPDATED_APPL
        defaultAlarmShouldNotBeFound("appl.in=" + UPDATED_APPL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByApplIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where appl is not null
        defaultAlarmShouldBeFound("appl.specified=true");

        // Get all the alarmList where appl is null
        defaultAlarmShouldNotBeFound("appl.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByBfuncIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where bfunc equals to DEFAULT_BFUNC
        defaultAlarmShouldBeFound("bfunc.equals=" + DEFAULT_BFUNC);

        // Get all the alarmList where bfunc equals to UPDATED_BFUNC
        defaultAlarmShouldNotBeFound("bfunc.equals=" + UPDATED_BFUNC);
    }

    @Test
    @Transactional
    public void getAllAlarmsByBfuncIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where bfunc in DEFAULT_BFUNC or UPDATED_BFUNC
        defaultAlarmShouldBeFound("bfunc.in=" + DEFAULT_BFUNC + "," + UPDATED_BFUNC);

        // Get all the alarmList where bfunc equals to UPDATED_BFUNC
        defaultAlarmShouldNotBeFound("bfunc.in=" + UPDATED_BFUNC);
    }

    @Test
    @Transactional
    public void getAllAlarmsByBfuncIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where bfunc is not null
        defaultAlarmShouldBeFound("bfunc.specified=true");

        // Get all the alarmList where bfunc is null
        defaultAlarmShouldNotBeFound("bfunc.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByChgjIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where chgj equals to DEFAULT_CHGJ
        defaultAlarmShouldBeFound("chgj.equals=" + DEFAULT_CHGJ);

        // Get all the alarmList where chgj equals to UPDATED_CHGJ
        defaultAlarmShouldNotBeFound("chgj.equals=" + UPDATED_CHGJ);
    }

    @Test
    @Transactional
    public void getAllAlarmsByChgjIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where chgj in DEFAULT_CHGJ or UPDATED_CHGJ
        defaultAlarmShouldBeFound("chgj.in=" + DEFAULT_CHGJ + "," + UPDATED_CHGJ);

        // Get all the alarmList where chgj equals to UPDATED_CHGJ
        defaultAlarmShouldNotBeFound("chgj.in=" + UPDATED_CHGJ);
    }

    @Test
    @Transactional
    public void getAllAlarmsByChgjIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where chgj is not null
        defaultAlarmShouldBeFound("chgj.specified=true");

        // Get all the alarmList where chgj is null
        defaultAlarmShouldNotBeFound("chgj.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByCiIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where ci equals to DEFAULT_CI
        defaultAlarmShouldBeFound("ci.equals=" + DEFAULT_CI);

        // Get all the alarmList where ci equals to UPDATED_CI
        defaultAlarmShouldNotBeFound("ci.equals=" + UPDATED_CI);
    }

    @Test
    @Transactional
    public void getAllAlarmsByCiIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where ci in DEFAULT_CI or UPDATED_CI
        defaultAlarmShouldBeFound("ci.in=" + DEFAULT_CI + "," + UPDATED_CI);

        // Get all the alarmList where ci equals to UPDATED_CI
        defaultAlarmShouldNotBeFound("ci.in=" + UPDATED_CI);
    }

    @Test
    @Transactional
    public void getAllAlarmsByCiIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where ci is not null
        defaultAlarmShouldBeFound("ci.specified=true");

        // Get all the alarmList where ci is null
        defaultAlarmShouldNotBeFound("ci.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByDescIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where desc equals to DEFAULT_DESC
        defaultAlarmShouldBeFound("desc.equals=" + DEFAULT_DESC);

        // Get all the alarmList where desc equals to UPDATED_DESC
        defaultAlarmShouldNotBeFound("desc.equals=" + UPDATED_DESC);
    }

    @Test
    @Transactional
    public void getAllAlarmsByDescIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where desc in DEFAULT_DESC or UPDATED_DESC
        defaultAlarmShouldBeFound("desc.in=" + DEFAULT_DESC + "," + UPDATED_DESC);

        // Get all the alarmList where desc equals to UPDATED_DESC
        defaultAlarmShouldNotBeFound("desc.in=" + UPDATED_DESC);
    }

    @Test
    @Transactional
    public void getAllAlarmsByDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where desc is not null
        defaultAlarmShouldBeFound("desc.specified=true");

        // Get all the alarmList where desc is null
        defaultAlarmShouldNotBeFound("desc.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where email equals to DEFAULT_EMAIL
        defaultAlarmShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the alarmList where email equals to UPDATED_EMAIL
        defaultAlarmShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultAlarmShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the alarmList where email equals to UPDATED_EMAIL
        defaultAlarmShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where email is not null
        defaultAlarmShouldBeFound("email.specified=true");

        // Get all the alarmList where email is null
        defaultAlarmShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByFmailIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where fmail equals to DEFAULT_FMAIL
        defaultAlarmShouldBeFound("fmail.equals=" + DEFAULT_FMAIL);

        // Get all the alarmList where fmail equals to UPDATED_FMAIL
        defaultAlarmShouldNotBeFound("fmail.equals=" + UPDATED_FMAIL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByFmailIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where fmail in DEFAULT_FMAIL or UPDATED_FMAIL
        defaultAlarmShouldBeFound("fmail.in=" + DEFAULT_FMAIL + "," + UPDATED_FMAIL);

        // Get all the alarmList where fmail equals to UPDATED_FMAIL
        defaultAlarmShouldNotBeFound("fmail.in=" + UPDATED_FMAIL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByFmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where fmail is not null
        defaultAlarmShouldBeFound("fmail.specified=true");

        // Get all the alarmList where fmail is null
        defaultAlarmShouldNotBeFound("fmail.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByFsmsIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where fsms equals to DEFAULT_FSMS
        defaultAlarmShouldBeFound("fsms.equals=" + DEFAULT_FSMS);

        // Get all the alarmList where fsms equals to UPDATED_FSMS
        defaultAlarmShouldNotBeFound("fsms.equals=" + UPDATED_FSMS);
    }

    @Test
    @Transactional
    public void getAllAlarmsByFsmsIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where fsms in DEFAULT_FSMS or UPDATED_FSMS
        defaultAlarmShouldBeFound("fsms.in=" + DEFAULT_FSMS + "," + UPDATED_FSMS);

        // Get all the alarmList where fsms equals to UPDATED_FSMS
        defaultAlarmShouldNotBeFound("fsms.in=" + UPDATED_FSMS);
    }

    @Test
    @Transactional
    public void getAllAlarmsByFsmsIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where fsms is not null
        defaultAlarmShouldBeFound("fsms.specified=true");

        // Get all the alarmList where fsms is null
        defaultAlarmShouldNotBeFound("fsms.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByFtlgIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where ftlg equals to DEFAULT_FTLG
        defaultAlarmShouldBeFound("ftlg.equals=" + DEFAULT_FTLG);

        // Get all the alarmList where ftlg equals to UPDATED_FTLG
        defaultAlarmShouldNotBeFound("ftlg.equals=" + UPDATED_FTLG);
    }

    @Test
    @Transactional
    public void getAllAlarmsByFtlgIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where ftlg in DEFAULT_FTLG or UPDATED_FTLG
        defaultAlarmShouldBeFound("ftlg.in=" + DEFAULT_FTLG + "," + UPDATED_FTLG);

        // Get all the alarmList where ftlg equals to UPDATED_FTLG
        defaultAlarmShouldNotBeFound("ftlg.in=" + UPDATED_FTLG);
    }

    @Test
    @Transactional
    public void getAllAlarmsByFtlgIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where ftlg is not null
        defaultAlarmShouldBeFound("ftlg.specified=true");

        // Get all the alarmList where ftlg is null
        defaultAlarmShouldNotBeFound("ftlg.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByHpsmIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where hpsm equals to DEFAULT_HPSM
        defaultAlarmShouldBeFound("hpsm.equals=" + DEFAULT_HPSM);

        // Get all the alarmList where hpsm equals to UPDATED_HPSM
        defaultAlarmShouldNotBeFound("hpsm.equals=" + UPDATED_HPSM);
    }

    @Test
    @Transactional
    public void getAllAlarmsByHpsmIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where hpsm in DEFAULT_HPSM or UPDATED_HPSM
        defaultAlarmShouldBeFound("hpsm.in=" + DEFAULT_HPSM + "," + UPDATED_HPSM);

        // Get all the alarmList where hpsm equals to UPDATED_HPSM
        defaultAlarmShouldNotBeFound("hpsm.in=" + UPDATED_HPSM);
    }

    @Test
    @Transactional
    public void getAllAlarmsByHpsmIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where hpsm is not null
        defaultAlarmShouldBeFound("hpsm.specified=true");

        // Get all the alarmList where hpsm is null
        defaultAlarmShouldNotBeFound("hpsm.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByInfrastructureIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where infrastructure equals to DEFAULT_INFRASTRUCTURE
        defaultAlarmShouldBeFound("infrastructure.equals=" + DEFAULT_INFRASTRUCTURE);

        // Get all the alarmList where infrastructure equals to UPDATED_INFRASTRUCTURE
        defaultAlarmShouldNotBeFound("infrastructure.equals=" + UPDATED_INFRASTRUCTURE);
    }

    @Test
    @Transactional
    public void getAllAlarmsByInfrastructureIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where infrastructure in DEFAULT_INFRASTRUCTURE or UPDATED_INFRASTRUCTURE
        defaultAlarmShouldBeFound("infrastructure.in=" + DEFAULT_INFRASTRUCTURE + "," + UPDATED_INFRASTRUCTURE);

        // Get all the alarmList where infrastructure equals to UPDATED_INFRASTRUCTURE
        defaultAlarmShouldNotBeFound("infrastructure.in=" + UPDATED_INFRASTRUCTURE);
    }

    @Test
    @Transactional
    public void getAllAlarmsByInfrastructureIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where infrastructure is not null
        defaultAlarmShouldBeFound("infrastructure.specified=true");

        // Get all the alarmList where infrastructure is null
        defaultAlarmShouldNotBeFound("infrastructure.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByMesstextIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where messtext equals to DEFAULT_MESSTEXT
        defaultAlarmShouldBeFound("messtext.equals=" + DEFAULT_MESSTEXT);

        // Get all the alarmList where messtext equals to UPDATED_MESSTEXT
        defaultAlarmShouldNotBeFound("messtext.equals=" + UPDATED_MESSTEXT);
    }

    @Test
    @Transactional
    public void getAllAlarmsByMesstextIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where messtext in DEFAULT_MESSTEXT or UPDATED_MESSTEXT
        defaultAlarmShouldBeFound("messtext.in=" + DEFAULT_MESSTEXT + "," + UPDATED_MESSTEXT);

        // Get all the alarmList where messtext equals to UPDATED_MESSTEXT
        defaultAlarmShouldNotBeFound("messtext.in=" + UPDATED_MESSTEXT);
    }

    @Test
    @Transactional
    public void getAllAlarmsByMesstextIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where messtext is not null
        defaultAlarmShouldBeFound("messtext.specified=true");

        // Get all the alarmList where messtext is null
        defaultAlarmShouldNotBeFound("messtext.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsBySitnameIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sitname equals to DEFAULT_SITNAME
        defaultAlarmShouldBeFound("sitname.equals=" + DEFAULT_SITNAME);

        // Get all the alarmList where sitname equals to UPDATED_SITNAME
        defaultAlarmShouldNotBeFound("sitname.equals=" + UPDATED_SITNAME);
    }

    @Test
    @Transactional
    public void getAllAlarmsBySitnameIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sitname in DEFAULT_SITNAME or UPDATED_SITNAME
        defaultAlarmShouldBeFound("sitname.in=" + DEFAULT_SITNAME + "," + UPDATED_SITNAME);

        // Get all the alarmList where sitname equals to UPDATED_SITNAME
        defaultAlarmShouldNotBeFound("sitname.in=" + UPDATED_SITNAME);
    }

    @Test
    @Transactional
    public void getAllAlarmsBySitnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sitname is not null
        defaultAlarmShouldBeFound("sitname.specified=true");

        // Get all the alarmList where sitname is null
        defaultAlarmShouldNotBeFound("sitname.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsBySittypeIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sittype equals to DEFAULT_SITTYPE
        defaultAlarmShouldBeFound("sittype.equals=" + DEFAULT_SITTYPE);

        // Get all the alarmList where sittype equals to UPDATED_SITTYPE
        defaultAlarmShouldNotBeFound("sittype.equals=" + UPDATED_SITTYPE);
    }

    @Test
    @Transactional
    public void getAllAlarmsBySittypeIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sittype in DEFAULT_SITTYPE or UPDATED_SITTYPE
        defaultAlarmShouldBeFound("sittype.in=" + DEFAULT_SITTYPE + "," + UPDATED_SITTYPE);

        // Get all the alarmList where sittype equals to UPDATED_SITTYPE
        defaultAlarmShouldNotBeFound("sittype.in=" + UPDATED_SITTYPE);
    }

    @Test
    @Transactional
    public void getAllAlarmsBySittypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sittype is not null
        defaultAlarmShouldBeFound("sittype.specified=true");

        // Get all the alarmList where sittype is null
        defaultAlarmShouldNotBeFound("sittype.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsBySmsIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sms equals to DEFAULT_SMS
        defaultAlarmShouldBeFound("sms.equals=" + DEFAULT_SMS);

        // Get all the alarmList where sms equals to UPDATED_SMS
        defaultAlarmShouldNotBeFound("sms.equals=" + UPDATED_SMS);
    }

    @Test
    @Transactional
    public void getAllAlarmsBySmsIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sms in DEFAULT_SMS or UPDATED_SMS
        defaultAlarmShouldBeFound("sms.in=" + DEFAULT_SMS + "," + UPDATED_SMS);

        // Get all the alarmList where sms equals to UPDATED_SMS
        defaultAlarmShouldNotBeFound("sms.in=" + UPDATED_SMS);
    }

    @Test
    @Transactional
    public void getAllAlarmsBySmsIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where sms is not null
        defaultAlarmShouldBeFound("sms.specified=true");

        // Get all the alarmList where sms is null
        defaultAlarmShouldNotBeFound("sms.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByTlgIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where tlg equals to DEFAULT_TLG
        defaultAlarmShouldBeFound("tlg.equals=" + DEFAULT_TLG);

        // Get all the alarmList where tlg equals to UPDATED_TLG
        defaultAlarmShouldNotBeFound("tlg.equals=" + UPDATED_TLG);
    }

    @Test
    @Transactional
    public void getAllAlarmsByTlgIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where tlg in DEFAULT_TLG or UPDATED_TLG
        defaultAlarmShouldBeFound("tlg.in=" + DEFAULT_TLG + "," + UPDATED_TLG);

        // Get all the alarmList where tlg equals to UPDATED_TLG
        defaultAlarmShouldNotBeFound("tlg.in=" + UPDATED_TLG);
    }

    @Test
    @Transactional
    public void getAllAlarmsByTlgIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where tlg is not null
        defaultAlarmShouldBeFound("tlg.specified=true");

        // Get all the alarmList where tlg is null
        defaultAlarmShouldNotBeFound("tlg.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByUrlIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where url equals to DEFAULT_URL
        defaultAlarmShouldBeFound("url.equals=" + DEFAULT_URL);

        // Get all the alarmList where url equals to UPDATED_URL
        defaultAlarmShouldNotBeFound("url.equals=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByUrlIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where url in DEFAULT_URL or UPDATED_URL
        defaultAlarmShouldBeFound("url.in=" + DEFAULT_URL + "," + UPDATED_URL);

        // Get all the alarmList where url equals to UPDATED_URL
        defaultAlarmShouldNotBeFound("url.in=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllAlarmsByUrlIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where url is not null
        defaultAlarmShouldBeFound("url.specified=true");

        // Get all the alarmList where url is null
        defaultAlarmShouldNotBeFound("url.specified=false");
    }

    @Test
    @Transactional
    public void getAllAlarmsByHpsm_overrideIsEqualToSomething() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where hpsm_override equals to DEFAULT_HPSM_OVERRIDE
        defaultAlarmShouldBeFound("hpsm_override.equals=" + DEFAULT_HPSM_OVERRIDE);

        // Get all the alarmList where hpsm_override equals to UPDATED_HPSM_OVERRIDE
        defaultAlarmShouldNotBeFound("hpsm_override.equals=" + UPDATED_HPSM_OVERRIDE);
    }

    @Test
    @Transactional
    public void getAllAlarmsByHpsm_overrideIsInShouldWork() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where hpsm_override in DEFAULT_HPSM_OVERRIDE or UPDATED_HPSM_OVERRIDE
        defaultAlarmShouldBeFound("hpsm_override.in=" + DEFAULT_HPSM_OVERRIDE + "," + UPDATED_HPSM_OVERRIDE);

        // Get all the alarmList where hpsm_override equals to UPDATED_HPSM_OVERRIDE
        defaultAlarmShouldNotBeFound("hpsm_override.in=" + UPDATED_HPSM_OVERRIDE);
    }

    @Test
    @Transactional
    public void getAllAlarmsByHpsm_overrideIsNullOrNotNull() throws Exception {
        // Initialize the database
        alarmRepository.saveAndFlush(alarm);

        // Get all the alarmList where hpsm_override is not null
        defaultAlarmShouldBeFound("hpsm_override.specified=true");

        // Get all the alarmList where hpsm_override is null
        defaultAlarmShouldNotBeFound("hpsm_override.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAlarmShouldBeFound(String filter) throws Exception {
        restAlarmMockMvc.perform(get("/api/alarms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alarm.getId().intValue())))
            .andExpect(jsonPath("$.[*].appl").value(hasItem(DEFAULT_APPL.toString())))
            .andExpect(jsonPath("$.[*].bfunc").value(hasItem(DEFAULT_BFUNC.toString())))
            .andExpect(jsonPath("$.[*].chgj").value(hasItem(DEFAULT_CHGJ.toString())))
            .andExpect(jsonPath("$.[*].ci").value(hasItem(DEFAULT_CI.toString())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].fmail").value(hasItem(DEFAULT_FMAIL.toString())))
            .andExpect(jsonPath("$.[*].fsms").value(hasItem(DEFAULT_FSMS.toString())))
            .andExpect(jsonPath("$.[*].ftlg").value(hasItem(DEFAULT_FTLG.toString())))
            .andExpect(jsonPath("$.[*].hpsm").value(hasItem(DEFAULT_HPSM.toString())))
            .andExpect(jsonPath("$.[*].infrastructure").value(hasItem(DEFAULT_INFRASTRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].messtext").value(hasItem(DEFAULT_MESSTEXT.toString())))
            .andExpect(jsonPath("$.[*].sitname").value(hasItem(DEFAULT_SITNAME.toString())))
            .andExpect(jsonPath("$.[*].sittype").value(hasItem(DEFAULT_SITTYPE.toString())))
            .andExpect(jsonPath("$.[*].sms").value(hasItem(DEFAULT_SMS.toString())))
            .andExpect(jsonPath("$.[*].tlg").value(hasItem(DEFAULT_TLG.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].hpsm_override").value(hasItem(DEFAULT_HPSM_OVERRIDE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAlarmShouldNotBeFound(String filter) throws Exception {
        restAlarmMockMvc.perform(get("/api/alarms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingAlarm() throws Exception {
        // Get the alarm
        restAlarmMockMvc.perform(get("/api/alarms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlarm() throws Exception {
        // Initialize the database
        alarmService.save(alarm);

        int databaseSizeBeforeUpdate = alarmRepository.findAll().size();

        // Update the alarm
        Alarm updatedAlarm = alarmRepository.findById(alarm.getId()).get();
        // Disconnect from session so that the updates on updatedAlarm are not directly saved in db
        em.detach(updatedAlarm);
        updatedAlarm
            .appl(UPDATED_APPL)
            .bfunc(UPDATED_BFUNC)
            .chgj(UPDATED_CHGJ)
            .ci(UPDATED_CI)
            .desc(UPDATED_DESC)
            .email(UPDATED_EMAIL)
            .fmail(UPDATED_FMAIL)
            .fsms(UPDATED_FSMS)
            .ftlg(UPDATED_FTLG)
            .hpsm(UPDATED_HPSM)
            .infrastructure(UPDATED_INFRASTRUCTURE)
            .messtext(UPDATED_MESSTEXT)
            .sitname(UPDATED_SITNAME)
            .sittype(UPDATED_SITTYPE)
            .sms(UPDATED_SMS)
            .tlg(UPDATED_TLG)
            .url(UPDATED_URL)
            .hpsm_override(UPDATED_HPSM_OVERRIDE);

        restAlarmMockMvc.perform(put("/api/alarms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlarm)))
            .andExpect(status().isOk());

        // Validate the Alarm in the database
        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeUpdate);
        Alarm testAlarm = alarmList.get(alarmList.size() - 1);
        assertThat(testAlarm.getAppl()).isEqualTo(UPDATED_APPL);
        assertThat(testAlarm.getBfunc()).isEqualTo(UPDATED_BFUNC);
        assertThat(testAlarm.getChgj()).isEqualTo(UPDATED_CHGJ);
        assertThat(testAlarm.getCi()).isEqualTo(UPDATED_CI);
        assertThat(testAlarm.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testAlarm.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAlarm.getFmail()).isEqualTo(UPDATED_FMAIL);
        assertThat(testAlarm.getFsms()).isEqualTo(UPDATED_FSMS);
        assertThat(testAlarm.getFtlg()).isEqualTo(UPDATED_FTLG);
        assertThat(testAlarm.getHpsm()).isEqualTo(UPDATED_HPSM);
        assertThat(testAlarm.getInfrastructure()).isEqualTo(UPDATED_INFRASTRUCTURE);
        assertThat(testAlarm.getMesstext()).isEqualTo(UPDATED_MESSTEXT);
        assertThat(testAlarm.getSitname()).isEqualTo(UPDATED_SITNAME);
        assertThat(testAlarm.getSittype()).isEqualTo(UPDATED_SITTYPE);
        assertThat(testAlarm.getSms()).isEqualTo(UPDATED_SMS);
        assertThat(testAlarm.getTlg()).isEqualTo(UPDATED_TLG);
        assertThat(testAlarm.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testAlarm.getHpsm_override()).isEqualTo(UPDATED_HPSM_OVERRIDE);
    }

    @Test
    @Transactional
    public void updateNonExistingAlarm() throws Exception {
        int databaseSizeBeforeUpdate = alarmRepository.findAll().size();

        // Create the Alarm

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlarmMockMvc.perform(put("/api/alarms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alarm)))
            .andExpect(status().isBadRequest());

        // Validate the Alarm in the database
        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlarm() throws Exception {
        // Initialize the database
        alarmService.save(alarm);

        int databaseSizeBeforeDelete = alarmRepository.findAll().size();

        // Get the alarm
        restAlarmMockMvc.perform(delete("/api/alarms/{id}", alarm.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Alarm> alarmList = alarmRepository.findAll();
        assertThat(alarmList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alarm.class);
        Alarm alarm1 = new Alarm();
        alarm1.setId(1L);
        Alarm alarm2 = new Alarm();
        alarm2.setId(alarm1.getId());
        assertThat(alarm1).isEqualTo(alarm2);
        alarm2.setId(2L);
        assertThat(alarm1).isNotEqualTo(alarm2);
        alarm1.setId(null);
        assertThat(alarm1).isNotEqualTo(alarm2);
    }
}
