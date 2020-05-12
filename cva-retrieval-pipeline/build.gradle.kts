import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val airtableVersion: String by project
val jsoupVersion: String by project
val smileNlpKtVersion: String by project
val smileCoreVersion: String by project


plugins {
    groovy
    application
}

version = "0.1"

application {
    mainClassName = "com._2horizon.cva.retrieval.Application"
}

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencies {

    implementation(project(":cva-common"))
    implementation(project(":cva-airtable"))
    
    // https://github.com/fuxingloh/airtable
    implementation("dev.fuxing:airtable-api:$airtableVersion")

    // https://jsoup.org/
    implementation("org.jsoup:jsoup:$jsoupVersion")

    // https://github.com/londogard/smile-nlp-kt
    implementation("com.londogard:smile-nlp-kt:$smileNlpKtVersion")

    implementation("com.github.haifengl:smile-core:$smileCoreVersion")
    implementation("com.github.haifengl:smile-nlp:$smileCoreVersion")

    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("io.micronaut.graphql:micronaut-graphql")
    kapt("io.micronaut.configuration:micronaut-openapi")
    
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
    withType<ShadowJar> {
        mergeServiceFiles()
    }
    withType<JavaExec> {
        classpath += configurations.getByName("developmentOnly")
        jvmArgs("-XX:TieredStopAtLevel=1", "-Dcom.sun.management.jmxremote")
    }
}

allOpen{
    annotation("io.micronaut.aop.Around")
}


