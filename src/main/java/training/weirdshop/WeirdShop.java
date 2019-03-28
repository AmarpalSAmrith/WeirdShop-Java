package training.weirdshop;

class WeirdShop {
    private Item[] items;

    public WeirdShop(Item[] items) {
        this.items = items;
    }

    void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            sellInIncrement(items[i]);
            updateQuality(items[i]);
            checkQualityIsInBounds(items[i]);
            
        }
    }

    public static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    public static boolean isBackStagePass(Item item) {
        return item.name.equals("Backstage Pass");
    }


    public static boolean isGoldCoin(Item item) {
        return item.name.equals("Gold Coin");
    }

    private void agedBrieQuality(Item item) {
        if (isAgedBrie(item) && item.quality < 50) {
            item.quality++;
        }
    }

    private void backStagePassQuality(Item item) {
        if (isBackStagePass(item)) {
            if (item.sellIn >= 0) {
                if (item.sellIn > 10) {
                    item.quality++;
                } else if (item.sellIn > 5) {
                    item.quality = item.quality + 2;
                } else {
                    item.quality = item.quality + 3;
                }
            } else {
                item.quality = 0;
            }
        }

    }

    private void goldCoinQuality(Item item) {
        if (isGoldCoin(item)) {
            item.quality = 80;
        }
    }

    private void otherItemsQuality(Item item) {
        if (!isAgedBrie(item) && !isBackStagePass(item) && !isGoldCoin(item) && item.quality > 0) {
            item.quality--;
            if (item.sellIn < 0) {
                item.quality--;
            }
        }
    }
    private void sellInIncrement(Item item) {
        if (!isGoldCoin(item)) {
            item.sellIn--;
        }
    }
    private void updateQuality(Item item) {
        agedBrieQuality(item);
        backStagePassQuality(item);
        goldCoinQuality(item);
        otherItemsQuality(item);
    }
    private void checkQualityIsInBounds(Item item) {
        if (item.quality > 50 && !isGoldCoin(item)) {
            item.quality = 50;
        } else if (item.quality < 0) {
            item.quality = 0;
        }
    }
}