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
        device1 = new Device("Z Flip","Samsung", LocalDateTime.now());
        device2 = new Device("Iphone 14","Apple", LocalDateTime.now());
    }

    @Test
    public void addDevice() {
        assertEquals(HttpStatus.CREATED, deviceService.addDevice(device1));
    }


}