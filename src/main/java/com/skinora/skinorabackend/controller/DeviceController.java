package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Device;
import com.skinora.skinorabackend.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    // Отримати всі пристрої пацієнта
    @GetMapping("/patient/{patientId}")
    public List<Device> getDevicesByPatient(@PathVariable Integer patientId) {
        return deviceService.getDevicesByPatient(patientId);
    }

    // Отримати пристрій за серійним номером
    @GetMapping("/serial/{serialNumber}")
    public Optional<Device> getDeviceBySerialNumber(@PathVariable String serialNumber) {
        return deviceService.getDeviceBySerialNumber(serialNumber);
    }
}
