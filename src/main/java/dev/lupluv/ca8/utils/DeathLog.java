package dev.lupluv.ca8.utils;

import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

public class DeathLog {

    Block chest;
    Inventory inv;

    public DeathLog(Block chest, Inventory inv) {
        this.chest = chest;
        this.inv = inv;
    }

    public Block getChest() {
        return chest;
    }

    public void setChest(Block chest) {
        this.chest = chest;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }
}
