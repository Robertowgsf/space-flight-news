package com.robertowgsf.spaceflightnews.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;

import java.util.List;

@Getter
@DynamoDBTable(tableName = "article")
public class Article {
    @DynamoDBHashKey
    private int id;
    private boolean featured;
    private String title;
    private String url;
    private String imageUrl;
    private String newsSite;
    private String summary;
    private String publishedAt;
    private List<Launch> launches;
    private List<Event> events;
}
