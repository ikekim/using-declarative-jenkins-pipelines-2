// library identifier: 'jenkins-pipeline-demo-library@master',
library identifier: 'jenkins-pipeline-demo-library-2@main',
        // retriever: modernSCM([$class: 'GitSCMSource', remote: 'https://github.com/sixeyed/jenkins-pipeline-demo-library.git'])
        retriever: modernSCM([$class: 'GitSCMSource', remote: 'https://github.com/ikekim/jenkins-pipeline-demo-library-2.git'])

pipeline {
    agent any
    parameters {
        booleanParam(name: 'RC', defaultValue: false, description: 'Is this a Release Candidate?')
    }
    environment {
        VERSION = "0.1.0"        
        VERSION_RC = "rc.2"
    }
    stages {
        stage('Audit tools') {                        
            steps {
                auditTools()
            }
        }
        stage('Build') {
            environment {
                VERSION_SUFFIX = getVersionSuffix rcNumber: env.VERSION_RC, isReleaseCandidate: params.RC
            }
            steps {
              echo "Building version: ${VERSION} with suffix: ${VERSION_SUFFIX}"
              // sh 'dotnet build -p:VersionPrefix="${VERSION}" --version-suffix "${VERSION_SUFFIX}" ./m3/src/Pi.Web/Pi.Web.csproj'
            }
        }
    }
}