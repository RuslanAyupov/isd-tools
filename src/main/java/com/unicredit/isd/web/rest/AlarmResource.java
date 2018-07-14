package com.unicredit.isd.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.unicredit.isd.domain.Alarm;
import com.unicredit.isd.service.AlarmService;
import com.unicredit.isd.web.rest.errors.BadRequestAlertException;
import com.unicredit.isd.web.rest.util.HeaderUtil;
import com.unicredit.isd.web.rest.util.PaginationUtil;
import com.unicredit.isd.service.dto.AlarmCriteria;
import com.unicredit.isd.service.AlarmQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Alarm.
 */
@RestController
@RequestMapping("/api")
public class AlarmResource {

    private final Logger log = LoggerFactory.getLogger(AlarmResource.class);

    private static final String ENTITY_NAME = "alarm";

    private final AlarmService alarmService;

    private final AlarmQueryService alarmQueryService;

    public AlarmResource(AlarmService alarmService, AlarmQueryService alarmQueryService) {
        this.alarmService = alarmService;
        this.alarmQueryService = alarmQueryService;
    }

    /**
     * POST  /alarms : Create a new alarm.
     *
     * @param alarm the alarm to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alarm, or with status 400 (Bad Request) if the alarm has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alarms")
    @Timed
    public ResponseEntity<Alarm> createAlarm(@Valid @RequestBody Alarm alarm) throws URISyntaxException {
        log.debug("REST request to save Alarm : {}", alarm);
        if (alarm.getId() != null) {
            throw new BadRequestAlertException("A new alarm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Alarm result = alarmService.save(alarm);
        return ResponseEntity.created(new URI("/api/alarms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alarms : Updates an existing alarm.
     *
     * @param alarm the alarm to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alarm,
     * or with status 400 (Bad Request) if the alarm is not valid,
     * or with status 500 (Internal Server Error) if the alarm couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alarms")
    @Timed
    public ResponseEntity<Alarm> updateAlarm(@Valid @RequestBody Alarm alarm) throws URISyntaxException {
        log.debug("REST request to update Alarm : {}", alarm);
        if (alarm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Alarm result = alarmService.save(alarm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alarm.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alarms : get all the alarms.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of alarms in body
     */
    @GetMapping("/alarms")
    @Timed
    public ResponseEntity<List<Alarm>> getAllAlarms(AlarmCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Alarms by criteria: {}", criteria);
        Page<Alarm> page = alarmQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/alarms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /alarms/:id : get the "id" alarm.
     *
     * @param id the id of the alarm to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alarm, or with status 404 (Not Found)
     */
    @GetMapping("/alarms/{id}")
    @Timed
    public ResponseEntity<Alarm> getAlarm(@PathVariable Long id) {
        log.debug("REST request to get Alarm : {}", id);
        Optional<Alarm> alarm = alarmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alarm);
    }

    /**
     * DELETE  /alarms/:id : delete the "id" alarm.
     *
     * @param id the id of the alarm to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alarms/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlarm(@PathVariable Long id) {
        log.debug("REST request to delete Alarm : {}", id);
        alarmService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
