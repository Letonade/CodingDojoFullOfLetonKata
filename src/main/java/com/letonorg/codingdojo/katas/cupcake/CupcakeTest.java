package com.letonorg.codingdojo.katas.cupcake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CupcakeTest {

    @Test
    void cupcake_is_a_cake() {
        Cake cake = new Cupcake();
        assertNotNull(cake);
    }

    @Test
    void name_of_plain_cupcake() {
        Cake cake = new Cupcake();
        assertEquals("Cupcake", cake.name());
    }

    @Test
    void name_of_plain_cookie() {
        Cake cake = new Cookie();
        assertEquals("Cookie", cake.name());
    }

    @Test
    void name_of_cupcake_with_one_topping() {
        Cake cake = new Chocolate(new Cupcake());
        assertEquals("Cupcake with Chocolate", cake.name());
    }

    @Test
    void name_of_cookie_with_one_topping() {
        Cake cake = new Chocolate(new Cookie());
        assertEquals("Cookie with Chocolate", cake.name());
    }

    @Test
    void name_of_cupcake_with_two_toppings_keeps_order() {
        Cake cake = new Nuts(new Chocolate(new Cupcake()));
        assertEquals("Cupcake with Chocolate and Nuts", cake.name());
    }

    @Test
    void name_of_cookie_with_two_toppings_keeps_order() {
        Cake cake = new Nuts(new Chocolate(new Cookie()));
        assertEquals("Cookie with Chocolate and Nuts", cake.name());
    }

    @Test
    void order_of_toppings_changes_the_name() {
        Cake chocolateThenNuts = new Nuts(new Chocolate(new Cupcake()));
        Cake nutsThenChocolate = new Chocolate(new Nuts(new Cupcake()));

        assertEquals("Cupcake with Chocolate and Nuts", chocolateThenNuts.name());
        assertEquals("Cupcake with Nuts and Chocolate", nutsThenChocolate.name());
        assertNotEquals(chocolateThenNuts.name(), nutsThenChocolate.name());
    }

    // --- Price ---------------------------------------------------------------

    @Test
    void price_of_plain_cupcake_is_1_0() {
        Cake cake = new Cupcake();
        assertEquals(1.0, cake.price(), 1e-9);
    }

    @Test
    void price_of_plain_cookie_is_2_0() {
        Cake cake = new Cookie();
        assertEquals(2.0, cake.price(), 1e-9);
    }

    @Test
    void price_of_cupcake_with_one_topping_is_1_1() {
        Cake cake = new Chocolate(new Cupcake());
        assertEquals(1.1, cake.price(), 1e-9);
    }

    @Test
    void price_of_cookie_with_one_topping_is_2_1() {
        Cake cake = new Chocolate(new Cookie());
        assertEquals(2.1, cake.price(), 1e-9);
    }

    @Test
    void price_of_cookie_with_two_toppings_is_2_2() {
        Cake cake = new Nuts(new Chocolate(new Cookie()));
        assertEquals(2.2, cake.price(), 1e-9);
    }

    // --- Bundle (composite) --------------------------------------------------

    @Test
    void bundle_of_one_cupcake_is_10_percent_off() {
        Cake bundle = new Bundle(new Cupcake());
        assertEquals(0.9, bundle.price(), 1e-9);
    }

    @Test
    void bundle_of_one_cupcake_and_one_cookie_is_10_percent_off_sum() {
        Cake bundle = new Bundle(new Cupcake(), new Cookie());
        assertEquals((1.0 + 2.0) * 0.9, bundle.price(), 1e-9);
    }

    @Test
    void bundle_of_two_cupcakes_and_one_cookie_is_10_percent_off_sum() {
        Cake bundle = new Bundle(new Cupcake(), new Cupcake(), new Cookie());
        assertEquals((1.0 + 1.0 + 2.0) * 0.9, bundle.price(), 1e-9);
    }

    @Test
    void bundle_can_contain_a_bundle_and_single_cakes() {
        Cake inner = new Bundle(new Cupcake(), new Cookie()); // (1 + 2) * 0.9 = 2.7
        Cake outer = new Bundle(inner, new Cupcake());        // (2.7 + 1) * 0.9 = 3.33

        assertEquals(3.33, outer.price(), 1e-9);
    }

    @Test
    void bundle_can_be_nested_many_levels() {
        Cake b1 = new Bundle(new Cupcake(), new Cookie()); // 2.7
        Cake fancyCupcake = new Sugar(new Nuts(new Chocolate(new Cupcake()))); // 1.3
        Cake b2 = new Bundle(fancyCupcake); // 1.3 * 0.9 = 1.17
        Cake b3 = new Bundle(b1, b2, new Cookie()); // (2.7 + 1.17 + 2.0) * 0.9 = 5.283

        assertEquals(5.283, b3.price(), 1e-9);
    }

    // Optional: if you want to enforce that bundles also have a non-empty name/description
    @Test
    void bundle_has_a_non_blank_name() {
        Cake bundle = new Bundle(new Cupcake(), new Cookie());
        assertFalse(bundle.name().isBlank());
    }
}
