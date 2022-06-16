package com.robertowgsf.spaceflightnews.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.List;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "article")
public class Article {
    @DynamoDBHashKey
    private int id;
    @DynamoDBTyped(BOOL)
    private boolean featured;
    private String title;
    private String url;
    private String imageUrl;
    private String newsSite;
    private String summary;
    private String publishedAt;
    @DynamoDBTypeConvertedJson
    private List<Launch> launches;
    @DynamoDBTypeConvertedJson
    private List<Event> events;
}
