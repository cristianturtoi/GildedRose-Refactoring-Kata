package com.gildedrose;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

enum ItemType {
    AGED_BRIE("Aged Brie",
            item -> incrementQualityConditioned().apply(item).getQuality(),
            item -> incrementQualityConditioned().apply(item).getQuality()),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert",
            item -> 0,
            getQualityStrategy()),
    SULFURAS("Sulfuras, Hand of Ragnaros",
            Item::getQuality,
            Item::getQuality),
    UNSPECIAL("Unspecial",
            ItemType::normalDecrease,
            ItemType::normalDecrease),
    CONJURED("Conjured Mana Cake",
            Item::getQuality,
            item -> normalDecreaseFunction().andThen(normalDecreaseFunction()).apply(item).getQuality())
    ;

    public static Function<Item, Item> incrementQualityConditioned() {
        return item -> {
            if (item.getQuality() < 50) {
                return new Item(item.getName(), item.getSellIn(), item.getQuality() + 1);
            }
            return item;
        };
    }

    private static Function<Item, Integer> getQualityStrategy() {
        return item -> {
            if (item.getSellIn() < 11) {
                return incrementQualityConditioned().andThen(incrementQualityConditioned()).apply(item).getQuality();
            }

            if (item.getSellIn() < 6) {
                return incrementQualityConditioned().apply(item).getQuality();
            }
            return item.getQuality();
        };
    }

    private static int normalDecrease(Item item) {
        if (item.getQuality() > 0) {
            return item.getQuality() - 1;
        }
        return item.getQuality();
    }

    public static Function<Item, Item> normalDecreaseFunction() {
        return item -> {
            if (item.getQuality() > 0) {
                return new Item(item.getName(), item.getSellIn(), item.getQuality() - 1);
            }
            return item;
        };
    }

    private static final Map<String, ItemType> NAME_TO_TYPE = Arrays.stream(values()).collect(toMap(ItemType::getName, x -> x));

    private final String name;
    private final Function<Item, Integer> negativeSellInStrategy;
    private final Function<Item, Integer> qualityStrategy;

    ItemType(String name, Function<Item, Integer> negativeSellInStrategy, Function<Item, Integer> qualityStrategy) {
        this.name = name;
        this.negativeSellInStrategy = negativeSellInStrategy;
        this.qualityStrategy = qualityStrategy;
    }

    public static ItemType of(String name) {
        return NAME_TO_TYPE.getOrDefault(name, UNSPECIAL);
    }

    public String getName() {
        return name;
    }

    int applyNegativeSellIn(Item item) {
        return negativeSellInStrategy.apply(item);
    }

    int updatedQuality(Item item) {
        return qualityStrategy.apply(item);
    }
}
