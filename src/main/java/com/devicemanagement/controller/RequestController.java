package com.devicemanagement.controller;

import com.devicemanagement.model.Device;
import com.devicemanagement.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    DeviceService deviceService;


    /**
     *
     * @return HTTP Status Code
     */
    @PostMapping("/addDevice")
    public ResponseEntity<Object> addDevice(@RequestBody @NotNull @NotEmpty Device device) {
        return new ResponseEntity<Object>(deviceService.addDevice(device));
    }

    @GetMapping(value = "/getdeviceby/{identifier}")
    public List<Object> getDeviceByIdentifier(@PathVariable(value = "identifier", required = true)
                                                @NotNull @NotEmpty String identifier) {
        return deviceService.getDeviceByIdentifier(identifier);
    }

    @GetMapping(value = "/getalldevices")
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping(value = "/getdevicebybrand/{brand}")
    public List<Object> getDeviceByBrand(@PathVariable(value = "brand", required = true)
                                              @NotNull @NotEmpty String brand) {
        return deviceService.getDeviceByBrand(brand);
    }

    @DeleteMapping(value = "/deledevice/{identifier}")
    public HttpStatus deleteDevice(@PathVariable(value = "identifier", required = true)
                                         @NotNull @NotEmpty String identifier) {
        return deviceService.deleteDevice(identifier);
    }

    @PatchMapping("/partialupdatedevice")
    public List<Device> partialUpdateDevice(@RequestBody @NotNull @NotEmpty Device device) {
        return deviceService.partialUpdateDevice(device);
    }

    @PutMapping("/fullupdatedevice")
    public List<Device> fullUpdateDevice(@RequestBody @NotNull @NotEmpty Device device) {
        return deviceService.fullUpdateDevice(device);
    }

}
