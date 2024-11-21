package com.devicemanagement.service;

import com.devicemanagement.model.Device;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    public List<Device> devices =  new ArrayList<>();

	/**
	 *
	 * @param device
	 * @return HttPResponseCode
	 */
	public HttpStatus addDevice(Device device){
		if(!devices.contains(device)){
			devices.add(device);
		}else{
			return HttpStatus.CONFLICT;
		}
		return HttpStatus.CREATED;
	}

	/**
	 *
	 * @return List<Device>
	 */
	public List<Device> getAllDevices(){
		if(devices.isEmpty()){
			Optional.empty();
		}
		return devices;
	}


	/**
	 *
	 * @param identifier
	 * @return List<Device>
	 */
	public List<Device> getDeviceByIdentifier(String identifier){
		return devices.stream()
				.filter(obj -> (obj.getName() != null && obj.getName().contains(identifier)) ||
						(obj.getBrand() != null && obj.getBrand().contains(identifier)))
				.collect(Collectors.toList());
	}


	/**
	 *
	 * @param brand
	 * @return List<Device> Branc
	 */
	public List<Device> getDeviceByBrand(String brand){
		return devices.stream()
				.filter(obj -> obj.getBrand() != null && obj.getBrand().contains(brand))
				.collect(Collectors.toList());
	}


	/**
	 *
	 * @param identifier
	 * @return HttpStatus
	 */
	public HttpStatus deleteDevice(String identifier) {
		List<Device> updatedList = devices.stream()
				.filter(obj -> (!obj.getBrand().equals(identifier)) ||
						(!obj.getName().equals(identifier)))
				.collect(Collectors.toList());

		return updatedList.size() < devices.size() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
	}

}
