package com.mcsuperplayer.resonantgeodes.registry.entity;

import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MaterialBlockEntity extends BlockEntity {
	private String material;

	public MaterialBlockEntity(BlockPos pos, BlockState state) {
		super(Registry.MATERIAL_BLOCK_ENTITY.get(), pos, state);
		this.material = "";
	}

	public void setMaterial(String material) {
		this.material = material;
		setChanged();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.material = tag.getString("geode_material");
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putString("geode_material", material);

	}

	public String getMaterial() {
		return material;
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();
		tag.putString("geode_material", material);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);
		this.material = tag.getString("geode_material");
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		this.handleUpdateTag(pkt.getTag());
	}
}
