package fr.aresrpg.commons.infra.database.mongodb.serialization;

import fr.aresrpg.commons.domain.serialization.Format;
import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.types.TypeEnum;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

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
		} else if (type == TypeEnum.OBJECT_ARRAY) {
			int length = Array.getLength(value);
			Document[] d = new Document[length];
			for (int i = 0; i < length; i++)
				context.serialize(d[i] = new Document(), Array.get(value, i), this);
			doc.put(name, Arrays.asList(d));
		} else doc.put(name, value);
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
	public Object read(Document doc) throws IOException {
		Map<String, Object> map = new LinkedHashMap<>();
		for (Map.Entry<String, Object> e : doc.entrySet()) {
			if (e.getValue() instanceof Document) {
				map.put(e.getKey(), read((Document) e.getValue()));
			} else if (e.getValue() instanceof List) {
				List<Object> dd = (List<Object>) e.getValue();
				Object[] mmap = new Object[dd.size()];
				if (!dd.isEmpty()) {
					boolean isdoc = dd.iterator().next() instanceof Document;
					for (int i = 0; i < dd.size(); i++)
						if (isdoc) mmap[i] = read((Document) dd.get(i));
						else mmap[i] = dd.get(i);
				}
				map.put(e.getKey(), mmap);
			} else map.put(e.getKey(), e.getValue());
		}
		return map;
	}

}
