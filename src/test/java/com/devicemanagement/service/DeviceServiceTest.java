package com.devicemanagement.service;

import com.devicemanagement.model.Device;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class DeviceServiceTest {

    @InjectMocks
    DeviceService deviceService;

//    Device device1;
//    Device device2;

//    @Before
//    public void setUp() throws Exception {
//        device1 = new Device("123-2323-21323","Z Flip","Samsung");
//        device2 = new Device("123-2323-213232323a","Iphone 14","Apple");
//    }
//
//    @Test
//    public void addDevice() {
//        assertEquals(HttpStatus.CREATED, deviceService.addDevice(device1));
//    }




//    private DeviceService deviceService;

    @Mock
    private List<Device> devices; // Mock the device list

    Device device1;
    Device device2;


    @BeforeAll
    public void setUp() {
//        MockitoAnnotations.openMocks(this); // Initialize mocks
//        deviceService = new DeviceService(devices); // Initialize service with mock device set
        device1 = new Device("91ba1708-acfd-4c0e-aa17-1c5945ac8bfd",
                "Iphone 14", "Apple");

        device2 = new Device("91ba1708-acfd-4c0e-aa17-1c5945ac8bfd",
                "Iphone 14", "Apple");

        devices.add(device1);
        devices.add(device2);

    }

    @Test
    public void testAddDevice_whenDeviceIsNotPresent_shouldReturnCreated() {
        when(devices.contains(device2)).thenReturn(false);

        // Act: Call the addDevice method
        HttpStatus result = deviceService.addDevice(device2);

        // Assert: Verify that the status returned is CREATED (201)
        assertEquals(HttpStatus.CREATED, result);

        // Verify that the device was added to the set
        verify(devices, times(1)).add(device2);
    }

    @Test
    public void testAddDevice_whenDeviceIsAlreadyPresent_shouldReturnConflict() {
        // Arrange: Mock the behavior when the device is already present in the set
        Device existingDevice = new Device("Device B", "Type B", "Inactive");
        when(devices.contains(existingDevice)).thenReturn(true); // Simulate device being in the set

        // Act: Call the addDevice method
        HttpStatus result = deviceService.addDevice(existingDevice);

        // Assert: Verify that the status returned is CONFLICT (409)
        assertEquals(HttpStatus.CONFLICT, result);

        // Verify that the device was not added again
        verify(devices, never()).add(existingDevice);
    }



}