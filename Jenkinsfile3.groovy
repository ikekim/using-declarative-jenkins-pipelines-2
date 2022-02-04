
// refers to the shared library in main branch and goes to var folder and executes auditTools()

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
    }
}