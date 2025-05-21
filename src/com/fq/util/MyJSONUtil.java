package com.fq.util;

import com.google.gson.*;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class MyJSONUtil {
	
	public static void main(String[] args){
		System.out.println(11);
	}
	public List convertJsonToList(String json) throws Exception {
		return convertJsonToObject(json, List.class);
	}
	
	public Map<?, ?> convertJsonToMap(String json) throws Exception {
		return convertJsonToObject(json, Map.class);
	}
	
	public <T> T convertJsonToObject(String json, Class<T> classOfT) throws Exception {
		return convertJsonToObject(json, classOfT);
	}
	
	public <T> T convertJsonToObject(String json, Type typeOfT) throws Exception {
		Gson gson = initGson();
		Object fromJson = gson.fromJson(json, typeOfT);
		
		
		return null;
	}
	
	public static Gson initGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(int.class, INTEGER);
		gsonBuilder.registerTypeAdapter(long.class, LONG);
		gsonBuilder.registerTypeAdapter(float.class, FLOAT);
		gsonBuilder.registerTypeAdapter(double.class, DOUBLE);
		try {
			Class builder = (Class) gsonBuilder.getClass();
			Field f = builder.getDeclaredField("instanceCreators");
			f.setAccessible(true);
			Map<Type, InstanceCreator<?>> val = (Map<Type, InstanceCreator<?>>) f.get(gsonBuilder);//得到此属性的值
			//注册数组的处理器
			gsonBuilder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(val)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Gson gson = gsonBuilder.create();
		return gson;
	}
	
	public static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() {
		@Override
		public Number read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return 0;
			}
			try {
				double i = in.nextDouble();
				return (int) i;
			} catch (NumberFormatException e) {
				throw new JsonSyntaxException(e);
			}
		}
		
		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			out.value(value);
		}
	};
	
	public static final TypeAdapter<Number> FLOAT = new TypeAdapter<Number>() {
		@Override
		public Number read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return 0;
			}
			try {
				double i = in.nextDouble();
				return (int) i;
			} catch (NumberFormatException e) {
				throw new JsonSyntaxException(e);
			}
		}
		
		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			out.value(value);
		}
	};
	
	public static final TypeAdapter<Number> LONG = new TypeAdapter<Number>() {
		@Override
		public Number read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return 0;
			}
			try {
				double i = in.nextDouble();
				return (int) i;
			} catch (NumberFormatException e) {
				throw new JsonSyntaxException(e);
			}
		}
		
		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			out.value(value);
		}
	};
	
	public static final TypeAdapter<Number> DOUBLE = new TypeAdapter<Number>() {
		@Override
		public Number read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return 0;
			}
			try {
				double i = in.nextDouble();
				return (int) i;
			} catch (NumberFormatException e) {
				throw new JsonSyntaxException(e);
			}
		}
		
		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			out.value(value);
		}
	};
}
