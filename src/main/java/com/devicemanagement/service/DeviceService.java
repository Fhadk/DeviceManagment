package com.devicemanagement.service;

import com.devicemanagement.model.Device;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
	public List<Object> getDeviceByIdentifier(String identifier){
		List<Device> result =  devices.stream()
				.filter(obj -> (obj.getName() != null && obj.getName().contains(identifier)) ||
						(obj.getBrand() != null && obj.getBrand().contains(identifier)) ||
						(obj.getId() != null && obj.getId().contains(identifier)))
				.collect(Collectors.toList());

		return result.isEmpty() ? Collections.singletonList(HttpStatus.NOT_FOUND) :
				Collections.singletonList(result);
	}


	/**
	 *
	 * @param brand
	 * @return List<Device> Brand
	 */
	public List<Object> getDeviceByBrand(String brand){
		List<Device> result = devices.stream()
				.filter(obj -> obj.getBrand() != null && obj.getBrand().contains(brand))
				.collect(Collectors.toList());

		return result.isEmpty() ? Collections.singletonList(HttpStatus.NOT_FOUND) :
				Collections.singletonList(result);
	}


	/**
	 *
	 * @param identifier
	 * @return HttpStatus
	 */
	public HttpStatus deleteDevice(String identifier) {
		List<Device> updatedList = devices.stream()
				.filter(obj -> (!obj.getId().equals(identifier)))
				.collect(Collectors.toList());

		return updatedList.size() < devices.size() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
	}


	/**
	 *
	 * @param device
	 * @return List<Device>
	 */
	public List<Device> partialUpdateDevice(Device device) {
		return devices.stream()
				.map(obj -> {
					if (obj.getId().equals(device.getId())) {
						if (device.getName() != null) obj.setName(device.getName());
						if (device.getBrand() != null) obj.setBrand(device.getBrand());
					}
					return obj;
				})
				.collect(Collectors.toList());
	}

	/**
	 *
	 * @param device
	 * @return List<Device>
	 */
	public List<Device> fullUpdateDevice(Device device) {
		return devices.stream()
				.map(obj -> obj.getId().equals(device.getId()) ? device : obj)
				.collect(Collectors.toList());
	}
}
