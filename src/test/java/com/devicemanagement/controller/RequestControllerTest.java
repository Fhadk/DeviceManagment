package com.devicemanagement.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.devicemanagement.model.Device;
import com.devicemanagement.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;

@WebMvcTest(RequestController.class)
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc for testing HTTP requests

    @MockBean
    private DeviceService deviceService; // Mock the service layer

    Device device;
    Device device2;


    @BeforeEach
    void setUp() {
        device = new Device("Type A", "Active");
//        device2 = new Device("Device b", "Type B", "Deleted");
    }

    @Test
    void addDevice() throws Exception {
        when(deviceService.addDevice(device)).thenReturn(HttpStatus.CREATED);

        // Act & Assert
        mockMvc.perform(post("/api/addDevice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Device A\", \"brand\":\"Type A\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getDeviceByIdentifier() {
    }

    @Test
    void getAllDevices() {
    }

    @Test
    void getDeviceByBrand() {
    }

    @Test
    void deleteDevice() {
    }

    @Test
    void partialUpdateDevice() {
    }

    @Test
    void fullUpdateDevice() {
    }
}