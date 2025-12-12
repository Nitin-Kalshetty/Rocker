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
                script {
                    // Create a standalone deployment script
                    writeFile file: 'deploy_app.sh', text: """#!/bin/bash
set -e
set -o pipefail

SERVICE="${env.SERVICE}"
DEPLOY_DIR="${DEPLOY_DIR}"
TARGET_DIR="\${DEPLOY_DIR}/\${SERVICE}"

echo "Deploying service: \${SERVICE} to \${TARGET_DIR}"

# Create directories
mkdir -p "\${TARGET_DIR}/logs"
chmod -R 755 "\${TARGET_DIR}"

# Copy JAR
cp "\${SERVICE}/target/"*.jar "\${TARGET_DIR}/app.jar"

# Stop old process
echo "Stopping old process..."
pkill -f "\${TARGET_DIR}/app.jar" || true
sleep 3
# Force kill if still running
pkill -9 -f "\${TARGET_DIR}/app.jar" 2>/dev/null || true

echo "Starting service..."
cd "\${TARGET_DIR}"

# METHOD 1: Use start-stop-daemon if available (recommended)
if command -v start-stop-daemon >/dev/null 2>&1; then
    echo "Using start-stop-daemon..."
    start-stop-daemon \
        --start \
        --background \
        --make-pidfile \
        --pidfile "\${TARGET_DIR}/app.pid" \
        --chdir "\${TARGET_DIR}" \
        --exec /usr/bin/java \
        -- \
        -jar app.jar \
        >> "\${TARGET_DIR}/logs/system.out.log" 2>&1
else
    # METHOD 2: Robust nohup with proper shell
    echo "Using nohup (fallback)..."
    # Create a startup script
    cat > "\${TARGET_DIR}/start.sh" << 'EOF'
#!/bin/bash
cd "\$(dirname "\$0")"
exec java -jar app.jar >> logs/system.out.log 2>&1
EOF
    
    chmod +x "\${TARGET_DIR}/start.sh"
    
    # Run with setsid to detach properly
    setsid nohup "\${TARGET_DIR}/start.sh" </dev/null >/dev/null 2>&1 &
    
    # Alternative: Use a proper daemon script
    # nohup bash -c "cd '\${TARGET_DIR}' && exec java -jar app.jar >> logs/system.out.log 2>&1" </dev/null >/dev/null 2>&1 &
fi

sleep 5

# Verify process is running
echo "Verifying process..."
if ps aux | grep -v grep | grep -q "java.*\${TARGET_DIR}/app.jar"; then
    PID=\$(ps aux | grep -v grep | grep "java.*\${TARGET_DIR}/app.jar" | awk '{print \$2}' | head -1)
    echo "✅ Service started successfully! PID: \${PID}"
    echo "\${PID}" > "\${TARGET_DIR}/app.pid"
    
    # Check if it's actually responding (optional)
    echo "Process info:"
    ps -p "\${PID}" -f 2>/dev/null || true
else
    echo "❌ FAILED to start service!"
    echo "Last 20 lines of log:"
    tail -20 "\${TARGET_DIR}/logs/system.out.log" 2>/dev/null || echo "No log file found"
    exit 1
fi
"""
                    
                    // Execute the deployment script
                    sh """
                        chmod +x deploy_app.sh
                        ./deploy_app.sh
                    """
                }
            }
        }
    }
}
