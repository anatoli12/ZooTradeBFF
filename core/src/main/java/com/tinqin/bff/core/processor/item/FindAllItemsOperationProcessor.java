package com.tinqin.bff.core.processor.item;

import com.tinqin.api.operation.item.findall.FindAllItemsInput;
import com.tinqin.api.operation.item.findall.FindAllItemsOperation;
import com.tinqin.api.operation.item.findall.FindAllItemsOutput;
import com.tinqin.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllItemsOperationProcessor implements FindAllItemsOperation {

    private final ZooStoreRestExport zooStoreRestExport;
    @Override
    public FindAllItemsOutput process(FindAllItemsInput request) {
        return zooStoreRestExport.findAll(request.getShowDeleted(),
                request.getPageNumber(),
                request.getPageSize(),
                request.getTitleContains(),
                request.getDescriptionContains());
    }
}
