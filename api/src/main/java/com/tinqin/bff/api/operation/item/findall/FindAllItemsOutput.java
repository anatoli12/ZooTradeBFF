package com.tinqin.bff.api.operation.item.findall;

import com.tinqin.bff.api.core.ProcessorOutput;
import com.tinqin.bff.api.operation.item.ItemDTO;
import lombok.*;

import java.util.List;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindAllItemsOutput implements ProcessorOutput {
    private List<ItemDTO> result;
}
