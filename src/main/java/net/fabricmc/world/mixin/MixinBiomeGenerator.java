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

import net.fabricmc.base.Fabric;
import net.fabricmc.world.WorldGenerationEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = BiomeGenerator.class, remap = false)
public class MixinBiomeGenerator {

	@Shadow
	public BlockPos b; //BlockPos of chunk

	@Inject(method = "a(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/World;Ljava/util/Random;)V", at = @At("HEAD"))
	public void generatePre(Biome biome, World world, Random random, CallbackInfo info) {
		Fabric.getEventBus().publish(new WorldGenerationEvent.Pre(biome, world, random, b));
	}

	@Inject(method = "a(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/World;Ljava/util/Random;)V", at = @At("RETURN"))
	public void generatePost(Biome biome, World world, Random random, CallbackInfo info) {
		Fabric.getEventBus().publish(new WorldGenerationEvent.Post(biome, world, random, b));
	}

}
