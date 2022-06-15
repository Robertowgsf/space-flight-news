package com.robertowgsf.spaceflightnews.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.robertowgsf.spaceflightnews.repository",
                            dynamoDBMapperConfigRef = "dynamoDBMapperConfig",
                            dynamoDBMapperRef = "dynamoDBMapper")
public class DynamoDBConfig {
    @Value("${aws.profile-name}") private String profileName;
    @Value("${aws.region}") private String region;

    @Bean("dynamoDBMapperConfig")
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean("dynamoDBMapper")
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withRegion(region)
                .build();
    }

    private AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new ProfileCredentialsProvider(profileName);
    }
}
