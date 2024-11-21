package com.devicemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {
//    @NotNull
//    private String id = UUID.randomUUID().toString();;
    private String name;
    private String brand;
    private LocalDateTime creationTime;
}
