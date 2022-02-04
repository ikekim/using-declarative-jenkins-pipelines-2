library identifier: 'jenkins-pipeline-demo-library-2@main', 
        retriever: modernSCM([$class: 'GitSCMSource', remote: 'https://github.com/ikekim/jenkins-pipeline-demo-library-2.git'])

pipeline {
    agent any
    stages {
        stage('Get Project') {                        
            steps {
                readConfigFileProperties.getConfigFileProperties('vars.yml')
            }
        }
    }
}