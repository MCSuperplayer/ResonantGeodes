package com.mcsuperplayer.resonantgeodes.world;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class GeodeLocationStorage extends SavedData {
	private static final String NAME = "geode_locations";
	private ListTag geodes = new ListTag();

	public static GeodeLocationStorage get(ServerLevel level) {
		return level.getDataStorage().computeIfAbsent(tag -> {
			GeodeLocationStorage data = new GeodeLocationStorage();
			data.geodes = tag.getList(NAME, Tag.TAG_COMPOUND);
			return data;
		}, GeodeLocationStorage::new, NAME);
	}

	public void addGeode(int x, int y, int z, String material) {
		CompoundTag geode = new CompoundTag();
		geode.putInt("x", x);
		geode.putInt("y", y);
		geode.putInt("z", z);
		geode.putString("material", material);
		geodes.add(geode);
		ResonantGeodes.debug("added " + material + " geode at x:" + x + " y:" + y + " z:" + z);
		setDirty();
	}

	public void removeGeode(int x, int y, int z, String material) {
		CompoundTag geode = new CompoundTag();
		geode.putInt("x", x);
		geode.putInt("y", y);
		geode.putInt("z", z);
		geode.putString("material", material);
		geodes.remove(geode);
		ResonantGeodes.debug("removed " + material + " geode at x:" + x + " y:" + y + " z:" + z);
		setDirty();

	}
	@Override
	public CompoundTag save(CompoundTag tag) {
		tag.put(NAME, geodes);
		return tag;
	}

}
