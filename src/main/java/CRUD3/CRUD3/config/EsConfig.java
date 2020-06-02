package CRUD3.CRUD3.config;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "CRUD3.CRUD3.esrepo")
////@ComponentScan(basePackages = { "com.baeldung.spring.data.es.service" })
//public class EsConfig {
//
//    @Value("${elasticsearch.host}")
//    private String EsHost;
//
//    @Value("${elasticsearch.port}")
//    private int EsPort;
//
//    @Value("${elasticsearch.clustername}")
//    private String EsClusterName;
//
//    @Bean
//    public Client client() throws Exception {
//
//        Settings esSettings = Settings.builder()
//                .put("cluster.name", EsClusterName)
//                .build();
//
//        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
//        TransportClient client = new PreBuiltTransportClient(esSettings);
//        client.addTransportAddress(
//                new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }
//
////    @Bean
////    public NodeBuilder builder() {
////        return new NodeBuilder();
////    }
////
////    @Bean
////    public ElasticsearchOperations elasticsearchTemplate() throws IOException {
////        return new ElasticsearchTemplate();
////    }
////
////    public ElasticsearchOperations elasticsearchTemplate() {
////        return new ElasticsearchTemplate();
////    }
//}