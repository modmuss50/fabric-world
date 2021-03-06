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

import net.fabricmc.base.loader.Init;
import net.minecraft.reference.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class TestFabricWorld implements IWorldGenerator {

	@Init
	public void init() {
		FabricWorld.registerWorldGen(this);
	}

	@Override
	public void generate(Chunk chunk, World world, Random random, BlockPos pos) {
		for (int i = 0; i < 10; i++) {
			world.setBlockState(pos.add(random.nextInt(15), 65 + i, random.nextInt(15)), Blocks.DIAMOND_BLOCK.defaultState, 2);
		}
	}
}
