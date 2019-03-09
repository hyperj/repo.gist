package net.hyperj.gist.jmh;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.openjdk.jmh.annotations.*;

import java.nio.charset.Charset;

public class BenchMark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {
        public Hasher murmur3;

        public String password = "4v3rys3kur3p455w0rd";

        @Setup(Level.Invocation)
        public void setUp() {
            murmur3 = Hashing.murmur3_128().newHasher();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void benchMurmur3_128(ExecutionPlan plan) {
        plan.murmur3.putString(plan.password, Charset.defaultCharset()).hash();
    }

}