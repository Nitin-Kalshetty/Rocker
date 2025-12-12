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
                        script: "git diff --name-only HEAD~1 HEAD 2>/dev/null || echo ''",
                        returnStdout: true
                    ).trim()

                    if (changes.split("\n").any { 
                        it.startsWith("users/") || it == "Jenkinsfile" 
                    }) {
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
                script {

                    def service = env.SERVICE
                    def targetDir = "${DEPLOY_DIR}/${service}"

                    sh """
                        echo "Deploying ${service}..."

                        mkdir -p ${targetDir}/logs
                        chmod -R 755 ${targetDir}

                        echo "Copying JAR..."
                        cp ${service}/target/*.jar ${targetDir}/app.jar
                    
                        echo "Stopping old process..."
                        pkill -f "${targetDir}/app.jar" || true
                        sleep 2

                        echo "Creating startup script..."
                        cat > ${targetDir}/start.sh << 'EOF'
#!/bin/bash
cd "$(dirname "$0")"

echo "Starting application..."
nohup java -jar app.jar >> logs/system.out.log 2>&1 &
PID=$!
echo "PID: \$PID"
echo \$PID > app.pid
EOF

                        chmod +x ${targetDir}/start.sh

                        echo "Running startup script..."
                        ${targetDir}/start.sh

                        sleep 2

                        echo "Checking process..."

                        if ps -p \$(cat ${targetDir}/app.pid) > /dev/null 2>&1; then
                            echo "✅ App running with PID \$(cat ${targetDir}/app.pid)"
                        else
                            echo "❌ PID missing, checking by process name..."
                            if ps aux | grep "[j]ava.*${targetDir}/app.jar" > /dev/null; then
                                echo "✅ App IS running (found by name)"
                            else
                                echo "❌ App failed to start!"
                                echo "Last 10 log lines:"
                                tail -10 ${targetDir}/logs/system.out.log || echo "No logs"
                                exit 1
                            fi
                        fi
                    """
                }
            }
        }
    }
}

