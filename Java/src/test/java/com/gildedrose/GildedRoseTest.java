package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GildedRoseTest {

    @Test
    public void updateQuality_fooItem_decrementSellIn() {
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(0, equalTo(app.items[0].sellIn));
    }

    @Test
    public void updateQuality_fooItem_decrementQuality() {
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(0, equalTo(app.items[0].quality));
    }

    @Test
    public void updateQuality_fooItem_decrementQualityTwice() {
        Item[] items = new Item[] { new Item("foo", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertThat(0, equalTo(app.items[0].sellIn));
        assertThat(0, equalTo(app.items[0].quality));
    }

    @Test
    public void updateQuality_fooItem_negativeQuality() {
        Item[] items = new Item[] { new Item("foo", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(1, equalTo(app.items[0].sellIn));
        assertThat(0, equalTo(app.items[0].quality));
    }

    @Test
    public void updateQuality_fooItem_negativeSellIn_checkSellIn() {
        Item[] items = new Item[] { new Item("foo", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(-1, equalTo(app.items[0].sellIn));
    }

    @Test
    public void updateQuality_fooItem_negativeSellIn_qualityDecradationTwice() {
        Item[] items = new Item[] { new Item("foo", 0, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(1, equalTo(app.items[0].quality));
    }

    @Test
    public void updateQuality_AgedBrie_decrementSellIn() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(0, equalTo(app.items[0].sellIn));
    }

    @Test
    public void updateQuality_AgedBrie_decrementQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(2, equalTo(app.items[0].quality));
    }

    @Test
    public void updateQuality_AgedBrie_negativeSellIn_qualityIncreasedTwice() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(-1, equalTo(app.items[0].sellIn));
        assertThat(3, equalTo(app.items[0].quality));
    }
}
