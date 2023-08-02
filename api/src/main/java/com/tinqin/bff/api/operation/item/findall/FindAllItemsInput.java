package com.tinqin.bff.api.operation.item.findall;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllItemsInput implements ProcessorInput {
    private Boolean showDeleted;
    private Integer pageNumber;
    private Integer pageSize;
//    private Optional<Boolean> orderDescending;
//    private Optional<String> searchOptional;
}
