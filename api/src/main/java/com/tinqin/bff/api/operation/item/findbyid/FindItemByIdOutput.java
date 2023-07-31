package com.tinqin.bff.api.operation.item.findbyid;

import com.tinqin.bff.api.core.ProcessorOutput;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindItemByIdOutput implements ProcessorOutput {
    private String itemId;
    private String itemTitle;
    private String itemDescription;
    private Set<String> itemVendorIds;
    private Set<String> itemMultimedia;
    private Set<String> itemTags;

    private String storageId;
    private String storageItemPrice;
    private int storageItemQuantity;
}
