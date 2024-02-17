/*
 * Copyright 2018-2024 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.tensorflow

import org.tensorflow.Graph
import org.tensorflow.Output
import org.tensorflow.ndarray.NdArray
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import space.kscience.attributes.SafeType
import space.kscience.kmath.operations.Int32Ring
import space.kscience.kmath.operations.Int64Ring
import space.kscience.kmath.structures.Int32
import space.kscience.kmath.structures.Int64

public class IntTensorFlowOutput(
    graph: Graph,
    output: Output<TInt32>,
) : TensorFlowOutput<Int, TInt32>(graph, output) {

    override val type: SafeType<Int32> get() = Int32Ring.type

    override fun org.tensorflow.Tensor.actualizeTensor(): NdArray<Int> = this as TInt32
}

public class LongTensorFlowOutput(
    graph: Graph,
    output: Output<TInt64>,
) : TensorFlowOutput<Long, TInt64>(graph, output) {

    override val type: SafeType<Int64> get() = Int64Ring.type
    override fun org.tensorflow.Tensor.actualizeTensor(): NdArray<Long> = this as TInt64
}