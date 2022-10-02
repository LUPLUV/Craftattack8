/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.v1_19_R1.block.impl;

public final class CraftSeaPickle extends org.bukkit.craftbukkit.v1_19_R1.block.data.CraftBlockData implements org.bukkit.block.data.type.SeaPickle, org.bukkit.block.data.Waterlogged {

    public CraftSeaPickle() {
        super();
    }

    public CraftSeaPickle(net.minecraft.world.level.block.state.BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.v1_19_R1.block.data.type.CraftSeaPickle

    private static final net.minecraft.world.level.block.state.properties.IntegerProperty PICKLES = getInteger(net.minecraft.world.level.block.SeaPickleBlock.class, "pickles");

    @Override
    public int getPickles() {
        return get(CraftSeaPickle.PICKLES);
    }

    @Override
    public void setPickles(int pickles) {
        set(CraftSeaPickle.PICKLES, pickles);
    }

    @Override
    public int getMinimumPickles() {
        return getMin(CraftSeaPickle.PICKLES);
    }

    @Override
    public int getMaximumPickles() {
        return getMax(CraftSeaPickle.PICKLES);
    }

    // org.bukkit.craftbukkit.v1_19_R1.block.data.CraftWaterlogged

    private static final net.minecraft.world.level.block.state.properties.BooleanProperty WATERLOGGED = getBoolean(net.minecraft.world.level.block.SeaPickleBlock.class, "waterlogged");

    @Override
    public boolean isWaterlogged() {
        return get(CraftSeaPickle.WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        set(CraftSeaPickle.WATERLOGGED, waterlogged);
    }
}
