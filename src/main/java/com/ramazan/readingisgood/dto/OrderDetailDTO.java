package com.ramazan.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    @NotNull
    private UUID fkBookId;

    @NotNull
    private Integer quantity;
}
