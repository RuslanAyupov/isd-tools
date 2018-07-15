package com.unicredit.isd.repository;

import com.unicredit.isd.domain.Isdcalc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Isdcalc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IsdcalcRepository extends JpaRepository<Isdcalc, Long> {

}
