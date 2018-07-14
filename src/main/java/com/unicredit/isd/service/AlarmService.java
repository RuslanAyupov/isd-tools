package com.unicredit.isd.service;

import com.unicredit.isd.domain.Alarm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Alarm.
 */
public interface AlarmService {

    /**
     * Save a alarm.
     *
     * @param alarm the entity to save
     * @return the persisted entity
     */
    Alarm save(Alarm alarm);

    /**
     * Get all the alarms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Alarm> findAll(Pageable pageable);


    /**
     * Get the "id" alarm.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Alarm> findOne(Long id);

    /**
     * Delete the "id" alarm.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
