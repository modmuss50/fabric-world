/*
 * Copyright 2016 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.world.mixin;

import net.fabricmc.world.FabricWorld;
import net.fabricmc.world.IWorldGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.generator.IChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Chunk.class, remap = false)
public class MixinChunk {

	@Shadow
	public int chunkX;
	@Shadow
	public int chunkZ;

	@Overwrite
	public void a(IChunkGenerator generator) {
		if (getChunk().isTerrainPopulated()) {
			if (generator.a(getChunk(), this.chunkX, this.chunkZ)) {
				getChunk().e();
			}
		} else {
			getChunk().o();
			generator.b(this.chunkX, this.chunkZ);
			for (IWorldGenerator iWorldGenerator : FabricWorld.worldGenerators) {
				iWorldGenerator.generate(getChunk(), getChunk().getWorld(), getChunk().a(0), new BlockPos(chunkX << 4, 0, chunkZ << 4));
			}
			getChunk().e();
		}

	}

	Chunk getChunk() {
		return (Chunk) (Object) this;
	}

}
