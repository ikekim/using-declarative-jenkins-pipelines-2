library identifier: 'jenkins-pipeline-demo-library-2@main', 
        retriever: modernSCM([$class: 'GitSCMSource', remote: 'https://github.com/ikekim/jenkins-pipeline-demo-library-2.git'])

pipeline {
    agent any
    stages {
        stage('Audit tools') {                        
            steps {
                auditTools()
            }
        }
        stage('Build') {
            environment {
                VERSION_SUFFIX = getVersionSuffix rcNumber: ${VERSION_RC}, isReleaseCandidate: ${RC}
            }
            steps {
              echo "Building version: ${VERSION} with suffix: ${VERSION_SUFFIX}"
              // sh 'dotnet build -p:VersionPrefix="${VERSION}" --version-suffix "${VERSION_SUFFIX}" ./m3/src/Pi.Web/Pi.Web.csproj'
            }
        }
    }
}