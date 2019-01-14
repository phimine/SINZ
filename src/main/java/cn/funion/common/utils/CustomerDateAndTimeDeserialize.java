package cn.funion.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class CustomerDateAndTimeDeserialize extends JsonDeserializer {
	private static final Logger _logger = LoggerFactory.getLogger(CustomerDateAndTimeDeserialize.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH);


	@Override
	public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
			throws IOException, JsonProcessingException {

        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
        	_logger.error("CustomerDateAndTimeDeserialize exception",e);
        }
        return paramDeserializationContext.parseDate(str);
    
	}
}
