Imported plugin jqoo

<build>
        <plugins>

            <!-- ================= jOOQ Code Generator ================= -->
            <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>3.18.6</version>

                <executions>
                    <execution>
                        <id>generate-jooq</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>

                <configuration>
                    <jdbc>
                        <driver>org.postgresql.Driver</driver>
                        <url>jdbc:postgresql://localhost:5432/backend</url>
                        <user>rocker</user>
                        <password>rocker</password>
                    </jdbc>

                    <generator>
                        <database>
                            <name>org.jooq.meta.postgres.PostgresDatabase</name>
                            <inputSchema>product_service</inputSchema>
                        </database>


                        <generate>
                            <tables>false</tables>
                            <records>false</records>
                            <daos>false</daos>
                            <pojos>true</pojos>
                            <interfaces>false</interfaces>
                        </generate>

                        <target>
                            <packageName>com.backend.rocker.entity</packageName>
                            <directory>${project.build.directory}/generated-sources/jooq</directory>
                        </target>
                    </generator>
                </configuration>
            </plugin>


            <!-- ===== Add generated sources to Maven classpath ===== -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
                <version>3.5.0</version>
                <executions>
                    <execution>
                        <id>add-jooq-sources</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>add-source</goal>
                        </goals>
                        <configuration>
                            <sources>
                                <source>${project.build.directory}/generated-sources/jooq</source>
                            </sources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <!-- ================= Spring Boot ================= -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

        </plugins>
    </build>

mvn clean generate-sources
