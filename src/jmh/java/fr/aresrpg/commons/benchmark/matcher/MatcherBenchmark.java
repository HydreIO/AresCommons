package fr.aresrpg.commons.benchmark.matcher;

import static fr.aresrpg.commons.domain.util.Predicates.is;
import static fr.aresrpg.commons.domain.condition.match.Matcher.match;
import static fr.aresrpg.commons.domain.condition.match.Matcher.when;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class MatcherBenchmark {

	private static final String HELLO = "Hello";

	@Benchmark
	public void benchMatcher(Blackhole blackhole) {
		blackhole.consume(match(HELLO, when(is("Red"), "Blue"), when(is("Blue"), "Red"), when(is(HELLO), "A+")));
	}

	@Benchmark
	public void benchSwitch(Blackhole blackhole) {
		switch (HELLO) {
			case "Red":
				blackhole.consume("Blue");
				break;
			case "Blue":
				blackhole.consume("Red");
				break;
			case HELLO:
				blackhole.consume("A+");
				break;
			default:
				return;
		}
	}

	@Benchmark
	public void benchIfElse(Blackhole blackhole) {
		if ("Red".equals(HELLO)) blackhole.consume("Blue");
		else if ("Blue".equals(HELLO)) blackhole.consume("Red");
		else if ("Hello".equals(HELLO)) blackhole.consume("A+");
	}
}
