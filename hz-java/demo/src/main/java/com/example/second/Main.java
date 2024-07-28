package com.example.second;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("hz-cluster");
        clientConfig.getNetworkConfig().addAddress("172.26.0.5:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        // IMap<String, String> mapString = client.getMap("customers"); //creates the map proxy

        // mapString.put("1","hello");
        // mapString.put("2", "world");
        // mapString.put("3", "whazzzzzaaa");
        System.out.println("DONE");
        IMap<String, Customer> mapCustomers = client.getMap("customers"); //creates the map proxy
        
        mapCustomers.put("1a", new Customer("Joe", "Smith"));
        mapCustomers.put("2a", new Customer("Ali", "Selam"));
        mapCustomers.put("3a", new Customer("Avi", "Noyan"));
        System.out.println("---->"+mapCustomers.get("1a").first);
        System.out.println("---->"+mapCustomers.get("2a").first);
        System.out.println("---->"+mapCustomers.get("3a").first);
        client.shutdown();
    }
    
    private static class Customer  implements Serializable{
        String first, last;
        public Customer(String f, String l){
            first =f; 
            last = l;
        }
    }
}
