package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.CfnBucket;
import software.amazon.awscdk.services.s3.CfnBucketProps;
import software.amazon.awscdk.services.s3.deployment.BucketDeployment;
import software.amazon.awscdk.services.s3.deployment.ISource;

public class AppsStack extends Stack {
    public AppsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AppsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);


        CfnBucket bucket = new CfnBucket(this, "L1Construct", CfnBucketProps.builder()
                .bucketName("example-cdk-bucket")
                .versioningConfiguration(CfnBucket.VersioningConfigurationProperty.builder()
                        .status("Enabled").build())
                .publicAccessBlockConfiguration(CfnBucket.PublicAccessBlockConfigurationProperty.builder()
                        .restrictPublicBuckets(true)
                        .ignorePublicAcls(true)
                        .blockPublicAcls(true)
                        .blockPublicPolicy(true)
                        .build())
                .build());

        bucket.applyRemovalPolicy(RemovalPolicy.RETAIN);
        ISource source = null;
        createDeploySourceToBucket(this, source, bucket);

    }

    private void createDeploySourceToBucket(AppsStack appsStack, ISource source, CfnBucket bucket) {
        //BucketDeployment bucketDeployment = new BucketDeployment();
    }


}
