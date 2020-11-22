package com.gltknbtn.service;

import com.gltknbtn.request.SavePersonRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "netflix-zuul-api-gateway-server")
@FeignClient(name = "crm-worker-service")
//@FeignClient(name = "crm-worker-servic", url = "http://localhost:8081")
public interface CrmProxyService {

    //@PostMapping("/savePerson")
    @PostMapping(value = "/savePerson")
    void savePerson(SavePersonRequest savePersonRequest);
}
