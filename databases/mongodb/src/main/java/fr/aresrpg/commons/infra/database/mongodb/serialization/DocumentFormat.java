package fr.aresrpg.commons.infra.database.mongodb.serialization;

import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.serialization.formats.Format;
import fr.aresrpg.commons.domain.types.TypeEnum;
import org.bson.Document;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DocumentFormat implements Format<Document , Document>{
	public static final DocumentFormat INSTANCE = new DocumentFormat();

	private DocumentFormat(){}

	@Override
	public void writeBegin(Document o) throws IOException {
		//Ignored
	}

	@Override
	public void writeValue(Document doc, String name , TypeEnum typeEnum, Object o, SerializationContext<Document , Document> serializationContext) throws IOException {
		if(typeEnum == TypeEnum.OBJECT){
			Document d = new Document();
			serializationContext.serialize(d , o , this);
			doc.put(name , d);
		}
		doc.put(name , o);
	}

	@Override
	public void writeBeginObject(Document o) throws IOException {
		//Ignored
	}

	@Override
	public void writeFieldSeparator(Document o, boolean b, boolean b1) throws IOException {
		//Ignored
	}

	@Override
	public void writeEndObject(Document o) throws IOException {
		//Ignored
	}

	@Override
	public void writeEnd(Document o) throws IOException {
		//Ignored
	}

	@Override
	public void read(Document document, Map<String, Object> map, SerializationContext<Document , Document> serializationContext) throws IOException {
		for(Map.Entry<String , Object> e : document.entrySet()){
			if(e instanceof Document){
				Map<String , Object> m = new LinkedHashMap<>();
				read((Document)e , m , serializationContext);
				map.put(e.getKey() , m);
			}else
				map.put(e.getKey() , e.getValue());
		}
	}
}
