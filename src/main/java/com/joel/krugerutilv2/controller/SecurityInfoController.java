package com.joel.krugerutilv2.controller;

import com.joel.krugerutilv2.dto.IpAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/security-info")
public class SecurityInfoController {

    @RequestMapping("/get-ip")
    public IpAddress getIp() {

        IpAddress ipAddress = new IpAddress();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            ipAddress.setIp(request.getHeader("x-original-forwarded-for"));
            System.out.println("OP1 IP: " + (ipAddress.getIp() == null ? "NULL" : ipAddress));
            if (ipAddress.getIp() == null) {
                ipAddress.setIp(request.getRemoteAddr());
                System.out.println("OP2 IP: " + (ipAddress.getIp() == null ? "NULL" : ipAddress));
            }else{
                ipAddress.setIp(ipAddress.getIp().split(",")[0]);
            }
        }catch (Exception e){
            ipAddress.setIp("ERROR");
        }

        return ipAddress;
    }
}
