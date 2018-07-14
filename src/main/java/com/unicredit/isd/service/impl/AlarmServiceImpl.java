package com.unicredit.isd.service.impl;

import com.unicredit.isd.service.AlarmService;
import com.unicredit.isd.domain.Alarm;
import com.unicredit.isd.repository.AlarmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Alarm.
 */
@Service
@Transactional
public class AlarmServiceImpl implements AlarmService {

    private final Logger log = LoggerFactory.getLogger(AlarmServiceImpl.class);

    private final AlarmRepository alarmRepository;

    public AlarmServiceImpl(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    /**
     * Save a alarm.
     *
     * @param alarm the entity to save
     * @return the persisted entity
     */
    @Override
    public Alarm save(Alarm alarm) {
        log.debug("Request to save Alarm : {}", alarm);        return alarmRepository.save(alarm);
    }

    /**
     * Get all the alarms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Alarm> findAll(Pageable pageable) {
        log.debug("Request to get all Alarms");
        return alarmRepository.findAll(pageable);
    }


    /**
     * Get one alarm by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Alarm> findOne(Long id) {
        log.debug("Request to get Alarm : {}", id);
        return alarmRepository.findById(id);
    }

    /**
     * Delete the alarm by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Alarm : {}", id);
        alarmRepository.deleteById(id);
    }
}
