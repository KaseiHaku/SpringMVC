package kasei.springmvc.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
//注意 <String, Date> 里的 Date，如果 Handler 参数里出现 Date 类型，那么就会调用这个转换器，String 表示原类型
public class String2TimestampConverter implements Converter<String, Timestamp> { 
	
	@Override
	public Timestamp convert(String source) {
		//实现字符串转日期类型
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Timestamp timestamp = new Timestamp(sf.parse(source).getTime());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
