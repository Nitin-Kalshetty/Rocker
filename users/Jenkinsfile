pipeline {
    agent any

    environment {
        SERVICE = ""
        DEPLOY_DIR = "/opt/rocker/deploy/services"
    }

    stages {
        stage('Detect Changed Service') {
            steps {
                script {
                    def changes = sh(
                        script: "git diff --name-only HEAD~1 HEAD",
                        returnStdout: true
                    ).trim()

                    if (changes.contains("users/")) SERVICE = "users"
                }
                echo "Changed service: ${SERVICE}"
            }
        }

        stage('Build Service') {
            when { expression { SERVICE != "" } }
            steps {
                sh "cd ${SERVICE} && ./mvn clean package -DskipTests"
            }
        }

        stage('Deploy JAR') {
            when { expression { SERVICE != "" } }
            steps {
                sh """
                    mkdir -p ${DEPLOY_DIR}/${SERVICE}
                    cp ${SERVICE}/target/*.jar ${DEPLOY_DIR}/${SERVICE}/app.jar

                    # stop old service
                    pkill -f "${SERVICE}/app.jar" || true

                    # start new service
                    nohup java -jar ${DEPLOY_DIR}/${SERVICE}/app.jar > ${DEPLOY_DIR}/${SERVICE}/log.txt 2>&1 &
                """
            }
        }
    }
}
