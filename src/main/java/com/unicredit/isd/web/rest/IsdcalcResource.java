package com.unicredit.isd.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.unicredit.isd.domain.Isdcalc;
import com.unicredit.isd.repository.IsdcalcRepository;
import com.unicredit.isd.web.rest.errors.BadRequestAlertException;
import com.unicredit.isd.web.rest.util.HeaderUtil;
import com.unicredit.isd.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Isdcalc.
 */
@RestController
@RequestMapping("/api")
public class IsdcalcResource {

    private final Logger log = LoggerFactory.getLogger(IsdcalcResource.class);

    private static final String ENTITY_NAME = "isdcalc";

    private final IsdcalcRepository isdcalcRepository;

    public IsdcalcResource(IsdcalcRepository isdcalcRepository) {
        this.isdcalcRepository = isdcalcRepository;
    }

    /**
     * POST  /isdcalcs : Create a new isdcalc.
     *
     * @param isdcalc the isdcalc to create
     * @return the ResponseEntity with status 201 (Created) and with body the new isdcalc, or with status 400 (Bad Request) if the isdcalc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/isdcalcs")
    @Timed
    public ResponseEntity<Isdcalc> createIsdcalc(@RequestBody Isdcalc isdcalc) throws URISyntaxException {
        log.debug("REST request to save Isdcalc : {}", isdcalc);
        if (isdcalc.getId() != null) {
            throw new BadRequestAlertException("A new isdcalc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Isdcalc result = isdcalcRepository.save(isdcalc);
        return ResponseEntity.created(new URI("/api/isdcalcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /isdcalcs : Updates an existing isdcalc.
     *
     * @param isdcalc the isdcalc to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated isdcalc,
     * or with status 400 (Bad Request) if the isdcalc is not valid,
     * or with status 500 (Internal Server Error) if the isdcalc couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/isdcalcs")
    @Timed
    public ResponseEntity<Isdcalc> updateIsdcalc(@RequestBody Isdcalc isdcalc) throws URISyntaxException {
        log.debug("REST request to update Isdcalc : {}", isdcalc);
        if (isdcalc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Isdcalc result = isdcalcRepository.save(isdcalc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, isdcalc.getId().toString()))
            .body(result);
    }

    /**
     * GET  /isdcalcs : get all the isdcalcs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of isdcalcs in body
     */
    @GetMapping("/isdcalcs")
    @Timed
    public ResponseEntity<List<Isdcalc>> getAllIsdcalcs(Pageable pageable) {
        log.debug("REST request to get a page of Isdcalcs");
        Page<Isdcalc> page = isdcalcRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/isdcalcs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /isdcalcs/:id : get the "id" isdcalc.
     *
     * @param id the id of the isdcalc to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the isdcalc, or with status 404 (Not Found)
     */
    @GetMapping("/isdcalcs/{id}")
    @Timed
    public ResponseEntity<Isdcalc> getIsdcalc(@PathVariable Long id) {
        log.debug("REST request to get Isdcalc : {}", id);
        Optional<Isdcalc> isdcalc = isdcalcRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(isdcalc);
    }

    /**
     * DELETE  /isdcalcs/:id : delete the "id" isdcalc.
     *
     * @param id the id of the isdcalc to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/isdcalcs/{id}")
    @Timed
    public ResponseEntity<Void> deleteIsdcalc(@PathVariable Long id) {
        log.debug("REST request to delete Isdcalc : {}", id);

        isdcalcRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
