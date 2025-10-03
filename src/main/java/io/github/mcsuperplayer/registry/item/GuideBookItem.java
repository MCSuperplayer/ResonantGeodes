package io.github.mcsuperplayer.registry.item;

import java.util.List;

import com.klikli_dev.modonomicon.api.ModonomiconConstants;
import com.klikli_dev.modonomicon.book.Book;
import com.klikli_dev.modonomicon.client.gui.BookGuiManager;
import com.klikli_dev.modonomicon.data.BookDataManager;
import com.klikli_dev.modonomicon.item.ModonomiconItem;

import io.github.mcsuperplayer.ResonantGeodes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class GuideBookItem extends ModonomiconItem {
	public static final ResourceLocation BOOK_OF_GEODES = ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, "book_of_geodes");

	public GuideBookItem(Properties properties) {
		super(properties);
	}

	@Override
	public Component getName(ItemStack stack) {
		Book book = BookDataManager.get().getBook(BOOK_OF_GEODES);
		if(book != null) {
			return Component.translatable(book.getName());
		}
		return super.getName(stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		// super.appendHoverText(stack, worldIn, tooltip, flagIn);

		Book book = BookDataManager.get().getBook(BOOK_OF_GEODES);
		if (book != null) {
			if (flagIn.isAdvanced()) {
				tooltip
						.add(Component
								.literal("Book ID: ")
								.withStyle(ChatFormatting.DARK_GRAY)
								.append(Component.literal(book.getId().toString()).withStyle(ChatFormatting.RED)));
			}
			if (!book.getTooltip().isBlank()) {
				tooltip.add(Component.translatable(book.getTooltip()).withStyle(ChatFormatting.GRAY));
			}
		} else {
			tooltip
					.add(Component
							.translatable(ModonomiconConstants.I18n.Tooltips.ITEM_NO_BOOK_FOUND_FOR_STACK,
									!stack.hasTag() ? Component.literal("{}")
											: NbtUtils.toPrettyComponent(stack.getTag()))
							.withStyle(ChatFormatting.DARK_GRAY));
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		var itemInHand = pPlayer.getItemInHand(pUsedHand);
		if (pLevel.isClientSide) {
			if (itemInHand.hasTag()) {
				var book = BookDataManager.get().getBook(BOOK_OF_GEODES);
				BookGuiManager.get().openBook(book.getId());
			} else {
				ResonantGeodes.error("Book of Geodes has no tag!");
			}
		}
		return InteractionResultHolder.sidedSuccess(itemInHand, pLevel.isClientSide);
	}

	public ItemStack getCreativeModeTabDisplayStack() {
		ItemStack stack = new ItemStack(this);

		CompoundTag tag = new CompoundTag();
		tag.putString(ModonomiconConstants.Nbt.ITEM_BOOK_ID_TAG, BOOK_OF_GEODES.toString());
		stack.setTag(tag);
		return stack;
	}
}
