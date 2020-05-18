package com.bsj.kyc.repository;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SelfDeclaredRepository extends CrudRepository<SelfDeclaredInfo, Long> {
        List<SelfDeclaredInfo> findByStatus(String status);
        Optional<SelfDeclaredInfo> findByNationalCode(String nationalCode);
        List<SelfDeclaredInfo> findAll();
        @Query(value = "select s from SelfDeclaredInfo s where s.status = 'VERIFIED' and s.nationalCode = ?1")
        Optional<SelfDeclaredInfo> findByStatusAndNationalCode(String nationalCode);


}
