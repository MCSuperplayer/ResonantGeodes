package io.github.mcsuperplayer.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class CrystalResonanceStorage extends SavedData {
	private static final String NAME = "crystal_resonance";
	private int resonance = 0;

	public static CrystalResonanceStorage get(ServerLevel level) {
		return level.getDataStorage().computeIfAbsent(tag -> {
			CrystalResonanceStorage data = new CrystalResonanceStorage();
			data.resonance = tag.getInt("resonance");
			return data;
		}, CrystalResonanceStorage::new, NAME);
	}

	public void add(int amount) {
		resonance += amount;
		setDirty();
	}

	public boolean consume(int amount) {
		if (resonance >= amount) {
			resonance -= amount;
			setDirty();
			return true;
		}
		return false;
	}

	public int get() {
		return resonance;
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		tag.putInt("resonance", resonance);
		return tag;
	}

}
