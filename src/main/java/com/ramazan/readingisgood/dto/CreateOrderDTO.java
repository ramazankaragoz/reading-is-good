package com.ramazan.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    @NotNull
    private UUID fkCustomerId;

    @NotNull
    @Valid
    private List<OrderDetailDTO> orderDetail=new ArrayList<>(0);

    @NotNull
    private Date startDate;

    private Date endDate;
}
