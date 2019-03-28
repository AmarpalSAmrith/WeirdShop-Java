package training.weirdshop;

class WeirdShop {
    private Item[] items;

    public WeirdShop(Item[] items) {
        this.items = items;
    }

    void updateQuality() { //MAIN
        for (Item item : items) {

            item.sellIn += sellInIncrement(item);
            updateQuality(item);
            updatePremiumQuality(item);
            checkQualityIsInBounds(item);

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

    private int agedBrieQuality(Item item) {
        if (item.quality < 50) {
            return 1;
        }
        return 0;
    }

    private int backStagePassQuality(Item item) {
        if (item.sellIn >= 0) {
            if (item.sellIn > 10) {
                return 1;
            } else if (item.sellIn > 5) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return -item.quality;
        }
    }

    private int goldCoinQuality(Item item) {
        return 0;
    }

    private int otherItemsQuality(Item item) {
        int total = 0;
        if (item.quality > 0) {
            total--;
            if (item.sellIn < 0) {
                total--;
            }
        }
        return total;
    }

    private boolean isSomeOtherItem(Item item) {
        return item.name.equals("Some other item");
    }

    private int sellInIncrement(Item item) {
        if (!isGoldCoin(item) && !isPremiumGoldCoin(item)) {
            return -1;
        }
        return 0;
    }

    private void updateQuality(Item item) {
        if (isAgedBrie(item)) {
            item.quality = item.quality + agedBrieQuality(item);
        }
        if (isBackStagePass(item)) {
            item.quality = item.quality + backStagePassQuality(item);
        }
        if (isGoldCoin(item)) {
            item.quality = item.quality + goldCoinQuality(item);
        }
        if (isSomeOtherItem(item)) {
            item.quality = item.quality + otherItemsQuality(item);
        }
    }

    private void checkQualityIsInBounds(Item item) {
        if (item.quality > 50 && !isGoldCoin(item) && !isPremiumGoldCoin(item)) {
            item.quality = 50;
        } else if (item.quality < 0) {
            item.quality = 0;
        }
    }

    ////////////////////////////////PREMIUM STUFF////////////////////////////////////////////////
    private void updatePremiumQuality(Item item) {
        if (isPremiumAgedBrie(item)) {
            item.quality = item.quality + (agedBrieQuality(item) * 2);
        }
        if (isPremiumBackStagePass(item)) {
            item.quality = item.quality + (backStagePassQuality(item) * 2);
        }
        if (isPremiumGoldCoin(item)) {
            item.quality = item.quality + (goldCoinQuality(item) * 2);
        }
        if (isSomeOtherPremiumItem(item)) {
            item.quality = item.quality + (otherItemsQuality(item) * 2);
        }
    }

    public static boolean isPremiumAgedBrie(Item item) {
        return item.name.equals("Premium Aged Brie");
    }

    public static boolean isPremiumBackStagePass(Item item) {
        return item.name.equals("Premium Backstage Pass");
    }


    public static boolean isPremiumGoldCoin(Item item) {
        return item.name.equals("Premium Gold Coin");
    }

    public static boolean isSomeOtherPremiumItem(Item item) {
        return item.name.equals("Some other Premium item");
    }
}