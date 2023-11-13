package com.example.gps_g11.Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @Test
    void getValorBolsa() {
        Context context = Context.getInstance();
        assertEquals(context.getValorBolsa(),110);
    }
}