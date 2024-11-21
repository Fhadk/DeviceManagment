package com.devicemanagement.service;

import com.devicemanagement.model.Device;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class DeviceServiceTest {

    @InjectMocks
    DeviceService deviceService;

    Device device1;
    Device device2;

    @Before
    public void setUp() throws Exception {
        device1 = new Device("123-2323-21323","Z Flip","Samsung");
        device2 = new Device("123-2323-213232323a","Iphone 14","Apple");
    }

    @Test
    public void addDevice() {
        assertEquals(HttpStatus.CREATED, deviceService.addDevice(device1));
    }


}