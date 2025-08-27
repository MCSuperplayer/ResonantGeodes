package com.mcsuperplayer.resonantgeodes.registry.command;

import com.mcsuperplayer.resonantgeodes.world.CrystalResonanceStorage;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

public class ResonantGeodesCommand {

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher
				.register(Commands
						.literal("resonantgeodes")
						.then(Commands
								.literal("resonance")
								.then(Commands
										.literal("get")
										.requires(cs -> true)
										.executes(context -> getResonance(context)))
								.then(Commands
										.literal("add")
										.requires(cs -> cs.hasPermission(2))
										.then(Commands
												.argument("amount", IntegerArgumentType.integer())
												.executes(context -> addResonance(context))))));
	}

	private static int getResonance(CommandContext<CommandSourceStack> context) {
		CommandSourceStack source = context.getSource();
		ServerLevel overworld = source.getServer().overworld();
		CrystalResonanceStorage resonance = CrystalResonanceStorage.get(overworld);
		source.sendSuccess(() -> Component.literal(String.valueOf(resonance.get())), false);
		return 1;
	}

	private static int addResonance(CommandContext<CommandSourceStack> context) {
		int amount = IntegerArgumentType.getInteger(context, "amount");
		CommandSourceStack source = context.getSource();
		ServerLevel overworld = source.getServer().overworld();

		CrystalResonanceStorage resonance = CrystalResonanceStorage.get(overworld);

		resonance.add(amount);

		source.sendSuccess(() -> Component.literal("Added " + String.valueOf(amount) + " to resonance."), false);
		return 1;
	}
}
