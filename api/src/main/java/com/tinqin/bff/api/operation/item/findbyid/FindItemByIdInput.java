package com.tinqin.bff.api.operation.item.findbyid;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class FindItemByIdInput implements ProcessorInput {
    private UUID id;
}
