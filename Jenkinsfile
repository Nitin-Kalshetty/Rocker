pipeline {
    agent any

    environment {
        DEPLOY_DIR = "/opt/rocker/deploy/services"
    }

    stages {

        stage('Detect Changed Service') {
    steps {
        script {
            env.SERVICE = ""

            def changes = sh(
                script: "git diff --name-only HEAD~1 HEAD",
                returnStdout: true
            ).trim()

            if (changes.split("\n").any { it.startsWith("users/") || it == "Jenkinsfile" }) {
                env.SERVICE = "users"
            }

            echo "Changed service: ${env.SERVICE}"
        }
    }
}


        stage('Build Service') {
            when { expression { env.SERVICE?.trim() } }
            steps {
                sh "cd ${env.SERVICE} && mvn clean package -DskipTests"
            }
        }

        stage('Deploy JAR') {
    when { expression { env.SERVICE?.trim() } }
    steps {
        sh """
            mkdir -p ${DEPLOY_DIR}/${env.SERVICE}
            cp ${env.SERVICE}/target/*.jar ${DEPLOY_DIR}/${env.SERVICE}/app.jar

            echo "Stopping old process..."
            pkill -f "${DEPLOY_DIR}/${env.SERVICE}/app.jar" || true

            echo "Starting new process..."
            nohup bash -c "java -jar ${DEPLOY_DIR}/${env.SERVICE}/app.jar > ${DEPLOY_DIR}/${env.SERVICE}/log.txt 2>&1" &
        """
    }
}

    }
}

