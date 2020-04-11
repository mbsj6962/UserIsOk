package com.bsj.kyc.repository;

import com.bsj.kyc.model.db.SabtInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SabtInfoRepository extends CrudRepository<SabtInfo, Long> {
}
