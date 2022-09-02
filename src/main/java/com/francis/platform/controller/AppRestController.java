package com.francis.platform.controller;

import com.francis.platform.common.info.SystemHardwareInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Francis
 */
@RestController
@RequestMapping("/app")
public class AppRestController {




    @GetMapping("/test")
    public SystemHardwareInfo test() throws Exception {
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.copyTo();
        return systemHardwareInfo  ;
    }

}
