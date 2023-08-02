package com.tinqin.bff.core.processor.item;

import com.tinqin.api.operation.item.findbyid.FindItemByIdOutput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdInput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOperation;
import com.tinqin.restexport.StorageRestExport;
import com.tinqin.restexport.ZooStoreRestExport;
import com.tinqin.storage.api.operation.storage.findstoragebyitemid.FindStorageByItemIdOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindItemByIdProcessor implements FindItemByIdOperation {
    private final ZooStoreRestExport zooStoreRestExport;
    private final StorageRestExport storageRestExport;
    @Override
    public com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOutput process(FindItemByIdInput input) {
        FindItemByIdOutput item = zooStoreRestExport.findItemById(String.valueOf(input.getId()));
        FindStorageByItemIdOutput itemStorage = storageRestExport.findByItemId(String.valueOf(input.getId()));

        return com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOutput.builder()
                .itemId(item.getBaseItemDTO().getId())
                .itemDescription(item.getBaseItemDTO().getDescription())
                .itemTitle(item.getBaseItemDTO().getTitle())
                .itemMultimedia(item.getBaseItemDTO().getMultimediaIds())
                .itemVendorIds(item.getBaseItemDTO().getVendorIds())
                .itemTags(item.getBaseItemDTO().getTagIds())
                .storageId(itemStorage.getStorageBaseDTO().getId())
                .storageItemPrice(itemStorage.getStorageBaseDTO().getPrice())
                .storageItemQuantity(itemStorage.getStorageBaseDTO().getQuantity())
                .build();

    }
}
