package sg.feed;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Warmup(iterations = 5, time = 5)
//@Measurement(iterations = 5, time = 5)
public class StringReplaceBenchmark {

    private static final String pattern = "[\r\n]+";

    private static final String title = "TWiV 869: Epstein-Barr virus and MS, a perfect storm";
    private static final String author = "Vincent Racaniello";
    private static final String content = """
                TWiV reviews epidemiological and experimental evidence that
                infection with Epstein-Barr virus leads to the production of
                 antibodies against a viral protein that cross-react with a 
                 human protein, leading to multiple sclerosis. Hosts: Vincent
                  Racaniello, Dickso…
            """;

    @Benchmark
    public String replaceAllTest() {
        return title.replaceAll(pattern, " ") +
                author.replaceAll(pattern, " ") +
                content.replaceAll(pattern, " ");
    }

    @Benchmark
    public String patternAllTest() {
        Pattern p = Pattern.compile(pattern);

        return p.matcher(title).replaceAll(" ") +
                p.matcher(author).replaceAll(" ") +
                p.matcher(content).replaceAll(" ");
    }

    @Benchmark
    public String replaceAllInOneTest() {
        String subject = title + ":" + content + ":" + author;

        return subject.replaceAll(pattern, " ");
    }

    @Benchmark
    public String patternAllInOneTest() {
        String subject = title + ":" + content + ":" + author;
        Pattern p = Pattern.compile(pattern);

        return p.matcher(subject).replaceAll(" ");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringReplaceBenchmark.class.getSimpleName())
                .forks(1)
                .jvmArgs("-server")
                .shouldDoGC(false)
                .build();

        new Runner(opt).run();
    }
}

// RESULTS
//StringReplaceBenchmark.patternAllInOneTest  thrpt    5  645.740 ± 15.306  ops/ms
//StringReplaceBenchmark.replaceAllInOneTest  thrpt    5  637.738 ±  3.634  ops/ms

//StringReplaceBenchmark.patternAllTest       thrpt    5  436.444 ±  4.116  ops/ms
//StringReplaceBenchmark.replaceAllTest       thrpt    5  417.050 ±  2.238  ops/ms

//StringReplaceBenchmark.patternAllInOneTest   avgt    5    0.002 ±  0.001   ms/op
//StringReplaceBenchmark.replaceAllInOneTest   avgt    5    0.002 ±  0.001   ms/op

//StringReplaceBenchmark.patternAllTest        avgt    5    0.002 ±  0.001   ms/op
//StringReplaceBenchmark.replaceAllTest        avgt    5    0.003 ±  0.001   ms/op