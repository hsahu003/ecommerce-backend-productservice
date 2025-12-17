package com.hemendrasahu.productservice.mappers;

import com.hemendrasahu.productservice.dtos.GenericProductDto;
import com.hemendrasahu.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    // Explicit mappings are optional here because field names match,
    // but keeping one @Mapping line makes it clear and helps if you later change names.

    GenericProductDto toGeneric(FakeStoreProductDto source);

    @InheritInverseConfiguration(name = "toGeneric")
    FakeStoreProductDto toFakeStore(GenericProductDto source);
}