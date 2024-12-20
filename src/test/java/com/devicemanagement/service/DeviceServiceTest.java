package com.devicemanagement.service;

import com.devicemanagement.model.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {


    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private List<Device> devices; // Mocked list

    @Mock
    Device newDevice;

    @BeforeEach
    public void setUp() {
         newDevice = new Device("Iphone 14", "Apple");
         devices.add(newDevice);
         devices.add(new Device("Z Flip", "Samsung"));
    }

    @Test
    public void testAddDevice_WhenDeviceIsNotPresent_ShouldReturnCreated() {
        when(devices.contains(newDevice)).thenReturn(false);
        HttpStatus status = deviceService.addDevice(newDevice);

        assertEquals(HttpStatus.CREATED, status);
    }

    @Test
    public void testAddDevice_WhenDeviceIsPresent_ShouldReturnConflict() {
        when(devices.contains(newDevice)).thenReturn(true);
        HttpStatus status = deviceService.addDevice(newDevice);

        assertEquals(HttpStatus.CONFLICT, status);
    }

    @Test
    public void testGetAllDevices_WhenNoDevice_ShouldReturnEmptyList() {
        when(devices.isEmpty()).thenReturn(false);

        assertEquals(deviceService.getAllDevices(), devices);
    }


    @Test
    public void testGetAllDevices_WhenDevices_ShouldReturnListDevices() {
        List<Device> emptyDevices = new ArrayList<>();
        when(devices.isEmpty()).thenReturn(emptyDevices.isEmpty());
        List<Device> result = deviceService.getAllDevices();

        assertEquals(result, emptyDevices);
    }

    @Test
    void testFullUpdateDevice_UpdatesDevice() {
        Device updatedDevice = new Device("Iphone X", "Apple");
        List<Device> result = deviceService.fullUpdateDevice(updatedDevice);

        assertEquals(0, result.size(), "The size of the device list should remain unchanged.");
    }

    @Test
    void testPartialUpdateDevice_UpdatesDevice() {
        Device updatedDevice = new Device("Iphone 11", "Apple");
        List<Device> result = deviceService.partialUpdateDevice(updatedDevice);

        assertEquals(0, result.size(), "The size of the device list should remain unchanged.");
    }

}