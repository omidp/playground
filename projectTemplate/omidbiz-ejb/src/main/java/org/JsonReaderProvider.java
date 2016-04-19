package org;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.omidbiz.core.axon.Axon;
import org.omidbiz.core.axon.AxonBuilder;

@Provider
@Consumes("application/json")
@Produces("application/json")
public class JsonReaderProvider implements MessageBodyReader<Object>{

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		Axon axon = new AxonBuilder().create();
		StringWriter w = new StringWriter();
		IOUtils.copy(entityStream, w, "UTF-8");
		Object object = axon.toObject(w.toString(), type, null);
		return object;
	}

}
