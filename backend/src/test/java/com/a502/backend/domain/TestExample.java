package com.a502.backend.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestExample {
    @Test
    public void testAdd(){
        assertEquals(42, Integer.sum(19,23));
    }

    @Test
    public void testDivide(){
        assertThrows(ArithmeticException.class, () ->{
            Integer.divideUnsigned(42, 0);
        });
    }
}
