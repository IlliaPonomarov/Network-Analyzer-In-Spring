# Network Analyzer in Spring Boot

## Description
This is a network analyzer which will be analyze traffic on the network and will be able to detect the following things:

- <h3>Traffic Categorization</h3> Implement logic to categorize network traffic based on the parsed information. For example, you could distinguish between HTTP, FTP, DNS, and other types of traffic.

- <h3>Traffic Statistics</h3> Implement logic to calculate and display statistics about the network traffic. For example, you could display the number of packets, the number of bytes, and the number of packets per second.

- <h3>The system could be configured to analyze and categorize traffic based on specific protocols.</h3> For example, it could focus on HTTP traffic, FTP transfers, DNS queries, etc. This helps in understanding the usage patterns of different protocols.

- <h3>Security-Related Traffic Analysis</h3> Implement logic to detect security-related traffic. For example, you could detect port scans, denial of service attacks, and other types of attacks.

- <h3>Performance Metrics</h3> Implement logic to calculate and display performance metrics. For example, you could display the number of packets per second, the number of bytes per second, and the number of packets per byte.

- <h3>Internal vs. External Traffic</h3> The analyzer might differentiate between internal network traffic (communication within the organization's network) and external traffic (communication outside the organization). This can be useful for security and performance analysis.

- <h3>Quality of Service (QoS) Monitoring</h3> The analyzer might be able to detect and report on the quality of service (QoS) of the network. For example, it could detect and report on the number of dropped packets, the number of retransmitted packets, and the number of packets that were delivered out of order.

- <h3>IoT Device Communication</h3> The analyzer might be able to detect and report on the communication between IoT devices. For example, it could detect and report on the number of packets sent and received by each device, the number of bytes sent and received by each device, and the number of packets sent and received by each device per second.

- <h3>Application-Specific Analysis: The analyzer might be able to detect and report on the communication between applications. For example, it could detect and report on the number of packets sent and received by each application, the number of bytes sent and received by each application, and the number of packets sent and received by each application per second.
  
## Installation
- ```git clone https://github.com/IlliaPonomarov/Network-Analyzer-In-Spring.git ```
- ```cd Network-Analyzer-In-Spring```
- ```mvn clean install```

## Usage
- ```java -jar target/network-analyzer-0.0.1-SNAPSHOT.jar```
- ```java -jar target/network-analyzer-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/application.properties,classpath:/application-dev.properties```
- ```java -jar target/network-analyzer-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/application.properties,classpath:/application-prod.properties```



