package com.bsj.kyc.utills;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class Utillity {

    public static ResponseEntity call(String uri, HttpMethod method, Object object, Class clazz){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(object, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(uri, method, entity, clazz);
    }

    public static Date getCurrentTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
       return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void uploadFile(MultipartFile file, String address) throws IOException {
        File convertFile = new File(address);
        convertFile.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(convertFile)){
            fout.write(file.getBytes());
        }
        catch (UnsupportedOperationException exe){
            exe.getCause();
        }
    }
    public static String generateAddress(MultipartFile file, String nationalCode, String storageAddress){
        return storageAddress +
                file.getOriginalFilename() +
                nationalCode;
    }
}
