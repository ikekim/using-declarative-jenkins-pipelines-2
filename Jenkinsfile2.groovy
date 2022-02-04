// see if the jenkinsfile will pick up the function within the same file i.e."void auditTools()".

pipeline {
    agent any
    parameters {
        booleanParam(name: 'RC', defaultValue: false, description: 'Is this a Release Candidate?')
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
    environment {
        DEMO='1.3'
        VERSION = "0.1.0"        
        VERSION_RC = "rc.2"
        RELEASE='20.04'
        LOG_LEVEL='INFO'
   }

    stages {
        stage('Audit tools') {                        
            steps {
                auditTools()
            }
        }
        stage('Example') {
            steps {
                echo "${params.RC} World!"
                echo "${params.Greeting} World!"
            }
        }
        stage('Build1') {
            steps {
                echo 'Building..'
                echo "thisis build number $BUILD_NUMBER"
            }
        }
        stage('Build2') {
            environment {
                VERSION_SUFFIX = getVersionSuffix()
            }
            steps {
              echo "Building version: ${VERSION} with suffix: ${VERSION_SUFFIX}"
              // sh 'dotnet build -p:VersionPrefix="${VERSION}" --version-suffix "${VERSION_SUFFIX}" ./m3/src/Pi.Web/Pi.Web.csproj'
            }
        }
        stage('stage-1') {
            steps {
                echo "This is build number $BUILD_NUMBER of demo $DEMO"
                sh '''
                echo "Using a multi-line shell step"
                chmod +x test.sh
                ./test.sh
                '''
            }
      }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo "Testing. I can see release ${RELEASE}, but not log level ${LOG_LEVEL}"
            }
        }
    }
}

void auditTools() {
    sh '''
        pwd
        git version
    '''
}

String getVersionSuffix() {
    if (params.RC) {
        return env.VERSION_RC
    } else {
        return env.VERSION_RC + '+ci.' + env.BUILD_NUMBER
    }
}