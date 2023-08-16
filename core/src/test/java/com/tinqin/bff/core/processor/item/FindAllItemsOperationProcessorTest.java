//package com.tinqin.bff.core.processor.item;
//
//import com.tinqin.api.operation.item.create.CreateItemInput;
//import com.tinqin.api.operation.item.create.CreateItemOutput;
//import com.tinqin.api.operation.item.findall.FindAllItemsOperation;
//import com.tinqin.persistence.model.Item;
//import com.tinqin.persistence.repository.ItemRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//class FindAllItemsOperationProcessorTest {
//
//    @InjectMocks
//    private FindAllItemsOperation findAllItemsOperation;
//
//    @Mock
//    private ItemRepository itemRepository;
//
//    private static PodamFactory podamFactory;
//    @BeforeAll
//    public static void setUp() {
//        podamFactory = new PodamFactoryImpl();
//    }
//    @Test
//    void processTest() {
//        //given
//        CreateItemOutput result = podamFactory.manufacturePojo(CreateItemOutput.class);
//        Mockito.when(itemRepository.findAll())
//
//    }
//}