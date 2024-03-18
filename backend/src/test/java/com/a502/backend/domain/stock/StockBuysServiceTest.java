//package com.a502.backend.domain.stock;
//
//import com.a502.backend.application.entity.User;
//import com.a502.backend.fixture.UserFixture;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//@Transactional
//@ExtendWith(MockitoExtension.class)
//class StockBuysServiceTest {
//
//    @InjectMocks
//    private StockBuysService stockBuysService;
//
//    @Mock
//    private StockBuysRepository stockBuysRepository;
//
//    @DisplayName("Save Test")
//    @Nested
//    class save{
//
//        @Test
//        @DisplayName("success")
//        void whenSuccess(){
//            //given
//            User parent = UserFixture.USER_PARENT.create();
//            User child1 = UserFixture.USER_CHILD1.create();
//            User child2 = UserFixture.USER_CHILD2.create();
//
//
//            //when then
////            assertDoesNotThrow(()->{
////                stockBuysService.save()
////            });
//        }
//
//        @Test
//        @DisplayName("fail : 돈이 파킹통장에 없을 때")
//        void whenFailByNoMoney(){
//
//        }
//
//
//    }
//
//}
