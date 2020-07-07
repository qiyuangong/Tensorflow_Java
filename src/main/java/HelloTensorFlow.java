import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import java.nio.FloatBuffer;

public class HelloTensorFlow {
  public static void main(String[] args) throws Exception {
    try (Graph g = new Graph()) {
      final String value = "Hello from " + TensorFlow.version();

      // Construct the computation graph with a single operation, a constant
      // named "MyConst" with a value "value".
      try (Tensor t = Tensor.create(value.getBytes("UTF-8"))) {
        // The Java API doesn't yet include convenience functions for adding operations.
        g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
      }
      long[] shape = new long[]{1, 2, 10};
      int length = 20;
      float[] rawFloat = new float[length];
      FloatBuffer buffer = FloatBuffer.wrap(rawFloat, 0, 20);
      try (Tensor t1 = Tensor.create(shape, buffer)) {
        // The Java API doesn't yet include convenience functions for adding operations.
        g.opBuilder("Const", "MyConst1").setAttr("dtype", t1.dataType()).setAttr("value", t1).build();
      }

      // Execute the "MyConst" operation in a Session.
      try (Session s = new Session(g);
          // Generally, there may be multiple output tensors,
          // all of them must be closed to prevent resource leaks.
          Tensor output = s.runner().fetch("MyConst").run().get(0);
          Tensor output1 = s.runner().fetch("MyConst1").run().get(0)
          ) {
        System.out.println(new String(output.bytesValue(), "UTF-8"));
        System.out.println(output1);
      }
    }
  }
}
