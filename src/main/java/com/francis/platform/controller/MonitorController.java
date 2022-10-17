package com.francis.platform.controller;

import com.francis.platform.common.info.SystemHardwareInfo;
import com.francis.platform.log.OperationLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Francis
 * @Date 2022-09-15 11:53
 **/
@RestController
@RequestMapping("/v1/monitor")
public class MonitorController {



    @GetMapping
    public SystemHardwareInfo queryMonitor() throws Exception {
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.copyTo();
        return systemHardwareInfo;
    }


}
