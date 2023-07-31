package com.tinqin.bff.api.operation.item.findall;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllItemsInput implements ProcessorInput {
    private Optional<Integer> page;
    private Optional<Integer> size;
//    private Optional<Boolean> orderDescending;
//    private Optional<String> searchOptional;
}
