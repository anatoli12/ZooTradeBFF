package com.tinqin.bff.core.processor.item;

import com.tinqin.api.operation.item.BaseItemDTO;
import com.tinqin.bff.api.operation.item.ItemDTO;
import com.tinqin.bff.api.operation.item.findall.FindAllItemsInput;
import com.tinqin.bff.api.operation.item.findall.FindAllItemsOperation;
import com.tinqin.bff.api.operation.item.findall.FindAllItemsOutput;
import com.tinqin.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllItemsProcessor implements FindAllItemsOperation {
    private final ZooStoreRestExport zooStoreRestExport;

    private static class ItemMapper {

        public static ItemDTO mapToItemDTO(BaseItemDTO baseItem) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(baseItem.getId());
            itemDTO.setTitle(baseItem.getTitle());
            itemDTO.setDescription(baseItem.getDescription());
            itemDTO.setVendorIds(new HashSet<>(baseItem.getVendorIds()));
            itemDTO.setMultimediaIds(new HashSet<>(baseItem.getMultimediaIds()));
            itemDTO.setTagIds(new HashSet<>(baseItem.getTagIds()));
            itemDTO.setDeleted(baseItem.getDeleted());
            return itemDTO;
        }

        public static List<ItemDTO> mapToItemDTOList(List<BaseItemDTO> baseItemList) {
            List<ItemDTO> itemDTOList = new ArrayList<>();
            for (BaseItemDTO baseItem : baseItemList) {
                itemDTOList.add(mapToItemDTO(baseItem));
            }
            return itemDTOList;
        }
    }


    @Override
    public FindAllItemsOutput process(FindAllItemsInput input) {
        com.tinqin.api.operation.item.findall.FindAllItemsOutput items = zooStoreRestExport.findAll(
                input.getShowDeleted(),
                input.getPageNumber(),
                input.getPageSize()
        );
        return FindAllItemsOutput.builder()
                .result(ItemMapper.mapToItemDTOList(items.getItems()))
                .build();
    }
}
