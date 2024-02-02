package alex.examples;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.AWSCostExplorerClient;
import com.amazonaws.services.costexplorer.model.DateInterval;
import com.amazonaws.services.costexplorer.model.Expression;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageRequest;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.amazonaws.services.costexplorer.model.TagValues;

public class Main {

  private static final String AWS_KEY = ""; //TODO: Fill value
  private static final String AWS_SECRET = ""; //TODO: Fill value
  private static final String REGION = "eu-west-1"; //TODO: Update region if needed

  public static void main(String[] args) {
    AWSCostExplorer costExplorer = AWSCostExplorerClient.builder()
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_KEY, AWS_SECRET)))
        .withRegion(REGION)
        .build();
    Expression expression = new Expression();
    expression.withTags(new TagValues().withKey("TAG-KEY").withValues("TAG-VALUE")); //TODO: Update tag key and value
    GetCostAndUsageResult result =
        costExplorer.getCostAndUsage(new GetCostAndUsageRequest().withTimePeriod(new DateInterval().withStart("2023-10-01").withEnd("2023-10-29")).withGranularity("DAILY").withMetrics("BlendedCost").withFilter(expression));

    result.getResultsByTime().forEach(r -> System.out.println(r));
  }
}