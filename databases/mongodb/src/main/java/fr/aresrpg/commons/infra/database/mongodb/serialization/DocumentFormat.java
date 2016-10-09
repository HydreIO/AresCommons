package fr.aresrpg.commons.infra.database.mongodb.serialization;

import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.serialization.Format;
import fr.aresrpg.commons.domain.types.TypeEnum;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bson.Document;

public class DocumentFormat implements Format<Document, Document> {
	public static final DocumentFormat INSTANCE = new DocumentFormat();

	private DocumentFormat() {
	}

	@Override
	public void writeBegin(Document o) throws IOException {
		// Ignored
	}

	@Override
	public void writeValue(Document doc, String name, TypeEnum type, Object value, SerializationContext context) throws IOException {
		if (type == TypeEnum.OBJECT) {
			Document d = new Document();
			context.serialize(d, value, this);
			doc.put(name, d);
		}
		doc.put(name, value);
	}

	@Override
	public void writeBeginObject(Document o) throws IOException {
		// Ignored
	}

	@Override
	public void writeFieldSeparator(Document o, boolean b, boolean b1) throws IOException {
		// Ignored
	}

	@Override
	public void writeEndObject(Document o) throws IOException {
		// Ignored
	}

	@Override
	public void writeEnd(Document o) throws IOException {
		// Ignored
	}

	@Override
	public void read(Document doc, Map<String, Object> container, SerializationContext context) throws IOException {
		for (Map.Entry<String, Object> e : doc.entrySet()) {
			if (e instanceof Document) {
				Map<String, Object> m = new LinkedHashMap<>();
				read((Document) e, m, context);
				container.put(e.getKey(), m);
			} else container.put(e.getKey(), e.getValue());
		}
	}

}
