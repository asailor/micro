package com.gdsig.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xs
 * @date 2022/12/26下午 5:51
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "account", shards = 1, replicas = 0)
public class EsAccount implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Text)
    private String name;

    @Field( type = FieldType.Date,
            format = DateFormat.date_hour_minute_second_millis
    )
//    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
}
