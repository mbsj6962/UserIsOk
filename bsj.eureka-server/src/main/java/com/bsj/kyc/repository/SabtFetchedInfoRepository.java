package com.bsj.kyc.repository;

import com.bsj.kyc.model.db.SabtFetchedInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SabtFetchedInfoRepository extends CrudRepository<SabtFetchedInfo, Long> {
}
