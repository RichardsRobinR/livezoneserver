plugins {
    id("java")
}

group = "org.rdx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

//    implementation ("org.eclipse.jetty:jetty-server:9.4.51.v2023021")
//    implementation ("org.eclipse.jetty.websocket:websocket-server:9.4.51.v20230217")


//    // https://mvnrepository.com/artifact/jakarta.websocket/jakarta.websocket-api
//    compileOnly("jakarta.websocket:jakarta.websocket-api:2.0.0")
//
//    // https://mvnrepository.com/artifact/org.eclipse.jetty.toolchain/jetty-jakarta-websocket-api
//    implementation("org.eclipse.jetty.toolchain:jetty-jakarta-websocket-api:2.0.0")

    // https://mvnrepository.com/artifact/org.eclipse.jetty.websocket/websocket-jetty-server
    implementation("org.eclipse.jetty.websocket:websocket-jetty-server:11.0.15")


}

tasks.test {
    useJUnitPlatform()
}