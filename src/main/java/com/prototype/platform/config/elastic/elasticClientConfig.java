package com.prototype.platform.config.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.prototype.platform.api.elastic.DTO.TestSw;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

@Configuration
public class elasticClientConfig {

    @Bean
    public static ElasticsearchClient createElasticClient() throws Exception {
        Path caCertificatePath = Paths.get("C:\\git\\java\\platform\\ca.crt");
        CertificateFactory factory =
                CertificateFactory.getInstance("X.509");
        Certificate trustedCa;
        try (InputStream is = Files.newInputStream(caCertificatePath)) {
            trustedCa = factory.generateCertificate(is);
        }
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);
        trustStore.setCertificateEntry("ca", trustedCa);
        SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null);
        final SSLContext sslContext = sslContextBuilder.build();
        RestClient restClient = RestClient.builder(
                        new HttpHost("192.168.10.87", 9200, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setSSLContext(sslContext);
                    }
                }).build();
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

    public static void main(String[] args) throws Exception {
        ElasticsearchClient client = createElasticClient();

        SearchResponse<TestSw> search = client.search(s -> s
                        .index("test_sw")
                        .query(q -> q
                                .matchAll((m->m))),
                TestSw.class);

        for (Hit<TestSw> hit: search.hits().hits()) {
            System.out.println(hit.source());
        }
    }
}
