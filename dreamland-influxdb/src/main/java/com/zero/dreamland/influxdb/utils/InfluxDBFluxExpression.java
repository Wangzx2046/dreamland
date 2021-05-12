package com.zero.dreamland.influxdb.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 
 * @author： wanzh  
 * @date： 2019年12月19日  
 */
public class InfluxDBFluxExpression {
	
	/**
	 * 通用表达式
	 * @param buffer
	 * @param bucketName 名称
	 * @param tableName 表名
	 * @param start 开始时间
	 * @param stop 结束时间
	 * 
	 */
	protected void appendCommonFlux(StringBuffer buffer, String bucketName, String tableName, 
			String start, String stop) {
		appendBucketFlux(buffer, bucketName);
		appendTimeRangeFlux(buffer, start, stop);
		appendTableFlux(buffer, tableName);
//		if(timestampFlag) {
//			appendTimestampFlux(buffer);
//		}
//		if(dropDefaultFlag) {
//			appendDropFlux(buffer);
//		}
		
	}
	
	
	/**
	 * 数据源(桶)表达式
	 * @param buffer
	 * @param bucketName 名称
	 */
	protected void appendBucketFlux(StringBuffer buffer, String bucketName) {
		buffer.append("from(bucket: \""+bucketName+"\") ");
	}
	
	/**
	 * 表名表达式
	 * @param buffer
	 * @param tableName 名称
	 */
	protected void appendTableFlux(StringBuffer buffer, String tableName) {
		buffer.append("|> filter(fn: (r) => r._measurement == \""+tableName+"\") ");
	}
	
	
	/**
	 * 时间范围表达式
	 * @param buffer
	 * @param start 开始时间
	 * @param stop 结束时间
	 */
	protected void appendTimeRangeFlux(StringBuffer buffer,  String start, String stop) {
		if(StringUtils.isBlank(start)) {
			start = "1970-01-01T00:00:00.000Z";
		}
		if(StringUtils.isBlank(stop)) {
			buffer.append("|> range(start:" + start + ") ");
		}else {
			buffer.append("|> range(start:" + start + ", stop:"+ stop +") ");
		}
	}
	
	/**
	 * 删除字段表达式 
	 * @param buffer
	 * @param args 需要删除的字段【 参数为空的话删除host字段】
	 */
	protected void appendDropFlux(StringBuffer buffer,  String... args) {
		if(args.length == 0) {
			buffer.append("|> drop(columns: [\"host\"]) ");
			return ;
		}
		buffer.append("|> drop(columns: [");
		for(int i = 0; i <  args.length; i++) {
			if(i != 0) {
				buffer.append(",");
			}
			buffer.append("\""+args[i]+"\"");
		}
		buffer.append("]) ");
	}
	
	/**
	 * 复制属性列表达式
	 * @param buffer
	 * @param oldField 原来的字段名称
	 * @param newField 新的字段名称
	 */
	protected void appendDuplicateFlux(StringBuffer buffer,  String oldField, String newField) {
		buffer.append("|> duplicate(column: \""+oldField+"\", as: \""+newField+"\") ");
	}
	
	/**
	 * 重命名属性列表达式
	 * @param buffer
	 * @param oldField 原来的字段名称
	 * @param newField 新的字段名称
	 */
	protected void appendRenameFlux(StringBuffer buffer,  String oldField, String newField) {
		buffer.append(" |> rename(columns: {"+oldField+": \""+newField+"\"}) ");
	}
	
	
	/**
	 * 最新一条数据表达式
	 * @param buffer
	 */
	protected void appendLastFlux(StringBuffer buffer) {
		buffer.append("|> last() ");
	}
	
	/**
	 * 分页查询
	 * @param buffer
	 * @param n
	 * @param offset
	 */
	protected void appendLimitFlux(StringBuffer buffer, int n, int offset) {
		buffer.append("|> limit(n:"+n+", offset: "+offset+") ");
	}
	
	/**
	 * 分组表达式
	 * @param buffer
	 */
	protected void appendGroupFlux(StringBuffer buffer, String ...columns) {
		if(columns.length == 0) {
			buffer.append("|> group() ");
		}else {
			buffer.append("|> group(columns:[ ");
			for(int i = 0; i < columns.length; i++) {
				if(i != 0) {
					buffer.append(",");
				}
				buffer.append("\""+columns[i]+"\"");
			}
			buffer.append("]) ");
		}
		
	}
	/**
	 * 去重表达式
	 * @param buffer
	 */
	protected void appendDistinctFlux(StringBuffer buffer, String ...columns) {
		if(columns.length == 0) {
			buffer.append("|> distinct() ");
		}else {
			buffer.append("|> distinct(column:\""+columns[0]+"\") ");
		}
		
	}
	/**
	 * 总数表达式
	 * @param buffer
	 */
	protected void appendCountFlux(StringBuffer buffer) {
		buffer.append("|> count() ");
	}
	
	/**
	 * 前几条数据
	 * @param buffer
	 * @param n
	 */
	protected void appendTopFlux(StringBuffer buffer, int n) {
		buffer.append("|> top(n:"+n+") ");
	}
	protected void appendBottomFlux(StringBuffer buffer, int n) {
		buffer.append("|> bottom(n:"+n+") ");
	}
	/**
	 * 排序
	 * @param buffer
	 * @param descFlag true 降序 ；false 升序
	 * @param columns
	 */
	protected void appendSortFlux(StringBuffer buffer, boolean descFlag, String ... columns) {
		if(columns.length == 0) {
			buffer.append("|> sort(columns: [\"_value\"], desc: "+descFlag+")");
		}else {
			buffer.append("|> sort(columns:[ ");
			for(int i = 0; i < columns.length; i++) {
				if(i != 0) {
					buffer.append(",");
				}
				buffer.append("\""+columns[i]+"\"");
			}
			buffer.append("], desc: "+descFlag+") ");
		}
	}
	
//	/**
//	 * 转换时间戳 表达式
//	 * @param buffer
//	 */
//	protected void appendTimestampFlux(StringBuffer buffer) {
//		buffer.append("|> map(fn:(r) => ({r with _time:(int(v:uint(v:r._time))/1000000 )})) ");
//	}
//	
//	/**
//	 * 转换时间格式表达式
//	 * @param buffer
//	 */
//	protected void appendTimeformatFlux(StringBuffer buffer) {
//		buffer.append("|> map(fn:(r) => ({r with _time:strings.substring(v: strings.replaceAll(v: string(v: r._time), t: \"T\", u: \" \"), start: 0, end: 19)})) ");
//	}
//
	/**
	 * 时移八小时
	 * @param buffer
	 */
	protected void appendTimeShiftFlux(StringBuffer buffer) {
		buffer.append("|> timeShift(duration: 8h) ");
	}
	/**
	 * 字段名 表达式
	 * @param buffer
	 */
/*	protected void appendFiledFlux(StringBuffer buffer, String... args) {
		boolean flag = true;
		for(String arg : args) {
			if(flag) {
				buffer.append("|> filter(fn: (r) =>");
				flag = false;
			}else {
				buffer.append(InfluxDBConst.JOIN_OR);
			}
			buffer.append(" r."+InfluxDBConst.FIELD_VAL+" "+InfluxDBConst.JOIN_EQUAL+" \""+arg+"\" ");
		}
		if(!flag) {
			buffer.append(")  ");
		}
	}*/
	
	/**
	 * 过滤单个字符表达式
	 * @param buffer
	 * @param list 
	 * @param operator 【== != 】
	 * @param join 【and or】
	 * @param fieldName
	 */
	protected void appendFilterFlux(StringBuffer buffer, List<String> list, String  operator, String join, String fieldName) {
		if(list == null || list.size() == 0) {
			return ;
		}
		for(int i = 0, size = list.size(); i< size; i++) {
			if(i == 0) {
				buffer.append("|> filter(fn: (r) =>");
			}else {
				buffer.append(join);
			}
			buffer.append(" r."+fieldName+" "+operator+" \""+list.get(i)+"\" ");
		}
		buffer.append(")  ");
	}
	
	/**
	 * 过滤表达式
	 * @param buffer
	 * @param map 
	 * @param operator 【== != 】
	 * @param join 【and or】
	 */
	protected void appendFilterFlux(StringBuffer buffer, Map<String,Object> map, String  operator, String join) {
		Set<Map.Entry<String,Object>> entrySet = map.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
		boolean flag = true;
		while(iterator.hasNext()) {
			Map.Entry<String, Object> next = iterator.next();
			String key = next.getKey();
			Object value = next.getValue();
			if(flag) {
				buffer.append("|> filter(fn: (r) =>");
				flag = false;
			}else {
				buffer.append(join);
			}
			buffer.append(" r."+key+" "+operator+" \""+value+"\" ");
		}
		if(!flag) {
			buffer.append(")  ");
		}
		
	}
	
	/**
	 * 过滤多个字段表达式
	 * @param buffer
	 * @param list 
	 * @param innerJoin 【and or】
	 * @param operator 【== != 】
	 * @param outerJoin 【and or】
	 */
	protected void appendMulFilterFlux(StringBuffer buffer, List<Map<String,Object>> list, String innerJoin, String  operator, String outerJoin) {
		if(list == null || list.size() == 0) {
			return ;
		}
		buffer.append("|> filter(fn: (r) => ");
		boolean outerFlag = true;
		for(int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Set<Map.Entry<String,Object>> entrySet = map.entrySet();
			Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
			boolean innerFlag = true;
			while(iterator.hasNext()) {
				Map.Entry<String, Object> next = iterator.next();
				String key = next.getKey();
				Object value = next.getValue();
				if(innerFlag) {
					if(outerFlag) {
						outerFlag = false;
					}else {
						buffer.append(outerJoin);
					}
					buffer.append(" ( ");
					innerFlag = false;
				}else {
					buffer.append(innerJoin);
				}
				buffer.append("  r."+key+" "+operator+" \""+value+"\"  ");
			}
			if(!innerFlag) {
				buffer.append(" )  ");
			}
		}
		buffer.append(" )  ");
		
	}
	
	/**
	 * 时间窗口统计
	 * @param buffer
	 * @param step 步长值【10m,1h,1d...】
	 * @param aggType 统计类型【sum,count,min,max...)
	 */
	protected void appendAggregateWindowFlux(StringBuffer buffer, String step, String aggType) {
		buffer.append("|> aggregateWindow(every: "+step+", fn: "+aggType+") ");
	}
	
	protected void appendWindowFlux(StringBuffer buffer, String step) {
		buffer.append("|> window(every: "+step+") ");
	}
	
	/**
	 * 不带时间窗口统计
	 * @param buffer
	 * @param aggType 统计类型【sum,count,min,max...)
	 */
	protected void appendAggregateFlux(StringBuffer buffer, String aggType) {
		buffer.append("|> "+aggType+"() ");
	}
	
	
	
	
	/**
	 * 多个查询结果需要指定每个输出结果名称
	 * @param buffer
	 * @param name
	 */
	protected void appendYieldFlux(StringBuffer buffer, String name) {
		buffer.append("|> yield(name: \""+name+"\") ");
	}
	
	/**
	 * 将时间指定为某单位
	 * @param buffer
	 * @param step
	 */
	protected void appendTruncateTimeColumn(StringBuffer buffer, String step) {
		buffer.append("|> truncateTimeColumn(unit: "+step+") ");
	}
	
	/**
	 * 导入包名
	 * @param buffer
	 * @param name 包名
	 */
	protected void appendImportFlux(StringBuffer buffer, String name) {
		buffer.append("import \""+name+"\" ");
	}
	
	/**
	 * 过滤空值
	 * @param buffer
	 */
	protected void appendExistsFlux(StringBuffer buffer) {
		buffer.append("|> filter(fn: (r) => exists r._value ) ");
	}
	

	/**
	 * 过滤0值
	 * @param buffer
	 */
	protected void appendZeroFlux(StringBuffer buffer) {
		buffer.append("|> filter(fn: (r) => r._value > 0) ");
	}

	  
}
