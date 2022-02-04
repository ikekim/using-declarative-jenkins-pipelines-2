
// refers to the shared library in main branch and goes to var folder and executes auditTools() and getVersionSuffix()

library identifier: 'jenkins-pipeline-demo-library-2@main',
        retriever: modernSCM([$class: 'GitSCMSource', remote: 'https://github.com/ikekim/jenkins-pipeline-demo-library-2.git'])

pipeline {
    agent any
    parameters {
        booleanParam(name: 'RC', defaultValue: false, description: 'Is this a Release Candidate?')
    }
    environment {
        TABLE1 = "bq_table1"        
        TABLE2 = "bq_table2"
    }
    stages {
        stage('Audit tools') {                        
            steps {
                auditTools()
            }
        }
        stage('Build') {
            environment {
                TABLES = bqTableNames table1: env.TABLE1, table2: env.TABLE2
            }
            steps {
              echo "migrating tables: ${TABLES}"
            }
        }
    }
}