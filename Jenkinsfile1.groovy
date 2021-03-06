
// test to see if pipeline passes the $DEMO env variable value to the script ./test.sh
pipeline {
    agent any
    parameters {
        booleanParam(name: 'RC', defaultValue: false, description: 'Is this a Release Candidate?')
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
    environment {
       DEMO='1.3'
       RELEASE='20.04'
       LOG_LEVEL='INFO'
   }

    stages {
        stage('Audit tools') {                        
            steps {
                sh '''
                  pwd
                  git version
                '''
            }
        }
        stage('Example') {
            steps {
                echo "${params.RC} World!"
                echo "${params.Greeting} World!"
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                echo "thisis build number $BUILD_NUMBER"
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