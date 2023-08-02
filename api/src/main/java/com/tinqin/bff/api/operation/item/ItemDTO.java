package com.tinqin.bff.api.operation.item;

import lombok.*;

import java.util.Set;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    protected String id;
    protected String title;
    protected String description;
    protected Set<String> vendorIds;
    protected Set<String> multimediaIds;
    protected Set<String> tagIds;
    protected Boolean deleted;
}
