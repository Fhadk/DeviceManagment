package com.devicemanagement.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.devicemanagement.model.Device;
import com.devicemanagement.service.DeviceService;

import java.util.List;

@WebMvcTest(RequestController.class)
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc for testing HTTP requests

    @MockBean
    private DeviceService deviceService; // Mock the service layer

    @Test
    void testGetDeviceByIdentifier() throws Exception {
        String identifier = "Apple";
        List<Object> mockDevices = List.of("Device1", "Device2");
        when(deviceService.getDeviceByIdentifier(identifier)).thenReturn(mockDevices);

        mockMvc.perform(get("/api/getdeviceby/{identifier}", identifier))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("Device1"))
                .andExpect(jsonPath("$[1]").value("Device2"));
    }

    @Test
    void testGetDeviceByBrand() throws Exception {
        String brand = "Samsung";
        List<Object> mockDevices = List.of("Device1", "Device2");
        when(deviceService.getDeviceByBrand(brand)).thenReturn(mockDevices);

        mockMvc.perform(get("/api/getdevicebybrand/{brand}", brand))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("Device1"))
                .andExpect(jsonPath("$[1]").value("Device2"));

    }

    @Test
    void testGetAllDevices() throws Exception {
        List<Device> mockDevices = List.of(new Device("Iphone 11","Apple"), new Device("Iphone 14","Apple"));
        when(deviceService.getAllDevices()).thenReturn(mockDevices);

        mockMvc.perform(get("/api/getalldevices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone 11"))
                .andExpect(jsonPath("$[1].name").value("Iphone 14"));

    }

    @Test
    void testDeleteDevice() throws Exception {
        String identifier = "Apple";
        when(deviceService.deleteDevice(identifier)).thenReturn(HttpStatus.NO_CONTENT);

        mockMvc.perform(delete("/api/deletedevice/{identifier}", identifier))
                .andExpect(status().isOk());
    }

    @Test
    void testPartialUpdateDevice() throws Exception {
        Device device  = new Device("Iphone 11", "Apple");
        List<Device> updatedDevices = List.of(device);
        when(deviceService.partialUpdateDevice(device)).thenReturn(updatedDevices);

        mockMvc.perform(patch("/api/partialupdatedevice")
                        .contentType("application/json")
                        .content("{\"name\": \"Iphone13\",\"brand\": \"Apple\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testFullUpdateDevice() throws Exception {
        Device device  = new Device("Iphone 13", "Apple");
        List<Device> updatedDevices = List.of(device);
        when(deviceService.fullUpdateDevice(device)).thenReturn(updatedDevices);

        mockMvc.perform(put("/api/fullupdatedevice")
                        .contentType("application/json")
                        .content("{\"name\": \"Iphone 14\",\"brand\": \"Apple\"}"))
                .andExpect(status().isOk());
    }

}