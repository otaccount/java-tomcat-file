package ot.file;

import java.io.InputStream;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class AwsFile extends FileAbstract {
	AmazonS3 client = null;
	String BUCKETNAME = "s3testot";

	public AwsFile() {
		// proxy環境の場合は、ClientConfigurationを設定する
		ClientConfiguration conf = new ClientConfiguration();
		System.out.println(System.getProperty("proxyHost"));
		conf.setProtocol(Protocol.HTTPS);
		conf.setProxyHost(System.getProperty("proxyHost"));
		conf.setProxyPort(Integer.parseInt(System.getProperty("proxyPort")));

		String accessKey = System.getProperty("accessKey");
		String secretKey = System.getProperty("secretKey");
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		String serviceEndpoint = System.getProperty("serviceEndpoint");
		String signingRegion = System.getProperty("signingRegion");
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(serviceEndpoint, signingRegion);

		client = AmazonS3ClientBuilder.standard()
				.withClientConfiguration(conf)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withEndpointConfiguration(endpointConfiguration)
				.build();
	}

	/**
	 * アップロード
	 */
	@Override
	public void write(final InputStream is, final String path) {
		ObjectMetadata om = new ObjectMetadata();

		final PutObjectRequest putRequest = new PutObjectRequest(BUCKETNAME, path, is, om);

		client.putObject(putRequest);
	}

	/**
	 * ダウンロード
	 */
	@Override
	public InputStream read(final String path) {
		S3Object object = client.getObject(BUCKETNAME, path);
		return object.getObjectContent();
	}
}
