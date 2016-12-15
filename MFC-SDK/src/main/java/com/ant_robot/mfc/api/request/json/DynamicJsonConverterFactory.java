package com.ant_robot.mfc.api.request.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by spawn on 15/12/16.
 */
public class DynamicJsonConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new DynamicJsonConverter(type);
    }

    /*
    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        try {
            InputStream in = typedInput.in(); // convert the typedInput to String
            String string = fromStream(in);
            in.close(); // we are responsible to close the InputStream after use

            string = StringUtils.replace(string, "{}", "null");
            //string = StringUtils.replace(string, "0000-00-00", "null");

            if (String.class.equals(type)) {
                return string;
            } else {
                return new GsonBuilder()
                        .setDateFormat(getDateFormat())
                        .create().fromJson(string, type); // convert to the supplied type, typically Object, JsonObject or Map<String, Object>
            }
        } catch (Exception e) { // a lot may happen here, whatever happens
            throw new ConversionException(e); // wrap it into ConversionException so retrofit can process it
        }
    }

    protected String getDateFormat() {
        return "yyyy-MM-dd";
    }

    @Override
    public TypedOutput toBody(Object object) { // not required
        return null;
    }

    private static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append("\r\n");
        }
        return out.toString();
    }
    */

}
