package com.joel.krugerutil.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/security-info")
public class SecurityInfoController {

    @RequestMapping("/get-ip")
    public String getIp() {
        String ipAddress;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            ipAddress = request.getHeader("x-original-forwarded-for");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }else{
                ipAddress=ipAddress.split(",")[0];
            }
        }catch (Exception e){
            ipAddress = "ERROR";
        }

        return ipAddress != null ? ipAddress : "NONE";
    }
}
