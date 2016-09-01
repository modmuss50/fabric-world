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

package net.fabricmc.world;

import net.fabricmc.api.Event;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class WorldGenerationEvent extends Event {

	private Biome biome;
	private World world;
	private Random random;
	private BlockPos chunkPos;

	public WorldGenerationEvent(Biome biome, World world, Random random, BlockPos chunkPos) {
		this.biome = biome;
		this.world = world;
		this.random = random;
		this.chunkPos = chunkPos;
	}

	public Biome getBiome() {
		return biome;
	}

	public World getWorld() {
		return world;
	}

	public Random getRandom() {
		return random;
	}

	public BlockPos getChunkPos() {
		return chunkPos;
	}

	public static class Pre extends WorldGenerationEvent {

		public Pre(Biome biome, World world, Random random, BlockPos chunkPos) {
			super(biome, world, random, chunkPos);
		}
	}

	public static class Post extends WorldGenerationEvent {

		public Post(Biome biome, World world, Random random, BlockPos chunkPos) {
			super(biome, world, random, chunkPos);
		}
	}
}
