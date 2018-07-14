package com.unicredit.isd.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.unicredit.isd.domain.Alarm;
import com.unicredit.isd.domain.*; // for static metamodels
import com.unicredit.isd.repository.AlarmRepository;
import com.unicredit.isd.service.dto.AlarmCriteria;


/**
 * Service for executing complex queries for Alarm entities in the database.
 * The main input is a {@link AlarmCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Alarm} or a {@link Page} of {@link Alarm} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AlarmQueryService extends QueryService<Alarm> {

    private final Logger log = LoggerFactory.getLogger(AlarmQueryService.class);

    private final AlarmRepository alarmRepository;

    public AlarmQueryService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    /**
     * Return a {@link List} of {@link Alarm} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Alarm> findByCriteria(AlarmCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Alarm> specification = createSpecification(criteria);
        return alarmRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Alarm} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Alarm> findByCriteria(AlarmCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Alarm> specification = createSpecification(criteria);
        return alarmRepository.findAll(specification, page);
    }

    /**
     * Function to convert AlarmCriteria to a {@link Specification}
     */
    private Specification<Alarm> createSpecification(AlarmCriteria criteria) {
        Specification<Alarm> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Alarm_.id));
            }
            if (criteria.getAppl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAppl(), Alarm_.appl));
            }
            if (criteria.getBfunc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBfunc(), Alarm_.bfunc));
            }
            if (criteria.getChgj() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChgj(), Alarm_.chgj));
            }
            if (criteria.getCi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCi(), Alarm_.ci));
            }
            if (criteria.getDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesc(), Alarm_.desc));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Alarm_.email));
            }
            if (criteria.getFmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFmail(), Alarm_.fmail));
            }
            if (criteria.getFsms() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFsms(), Alarm_.fsms));
            }
            if (criteria.getFtlg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFtlg(), Alarm_.ftlg));
            }
            if (criteria.getHpsm() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHpsm(), Alarm_.hpsm));
            }
            if (criteria.getInfrastructure() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInfrastructure(), Alarm_.infrastructure));
            }
            if (criteria.getMesstext() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMesstext(), Alarm_.messtext));
            }
            if (criteria.getSitname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSitname(), Alarm_.sitname));
            }
            if (criteria.getSittype() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSittype(), Alarm_.sittype));
            }
            if (criteria.getSms() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSms(), Alarm_.sms));
            }
            if (criteria.getTlg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTlg(), Alarm_.tlg));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Alarm_.url));
            }
            if (criteria.getHpsm_override() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHpsm_override(), Alarm_.hpsm_override));
            }
        }
        return specification;
    }

}
