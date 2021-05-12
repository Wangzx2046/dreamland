package com.zero.dreamland.influxdb.biz;

import com.influxdb.client.*;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.exceptions.InfluxException;
import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Repository
public class TimeSeriesRepository {
    @Autowired
    InfluxDBClient influxDBClient;
    @Value("${spring.influx.org:''}")
    private String org;

    @Value("${spring.influx.bucket:''}")
    private String bucket;

    public void save(String measurement, Map<String, Object> fields) {

        WriteOptions writeOptions = WriteOptions.builder()
                .batchSize(5000)
                .flushInterval(1000)
                .bufferLimit(10000)
                .jitterInterval(1000)
                .retryInterval(5000)
                .build();
        try (WriteApi writeApi = influxDBClient.getWriteApi(writeOptions)) {
            Point point = Point.measurement(measurement)
                    .addFields(fields)
                    .time(Instant.now(), WritePrecision.NS);
            writeApi.writePoint(bucket, org, point);
        }
    }

    public List<FluxTable> findAll() {
        String flux = String.format("from(bucket: \"%s\") |> range(start: 0)", bucket);
        QueryApi queryApi = influxDBClient.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux, org);
        return tables;
    }


    public List<FluxTable> findByFlux(String flux) {
        QueryApi queryApi = influxDBClient.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux, org);
        return tables;
    }


    public void delete(String measurement, Map<String, Object> fields) {
        DeleteApi deleteApi = influxDBClient.getDeleteApi();
        try {
            OffsetDateTime start = OffsetDateTime.now().minus(1, ChronoUnit.HOURS);
            OffsetDateTime stop = OffsetDateTime.now();
            deleteApi.delete(start, stop, " ", bucket, org);

        } catch (InfluxException ie) {
            System.out.println("InfluxException：" + ie);
        }

        influxDBClient.close();
    }


}