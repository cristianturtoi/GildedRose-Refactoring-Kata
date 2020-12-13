package com.gildedrose;

public class Item {

    private final String name;
    private int sellIn;
    private int quality;
    private ItemType itemType;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.itemType = ItemType.of(name);
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getSellIn() + ", " + this.getQuality();
    }

    void decrementSellIn() {
        sellIn -= 1;
    }

    void incrementQuality() {
        quality += 1;
    }

    void decrementQuality() {
        quality -= 1;
    }
}
