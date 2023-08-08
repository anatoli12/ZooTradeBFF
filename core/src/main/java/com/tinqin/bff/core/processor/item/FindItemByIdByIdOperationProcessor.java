package com.tinqin.bff.core.processor.item;

import com.tinqin.api.operation.item.findbyid.FindItemByIdInput;
import com.tinqin.api.operation.item.findbyid.FindItemByIdOperation;
import com.tinqin.api.operation.item.findbyid.FindItemByIdOutput;
import com.tinqin.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindItemByIdByIdOperationProcessor implements FindItemByIdOperation {
    private final ZooStoreRestExport zooStoreRestExport;
    @Override
    public FindItemByIdOutput process(FindItemByIdInput request) {
        return zooStoreRestExport.findItemById(request.getId());
    }
}
