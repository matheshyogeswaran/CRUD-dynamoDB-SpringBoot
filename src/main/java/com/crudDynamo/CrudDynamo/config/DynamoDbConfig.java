package com.crudDynamo.CrudDynamo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {


    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.access.secret-key}")
    private String awsSecretKey;

    @Value("${aws.dynamodb.endpoint}")
    private String awsDynamoDBEndPoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AWSCredentials amazonAWSCredentials(){
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }


    @Bean
    public AWSCredentialsProvider amazonAWSCredentialsProvider(){
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
                .withCredentials(amazonAWSCredentialsProvider())
                .build();
    }

    @Bean
    public DynamoDBMapper mapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }
}
