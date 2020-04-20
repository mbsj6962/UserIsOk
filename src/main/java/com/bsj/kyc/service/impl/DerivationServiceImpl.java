package com.bsj.kyc.service.impl;

import com.bsj.kyc.model.db.SabtFetchedInfo;
import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.type.Status;
import com.bsj.kyc.service.DerivationService;
import com.bsj.kyc.service.SabtInfoService;
import com.bsj.kyc.utills.Utility;
import com.bsj.kyc.repository.SelfDeclaredRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
public class DerivationServiceImpl implements DerivationService {

    private final static Logger logger = LoggerFactory.getLogger(DerivationServiceImpl.class);

    private final SelfDeclaredRepository selfDeclaredRepository;

    private final SabtInfoService sabtInfoService;

    public DerivationServiceImpl(SelfDeclaredRepository selfDeclaredRepository, SabtInfoService sabtInfoService) {
        this.selfDeclaredRepository = selfDeclaredRepository;
        this.sabtInfoService = sabtInfoService;
    }

    @Override
    @Scheduled(fixedRate = 10000)  /*10 second scheduling*/
    public SelfDeclaredInfo isValid() throws ParseException {
    //        codeList.add(callSabtDto.getNationalCode());
//
//        ObjectNode uri = objectMapper.createObjectNode();
//        uri.put("timer","{time1, time2, ...}");
//        uri.put("method", HttpMethod.POST.toString());
//        uri.put("user", "One Time generated user with Ansible");
//        uri.put("password","password");
//        uri.put("url","/sabt/realPersonality");
//        uri.put("body",callSabtDto.getNationalCode());
//        uri.put("body_format","json");
//        uri.put("return_content","yes");
//        uri.put("status_code","201");
//        utilityMethod.call(ASIBLE_URI,
//                HttpMethod.POST,
//                uri,
//                Object.class);
//
//        ResponseEntity<InfoDto> result = utilityMethod.call(SABT_URI,
//                HttpMethod.POST,
//                objectMapper.readTree(callSabtDto.getNationalCode()),
//                Object.class);

//        String useless = "";
//        SabtResultDto sabtResultDto = sabtClientService.getInfo();
//        logger.info("person information fetched from SABT organization {} :" + sabtResultDto);
        SelfDeclaredInfo selfDeclaredInfo = new SelfDeclaredInfo();
        SabtFetchedInfo sabtInfo;
        List<SelfDeclaredInfo> list = selfDeclaredRepository.findByStatus(Status.PENDING.getStatus());
        for(int i = 0; i< list.size(); i++){
            sabtInfo = sabtInfoService.fetchInfo(list.get(i).getNationalCode());
            if(sabtInfo.getName().equals(list.get(i).getName()) &&
               sabtInfo.getFamilyName().equals(list.get(i).getFamilyName()) &&
                sabtInfo.getLivingStatus().equals(Status.ALIVE.getStatus())){
                selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(list.get(i).getNationalCode()).orElseThrow(NoSuchElementException::new);
                selfDeclaredInfo.setStatus(Status.VERIFIED.getStatus());
            }
            else {
                selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(list.get(i).getNationalCode()).orElseThrow(NoSuchElementException::new);
                selfDeclaredInfo.setStatus(Status.FAILED.getStatus());
            }
            selfDeclaredRepository.save(selfDeclaredInfo);
            logger.info("Time is {} :" + Utility.getCurrentTime());
        }
        return selfDeclaredInfo;
    }

     @Override
     public SelfDeclaredInfo isConfirmed(String nationalCode) {
         Optional<SelfDeclaredInfo> optional = selfDeclaredRepository.findByStatusAndNationalCode(nationalCode);
        if (optional.get().equals(null)){
            return optional.orElseThrow(NoSuchElementException::new);
        }
         return selfDeclaredRepository.save(optional.get());
     }

 }
