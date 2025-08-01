package com.mcsuperplayer.resonantgeodes.network;

import java.util.function.Supplier;

import com.mcsuperplayer.resonantgeodes.registry.entity.CrystalBurnerBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

public class BurnerStatusPacket {
	private final BlockPos pos;
	private final Component status;
	private final Component info;

	public BurnerStatusPacket(BlockPos pos, Component status, Component info) {
		this.pos = pos;
		this.status = status;
		this.info = info;
	}

	public BurnerStatusPacket(FriendlyByteBuf buf) {
		this.pos = buf.readBlockPos();
		this.status = buf.readComponent();
		this.info = buf.readComponent();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeBlockPos(pos);
		buf.writeComponent(status);
		buf.writeComponent(info);
	}

	@SuppressWarnings("resource")
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientLevel level = Minecraft.getInstance().level;
			if (level != null && level.getBlockEntity(pos) instanceof CrystalBurnerBlockEntity burner) {
				burner.setClientStatus(status, info);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
