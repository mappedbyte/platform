package com.francis.platform.controller;

import com.francis.platform.entity.Device;
import com.francis.platform.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Francis
 * @Date 2022-09-15 21:14
 **/
@RequestMapping("/v1/device")
@RestController
@AllArgsConstructor
public class DeviceController {

    private DeviceService deviceService;


    @PostMapping("/add")
    public void add(@RequestBody Device device) {
        deviceService.save(device);
    }

}
