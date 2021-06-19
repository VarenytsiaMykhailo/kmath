/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.commons.random

import kotlinx.coroutines.runBlocking
import space.kscience.kmath.misc.PerformancePitfall
import space.kscience.kmath.samplers.GaussianSampler
import space.kscience.kmath.stat.RandomGenerator
import space.kscience.kmath.stat.next

public class CMRandomGeneratorWrapper(
    public val factory: (IntArray) -> RandomGenerator,
) : org.apache.commons.math3.random.RandomGenerator {
    private var generator: RandomGenerator = factory(intArrayOf())

    public override fun nextBoolean(): Boolean = generator.nextBoolean()
    public override fun nextFloat(): Float = generator.nextDouble().toFloat()

    public override fun setSeed(seed: Int) {
        generator = factory(intArrayOf(seed))
    }

    public override fun setSeed(seed: IntArray) {
        generator = factory(seed)
    }

    public override fun setSeed(seed: Long) {
        setSeed(seed.toInt())
    }

    public override fun nextBytes(bytes: ByteArray) {
        generator.fillBytes(bytes)
    }

    public override fun nextInt(): Int = generator.nextInt()
    public override fun nextInt(n: Int): Int = generator.nextInt(n)

    @PerformancePitfall
    public override fun nextGaussian(): Double = runBlocking { GaussianSampler(0.0, 1.0).next(generator) }

    public override fun nextDouble(): Double = generator.nextDouble()
    public override fun nextLong(): Long = generator.nextLong()
}
