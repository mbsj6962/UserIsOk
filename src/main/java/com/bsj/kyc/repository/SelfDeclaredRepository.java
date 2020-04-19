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
//        TODO make it JPQL
        @Query(value = "select * from self_declared_info where status = 'VERIFIED' and national_code = :nationalCode",
                nativeQuery = true)
        Optional<SelfDeclaredInfo> findByStatusAndNationalCode(String nationalCode);


}
