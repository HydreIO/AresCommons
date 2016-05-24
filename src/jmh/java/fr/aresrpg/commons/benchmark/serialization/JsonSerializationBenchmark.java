package fr.aresrpg.commons.benchmark.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.aresrpg.commons.serialization.Serializer;
import fr.aresrpg.commons.serialization.field.ReflectionFieldModifier;
import fr.aresrpg.commons.serialization.formats.json.JsonSerializationFormat;
import org.boon.json.serializers.impl.JsonSimpleSerializerImpl;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@State(Scope.Benchmark)
public class JsonSerializationBenchmark {

	public static class Message{
		private String msg = "Hello, world!";

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}
	}

	private Message message;
	private ObjectMapper jackson;
	private JsonSimpleSerializerImpl boon;
	private Serializer ares;

	@Setup(Level.Trial)
	public void setup(){
		message = new Message();
		jackson = new ObjectMapper();
		boon = new JsonSimpleSerializerImpl();
		ares = new Serializer(new JsonSerializationFormat() , new ArrayList<>() , n -> n , new ReflectionFieldModifier());
	}

	@Benchmark
	public void boonSerialize(Blackhole bh){
		bh.consume(boon.serialize(message).toString());
	}

	@Benchmark
	public void jacksonSerialize(Blackhole bh) throws JsonProcessingException{
		bh.consume(jackson.writeValueAsString(message));
	}

	@Benchmark
	public void aresSerialize(Blackhole bh) throws IOException {
		ares.serialize(new OutputStream() {
			@Override
			public void write(int i) throws IOException {
				bh.consume(i);
			}
		} , message);
	}


}
