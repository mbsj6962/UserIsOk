package com.bsj.kyc.service.impl;

import com.bsj.kyc.model.db.SabtInfo;
import com.bsj.kyc.repository.SelfDeclaredRepository;
import com.bsj.kyc.service.DerivationService;
import com.bsj.kyc.service.SabtInfoService;
import com.bsj.kyc.utills.Utillity;
//import com.vsq.kyc.feignClients.SabtClientService;
import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.type.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

/**@Author By MohamadBsj6962
                if you go into any trouble
                         please contact me mohamadbiiij@gmail.com ***/


@Service
public class DerivationServiceImpl implements DerivationService {

    Logger logger = LoggerFactory.getLogger(DerivationServiceImpl.class);

    @Autowired
    private SelfDeclaredRepository selfDeclaredRepository;

    @Autowired
    private SabtInfoService sabtInfoService;

    @Override
    @Scheduled(fixedRate = 10000)  /*10 second scheduling*/
    public void isValid() throws ParseException {
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
        SelfDeclaredInfo selfDeclaredInfo;
        SabtInfo sabtInfo;
        List<SelfDeclaredInfo> list = selfDeclaredRepository.findByStatus(Status.PENDING.getStatus());
        for(int i = 0; i< list.size(); i++){
            sabtInfo = sabtInfoService.fetchInfo(list.get(i).getNationalCode());
            if(sabtInfo.getName().equals(list.get(i).getName()) &&
               sabtInfo.getFamilyName().equals(list.get(i).getFamilyName()) &&
                sabtInfo.getLivingStatus().equals("زنده")){
                selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(list.get(i).getNationalCode()).orElseThrow(NoSuchElementException::new);
                selfDeclaredInfo.setStatus(Status.VERIFIED.getStatus());
            }
            else {
                selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(list.get(i).getNationalCode()).orElseThrow(NoSuchElementException::new);
                selfDeclaredInfo.setStatus(Status.FAILED.getStatus());
            }
            selfDeclaredRepository.save(selfDeclaredInfo);
            logger.info("Time is {} :" + Utillity.getCurrentTime());
        }
    }

}
