package org;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.omidbiz.core.axon.Axon;
import org.omidbiz.core.axon.AxonBuilder;

@Provider
@Consumes("application/json")
@Produces("application/json")
public class JsonWriterProvider implements MessageBodyWriter<Object> {

	@Override
	public long getSize(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		Axon axon = new AxonBuilder().create();
		String json = axon.toJson(arg0);
		return json.getBytes().length;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return true;
	}

	@Override
	public void writeTo(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream arg6)
			throws IOException, WebApplicationException {
		Axon axon = new AxonBuilder().create();
		String json = axon.toJson(arg0);
		IOUtils.write(json.getBytes(), arg6);
	}
}
