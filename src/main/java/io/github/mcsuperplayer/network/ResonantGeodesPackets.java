package io.github.mcsuperplayer.network;

import io.github.mcsuperplayer.ResonantGeodes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ResonantGeodesPackets {
	private static final String PROTOCOL_VERSION = "1.0";
	public static final SimpleChannel CHANNEL = NetworkRegistry
			.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, "main"),
					() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

	private static int packetId = 0;

	public static void register() {
		CHANNEL.registerMessage(packetId++, DrillStatusPacket.class, DrillStatusPacket::toBytes,
				DrillStatusPacket::new, DrillStatusPacket::handle);
		CHANNEL.registerMessage(packetId++, BurnerStatusPacket.class, BurnerStatusPacket::toBytes, 
				BurnerStatusPacket::new, BurnerStatusPacket::handle);
	}

	public static void sendToClient(ServerPlayer player, Object packet) {
		CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
	}
}
