package fr.aresrpg.commons.benchmark.matcher;

import static fr.aresrpg.commons.domain.Predicates.is;
import static fr.aresrpg.commons.domain.condition.match.Matcher.match;
import static fr.aresrpg.commons.domain.condition.match.Matcher.when;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class MatcherBenchmark {

	@Benchmark
	public void benchMatcher(Blackhole blackhole) {
		blackhole.consume(match("Hello", when(is("Red"), "Blue"), when(is("Blue"), "Red"), when(is("Hello"), "A+")));
	}

	@Benchmark
	public void benchSwitch(Blackhole blackhole) {
		switch ("Hello") {
			case "Red":
				blackhole.consume("Blue");
				break;
			case "Blue":
				blackhole.consume("Red");
				break;
			case "Hello":
				blackhole.consume("A+");
				break;
		}
	}

	@Benchmark
	public void benchIfElse(Blackhole blackhole) {
		String hello = "Hello";
		if (hello.equals("Red")) blackhole.consume("Blue");
		else if (hello.equals("Blue")) blackhole.consume("Red");
		else if (hello.equals("Hello")) blackhole.consume("A+");
	}
}
