package com.gildedrose;

class GildedRose {
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.setQuality(item.getItemType().updatedQuality(item));

            if (!ItemType.SULFURAS.equals(item.getItemType())) {
                item.decrementSellIn();
            }

            if (item.getSellIn() < 0) {
                item.setQuality(item.getItemType().applyNegativeSellIn(item));
            }
        }
    }
}